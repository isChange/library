package com.igeek.library.mapper;


import com.igeek.library.entity.ReaderCard;
import org.apache.ibatis.annotations.Param;

public interface ReaderCardMapper {
    //根据readerId找readerCard
    public ReaderCard findReaderById(@Param("id") Integer id);
    //修改密码
    Integer updatePassword(@Param("readerId") Integer readerId, @Param("newPassword") String newPassword);
    //修改用户名
    Integer updateInfo(@Param("id") Integer id,@Param("name") String name);
    //添加读者卡
    Integer addReaderCard(ReaderCard readerCard);
    //根据Id删除
    Integer delReaderCardById(@Param("readerId") Integer readerId);
}