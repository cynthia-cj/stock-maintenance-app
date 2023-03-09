package com.stock_app2.web.jdbc;

public class Invoice {
	private int invoiceId;
	private String invoice_date;
	private String itemName;
	private int quantity;
	public Invoice(int invoiceId, String invoice_date, String itemName, int quantity) {
		
		this.invoiceId = invoiceId;
		this.invoice_date = invoice_date;
		this.itemName = itemName;
		this.quantity = quantity;
	}
	public Invoice(String invoice_date, String itemName, int quantity) {

		this.invoice_date = invoice_date;
		this.itemName = itemName;
		this.quantity = quantity;
	}
	public int getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}
	public String getInvoice_date() {
		return invoice_date;
	}
	public void setInvoice_date(String invoice_date) {
		this.invoice_date = invoice_date;
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
	
	
}
