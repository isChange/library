package com.igeek.library.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.igeek.library.Vo.OrderVo;
import com.igeek.library.Vo.OrderVo2;
import com.igeek.library.entity.Order;
import com.igeek.library.entity.Page;
import com.igeek.library.mapper.OrderMapper;
import com.igeek.library.service.IOrderService;
import com.igeek.library.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class OrderServiceImp implements IOrderService {
    @Autowired
    OrderMapper orderMapper;

    @Override
    public Order add(Long readerId,Integer price) {
        Order order = new Order();
        order.setReaderId(readerId);
        order.setTitle("图书充值");
        order.setPrice(price);
        order.setCreateDate(DateUtils.createDate());
        Integer integer = orderMapper.addOrder(order);
        return order;
    }

    @Override
    public Page<OrderVo> list(Long readerId , Integer pageNum) {
        PageHelper.startPage(pageNum,5);
        List<OrderVo> allByReaderId = orderMapper.findAllByReaderId(Integer.parseInt(readerId.toString()));
        PageInfo<OrderVo> orderVoPageInfo = new PageInfo<>(allByReaderId, 5);
        Page<OrderVo> orderVoPage = new Page<>();
        orderVoPage.setItems(allByReaderId);
        orderVoPage.setPageTotalCount((int) orderVoPageInfo.getTotal());
        orderVoPage.setPageTotal(orderVoPageInfo.getPages());
        orderVoPage.setPageNo(pageNum);
        return orderVoPage;
    }

    @Override
    public Page<OrderVo2> findAll(Integer pageNum) {
        PageHelper.startPage(pageNum,5);
        List<OrderVo2> all = orderMapper.findAll();
        PageInfo<OrderVo2> orderVoPageInfo = new PageInfo<>(all, 5);
        Page<OrderVo2> orderVoPage = new Page<>();
        orderVoPage.setItems(all);
        orderVoPage.setPageTotal(orderVoPageInfo.getPages());
        orderVoPage.setPageTotalCount((int) orderVoPageInfo.getTotal());
        orderVoPage.setPageNo(pageNum);
        return orderVoPage;
    }

    @Override
    public Page<OrderVo2> search(Integer pageNum, Integer readerId) {
        PageHelper.startPage(pageNum,5);
        List<OrderVo2> all = null;
        if (readerId != null){
            all = orderMapper.search(readerId);
        }else {
            all = orderMapper.findAll();
        }
        PageInfo<OrderVo2> orderVoPageInfo = new PageInfo<>(all, 5);
        Page<OrderVo2> orderVoPage = new Page<>();
        orderVoPage.setItems(all);
        orderVoPage.setPageTotal(orderVoPageInfo.getPages());
        orderVoPage.setPageTotalCount((int) orderVoPageInfo.getTotal());
        orderVoPage.setPageNo(pageNum);
        return orderVoPage;
    }
}
