package com.dwq.entity.dto;

import lombok.Data;

// 地址实体类
@Data
public class Address {
    private Integer addressId; // 地址主键
    private Integer userId;    // 用户ID
    private String consignee;  // 收货人
    private String address;    // 详细地址
}
