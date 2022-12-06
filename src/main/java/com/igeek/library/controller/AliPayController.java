package com.igeek.library.controller;

import com.igeek.library.Vo.ResultVo;
import com.igeek.library.entity.ReaderCard;
import com.igeek.library.service.AliPayService;
import com.igeek.library.utils.ResultVoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/ali-pay")
public class AliPayController {
    @Autowired
    AliPayService aliPayService;
    @PostMapping("/trade/page/pay")
    public ResultVo aliPay(Integer price, HttpServletRequest request){
        HttpSession session = request.getSession();
        ReaderCard readercard = (ReaderCard) session.getAttribute("readercard");
        String formStr =  aliPayService.tradeCreate(readercard.getReaderId(),price);
          return ResultVoUtils.getSuccess(formStr);
    }

    @RequestMapping("/success")
    public ModelAndView Return(ModelAndView mav){
        mav.setViewName("forward:/readerCard/readerInfo");
        return mav;
    }
}
