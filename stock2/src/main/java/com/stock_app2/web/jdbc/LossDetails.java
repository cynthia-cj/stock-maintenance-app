package com.stock_app2.web.jdbc;

public class LossDetails {
	private int id;
	private String date;
	private String whName;
	private String itemName;
	private int quantity;
	private String reason;
	
	
	
	public LossDetails(int id, String date, String whName, String itemName, int quantity, String reason) {
		
		this.id = id;
		this.date = date;
		this.whName = whName;
		this.itemName = itemName;
		this.quantity = quantity;
		this.reason = reason;
	}

	public LossDetails(String date, String whName, String itemName, int quantity, String reason) {
		
		this.date = date;
		this.whName = whName;
		this.itemName = itemName;
		this.quantity = quantity;
		this.reason = reason;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getWhName() {
		return whName;
	}

	public void setWhName(String whName) {
		this.whName = whName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "LossDetails [id=" + id + ", date=" + date + ", whName=" + whName + ", itemName=" + itemName
				+ ", quantity=" + quantity + ", reason=" + reason + "]";
	}
	
	
	
}
