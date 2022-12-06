package com.igeek.library.Vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;

@Data
public class BookVo {
    private Long bookId;
    private String name;
    private String author;
    private String publish;
    private String isbn;
    private String language;
    private BigDecimal price;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String pubDate;
    private String className;
    private Integer number;
    private String introduction;
}
