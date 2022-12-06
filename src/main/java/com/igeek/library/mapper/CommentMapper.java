package com.igeek.library.mapper;

import com.igeek.library.Vo.CommentVo;
import com.igeek.library.Vo.CommentVo2;
import com.igeek.library.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper {
    //根据书籍id查找评论
    public List<CommentVo> findAllByBookId(@Param("bookId") Integer bookId);
    //添加评论
    public Integer addComment(Comment comment);
    //查询所有评论
    public List<CommentVo2> findAll();
    //根据id删除
    public Integer del(@Param("id") Integer id,@Param("bookId")Integer bookId );
    //根据Bookid查找
    public List<CommentVo2> findCommentVo2ByBookId(@Param("bookId") Integer bookId);
}
