package com.example.demohandleorderstate.controller;

import com.example.demohandleorderstate.model.Order;
import com.example.demohandleorderstate.model.OrderHistory;
import com.example.demohandleorderstate.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderService orderService;

    @PostMapping
    public Order createOrder() {
        return orderService.createOrder();
    }

    @GetMapping("/{orderId}/history")
    public List<OrderHistory> getOrderHistory(@PathVariable Long orderId) {
        return orderService.getOrderHistory(orderId);
    }

    @PutMapping("/{orderId}/confirm")
    public ResponseEntity<Order> confirmOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.confirmOrder(orderId));
    }

    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<Order> cancelOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.cancelOrder(orderId));
    }

    @PutMapping("/{orderId}/hold")
    public ResponseEntity<Order> waitForInput(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.waitForInput(orderId));
    }

    @PutMapping("/{orderId}/prepare")
    public ResponseEntity<Order> prepareOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.prepareOrder(orderId));
    }

    @PutMapping("/{orderId}/ship")
    public ResponseEntity<Order> shipOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.shipOrder(orderId));
    }

    @PutMapping("/{orderId}/reportLost")
    public ResponseEntity<Order> reportLostOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.reportLostOrder(orderId));
    }
}
