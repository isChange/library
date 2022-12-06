package com.igeek.library.mapper;


import com.igeek.library.entity.ReaderInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReaderInfoMapper {
    //查找所有读者信息
    public List<ReaderInfo> list();
    //根据id查找读者信息
    public ReaderInfo findReaderById(@Param("id") Integer id);
    //根据id修改读者信息
    public Integer updateById(ReaderInfo readerInfo);
    //根据id删除读者
    public Integer delById(@Param("id")Integer id);
    //添加读者
    public Integer addReader(ReaderInfo readerInfo);
    //加  修改余额
    public  Integer updateBalance(@Param("balance") Integer balance,@Param("readerId") Integer readerId);
    //减  修改
    public  Integer updateBalanceDiv(@Param("balance") Integer balance,@Param("readerId") Integer readerId);
}