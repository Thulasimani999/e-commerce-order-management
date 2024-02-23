package com.example.ecommerce.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.ordermanagement.entity.OrderEntity;
import com.ordermanagement.repository.OrderRepository;
import com.ordermanagement.service.OrderService;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

	@Mock
	private OrderRepository orderRepository;
	@InjectMocks
	private OrderService orderService;

	@Test
	public void testnewOrder() {
		OrderEntity order1 = new OrderEntity();
		order1.setCustomerName("John ");
		order1.setTotalAmount(BigDecimal.valueOf(100));
		order1.setOrderStatus("PENDING");
		order1.setOrderDate(LocalDate.now());
		when(orderRepository.save(any(OrderEntity.class))).thenReturn(order1);

		OrderEntity savedOrder = orderService.newOrder(order1);

		assertNotNull(savedOrder);
		assertEquals("John ", savedOrder.getCustomerName());
		assertEquals(BigDecimal.valueOf(100), savedOrder.getTotalAmount());
		assertEquals("PENDING", savedOrder.getOrderStatus());
		assertEquals("2024-02-23", savedOrder.getOrderDate().toString());

	}

	@Test
	public void testListOfOrders() {
		List<OrderEntity> orderentity = null;
		Mockito.when(orderRepository.findAll()).thenReturn(orderentity);
		assertThat(orderService.ListOfOrders()).isEqualTo(orderentity);
	}

	@Test
	public void testGetOrderById() {
		OrderEntity order = new OrderEntity();
		order.setOrderID(1);
		order.setCustomerName("John ");
		order.setTotalAmount(BigDecimal.valueOf(100));
		order.setOrderStatus("SHIPPED");
		order.setOrderDate(LocalDate.now());
		when(orderRepository.findById(1)).thenReturn(Optional.of(order));
		Optional<OrderEntity> retrievedOrder = orderService.singleOrder(1);
		assertTrue(retrievedOrder.isPresent());
		assertEquals("John ", retrievedOrder.get().getCustomerName());
		assertEquals("SHIPPED", retrievedOrder.get().getOrderStatus());
		assertEquals("2024-02-23", retrievedOrder.get().getOrderDate().toString());
	}

	@Test
	public void testUpdateOrder_CustomerName() {
		OrderEntity existingOrder = new OrderEntity();
		existingOrder.setOrderID(1);
		existingOrder.setCustomerName("John");
		existingOrder.setTotalAmount(BigDecimal.valueOf(100));
		existingOrder.setOrderDate(LocalDate.now());
		OrderEntity updatedOrder = new OrderEntity();
		updatedOrder.setCustomerName("Raju");
		when(orderRepository.findById(1)).thenReturn(Optional.of(existingOrder));
		when(orderRepository.saveAndFlush(any(OrderEntity.class))).thenReturn(existingOrder);
		Optional<OrderEntity> result = orderService.updatedOrder(updatedOrder, 1);
		assertTrue(result.isPresent());
		assertEquals("Raju", result.get().getCustomerName());
	}

	@Test
	public void testUpdateOrder_OrderStatus() {
		OrderEntity existingOrder = new OrderEntity();
		existingOrder.setOrderID(1);
		existingOrder.setCustomerName("John");
		existingOrder.setTotalAmount(BigDecimal.valueOf(100));
		existingOrder.setOrderStatus("PENDING");
		existingOrder.setOrderDate(LocalDate.now());
		OrderEntity updatedOrder = new OrderEntity();
		updatedOrder.setOrderStatus("CONFIRMED");
		when(orderRepository.findById(1)).thenReturn(Optional.of(existingOrder));
		when(orderRepository.saveAndFlush(any(OrderEntity.class))).thenReturn(existingOrder);
		Optional<OrderEntity> result = orderService.updatedOrder(updatedOrder, 1);
		assertTrue(result.isPresent());
		assertEquals("CONFIRMED", result.get().getOrderStatus());

	}

	@Test
	public void testUpdateOrder_TotalAmount() {
		OrderEntity existingOrder = new OrderEntity();
		existingOrder.setOrderID(1);
		existingOrder.setCustomerName("John ");
		existingOrder.setTotalAmount(BigDecimal.valueOf(100));
		existingOrder.setOrderDate(LocalDate.now());
		OrderEntity updatedOrder = new OrderEntity();
		updatedOrder.setTotalAmount(BigDecimal.valueOf(200));
		when(orderRepository.findById(1)).thenReturn(Optional.of(existingOrder));
		when(orderRepository.saveAndFlush(any(OrderEntity.class))).thenReturn(existingOrder);
		Optional<OrderEntity> result = orderService.updatedOrder(updatedOrder, 1);
		assertTrue(result.isPresent());
		assertEquals(BigDecimal.valueOf(200), result.get().getTotalAmount());

	}

}