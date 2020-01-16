package com.woniu.beans;

public class Suppliers {
	
	private int suppliers_id;
	private String suppliers_code;
	private String suppliers_name;
	private String suppliers_phone;
	public Suppliers() {
		// TODO Auto-generated constructor stub
	}
	public Suppliers(int suppliers_id, String suppliers_code, String suppliers_name, String suppliers_phone) {
		super();
		this.suppliers_id = suppliers_id;
		this.suppliers_code = suppliers_code;
		this.suppliers_name = suppliers_name;
		this.suppliers_phone = suppliers_phone;
	}
	public int getSuppliers_id() {
		return suppliers_id;
	}
	public void setSuppliers_id(int suppliers_id) {
		this.suppliers_id = suppliers_id;
	}
	public String getSuppliers_code() {
		return suppliers_code;
	}
	public void setSuppliers_code(String suppliers_code) {
		this.suppliers_code = suppliers_code;
	}
	public String getSuppliers_name() {
		return suppliers_name;
	}
	public void setSuppliers_name(String suppliers_name) {
		this.suppliers_name = suppliers_name;
	}
	public String getSuppliers_phone() {
		return suppliers_phone;
	}
	public void setSuppliers_phone(String suppliers_phone) {
		this.suppliers_phone = suppliers_phone;
	}
	@Override
	public String toString() {
		return "Suppliers [suppiers_id=" + suppliers_id + ", suppliers_code=" + suppliers_code + ", suppliers_name="
				+ suppliers_name + ", suppliers_phone=" + suppliers_phone + "]";
	}
	
	

}
