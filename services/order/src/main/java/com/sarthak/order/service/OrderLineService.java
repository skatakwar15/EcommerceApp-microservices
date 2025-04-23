package com.sarthak.order.service;

import com.sarthak.order.entity.OrderLine;
import com.sarthak.order.model.OrderLineRequest;
import com.sarthak.order.model.OrderLineResponse;
import com.sarthak.order.repository.OrderLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private OrderLineRepository orderLineRepository;
    private OrderLineMapper orderLineMapper;
    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
        OrderLine savedOrderLine = orderLineMapper.toOrderLine(orderLineRequest);
        return orderLineRepository.save(savedOrderLine).getId();
    }

    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
        return orderLineRepository.findAllByOrderId(orderId)
                .stream()
                .map(orderLineMapper::toOrderLineResponse)
                .toList();
    }
}
