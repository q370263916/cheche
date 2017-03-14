package com.chechemotel.logs.common;

import java.util.Collection;

/**
 * 
 * @author ljw
 * 
 * @param 数据记录
 */
public class MongoPageBean<T> {

	/**
	 * 返回的数据集
	 */
	private Collection<T> result;

	/**
	 * 起始页
	 */
	private int startPage;

	

	/**
	 * 每页显示多少条记录
	 */
	private int pageSize;

	/**
	 * 一共多少条记录
	 */
	private int totals;

	public Collection<T> getResult() {
		return result;
	}

	public void setResult(Collection<T> result) {
		this.result = result;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotals() {
		return totals;
	}

	public void setTotals(int totals) {
		this.totals = totals;
	}
	/**
	 * 总页码数
	 */
	public int getTotalPage() {
		if (totals == 0 || pageSize == 0) {
			return 0;
		}
		if (totals % pageSize == 0) {
			return totals / pageSize;
		} else {
			return totals / pageSize + 1;
		}
	}

}
