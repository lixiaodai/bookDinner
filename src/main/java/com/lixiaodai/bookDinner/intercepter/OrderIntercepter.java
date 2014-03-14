package com.lixiaodai.bookDinner.intercepter;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

import com.lixiaodai.bookDinner.entity.extend.jtablePage.JTablePage;
import com.lixiaodai.bookDinner.util.ReflectUtil;

@Intercepts({
	@Signature(method = "prepare",type = StatementHandler.class,args = {Connection.class})
})
public class OrderIntercepter implements Interceptor{
	
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
			if("".equals(jTablePage.getJtSorting())||jTablePage.getJtSorting()==null){
				return invocation.proceed();
			}
			String sql = boundSql.getSql();
			String orderSQL = getOrderSQL(jTablePage,sql);
			ReflectUtil.setFieldValue(boundSql, "sql", orderSQL);
		}
		return invocation.proceed();
	}

	private String getOrderSQL(JTablePage<?> jTablePage, String sql) {
		StringBuffer buffer = new StringBuffer(sql);
		buffer.append(" order by ").append(jTablePage.getJtSorting());
		return buffer.toString();
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
	}
}
