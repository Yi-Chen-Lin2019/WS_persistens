package Models;

import java.time.*;

public abstract class GenericOrder {
	private int orderID;
	private LocalDateTime orderDate;
	private LocalDateTime deliveryDate;
	private String deliveryAddress;
	private boolean isDelivered;
	private double amount;
	private EmployeeRole employee;
	private String orderType;
	
	public GenericOrder(LocalDateTime deliveryDate, LocalDateTime orderDate, String deliveryAddress, boolean isDelivered, double amount, EmployeeRole employee, String orderType)
	{
		this.orderDate = orderDate;
		this.deliveryDate = deliveryDate;
		this.deliveryAddress = deliveryAddress;
		this.isDelivered = isDelivered;
		this.amount = amount;
		this.setEmployee(employee);
		this.orderType = orderType;
		
	}
	
	public String getOrderType() {
		return orderType;
	}
	
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public LocalDateTime getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}
	public LocalDateTime getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(LocalDateTime deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public boolean isDelivered() {
		return isDelivered;
	}
	public void setDelivered(boolean isDelivered) {
		this.isDelivered = isDelivered;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public EmployeeRole getEmployee() {
		return employee;
	}
	public void setEmployee(EmployeeRole employee) {
		this.employee = employee;
	}
	
	
}
