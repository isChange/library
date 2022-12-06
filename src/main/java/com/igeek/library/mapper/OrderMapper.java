package com.igeek.library.mapper;

import com.igeek.library.Vo.OrderVo;
import com.igeek.library.Vo.OrderVo2;
import com.igeek.library.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {

    public Integer addOrder(Order order);

    public List<OrderVo> findAllByReaderId(@Param("readerId") Integer readerId);

    public List<OrderVo2> findAll();

    public List<OrderVo2> search(@Param("readerId") Integer readerId);
 }
