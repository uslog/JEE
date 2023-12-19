package com.dwq.entity.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

// 购物实体类
@Data
public class Shopping {
    private Integer shoppingId; // 主键
    private Integer userId;     // 用户ID
    private Integer petId;      // 宠物ID
    private BigDecimal totalPrice; // 总计
    private Date createTime;    // 加入时间
}
