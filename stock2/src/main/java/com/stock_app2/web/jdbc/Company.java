package com.stock_app2.web.jdbc;

public class Company {
	private int comp_id;
	private String comp_name;
	private String comp_username;
	private String password;
	
	public Company(int comp_id, String comp_name, String comp_username, String password) {

		this.comp_id = comp_id;
		this.comp_name = comp_name;
		this.comp_username = comp_username;
		this.password = password;
	}

	public Company(String comp_name, String comp_username, String password) {
		
		this.comp_name = comp_name;
		this.comp_username = comp_username;
		this.password = password;
	}

	public int getComp_id() {
		return comp_id;
	}

	public void setComp_id(int comp_id) {
		this.comp_id = comp_id;
	}

	public String getComp_name() {
		return comp_name;
	}

	public void setComp_name(String comp_name) {
		this.comp_name = comp_name;
	}

	public String getComp_username() {
		return comp_username;
	}

	public void setComp_username(String comp_username) {
		this.comp_username = comp_username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Company [comp_id=" + comp_id + ", comp_name=" + comp_name + ", comp_username=" + comp_username
				+ ", password=" + password + "]";
	}
	
	

}
