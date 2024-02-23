package com.ordermanagement.service;

import com.ordermanagement.entity.OrderEntity;
import com.ordermanagement.exception.OrderNotFoundException;
import com.ordermanagement.repository.OrderRepository;
import com.ordermanagement.validation.OrderValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    OrderValidation validation = new OrderValidation();

    public OrderEntity newOrder(OrderEntity orderEntity) {
        validation.validateOrder(orderEntity);
        return orderRepository.save(orderEntity);
    }


    public List<OrderEntity> ListOfOrders() {
        return orderRepository.findAll();

    }

    public Optional<OrderEntity> singleOrder(Integer orderId) {
        return orderRepository.findById(orderId);
    }


 

    public Optional<OrderEntity> updatedOrder(OrderEntity order, Integer orderId) throws OrderNotFoundException {
    	Optional<OrderEntity> existingOrders = orderRepository.findById(orderId);
    	if (existingOrders.isPresent()) {
    	OrderEntity existingOrder = existingOrders.get();
    	if (order.getCustomerName() != null) {
    	existingOrder.setCustomerName(order.getCustomerName());
    	}
    	if (order.getOrderStatus() != null) {
    	existingOrder.setOrderStatus(order.getOrderStatus());
    	}
    	if (order.getTotalAmount() != null) {
    	existingOrder.setTotalAmount(order.getTotalAmount());
    	}
    	orderRepository.saveAndFlush(existingOrder);
    	return Optional.of(existingOrder);
    	} else {
    	throw new OrderNotFoundException("Order not found with ID: " + orderId);
    	}
    	}

}


