package com.ordermanagement.controller;

import com.ordermanagement.entity.OrderEntity;
import com.ordermanagement.exception.InvalidOrderException;
import com.ordermanagement.exception.OrderNotFoundException;
import com.ordermanagement.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/new_Order")
    public ResponseEntity<OrderEntity> newOrder(@RequestBody OrderEntity orderEntity) {
        return new ResponseEntity<>(orderService.newOrder(orderEntity), HttpStatus.CREATED);
    }
    @GetMapping("/orders")
    public ResponseEntity<List<OrderEntity>> ListofOrders() {
        return new ResponseEntity<>(orderService.ListOfOrders(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<OrderEntity>> OrderById(@PathVariable("id") Integer orderId) {
        OrderEntity order = orderService.singleOrder(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
        return new ResponseEntity<>(Optional.ofNullable(order), HttpStatus.OK);
    }

     @PutMapping("/modify")
     public ResponseEntity updatedById(@RequestParam("id") Integer orderId, @RequestBody OrderEntity orderEntity) {
      orderService.updatedOrder(orderEntity,orderId);
       return ResponseEntity.ok("updated Successfully");
     }
     
     
    @ExceptionHandler({InvalidOrderException.class,OrderNotFoundException.class})
    public ResponseEntity<String> handleCustomExceptions(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

 