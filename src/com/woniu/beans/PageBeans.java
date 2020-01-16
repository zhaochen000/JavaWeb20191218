package com.woniu.beans;

import java.util.List;
/*
 * 分页
 */
public class PageBeans<T> {
	private  int  pages;//一共有多少页
	private  int pageSize;//一页中有多少条数据
	private int totalCount;//一共有多少条数据
	private  int currentPage;//当前页
	private  List<T> data;//每页显示的数据
	public int getPages() {
		this.pages=totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}

}
