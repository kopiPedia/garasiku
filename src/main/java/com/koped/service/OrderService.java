package com.koped.service;

import com.koped.model.Cart;
import com.koped.model.Order;
import java.util.List;

public interface OrderService {
    Order createOrder(Order order);

    Order updateStatus(int id, String status);
    String deleteOrder(int id);
    Order getOrderById(int id);
    List<Order> getAllOrders();

    void saveOrder(Order order);
    List<Order> findByProductId(String productId);

}

