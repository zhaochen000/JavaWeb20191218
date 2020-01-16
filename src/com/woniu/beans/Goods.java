package com.woniu.beans;

import java.util.Date;

public class Goods {

	private int goods_id;
	private String goods_code;
	private String goods_name;
	private int types_id;
	private int suppliers_id;
	private Date goods_date;
	private float goods_price;
	private int goods_count;
	private String goods_level;
	private String goods_spec;
	private String goods_remarks;
	
	private Types types;
	private Suppliers  suppliers;
	
	public Types getTypes() {
		return types;
	}

	public void setTypes(Types types) {
		this.types = types;
	}

	public Suppliers getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(Suppliers suppliers) {
		this.suppliers = suppliers;
	}

	public Goods() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Goods(int goods_id, String goods_code, String goods_name, int types_id, int suppliers_id, Date goods_date,
			float goods_price, int goods_count, String goods_level, String goods_spec, String goods_remarks,
			Types types, Suppliers suppliers) {
		super();
		this.goods_id = goods_id;
		this.goods_code = goods_code;
		this.goods_name = goods_name;
		this.types_id = types_id;
		this.suppliers_id = suppliers_id;
		this.goods_date = goods_date;
		this.goods_price = goods_price;
		this.goods_count = goods_count;
		this.goods_level = goods_level;
		this.goods_spec = goods_spec;
		this.goods_remarks = goods_remarks;
		this.types = types;
		this.suppliers = suppliers;
	}

	public Goods(int goods_id, String goods_code, String goods_name, int types_id, int suppliers_id, float goods_price,
			int goods_count, String goods_level, String goods_spec, String goods_remarks) {
		super();
		this.goods_id = goods_id;
		this.goods_code = goods_code;
		this.goods_name = goods_name;
		this.types_id = types_id;
		this.suppliers_id = suppliers_id;
		this.goods_price = goods_price;
		this.goods_count = goods_count;
		this.goods_level = goods_level;
		this.goods_spec = goods_spec;
		this.goods_remarks = goods_remarks;
	}

	public Goods(String goods_code, String goods_name, int types_id, int suppliers_id, float goods_price,
			int goods_count, String goods_level, String goods_spec, String goods_remarks) {
		super();
		this.goods_code = goods_code;
		this.goods_name = goods_name;
		this.types_id = types_id;
		this.suppliers_id = suppliers_id;
		this.goods_price = goods_price;
		this.goods_count = goods_count;
		this.goods_level = goods_level;
		this.goods_spec = goods_spec;
		this.goods_remarks = goods_remarks;
	}

	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	public String getGoods_code() {
		return goods_code;
	}
	public void setGoods_code(String goods_code) {
		this.goods_code = goods_code;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public int getTypes_id() {
		return types_id;
	}
	public void setTypes_id(int types_id) {
		this.types_id = types_id;
	}
	public int getSuppliers_id() {
		return suppliers_id;
	}
	public void setSuppliers_id(int suppliers_id) {
		this.suppliers_id = suppliers_id;
	}
	public Date getGoods_date() {
		return goods_date;
	}
	public void setGoods_date(Date goods_date) {
		this.goods_date = goods_date;
	}
	public float getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(float goods_price) {
		this.goods_price = goods_price;
	}
	public int getGoods_count() {
		return goods_count;
	}
	public void setGoods_count(int goods_count) {
		this.goods_count = goods_count;
	}
	public String getGoods_level() {
		return goods_level;
	}
	public void setGoods_level(String goods_level) {
		this.goods_level = goods_level;
	}
	public String getGoods_spec() {
		return goods_spec;
	}
	public void setGoods_spec(String goods_spec) {
		this.goods_spec = goods_spec;
	}
	public String getGoods_remarks() {
		return goods_remarks;
	}
	public void setGoods_remarks(String goods_remarks) {
		this.goods_remarks = goods_remarks;
	}
	public Goods(int goods_id, String goods_code, String goods_name, int types_id, int suppliers_id, Date goods_date,
			float goods_price, int goods_count, String goods_level, String goods_spec, String goods_remarks) {
		super();
		this.goods_id = goods_id;
		this.goods_code = goods_code;
		this.goods_name = goods_name;
		this.types_id = types_id;
		this.suppliers_id = suppliers_id;
		this.goods_date = goods_date;
		this.goods_price = goods_price;
		this.goods_count = goods_count;
		this.goods_level = goods_level;
		this.goods_spec = goods_spec;
		this.goods_remarks = goods_remarks;
	}
	@Override
	public String toString() {
		return "Goods [goods_id=" + goods_id + ", goods_code=" + goods_code + ", goods_name=" + goods_name
				+ ", types_id=" + types_id + ", suppliers_id=" + suppliers_id + ", goods_date=" + goods_date
				+ ", goods_price=" + goods_price + ", goods_count=" + goods_count + ", goods_level=" + goods_level
				+ ", goods_spec=" + goods_spec + ", goods_remarks=" + goods_remarks + "]";
	}
	
	
}
