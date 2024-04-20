package com.koped.model;

import com.koped.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;



@Getter
@Setter
@Table(name = "tbl_order")
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user")
    private User user;

    @Column(name = "cart")
    private Cart cart;

    @Column(name = "order_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderDate;

    @Column(name = "status")
    private OrderStatus status;
}