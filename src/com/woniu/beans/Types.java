package com.woniu.beans;

public class Types {

	private int types_id;
	private String types_code;
	private String types_name;
	public Types() {
		// TODO Auto-generated constructor stub
	}
	public int getTypes_id() {
		return types_id;
	}
	public void setTypes_id(int types_id) {
		this.types_id = types_id;
	}
	public String getTypes_code() {
		return types_code;
	}
	public void setTypes_code(String types_code) {
		this.types_code = types_code;
	}
	public String getTypes_name() {
		return types_name;
	}
	public void setTypes_name(String types_name) {
		this.types_name = types_name;
	}
	public Types(int types_id, String types_code, String types_name) {
		super();
		this.types_id = types_id;
		this.types_code = types_code;
		this.types_name = types_name;
	}
	@Override
	public String toString() {
		return "Types [types_id=" + types_id + ", types_code=" + types_code + ", types_name=" + types_name + "]";
	}
	

}
