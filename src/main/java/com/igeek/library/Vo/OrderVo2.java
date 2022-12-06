package com.igeek.library.Vo;

import lombok.Data;

@Data
public class OrderVo2 {
    private Long id;
    private Long readerId;
    private String name;
    private String title;
    private String createDate;
    private Integer price;
}
