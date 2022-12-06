package com.igeek.library.mapper;


import com.igeek.library.Vo.ReaderLendVo;
import com.igeek.library.entity.LendList;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface LendListMapper {
    //查找所有借阅信息
    public List<LendList> list();
    //根据流水号删除借阅记录
    public Integer del(@Param("serNum") Integer serNum);
    //根据readerId查找借读信息
    public List<LendList> findLendByReaderId(@Param("id") Integer id);
    //增加一个借阅记录
    public Integer addLend(LendList lendList);
    //根据id增加归还日期
    public Integer updateBackTimeById(@Param("id") Integer id , @Param("date")Date date , @Param("bookId")Integer booKid);
    //根据id查询借阅书名和借出时间和归还时间
    public List<ReaderLendVo> findReaderLendVoById(@Param("readerId") Integer readerId);
    //根据readerId和BookId删除记录
    public Integer delByReaderIdAndBookId(@Param("readerId") Integer readerId ,@Param("bookId") Integer bookId);
    //根据serNum
    public LendList findBySerNum(@Param("serNum") Integer serNum);
}