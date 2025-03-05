package com.example.demohandleorderstate.listener;

import com.example.demohandleorderstate.event.OrderStateChangedEvent;
import com.example.demohandleorderstate.model.OrderHistory;
import com.example.demohandleorderstate.repository.OrderHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderStateChangedListener {

    private final OrderHistoryRepository orderHistoryRepository;

    @EventListener
    public void handleOrderStateChange(OrderStateChangedEvent event) {
        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setOrderId(event.orderId());
        orderHistory.setPreviousState(event.previousState());
        orderHistory.setNextState(event.nextState());
        orderHistoryRepository.save(orderHistory);
    }
}
