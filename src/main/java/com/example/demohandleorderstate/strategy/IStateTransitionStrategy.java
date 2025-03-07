package com.example.demohandleorderstate.strategy;

import com.example.demohandleorderstate.model.OrderEvent;
import com.example.demohandleorderstate.model.OrderState;

public interface IStateTransitionStrategy{
    OrderState getNextState(OrderState currentState, OrderEvent event);
}
