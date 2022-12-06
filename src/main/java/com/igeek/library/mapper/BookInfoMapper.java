package com.igeek.library.mapper;


import com.igeek.library.entity.BookInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookInfoMapper {
    //查询所有书籍记录
    List<BookInfo> findAll();

    //根据图书馆名字模糊查询
    List<BookInfo> findAllByLike(@Param("name") String name);

    //根据id名查询

    BookInfo findBookById(@Param("id") Integer id);

    //根据id修改信息

    Integer updateBookById(BookInfo bookInfo);

    //根据id删除数据

    Integer delByBookId(@Param("id") Integer id);

    //添加书籍

    Integer addBook(BookInfo bookInfo);

    //指定书籍数量-1
    Integer delNumberByBookId(@Param("id") Integer id );

    //指定书籍数量+1
    Integer addNumberByBookId(@Param("id") Integer id );

    BookInfo findBookByName(@Param("bookName") String bookName);

}