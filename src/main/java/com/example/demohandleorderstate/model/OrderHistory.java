package com.example.demohandleorderstate.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class OrderHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;

    @Enumerated(EnumType.STRING)
    private OrderState previousState;

    @Enumerated(EnumType.STRING)
    private OrderState nextState;

    private LocalDateTime changedAt = LocalDateTime.now();
}
