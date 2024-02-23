package com.ordermanagement.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;




@Entity

public class OrderEntity {
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderID;

	private String customerName;

	private LocalDate orderDate;

	private BigDecimal totalAmount;

	private String orderStatus;

	public OrderEntity(Integer orderID, String customerName, LocalDate orderDate, BigDecimal totalAmount,
			String orderStatus) {
		this.orderID = orderID;
		this.customerName = customerName;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		this.orderStatus = orderStatus;
	}

	public OrderEntity(String customerName, BigDecimal totalAmount, String orderStatus, LocalDate now) {
		this.customerName = customerName;
		this.orderDate = now;
		this.totalAmount = totalAmount;
		this.orderStatus = orderStatus;
	}

	public Integer getOrderID() {
		return orderID;
	}

	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public OrderEntity() {

	}

}
