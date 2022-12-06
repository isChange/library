package com.igeek.library.utils;

import com.igeek.library.Vo.ResultVo;

public class ResultVoUtils {

    public static ResultVo getSuccess(Object o){
        ResultVo<Object> ResultVo = new ResultVo<>();
        ResultVo.setCode(0);
        ResultVo.setData(o);
        return ResultVo;
    }
    public static ResultVo getFailed(){
        ResultVo<Object> ResultVo = new ResultVo<>();
        ResultVo.setCode(-1);
        return ResultVo;
    }
}
