package com.example.demohandleorderstate.strategy;

import com.example.demohandleorderstate.model.OrderEvent;
import com.example.demohandleorderstate.model.OrderState;
import com.example.demohandleorderstate.strategy.concrete.*;

import java.util.HashMap;
import java.util.Map;

public final class StateTransitionHandler {
    private static final Map<OrderState, IStateTransitionStrategy> strategies;

    static {
        strategies = new HashMap<>();
        strategies.put(OrderState.CHO_XAC_NHAN, new WaitingForConfirmationTransitionStrategy());
        strategies.put(OrderState.DA_XAC_NHAN, new OrderConfirmedTransitionStrategy());
        strategies.put(OrderState.CHO_NHAP_HANG, new WaitingForStockTransitionStrategy());
        strategies.put(OrderState.DANG_CHUAN_BI, new PreparingOrderTransitionStrategy());
        strategies.put(OrderState.DANG_GIAO_HANG, new ShippingTransitionStrategy());
        strategies.put(OrderState.GIAO_THAT_BAI, new FailedDeliveryTransitionStrategy());
    }

    public static OrderState handler(OrderState state, OrderEvent event) {
        IStateTransitionStrategy strategy = strategies.get(state);
        if (strategy != null) {
            return strategy.getNextState(state, event);
        }
        return state;
    }
}
