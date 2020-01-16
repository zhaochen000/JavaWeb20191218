package com.woniu.beans;

import java.util.Date;

public class OutStack {

	private int outStack_id;
	private int goods_id;
	private int user_id;
	private String outStack_code;
	private int outStack_count;
	private Date outStack_time;
	private String outStack_status;
	private String outStack_remarks;
	private Goods goods;
	public OutStack() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public OutStack(int outStack_id, int goods_id, int user_id, String outStack_code, int outStack_count,
			Date outStack_time, String outStack_status, String outStack_remarks, Goods goods) {
		super();
		this.outStack_id = outStack_id;
		this.goods_id = goods_id;
		this.user_id = user_id;
		this.outStack_code = outStack_code;
		this.outStack_count = outStack_count;
		this.outStack_time = outStack_time;
		this.outStack_status = outStack_status;
		this.outStack_remarks = outStack_remarks;
		this.goods = goods;
	}


	public OutStack(int outStack_id, int goods_id, int outStack_count) {
		super();
		this.outStack_id = outStack_id;
		this.goods_id = goods_id;
		this.outStack_count = outStack_count;
	}


	public OutStack(int outStack_id, int goods_id, int user_id, String outStack_code, int outStack_count,
			Date outStack_time, String outStack_status, String outStack_remarks) {
		super();
		this.outStack_id = outStack_id;
		this.goods_id = goods_id;
		this.user_id = user_id;
		this.outStack_code = outStack_code;
		this.outStack_count = outStack_count;
		this.outStack_time = outStack_time;
		this.outStack_status = outStack_status;
		this.outStack_remarks = outStack_remarks;
	}
	
	public OutStack(int outStack_id, int goods_id, int user_id, String outStack_code, int outStack_count,
			String outStack_remarks) {
		super();
		this.outStack_id = outStack_id;
		this.goods_id = goods_id;
		this.user_id = user_id;
		this.outStack_code = outStack_code;
		this.outStack_count = outStack_count;
		this.outStack_remarks = outStack_remarks;
	}
	public OutStack(int goods_id, int user_id, String outStack_code, int outStack_count, String outStack_remarks) {
		super();
		this.goods_id = goods_id;
		this.user_id = user_id;
		this.outStack_code = outStack_code;
		this.outStack_count = outStack_count;
		this.outStack_remarks = outStack_remarks;
	}


	public int getOutStack_id() {
		return outStack_id;
	}
	public void setOutStack_id(int outStack_id) {
		this.outStack_id = outStack_id;
	}
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getOutStack_code() {
		return outStack_code;
	}
	public void setOutStack_code(String outStack_code) {
		this.outStack_code = outStack_code;
	}
	public int getOutStack_count() {
		return outStack_count;
	}
	public void setOutStack_count(int outStack_count) {
		this.outStack_count = outStack_count;
	}
	public Date getOutStack_time() {
		return outStack_time;
	}
	public void setOutStack_time(Date outStack_time) {
		this.outStack_time = outStack_time;
	}
	public String getOutStack_status() {
		return outStack_status;
	}
	public void setOutStack_status(String outStack_status) {
		this.outStack_status = outStack_status;
	}
	public String getOutStack_remarks() {
		return outStack_remarks;
	}
	public void setOutStack_remarks(String outStack_remarks) {
		this.outStack_remarks = outStack_remarks;
	}
	
	public Goods getGoods() {
		return goods;
	}


	public void setGoods(Goods goods) {
		this.goods = goods;
	}


	@Override
	public String toString() {
		return "OutStack [outStack_id=" + outStack_id + ", goods_id=" + goods_id + ", user_id=" + user_id
				+ ", outStack_code=" + outStack_code + ", outStack_count=" + outStack_count + ", outStack_time="
				+ outStack_time + ", outStack_status=" + outStack_status + ", outStack_remarks=" + outStack_remarks
				+ "]";
	}
	
}
