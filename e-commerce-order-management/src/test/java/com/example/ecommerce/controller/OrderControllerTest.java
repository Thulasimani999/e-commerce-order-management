package com.example.ecommerce.controller;

import com.ordermanagement.controller.OrderController;
import com.ordermanagement.entity.OrderEntity;
import com.ordermanagement.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {
	@Mock
	private OrderService orderService;
	@InjectMocks
	private OrderController orderController;

	@Test
	public void testnewOrder() {
		OrderEntity order = new OrderEntity();
		order.setCustomerName("John");
		order.setTotalAmount(BigDecimal.valueOf(100));
		order.setOrderStatus("PENDING");
		order.setOrderDate(LocalDate.now());
		when(orderService.newOrder(any(OrderEntity.class))).thenReturn(order);
		ResponseEntity<OrderEntity> response = orderController.newOrder(order);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals("John", response.getBody().getCustomerName());
		assertEquals(BigDecimal.valueOf(100), response.getBody().getTotalAmount());
		assertEquals("PENDING", response.getBody().getOrderStatus());
		assertEquals("2024-02-23", response.getBody().getOrderDate().toString());
	}

	@Test
	public void testGetAllOrders() {
		OrderEntity order1 = new OrderEntity();
		order1.setOrderID(1);
		order1.setCustomerName("John");
		order1.setTotalAmount(BigDecimal.valueOf(100));
		order1.setOrderStatus("PENDING");
		order1.setOrderDate(LocalDate.now());
		OrderEntity order2 = new OrderEntity();
		order2.setOrderID(2);
		order2.setCustomerName("Raj");
		order2.setTotalAmount(BigDecimal.valueOf(200));
		order2.setOrderStatus("PENDING");
		order2.setOrderDate(LocalDate.now());
		List<OrderEntity> orders = Arrays.asList(order1, order2);
		when(orderService.ListOfOrders()).thenReturn(orders);
		ResponseEntity<List<OrderEntity>> response = orderController.ListofOrders();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(2, response.getBody().size());
		assertEquals("John", response.getBody().get(0).getCustomerName());
		assertEquals("Raj", response.getBody().get(1).getCustomerName());
		assertEquals(BigDecimal.valueOf(100), response.getBody().get(0).getTotalAmount());
		assertEquals("2024-02-23", response.getBody().get(1).getOrderDate().toString());
	}

	@Test
	public void testGetOrderById() {
		OrderEntity order = new OrderEntity();
		order.setOrderID(1);
		order.setCustomerName("John");
		order.setTotalAmount(BigDecimal.valueOf(100));
		order.setOrderDate(LocalDate.now());
		when(orderService.singleOrder(1)).thenReturn(Optional.of(order));
		ResponseEntity<Optional<OrderEntity>> response = orderController.OrderById(1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals("John", response.getBody().get().getCustomerName());
		assertEquals(BigDecimal.valueOf(100), response.getBody().get().getTotalAmount());
		assertEquals("2024-02-23", response.getBody().get().getOrderDate().toString());
	}

	@Test
	public void testUpdateOrder() {
		// Setup
		OrderEntity order = new OrderEntity();
		order.setOrderID(1);
		order.setCustomerName("John");
		order.setTotalAmount(BigDecimal.valueOf(100));

		when(orderService.updatedOrder(any(OrderEntity.class), eq(1))).thenReturn(Optional.of(order));

		ResponseEntity<String> response = orderController.updatedById(1, order);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("updated Successfully", response.getBody());
	}
}