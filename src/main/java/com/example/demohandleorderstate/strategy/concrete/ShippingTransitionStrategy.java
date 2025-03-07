package com.example.demohandleorderstate.strategy.concrete;

import com.example.demohandleorderstate.model.OrderEvent;
import com.example.demohandleorderstate.model.OrderState;
import com.example.demohandleorderstate.strategy.IStateTransitionStrategy;

public final class ShippingTransitionStrategy implements IStateTransitionStrategy {
    @Override
    public OrderState getNextState(OrderState currentState, OrderEvent event) {
        return switch (event) {
            case KHACH_NHAN -> OrderState.GIAO_THANH_CONG;
            case KHACH_BOM -> OrderState.GIAO_THAT_BAI;
            default -> currentState;
        };
    }
}
