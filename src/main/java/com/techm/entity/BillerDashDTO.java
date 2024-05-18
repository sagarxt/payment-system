package com.techm.entity;

public class BillerDashDTO {

	private int totalProducts;
	private int totalOrders;
	private int pendingOrders;
	private int customers;
	
	public BillerDashDTO() {
	}

	public int getTotalProducts() {
		return totalProducts;
	}

	public void setTotalProducts(int totalProducts) {
		this.totalProducts = totalProducts;
	}

	public int getTotalOrders() {
		return totalOrders;
	}

	public void setTotalOrders(int totalOrders) {
		this.totalOrders = totalOrders;
	}

	public int getPendingOrders() {
		return pendingOrders;
	}

	public void setPendingOrders(int pendingOrders) {
		this.pendingOrders = pendingOrders;
	}

	public int getCustomers() {
		return customers;
	}

	public void setCustomers(int customers) {
		this.customers = customers;
	}
	
	
}
