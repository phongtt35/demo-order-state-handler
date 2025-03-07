package com.example.demohandleorderstate.service;

import com.example.demohandleorderstate.event.OrderStateChangedEvent;
import com.example.demohandleorderstate.model.Order;
import com.example.demohandleorderstate.model.OrderEvent;
import com.example.demohandleorderstate.model.OrderHistory;
import com.example.demohandleorderstate.model.OrderState;
import com.example.demohandleorderstate.repository.OrderHistoryRepository;
import com.example.demohandleorderstate.repository.OrderRepository;
import com.example.demohandleorderstate.strategy.StateTransitionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderHistoryRepository orderHistoryRepository;
    private final ApplicationEventPublisher eventPublisher;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional
    public Order createOrder() {
        Order order = new Order();
        order.setState(OrderState.CHO_XAC_NHAN);
        return orderRepository.save(order);
    }

    @Transactional
    public Order confirmOrder(Long orderId) {
        return changeOrderState(orderId, OrderEvent.XAC_NHAN);
    }

    @Transactional
    public Order cancelOrder(Long orderId) {
        return changeOrderState(orderId, OrderEvent.HUY_DON);
    }

    @Transactional
    public Order waitForInput(Long orderId) {
        return changeOrderState(orderId, OrderEvent.CHO_NHAP);
    }

    @Transactional
    public Order prepareOrder(Long orderId) {
        return changeOrderState(orderId, OrderEvent.CHUAN_BI);
    }

    @Transactional
    public Order shipOrder(Long orderId) {
        return changeOrderState(orderId, OrderEvent.GUI_GIAO);
    }

    @Transactional
    public Order ghostOrder(Long orderId) {
        return changeOrderState(orderId, OrderEvent.KHACH_BOM);
    }

    @Transactional
    public Order completeOrder(Long orderId) {
        return changeOrderState(orderId, OrderEvent.KHACH_NHAN);
    }

    @Transactional
    public Order reportLostOrder(Long orderId) {
        return changeOrderState(orderId, OrderEvent.GHI_MAT);
    }

    @Transactional
    public Order returnOrder(Long orderId) {
        return changeOrderState(orderId, OrderEvent.HANG_VE_LAI);
    }

    private Order changeOrderState(Long orderId, OrderEvent orderEvent) {
        // Get order by id
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Set state machine context
        OrderState previousState = order.getState();

        // Get the new state after processing the event
        OrderState nextState = StateTransitionHandler.handler(previousState, orderEvent);
        order.setState(nextState);

        // Save updated order state
        orderRepository.save(order);

        // Save order history for tracking state changes
        eventPublisher.publishEvent(new OrderStateChangedEvent(orderId, previousState, nextState));

        return order;
    }


    public List<OrderHistory> getOrderHistory(Long orderId) {
        return orderHistoryRepository.findByOrderId(orderId);
    }
}