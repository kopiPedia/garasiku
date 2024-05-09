package com.koped.service;

import com.koped.enums.OrderStatus;
import com.koped.model.Order;
import com.koped.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order updateStatus(int id, String status) {
        Optional<Order> optionalOrder = orderRepository.findById(Integer.parseInt(String.valueOf(id)));
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setStatus(OrderStatus.valueOf(status));
            orderRepository.save(order);
            return order;
        } else {
            throw new NoSuchElementException("Order not found with id: " + id);
        }
    }

    @Override
    public Order getOrderById(int id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        return optionalOrder.orElse(null);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public String deleteOrder(int id) {
        orderRepository.deleteById(id);
        return null;
    }
}