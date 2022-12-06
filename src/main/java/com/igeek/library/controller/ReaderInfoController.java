package com.igeek.library.controller;

import com.igeek.library.Vo.ResultVo;
import com.igeek.library.entity.Page;
import com.igeek.library.entity.ReaderInfo;
import com.igeek.library.service.IReaderCardService;
import com.igeek.library.service.IReaderInfoService;
import com.igeek.library.utils.PasswordUtils;
import com.igeek.library.utils.ResultVoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/readerInfo")
public class ReaderInfoController {
    @Autowired
    IReaderInfoService readerInfoService;
    @Autowired
    IReaderCardService readerCardService;

    @GetMapping("/list")
    public ModelAndView list(ModelAndView mav,Integer pageNum){
        Page<ReaderInfo> list = readerInfoService.list(pageNum);
        mav.addObject("page",list);
        list.setUrl("/library/readerInfo/list");
        mav.setViewName("admin_readers");
        return mav;

    }

    @GetMapping("/findById")
    public ModelAndView findById(ModelAndView mav, HttpServletRequest request){
        //获取传入的参数id
        String readerId = request.getParameter("readerId");
        ReaderInfo readerInfo = readerInfoService.findById(Integer.parseInt(readerId));
        mav.addObject("readerInfo",readerInfo);
        mav.setViewName("admin_reader_edit");
        return mav;
    }

    @PostMapping("/update")
    public ResultVo update(ReaderInfo readerInfo){
        Integer integer = readerInfoService.updateById(readerInfo);
        if (integer != 1){
            return ResultVoUtils.getFailed();
        }else {
            return ResultVoUtils.getSuccess(null);
        }
    }

    @GetMapping("/del")
    public ModelAndView del(ModelAndView mav,HttpServletRequest request){
        //获取id
        String readerId = request.getParameter("readerId");
        Integer integer = readerInfoService.delById(Integer.parseInt(readerId));
        if (integer == -1){
            mav.addObject("msg","该用户正在借阅！");
        }
        //同时读者卡也要删除
        readerCardService.delReaderCardById(Integer.parseInt(readerId));
        mav.setViewName("redirect:/readerInfo/list?pageNum=1");
        return mav;
    }
    @PostMapping("/add")
    public ResultVo add(ReaderInfo readerInfo , String password){
        //获取盐值
        String salt = UUID.randomUUID().toString();
        //对密码加密
        String md5Password = PasswordUtils.getMd5Password(password, salt);
        Integer integer = readerInfoService.addReader(readerInfo,md5Password,salt);
        if (integer != 1){
            return ResultVoUtils.getFailed();
        }else {
            return ResultVoUtils.getSuccess(null);
        }

    }
}
