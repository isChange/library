package com.igeek.library.controller;

import com.igeek.library.Vo.ResultVo;
import com.igeek.library.entity.*;
import com.igeek.library.mapper.ReaderInfoMapper;
import com.igeek.library.service.IBookInfoService;
import com.igeek.library.service.ILendListService;
import com.igeek.library.service.IReaderInfoService;
import com.igeek.library.utils.ResultVoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/lendList")
@RestController
public class LendListController {
    @Autowired
    ILendListService lendListService;
    @Autowired
    IBookInfoService bookInfoService;
    @Autowired
    ReaderInfoMapper readerInfoMapper;

    @RequestMapping ("/list")
    public ModelAndView list(ModelAndView mav,Integer pageNum){
        Page<LendList> list = lendListService.list(pageNum);
        list.setUrl("/library/lendList/list");
        mav.addObject("page",list);
        mav.setViewName("admin_lend_list");
        return mav;
    }

    @PostMapping("/del/{serNum}/{pageNum}")
    public ResultVo del(@PathVariable("serNum")Integer serNum,@PathVariable("pageNum") Integer pageNum){
        Integer del = lendListService.del(serNum);
        if (del != 1){
            return ResultVoUtils.getFailed();
        }
        return ResultVoUtils.getSuccess(pageNum);
    }
    @GetMapping("/lendEdit")
    public ModelAndView lendEdit(HttpServletRequest request, ModelAndView modelAndView){
        HttpSession session = request.getSession();
        ReaderCard readercard = (ReaderCard) session.getAttribute("readercard");
        ReaderInfo readerInfo = readerInfoMapper.findReaderById(Math.toIntExact(readercard.getReaderId()));
        //获取书籍id
        String bookId = request.getParameter("bookId");
        //根据ID查书籍并且存入域中回显
        BookInfo bookById = bookInfoService.findBookById(Integer.valueOf(bookId));
        modelAndView.addObject("book",bookById);
        modelAndView.setViewName("reader_lend_edit");
        return modelAndView;
    }
}
