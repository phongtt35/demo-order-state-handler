package com.example.demohandleorderstate.strategy.concrete;

import com.example.demohandleorderstate.model.OrderEvent;
import com.example.demohandleorderstate.model.OrderState;
import com.example.demohandleorderstate.strategy.IStateTransitionStrategy;

public final class WaitingForStockTransitionStrategy implements IStateTransitionStrategy {
    @Override
    public OrderState getNextState(OrderState currentState, OrderEvent event) {
        return switch (event) {
            case HUY_DON -> OrderState.DA_HUY;
            case CHUAN_BI -> OrderState.DANG_CHUAN_BI;
            default -> currentState;
        };
    }
}
