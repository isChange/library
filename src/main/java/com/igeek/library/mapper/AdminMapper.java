package com.igeek.library.mapper;

import com.igeek.library.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminMapper {
    //根据用户名查找用户信息
    Admin findAdminByName(@Param("username")String username);

    //查讯所有的用户记录
    List<Admin> findAll();

    //修改管理员的密码
    Integer updatePassword(@Param("username")String username ,@Param("password") String password);
}