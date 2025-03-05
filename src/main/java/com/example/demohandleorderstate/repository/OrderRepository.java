package com.example.demohandleorderstate.repository;

import com.example.demohandleorderstate.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
