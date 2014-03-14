package com.lixiaodai.bookDinner.entity.extend.jtablePage;

public class JTableItemResult<T> {

	private String result;
	private T record;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public T getRecord() {
		return record;
	}
	public void setRecord(T record) {
		this.record = record;
	}
	
	
}
