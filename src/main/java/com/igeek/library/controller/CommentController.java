package com.igeek.library.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.igeek.library.Vo.CommentVo;
import com.igeek.library.Vo.CommentVo2;
import com.igeek.library.Vo.ResultVo;
import com.igeek.library.entity.BookInfo;
import com.igeek.library.entity.Comment;
import com.igeek.library.entity.Page;
import com.igeek.library.entity.ReaderCard;
import com.igeek.library.mapper.BookInfoMapper;
import com.igeek.library.mapper.CommentMapper;
import com.igeek.library.service.IBookInfoService;
import com.igeek.library.utils.DateUtils;
import com.igeek.library.utils.ResultVoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController{
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    IBookInfoService bookInfoService;
    @Autowired
    BookInfoMapper bookInfoMapper;
    @RequestMapping("/detail")
    public ModelAndView detail(ModelAndView mav , HttpServletRequest request){
        String bookId = request.getParameter("bookId");
        List<CommentVo> allByBookId = commentMapper.findAllByBookId(Integer.valueOf(bookId));
        BookInfo bookById = bookInfoService.findBookById(Integer.valueOf(bookId));
        //将数据存入域中回显
        mav.addObject("book",bookById);
        mav.addObject("comments",allByBookId);
        mav.setViewName("comment");
        return mav;
    }
    @PostMapping("/add")
    public ResultVo add(Integer bookId , String content , HttpServletRequest request){
        HttpSession session = request.getSession();
        ReaderCard readercard = (ReaderCard) session.getAttribute("readercard");
        Comment comment = new Comment();
        comment.setReaderId(Math.toIntExact(readercard.getReaderId()));
        comment.setBookId(bookId);
        comment.setContent(content);
        comment.setCreateDate(DateUtils.createDate());
        Integer result = commentMapper.addComment(comment);
        if (result != 1){
            return ResultVoUtils.getFailed();
        }
        return ResultVoUtils.getSuccess(null);
    }
    @RequestMapping("/list")
    public ModelAndView list(ModelAndView mav,Integer pageNum){
        PageHelper.startPage(pageNum,5);
        List<CommentVo2> all = commentMapper.findAll();
        System.out.println(all);
        PageInfo<CommentVo2> commentVo2PageInfo = new PageInfo<>(all, 5);
        Page<CommentVo2> commentVo2Page = new Page<>();
        commentVo2Page.setUrl("/library/comment/list");
        commentVo2Page.setItems(all);
        commentVo2Page.setPageTotal(commentVo2PageInfo.getPages());
        commentVo2Page.setPageTotalCount((int) commentVo2PageInfo.getTotal());
        commentVo2Page.setPageNo(pageNum);
        mav.addObject("page",commentVo2Page);
        mav.setViewName("admin_comment");
        return mav;
    }
    @PostMapping("/del")
    public ResultVo del(Integer id , String bookName){
        //根据bookid和readerid删除
        BookInfo bookByName = bookInfoMapper.findBookByName(bookName);
        Integer del = commentMapper.del(id, Integer.parseInt(bookByName.getBookId().toString()));
        if (del != 1){
            return ResultVoUtils.getFailed();
        }
        return ResultVoUtils.getSuccess(null);
    }

    @GetMapping("/bookList")
    public ResultVo bookList(){
        List<BookInfo> all = bookInfoMapper.findAll();
        return  ResultVoUtils.getSuccess(all);
    }

    @PostMapping("/search")
    public ModelAndView search(ModelAndView mav , Integer pageNum , Integer bookId){
        PageHelper.startPage(pageNum,5);
        List<CommentVo2> all = commentMapper.findCommentVo2ByBookId(bookId);
        PageInfo<CommentVo2> commentVo2PageInfo = new PageInfo<>(all, 5);
        Page<CommentVo2> commentVo2Page = new Page<>();
        commentVo2Page.setUrl("/library/comment/list");
        commentVo2Page.setItems(all);
        commentVo2Page.setPageTotal(commentVo2PageInfo.getPages());
        commentVo2Page.setPageTotalCount((int) commentVo2PageInfo.getTotal());
        commentVo2Page.setPageNo(pageNum);
        mav.addObject("page",commentVo2Page);
        mav.setViewName("admin_comment");
        return mav;
    }

}
