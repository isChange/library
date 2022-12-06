package com.igeek.library.controller;

import com.igeek.library.Vo.ResultVo;
import com.igeek.library.entity.ClassInfo;
import com.igeek.library.service.IClassInfoService;
import com.igeek.library.utils.ResultVoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/classInfo")
public class ClassInfoController {
    @Autowired
    IClassInfoService classInfoService;

    @GetMapping("/list")
    public ResultVo list(){
        List<ClassInfo> list = classInfoService.list();
        return ResultVoUtils.getSuccess(list);
    }
}
