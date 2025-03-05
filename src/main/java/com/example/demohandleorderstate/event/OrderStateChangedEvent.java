package com.example.demohandleorderstate.event;

import com.example.demohandleorderstate.model.OrderState;

public record OrderStateChangedEvent(Long orderId, OrderState previousState, OrderState nextState) {

}
