package com.lixiaodai.bookDinner.entity.extend.jtablePage;

public class JTablePage<T> {
	
	public final static String OK = "OK";
	public final static String NO = "NO";
	
	private Integer jtStartIndex;
	private Integer jtPageSize;
	private String jtSorting;
	private JTablePageResult<T> jTablePageResult;
	
	public JTablePage(){
		this.jTablePageResult = new JTablePageResult<T>();
	}
	
	public Integer getJtStartIndex() {
		return jtStartIndex;
	}
	public void setJtStartIndex(Integer jtStartIndex) {
		this.jtStartIndex = jtStartIndex;
	}
	public Integer getJtPageSize() {
		return jtPageSize;
	}
	public void setJtPageSize(Integer jtPageSize) {
		this.jtPageSize = jtPageSize;
	}
	public String getJtSorting() {
		return jtSorting;
	}
	public void setJtSorting(String jtSorting) {
		this.jtSorting = jtSorting;
	}
	public JTablePageResult<T> getjTablePageResult() {
		return jTablePageResult;
	}
	public void setjTablePageResult(JTablePageResult<T> jTablePageResult) {
		this.jTablePageResult = jTablePageResult;
	}
	
	
}
