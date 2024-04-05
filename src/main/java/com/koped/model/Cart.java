package com.koped.model;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter

public class Cart {
    private User user;
    private Product product;
    private int quantity;
    private int id; // ini auto increment buat tiap object
    private int price;
}
