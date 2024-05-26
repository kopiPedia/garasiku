package com.koped.service;

import com.koped.enums.OrderStatus;
import com.koped.model.Order;
import com.koped.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @InjectMocks
    OrderServiceImpl orderService;
    @Mock
    OrderRepository orderRepository;
    List<Order> orders;
    @BeforeEach
    void setUp() {
        orders = new ArrayList<>();
        Order order1 = new Order();
        order1.setId(1);
        orders.add(order1);
        Order order2 = new Order();
        order2.setId(2);
        orders.add(order2);
    }

    @Test
    void testCreateOrder() {
        Order order = orders.get(0);
        when(orderRepository.save(order)).thenReturn(order);

        Order result = orderService.createOrder(order);
        verify(orderRepository, times(1)).save(order);
        assertEquals(order.getId(), result.getId());
    }

//    @Test
//    void testUpdateStatus() {
//        Order order = orders.get(0);
//        when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));
//        when(orderRepository.save(any(Order.class))).thenReturn(order);
//
//        Order result = orderService.updateStatus(order.getId(), OrderStatus.SHIPPED.name());
//
//        assertEquals(order.getId(), result.getId());
//        assertEquals(OrderStatus.SHIPPED, result.getStatus());
//        verify(orderRepository, times(1)).save(any(Order.class));
//    }

    @Test
    void testUpdateStatusInvalidStatus() {
        Order order = orders.get(0);
        when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));

        assertThrows(IllegalArgumentException.class,
                () -> orderService.updateStatus(order.getId(), "MEOW"));
        verify(orderRepository, times(0)).save(any(Order.class));
    }

    @Test
    void testUpdateStatusInvalidOrderId() {
        when(orderRepository.findById(999)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class,
                () -> orderService.updateStatus(999, "SHIPPED"));

        verify(orderRepository, times(0)).save(any(Order.class));
    }

    @Test
    void testGetOrderById() {
        Order order = orders.get(0);
        when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));

        Order result = orderService.getOrderById(order.getId());
        assertEquals(order.getId(), result.getId());
    }

    @Test
    void testGetOrderByIdNotFound() {
        when(orderRepository.findById(999)).thenReturn(Optional.empty());

        Order result = orderService.getOrderById(999);
        assertNull(result);
    }

    @Test
    void testGetAllOrders() {
        when(orderRepository.findAll()).thenReturn(orders);

        List<Order> results = orderService.getAllOrders();
        assertEquals(2, results.size());
    }

    @Test
    void testGetAllOrdersWithNoOrders() {
        when(orderRepository.findAll()).thenReturn(new ArrayList<>());
        List<Order> results = orderService.getAllOrders();
        assertTrue(results.isEmpty());
    }
}