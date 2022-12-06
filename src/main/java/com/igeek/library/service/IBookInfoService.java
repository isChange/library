package com.igeek.library.service;


import com.igeek.library.Vo.ResultVo;
import com.igeek.library.entity.BookInfo;
import com.igeek.library.entity.Page;

import java.util.List;

public interface IBookInfoService {

    public Page<BookInfo> findAll(Integer pageNum);

    public Page<BookInfo> findAllByLike(String name,Integer pageNum);

    public BookInfo findBookById(Integer id);

    public Integer updateBookById(BookInfo bookInfo);

    public Integer delBookById(Integer id);

    public Integer addBook(BookInfo bookInfo);
}
