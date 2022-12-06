package com.igeek.library.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Order {
    private Long id;
    private Long readerId;
    private String title;
    private String createDate;
    private Integer price;
}
