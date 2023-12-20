package com.dwq.controller;

import com.dwq.entity.dto.OrderDetail;
import com.dwq.service.impl.OrderDetailService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/orderDetails")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @PostMapping
    public void addOrderDetail(@ModelAttribute OrderDetail orderDetail) {
        orderDetailService.addOrderDetail(orderDetail);
    }

    @DeleteMapping("/{id}")
    public void deleteOrderDetail(@PathVariable("id") Integer detailId) {
        orderDetailService.deleteOrderDetail(detailId);
    }

    @PutMapping
    public void updateOrderDetail(@ModelAttribute OrderDetail orderDetail) {
        orderDetailService.updateOrderDetail(orderDetail);
    }

    @GetMapping("/{id}")
    public OrderDetail getOrderDetail(@PathVariable("id") Integer detailId) {
        return orderDetailService.getOrderDetail(detailId);
    }

    @GetMapping
    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailService.getAllOrderDetails();
    }
}
