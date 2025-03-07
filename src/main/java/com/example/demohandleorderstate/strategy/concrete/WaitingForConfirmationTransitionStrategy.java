package com.example.demohandleorderstate.strategy.concrete;

import com.example.demohandleorderstate.model.OrderEvent;
import com.example.demohandleorderstate.model.OrderState;
import com.example.demohandleorderstate.strategy.IStateTransitionStrategy;

public final class WaitingForConfirmationTransitionStrategy implements IStateTransitionStrategy {

    @Override
    public OrderState getNextState(OrderState currentState, OrderEvent event) {
        return switch (event) {
            case XAC_NHAN -> OrderState.DA_XAC_NHAN;
            case CHO_NHAP -> OrderState.CHO_NHAP_HANG;
            case HUY_DON -> OrderState.DA_HUY;
            default -> currentState;
        };
    }
}
