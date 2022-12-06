package com.igeek.library.Vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CommentVo2 {
    private Long id;
    private String readerName;
    private String bookName;
    private String createDate;
    private String content;
}
