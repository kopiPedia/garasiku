package com.koped.repository;

import com.koped.enums.OrderStatus;
import com.koped.model.Order;
import com.koped.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class OrderRepositoryTest {

    private OrderRepository orderRepository;

    @BeforeEach
    public void setUp() {
        orderRepository = Mockito.mock(OrderRepository.class);
    }

    @Test
    public void testSaveOrder() {
        Order order = new Order();
        order.setId(1);
        when(orderRepository.save(order)).thenReturn(order);

        Order savedOrder = orderRepository.save(order);
        assertEquals(order.getId(), savedOrder.getId());
    }

    @Test
    public void testFindById() {
        Order order = new Order();
        order.setId(1);
        when(orderRepository.findById(1)).thenReturn(Optional.of(order));

        Optional<Order> foundOrder = orderRepository.findById(1);
        assertTrue(foundOrder.isPresent());
        assertEquals(order.getId(), foundOrder.get().getId());
    }

    @Test
    public void testFindAll() {
        Order order1 = new Order();
        order1.setId(1);

        Order order2 = new Order();
        order2.setId(2);

        when(orderRepository.findAll()).thenReturn(Arrays.asList(order1, order2));

        List<Order> orders = orderRepository.findAll();
        assertEquals(2, orders.size());
    }


    @Test
    public void testDeleteById() {
        Order order = new Order();
        order.setId(1);
        orderRepository.save(order);

        orderRepository.deleteById(1);
        Optional<Order> deletedOrder = orderRepository.findById(1);
        assertTrue(deletedOrder.isEmpty());
    }

//    @Test
//    public void testFindByStatus() {
//        Order order1 = new Order();
//        order1.setStatus(OrderStatus.PLACED);
//
//        Order order2 = new Order();
//        order2.setStatus(OrderStatus.PLACED);
//
//        when(orderRepository.findByStatus(OrderStatus.PLACED)).thenReturn(Arrays.asList(order1, order2));
//
//        List<Order> orders = orderRepository.findByStatus(OrderStatus.PLACED);
//        assertEquals(2, orders.size());
//    }
}
