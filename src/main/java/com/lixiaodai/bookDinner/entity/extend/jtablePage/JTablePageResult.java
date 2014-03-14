package com.lixiaodai.bookDinner.entity.extend.jtablePage;

import java.util.List;

public class JTablePageResult<T> {

	private String result;
	private List<T> records;
	private Integer totalRecordCount;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Integer getTotalRecordCount() {
		return totalRecordCount;
	}
	public void setTotalRecordCount(Integer totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}
	public List<T> getRecords() {
		return records;
	}
	public void setRecords(List<T> records) {
		this.records = records;
	}
	
}
