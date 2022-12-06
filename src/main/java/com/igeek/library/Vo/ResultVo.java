package com.igeek.library.Vo;

import lombok.Data;

@Data
public class ResultVo<T> {
    private Integer code;
    private T data;

}
