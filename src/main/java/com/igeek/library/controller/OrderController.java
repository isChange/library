package com.igeek.library.controller;

import com.igeek.library.Vo.OrderVo;
import com.igeek.library.Vo.OrderVo2;
import com.igeek.library.Vo.ResultVo;
import com.igeek.library.entity.Page;
import com.igeek.library.entity.ReaderCard;
import com.igeek.library.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    IOrderService orderService;


    @RequestMapping("/listByReaderId")
    public ModelAndView list(ModelAndView mav , HttpServletRequest request ,Integer pageNum){
        HttpSession session = request.getSession();
        ReaderCard readercard = (ReaderCard) session.getAttribute("readercard");
        Page<OrderVo> list = orderService.list(readercard.getReaderId(), pageNum);
        list.setUrl("/library/order/listByReaderId");
        mav.addObject("page",list);
        mav.setViewName("reader_bill");
        return mav;
    }

    @RequestMapping("/list")
    public ModelAndView listAll(ModelAndView mav, Integer pageNum){
        Page<OrderVo2> all = orderService.findAll(pageNum);
        all.setUrl("/library/order/list");
        mav.addObject("page",all);
        mav.setViewName("admin_bill");
        return mav;
    }
    @RequestMapping("/search")
    public ModelAndView search(Integer pageNum,ModelAndView mav,Integer search){
        Page<OrderVo2> orderVo2Page = orderService.search(pageNum, search);
        orderVo2Page.setUrl("/library/order/search");
        mav.addObject("page",orderVo2Page);
        //回显输入框的内容
        mav.addObject("search",search);
        mav.setViewName("admin_bill");
        return mav;

    }

}
