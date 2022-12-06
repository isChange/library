package com.igeek.library.service;

import com.igeek.library.Vo.OrderVo;
import com.igeek.library.Vo.OrderVo2;
import com.igeek.library.entity.Order;
import com.igeek.library.entity.Page;



public interface IOrderService {

    public Order add(Long readerId , Integer price);

    public Page<OrderVo> list(Long readerId , Integer pageNum);

    public Page<OrderVo2> findAll(Integer pageNum);

    public Page<OrderVo2> search(Integer pageNum,Integer readerId);
}
