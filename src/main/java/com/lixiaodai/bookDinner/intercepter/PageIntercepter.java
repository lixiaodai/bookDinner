package com.lixiaodai.bookDinner.intercepter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;

import com.lixiaodai.bookDinner.entity.extend.jtablePage.JTablePage;
import com.lixiaodai.bookDinner.util.ReflectUtil;

@Intercepts({
	@Signature(method = "prepare",type = StatementHandler.class,args = {Connection.class})
})
public class PageIntercepter implements Interceptor{
	
	private String databaseType;

	public Object intercept(Invocation invocation) throws Throwable {
		RoutingStatementHandler handler = null;
		StatementHandler delegate = null;
		try {
			handler = (RoutingStatementHandler)invocation.getTarget();
			delegate =  (StatementHandler)ReflectUtil.getFieldValue(handler, "delegate");
		} catch (Exception e) {
			delegate = (StatementHandler)invocation.getTarget();
			handler = (RoutingStatementHandler)ReflectUtil.getFieldValue(ReflectUtil.getFieldValue(delegate, "h"), "target");			
			delegate =  (StatementHandler)ReflectUtil.getFieldValue(handler, "delegate");
		}
		BoundSql boundSql = delegate.getBoundSql();
		Object object = boundSql.getParameterObject();
		if(object instanceof JTablePage<?>){
			JTablePage<?> jTablePage = (JTablePage<?>)object;
			MappedStatement mappedStatement = (MappedStatement)ReflectUtil.getFieldValue(delegate, "mappedStatement");
			Connection connection = (Connection)invocation.getArgs()[0];
			String sql = boundSql.getSql();
			this.setTotalRecord(jTablePage,mappedStatement,connection);
			String pageSQL = getPageSQL(jTablePage,sql);
			ReflectUtil.setFieldValue(boundSql, "sql", pageSQL);
		}
		return invocation.proceed();
	}

	private String getPageSQL(JTablePage<?> jTablePage, String sql) {
		StringBuffer buffer = new StringBuffer(sql);
		if("mysql".equalsIgnoreCase(databaseType)){
			return getMySQLPageSQL(jTablePage,buffer);
		}else if("oracle".equalsIgnoreCase(databaseType)){
			return getOraclePageSQL(jTablePage,buffer);
		}else{
			System.out.println("Unknow DataBaseType");
			return null;
		}
	}

	private String getOraclePageSQL(JTablePage<?> jTablePage,
			StringBuffer buffer) {
		buffer.insert(0, "select u.*,rownum r from (").append(") u where rownum <").append(jTablePage.getJtStartIndex()+jTablePage.getJtPageSize());
		buffer.insert(0, "select * from (").append(") where r>=").append(jTablePage.getJtStartIndex());
		return buffer.toString();
	}

	private String getMySQLPageSQL(JTablePage<?> jTablePage, StringBuffer sql) {
		return sql.append(" limit ").append(jTablePage.getJtStartIndex()).append(",").append(jTablePage.getJtPageSize()).toString();
	}

	private void setTotalRecord(JTablePage<?> jTablePage,
			MappedStatement mappedStatement, Connection connection) {
		BoundSql boundSql = mappedStatement.getBoundSql(jTablePage);
		String sql = boundSql.getSql();
		String countSQL=getCountSQL(sql);
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		BoundSql countBoundSql = new BoundSql(mappedStatement.getConfiguration(), countSQL, parameterMappings, jTablePage);
		ParameterHandler handler = new DefaultParameterHandler(mappedStatement, jTablePage, countBoundSql);
		PreparedStatement preparedStatement =null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(countSQL);
			handler.setParameters(preparedStatement);
			resultSet= preparedStatement.executeQuery();
			if(resultSet.next()){
				jTablePage.getjTablePageResult().setTotalRecordCount(resultSet.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
	           try {  
	               if (resultSet != null)  
	                   resultSet.close();  
	                if (preparedStatement != null)  
	                   preparedStatement.close();  
	            } catch (SQLException e) {  
	               e.printStackTrace();  
	            }  
		}
	}

	private String getCountSQL(String sql) {
	       int index = sql.indexOf("from");  
	       return "select count(*) " + sql.substring(index);  
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
		this.databaseType = properties.getProperty("databaseType"); 		
	}

	public String getDatabaseType() {
		return databaseType;
	}

	public void setDatabaseType(String databaseType) {
		this.databaseType = databaseType;
	}
	
	
}
