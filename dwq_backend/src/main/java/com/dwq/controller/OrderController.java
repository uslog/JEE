package com.dwq.controller;
import com.dwq.entity.dto.Order;
import com.dwq.service.impl.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/auth/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public int addOrder(@ModelAttribute Order order) {
        return orderService.addOrder(order);
    }

    @DeleteMapping("/{id}")
    public int deleteOrder(@PathVariable int id) {
        return orderService.deleteOrder(id);
    }

    @PutMapping
    public int updateOrder(@ModelAttribute Order order) {
        return orderService.updateOrder(order);
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable int id) {
        return orderService.getOrderById(id);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
}
