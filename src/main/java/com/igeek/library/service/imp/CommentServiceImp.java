package com.igeek.library.service.imp;

import com.igeek.library.entity.Comment;
import com.igeek.library.mapper.CommentMapper;
import com.igeek.library.service.ICommentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CommentServiceImp implements ICommentService {
    @Autowired
    CommentMapper commentMapper;
    @Override
    public Integer addComment(Comment comment) {
        return commentMapper.addComment(comment);
    }
}
