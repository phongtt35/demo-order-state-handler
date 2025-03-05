package com.example.demohandleorderstate.repository;

import com.example.demohandleorderstate.model.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {

    List<OrderHistory> findByOrderId(Long orderId);
}
