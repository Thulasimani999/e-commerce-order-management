package com.ordermanagement.validation;

import com.ordermanagement.entity.OrderEntity;
import com.ordermanagement.entity.OrderStatus;
import com.ordermanagement.exception.InvalidOrderException;


import java.math.BigDecimal;

import org.apache.commons.lang3.EnumUtils;



public class OrderValidation {

	public void validateOrder(OrderEntity orderEntity) {
		if (orderEntity.getCustomerName() == null || orderEntity.getCustomerName().isEmpty()) {
			throw new InvalidOrderException("Customer name is required.");
		}
		else if (orderEntity.getOrderStatus() == null
				|| !EnumUtils.isValidEnum(OrderStatus.class, orderEntity.getOrderStatus())) {
			throw new InvalidOrderException("Order status is Invalid.");
		}
	
		else if (orderEntity.getTotalAmount() == null
				|| orderEntity.getTotalAmount().compareTo(BigDecimal.ZERO) <= 0) {
			throw new InvalidOrderException("Total amount must be a positive value.");
		}
	}

}
