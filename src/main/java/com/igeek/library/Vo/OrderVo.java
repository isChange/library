package com.igeek.library.Vo;

import lombok.Data;

@Data
public class OrderVo {
    private Long id;
    private String name;
    private String title;
    private String createDate;
    private Integer price;
}
