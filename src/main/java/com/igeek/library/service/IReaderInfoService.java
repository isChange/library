package com.igeek.library.service;

import com.igeek.library.entity.Page;
import com.igeek.library.entity.ReaderInfo;

import java.util.List;

public interface IReaderInfoService {

    public Page<ReaderInfo> list(Integer pageNum);

    public ReaderInfo findById(Integer id);

    public Integer updateById(ReaderInfo readerInfo);

    public Integer delById(Integer id);

    public Integer addReader(ReaderInfo readerInfo , String password,String salt);


}
