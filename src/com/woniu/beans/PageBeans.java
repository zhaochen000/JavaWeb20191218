package com.woniu.beans;

import java.util.List;
/*
 * ��ҳ
 */
public class PageBeans<T> {
	private  int  pages;//һ���ж���ҳ
	private  int pageSize;//һҳ���ж���������
	private int totalCount;//һ���ж���������
	private  int currentPage;//��ǰҳ
	private  List<T> data;//ÿҳ��ʾ������
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
