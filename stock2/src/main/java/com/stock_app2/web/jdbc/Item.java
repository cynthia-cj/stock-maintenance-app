package com.stock_app2.web.jdbc;

public class Item {
	private int item_id;
	private String item_name;
	private String brand;
	private int count;
	
	public Item(int item_id, String item_name, String brand, int count) {
		this.item_id = item_id;
		this.item_name = item_name;
		this.brand = brand;
		this.count = count;
	}

	public Item(String item_name, String brand, int count) {
		this.item_name = item_name;
		this.brand = brand;
		this.count = count;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Item [item_id=" + item_id + ", item_name=" + item_name + ", brand=" + brand + ", count=" + count + "]";
	}
	
}
