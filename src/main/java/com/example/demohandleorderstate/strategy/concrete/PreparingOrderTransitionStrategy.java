package com.example.demohandleorderstate.strategy.concrete;

import com.example.demohandleorderstate.model.OrderEvent;
import com.example.demohandleorderstate.model.OrderState;
import com.example.demohandleorderstate.strategy.IStateTransitionStrategy;

public final class PreparingOrderTransitionStrategy implements IStateTransitionStrategy {
    @Override
    public OrderState getNextState(OrderState currentState, OrderEvent event) {
        return switch (event) {
            case GUI_GIAO -> OrderState.DANG_GIAO_HANG;
            case HUY_DON -> OrderState.DA_HUY;
            default -> currentState;
        };
    }
}
