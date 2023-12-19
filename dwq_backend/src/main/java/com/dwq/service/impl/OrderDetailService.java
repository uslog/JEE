package com.dwq.service.impl;
import com.dwq.entity.dto.OrderDetail;
import com.dwq.mapper.OrderDetailMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {

    private final OrderDetailMapper orderDetailMapper;

    public OrderDetailService(OrderDetailMapper orderDetailMapper) {
        this.orderDetailMapper = orderDetailMapper;
    }

    public void addOrderDetail(OrderDetail orderDetail) {
        orderDetailMapper.insertOrderDetail(orderDetail);
    }

    public void deleteOrderDetail(Integer detailId) {
        orderDetailMapper.deleteOrderDetail(detailId);
    }


    public void updateOrderDetail(OrderDetail orderDetail) {
        orderDetailMapper.updateOrderDetail(orderDetail);
    }

    public OrderDetail getOrderDetail(Integer detailId) {
        return orderDetailMapper.selectOrderDetailById(detailId);
    }

    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailMapper.selectAllOrderDetails();
    }
}
