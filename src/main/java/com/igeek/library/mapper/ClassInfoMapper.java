package com.igeek.library.mapper;


import com.igeek.library.entity.ClassInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassInfoMapper {

    //根据id找类别
    public ClassInfo findClassInfoById(@Param("id") Integer id);

    //查找所有类型
    public List<ClassInfo> findAll();

}