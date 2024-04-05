package com.koped.repository;

import com.koped.model.Cart;
import com.koped.model.User;
import com.koped.model.Product;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;


public class CartRepository {
    public Cart create(Cart cart) {
    }

    public void deleteByUserAndProduct(int id) {
        carts.remove(id);
    }

    public Cart findByUserAndProduct(int id, int quantity) {
    }

    public void update(int id, Cart cart) {
    }

    public Iterator<Cart> findAllByUser(User user) {
    }
}
