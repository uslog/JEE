package com.dwq.service.impl;

import com.dwq.entity.dto.Order;
import com.dwq.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;

    public int addOrder(Order order) {
        return orderMapper.insertOrder(order);
    }

    public int deleteOrder(int id) {
        return orderMapper.deleteOrder(id);
    }

    public int updateOrder(Order order) {
        return orderMapper.updateOrder(order);
    }

    public Order getOrderById(int id) {
        return orderMapper.selectOrderById(id);
    }

    public List<Order> getAllOrders() {
        return orderMapper.selectAllOrders();
    }
}
