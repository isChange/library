package com.igeek.library.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Comment {
    private Long id;
    private Integer readerId;
    private Integer bookId;
    private String createDate;
    private String content;
}
