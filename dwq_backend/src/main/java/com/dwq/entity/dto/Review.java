package com.dwq.entity.dto;
import lombok.Data;
import java.util.Date;

// 评价实体类
@Data
public class Review {
    private Integer reviewId; // 评价ID
    private Integer userId;   // 用户ID
    private Integer petId;    // 宠物ID
    private Integer rating;   // 评分
    private Date reviewDate;  // 评价日期
}

