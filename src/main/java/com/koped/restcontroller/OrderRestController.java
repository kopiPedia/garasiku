package com.koped.restcontroller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.koped.model.Order;
import com.koped.service.OrderServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderServiceImpl orderService;


    @GetMapping("/list")
    public List<Order> findAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/search/{orderId}")
    public Order findByOrderId(@PathVariable String orderId) {
        return orderService.getOrderById(Integer.parseInt(orderId));
    }

    @PostMapping("/create")
    public Order createNewOrder(@RequestBody Order data) {
        return orderService.createOrder(data);
    }

    @DeleteMapping("/delete/{orderId}")
    public String deleteOrderByOrderId(@PathVariable String orderId) {
        return orderService.deleteOrder(Integer.parseInt(orderId));
    }

    @PutMapping("/update")
    public Order updateOrderStatus(@PathVariable String orderId, @PathVariable String status) {
        return orderService.updateStatus(Integer.parseInt(orderId), status);
    }

}