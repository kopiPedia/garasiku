package com.koped.model;

import com.koped.enums.OrderStatus;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTest {

//    @Test
//    public void testOrderProperties() {
//
//        Order order = new Order();
//        order.setId(1);
//        order.setUserId(1);
//        order.setOrderDate(Timestamp.now());
//        order.setStatus(OrderStatus.PLACED);
//
//        assertEquals(1, order.getId(), "The ID should be correctly set in the order.");
//        assertEquals(1, order.getUserId(), "The user ID should be correctly set in the order.");
//        assertEquals(OrderStatus.PLACED, order.getStatus(), "The status should be correctly set in the order.");
//    }
//
//    @Test
//    public void testOrderDateIsSetCorrectly() {
//        Order order = new Order();
//        LocalDateTime now = LocalDateTime.now();
//        order.setOrderDate(now);
//
//        assertEquals(now, order.getOrderDate(), "The order date should be correctly set in the order.");
//    }

//    @Test
//    public void testOrderDateFormat() {
//        Order order = new Order();
//        LocalDateTime now = LocalDateTime.now();
//        order.setOrderDate(now);
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String formattedNow = now.format(formatter);
//
//        assertEquals(formattedNow, order.getOrderDate().format(formatter), "The order date should be in the correct format.");
//    }

//    @Test
//    public void testOrderStatusIsSetCorrectly() {
//        Order order = new Order();
//        order.setStatus(OrderStatus.PLACED);
//
//        assertEquals(OrderStatus.PLACED, order.getStatus(), "The status should be correctly set in the order.");
//    }


    @Test
    public void testSetOrderDate() {
        Order order = new Order();
        LocalDateTime expectedDate = LocalDateTime.of(2024, 5, 8, 15, 30);  // Example date and time
        order.setOrderDate(Timestamp.valueOf(expectedDate));

        assertEquals(expectedDate, order.getOrderDate(), "The order date should be correctly set and retrieved.");
    }

}
