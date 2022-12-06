package com.igeek.library.Vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CommentVo {private Long id;
    private String name;
    private String createDate;
    private String content;
}
