package com.igeek.library.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.igeek.library.entity.BookInfo;
import com.igeek.library.entity.Page;
import com.igeek.library.mapper.BookInfoMapper;
import com.igeek.library.service.IBookInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class BookInfoServiceImp implements IBookInfoService {
    @Autowired
    BookInfoMapper bookInfoMapper;

    @Override
    public Page<BookInfo> findAll(Integer pageNum) {
        //开启分页
        PageHelper.startPage(pageNum,5);
        List<BookInfo> bookInfos = bookInfoMapper.findAll();
        //分页信息,navigatePages为导航分页的页码数
        PageInfo<BookInfo> bookInfoPageInfo = new PageInfo<>(bookInfos, 5);
        Page<BookInfo> bookInfoPage = new Page<>();
        //注入分页总页码
        bookInfoPage.setPageTotal(bookInfoPageInfo.getPages());
        bookInfoPage.setPageNo(pageNum);
        //注入分页记录
        bookInfoPage.setItems(bookInfos);
        //注入总记录数
        bookInfoPage.setPageTotalCount((int) bookInfoPageInfo.getTotal());
        return bookInfoPage;
    }

    @Override
    public Page<BookInfo> findAllByLike(String name,Integer pageNum) {
        //开启分页
        PageHelper.startPage(pageNum,5);
        List<BookInfo> bookInfos = null;
        if (name == null){
            bookInfos = bookInfoMapper.findAll();
        }else {
            bookInfos = bookInfoMapper.findAllByLike(name);
        }
        //分页信息,navigatePages为导航分页的页码数
        PageInfo<BookInfo> bookInfoPageInfo = new PageInfo<>(bookInfos, 5);
        Page<BookInfo> bookInfoPage = new Page<>();
        //注入分页总页码
        bookInfoPage.setPageTotal(bookInfoPageInfo.getPages());
        bookInfoPage.setPageNo(bookInfoPageInfo.getPageNum());
        //注入分页记录
        bookInfoPage.setItems(bookInfos);
        //注入总记录数
        bookInfoPage.setPageTotalCount((int) bookInfoPageInfo.getTotal());
        return bookInfoPage;
    }

    @Override
    public BookInfo findBookById(Integer id) {
        BookInfo bookById = bookInfoMapper.findBookById(id);
        return bookById;
    }

    @Override
    public Integer updateBookById(BookInfo bookInfo) {
        Integer integer = bookInfoMapper.updateBookById(bookInfo);
        return integer;
    }

    @Override
    public Integer delBookById(Integer id) {
        Integer integer = null;
        try {
            //如果发生外键冲突捕获并放回对应的代号
            integer = bookInfoMapper.delByBookId(id);
            return integer;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }

    @Override
    public Integer addBook(BookInfo bookInfo) {
        Integer integer = bookInfoMapper.addBook(bookInfo);
        return integer;
    }
}
