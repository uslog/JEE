package com.dwq.entity.dto;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class Pet {
    private Integer id;
    private String name;
    private String gender;
    private Integer age;
    private String species;
    private String avatar;
    private String temperament;
    private BigDecimal price;
}
