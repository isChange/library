package com.igeek.library.service.imp;


import com.igeek.library.entity.ClassInfo;
import com.igeek.library.mapper.ClassInfoMapper;
import com.igeek.library.service.IClassInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class ClassInfoServiceImp implements IClassInfoService {
    @Autowired
    ClassInfoMapper classInfoMapper;

    @Override
    public List<ClassInfo> list() {
        List<ClassInfo> all = classInfoMapper.findAll();
        return all;
    }
}
