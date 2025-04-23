package com.sarthak.order.service;

import com.sarthak.order.clients.CustomerClient;
import com.sarthak.order.clients.PaymentClient;
import com.sarthak.order.clients.ProductClient;
import com.sarthak.order.entity.Order;
import com.sarthak.order.exception.BusinessException;
import com.sarthak.order.kafka.OrderProducer;
import com.sarthak.order.model.*;
import com.sarthak.order.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private CustomerClient customerClient;

    private ProductClient productClient;

    private OrderRepository orderRepository;

    private OrderMapper orderMapper;

    private OrderLineService orderLineService;

    private OrderProducer orderProducer;

    private PaymentClient paymentClient;

    public Integer createOrder(@Valid OrderRequest request) {
        //todo
        //check the customer--OpenFeign
        CustomerResponse customer = customerClient
                .findById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order for non existing customer"));
        //purchase the product

        List<PurchaseResponse> purchaseResponses = productClient.purchaseProduct(request.products());
        //persist the order
        Order savedOrder = orderRepository.save(orderMapper.toOrder(request));
        //persist order lines
        for(PurchaseRequest purchaseRequest: request.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            savedOrder.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }
        // todo start payment process

        PaymentRequest paymentRequest = new PaymentRequest(
                request.id(),
                request.amount(),
                request.paymentMethod(),
                savedOrder.getId(),
                savedOrder.getCustomerId(),
                customer);

        paymentClient.requestOrderPayment(paymentRequest);

        //send the order confirmation to notification MS(kafka)
        orderProducer.OrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchaseResponses
                ));
        return savedOrder.getId();
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll()
                .stream().map(orderMapper::fromOrder)
                .toList();
    }

    public OrderResponse findById(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapper::fromOrder)
                .orElseThrow(()-> new EntityNotFoundException(String.format("Order with id %d not found", orderId)));
    }
}
