package com.example.demohandleorderstate.config;

import com.example.demohandleorderstate.model.OrderEvent;
import com.example.demohandleorderstate.model.OrderState;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

@Configuration
@EnableStateMachine
public class OrderStateConfig extends StateMachineConfigurerAdapter<OrderState, OrderEvent> {

    @Override
    public void configure(StateMachineStateConfigurer<OrderState, OrderEvent> states) throws Exception {
        states.withStates()
                .initial(OrderState.CHO_XAC_NHAN)
                .states(EnumSet.allOf(OrderState.class))
                .end(OrderState.DA_HUY)
                .end(OrderState.GIAO_THANH_CONG)
                .end(OrderState.MAT_HANG)
                .end(OrderState.DA_TRA_HANG);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderState, OrderEvent> transitions) throws Exception {
        transitions
                .withExternal().source(OrderState.CHO_XAC_NHAN).target(OrderState.DA_XAC_NHAN).event(OrderEvent.XAC_NHAN)
                .and()
                .withExternal().source(OrderState.CHO_XAC_NHAN).target(OrderState.DA_HUY).event(OrderEvent.HUY_DON)
                .and()
                .withExternal().source(OrderState.CHO_XAC_NHAN).target(OrderState.CHO_NHAP_HANG).event(OrderEvent.CHO_NHAP)
                .and()
                .withExternal().source(OrderState.CHO_NHAP_HANG).target(OrderState.DANG_CHUAN_BI).event(OrderEvent.CHUAN_BI)
                .and()
                .withExternal().source(OrderState.CHO_NHAP_HANG).target(OrderState.DA_HUY).event(OrderEvent.HUY_DON)
                .and()
                .withExternal().source(OrderState.DA_XAC_NHAN).target(OrderState.DANG_CHUAN_BI).event(OrderEvent.CHUAN_BI)
                .and()
                .withExternal().source(OrderState.DA_XAC_NHAN).target(OrderState.DA_HUY).event(OrderEvent.HUY_DON)
                .and()
                .withExternal().source(OrderState.DANG_CHUAN_BI).target(OrderState.DA_HUY).event(OrderEvent.HUY_DON)
                .and()
                .withExternal().source(OrderState.DANG_CHUAN_BI).target(OrderState.DANG_GIAO_HANG).event(OrderEvent.GUI_GIAO)
                .and()
                .withExternal().source(OrderState.DANG_GIAO_HANG).target(OrderState.GIAO_THANH_CONG).event(OrderEvent.KHACH_NHAN)
                .and()
                .withExternal().source(OrderState.DANG_GIAO_HANG).target(OrderState.GIAO_THAT_BAI).event(OrderEvent.KHACH_BOM)
                .and()
                .withExternal().source(OrderState.GIAO_THAT_BAI).target(OrderState.DA_TRA_HANG).event(OrderEvent.HANG_VE_LAI)
                .and()
                .withExternal().source(OrderState.GIAO_THAT_BAI).target(OrderState.MAT_HANG).event(OrderEvent.GHI_MAT);
    }
}