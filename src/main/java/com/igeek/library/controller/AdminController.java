package com.igeek.library.controller;

import com.igeek.library.Vo.ResultVo;
import com.igeek.library.entity.Admin;
import com.igeek.library.mapper.AdminMapper;
import com.igeek.library.service.IAdminService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    IAdminService adminService;
    @Autowired
    AdminMapper adminMapper;
    @PostMapping("/login")
    public ResultVo login( Admin admin, HttpServletRequest request , String code){
        HttpSession session = request.getSession();
        //获取正确验证码与传入验证码比较
        String code2 = (String) session.getAttribute(KAPTCHA_SESSION_KEY);
        if(!code2.equalsIgnoreCase(code)){
            ResultVo resultVo = new ResultVo();
            resultVo.setCode(-3);
            resultVo.setData("验证码错误");
            return  resultVo;
        }
        ResultVo resultVo = adminService.login(admin);
        //如果登入成功就将用户信息保存到Session中
        if (resultVo.getCode() == 0){
            session.setAttribute("admin",admin);
        }
        return resultVo;
    }


    @PostMapping("/update")
    public ResultVo updatePwd(HttpServletRequest request,String newPassword,String oldPassword){
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        ResultVo resultVo = adminService.update(admin.getUsername(), newPassword, oldPassword);
        return resultVo;
    }
    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,ModelAndView mav){
        HttpSession session = request.getSession();
        session.removeAttribute("admin");
        mav.setViewName("index");
        return mav;
    }

}
