package com.sarthak.order.repository;

import com.sarthak.order.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderLineRepository extends JpaRepository <OrderLine, Integer> {
    List<OrderLine> findAllByOrderId(Integer orderId);
}
