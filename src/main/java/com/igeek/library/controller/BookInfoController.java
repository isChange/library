package com.igeek.library.controller;



import com.igeek.library.Vo.BookVo;
import com.igeek.library.Vo.ResultVo;
import com.igeek.library.entity.BookInfo;
import com.igeek.library.entity.ClassInfo;
import com.igeek.library.entity.Page;
import com.igeek.library.mapper.ClassInfoMapper;
import com.igeek.library.service.IBookInfoService;
import com.igeek.library.utils.ResultVoUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import java.util.List;
@RestController
@RequestMapping("/book")
public class BookInfoController {
    @Autowired
    IBookInfoService bookInfoService;
    @Autowired
    ClassInfoMapper classInfoMapper;
    @RequestMapping("/list")
    public ModelAndView list(ModelAndView mav,Integer pageNum){
        //获取所有书籍
        Page<BookInfo> all = bookInfoService.findAll(pageNum);
        all.setUrl("/library/book/list");
        //将书籍存入request域中
        mav.addObject("page",all);
        //返回结果
        mav.setViewName("admin_books");
        return mav;
    }
    @PostMapping("/search")
    public ModelAndView search(ModelAndView mav,String searchWord,Integer pageNum){
        //根据查询条件查找
        Page<BookInfo> allByLike = bookInfoService.findAllByLike(searchWord,pageNum);
        allByLike.setUrl("/library/book/search");
        mav.addObject("page",allByLike);
        mav.addObject("search",searchWord);
        mav.setViewName("admin_books");
        return mav;
    }

    @GetMapping("/findBookById")
    public ModelAndView findBookById(Integer id, ModelAndView modelAndView){
        //回显数据
        BookInfo book = bookInfoService.findBookById(id);
        modelAndView.addObject("book",book);
        modelAndView.setViewName("admin_book_edit");
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView updateById(BookInfo bookInfo , ModelAndView mav ,Integer pageNum){
        System.out.println("bookInfo:"+bookInfo);
        Integer integer = bookInfoService.updateBookById(bookInfo);
        mav.setViewName("forward:/book/list?pageNum=" + pageNum);
        return mav;
    }

    @PostMapping("/del/{id}/{pageNum}")
    public ResultVo del(@PathVariable("id") Integer id ,@PathVariable("pageNum") Integer pageNum){
        Integer integer = bookInfoService.delBookById(id);
        if (integer == -1){
            return ResultVoUtils.getFailed();
        }else if (integer == 0){
            return ResultVoUtils.getFailed();
        }
        return ResultVoUtils.getSuccess(null);
    }

    @PostMapping("/add")
    public ResultVo add( BookInfo bookInfo){
        Integer result = bookInfoService.addBook(bookInfo);
        if (result != 1){
            return ResultVoUtils.getFailed();
        }else {
            return ResultVoUtils.getSuccess(null);
        }
    }

    @GetMapping("/detail")
    public ModelAndView detail(ModelAndView mav, HttpServletRequest request){
        String bookId = request.getParameter("bookId");
        BookInfo bookById = bookInfoService.findBookById(Integer.valueOf(bookId));
        //将类别号转化为类别名
        BookVo bookVo = new BookVo();
        BeanUtils.copyProperties(bookById,bookVo);
        ClassInfo classInfoById = classInfoMapper.findClassInfoById(Math.toIntExact(bookById.getClassId()));
        bookVo.setClassName(classInfoById.getClassName());
        mav.addObject("detail",bookVo);
        mav.setViewName("admin_book_detail");
        return mav;
    }
}
