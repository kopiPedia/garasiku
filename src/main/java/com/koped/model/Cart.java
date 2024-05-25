package com.koped.model;
import jakarta.persistence.*;
import lombok.Setter;
import lombok.Getter;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
@Table(name = "tbl_cart")
public class Cart {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "product")
    private String product;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private double price;

    @Column(name = "productId")
    private String productId;

}