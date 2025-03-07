package com.example.demohandleorderstate.strategy.concrete;

import com.example.demohandleorderstate.model.OrderEvent;
import com.example.demohandleorderstate.model.OrderState;
import com.example.demohandleorderstate.strategy.IStateTransitionStrategy;

public final class FailedDeliveryTransitionStrategy implements IStateTransitionStrategy {
    @Override
    public OrderState getNextState(OrderState currentState, OrderEvent event) {
        return switch (event) {
            case HANG_VE_LAI -> OrderState.DA_TRA_HANG;
            case GHI_MAT -> OrderState.MAT_HANG;
            default -> currentState;
        };
    }
}
