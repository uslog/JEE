package com.dwq.entity.dto;
import lombok.Data;
import java.math.BigDecimal;

@Data
// 订单详情实体类
public class OrderDetail {
    private Integer detailId; // 订单详情ID
    private Integer orderId;  // 订单ID
    private Integer petId;    // 宠物ID
    private Integer quantity; // 数量
    private BigDecimal price; // 单价
}
