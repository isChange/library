package com.igeek.library.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.igeek.library.Vo.BookVo;
import com.igeek.library.Vo.ReaderLendVo;
import com.igeek.library.Vo.ResultVo;
import com.igeek.library.entity.*;
import com.igeek.library.mapper.BookInfoMapper;
import com.igeek.library.mapper.ClassInfoMapper;
import com.igeek.library.mapper.LendListMapper;
import com.igeek.library.mapper.ReaderInfoMapper;
import com.igeek.library.service.IBookInfoService;
import com.igeek.library.service.ILendListService;
import com.igeek.library.service.IReaderCardService;
import com.igeek.library.service.IReaderInfoService;
import com.igeek.library.utils.ResultVoUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

@RestController
@RequestMapping("/readerCard")
public class ReaderCardController {
    @Autowired
    IReaderCardService readerCardService;
    @Autowired
    IBookInfoService bookInfoService;
    @Autowired
    ILendListService lendListService;
    @Autowired
    LendListMapper lendListMapper;
    @Autowired
    BookInfoMapper bookInfoMapper;
    @Autowired
    IReaderInfoService readerInfoService;
    @Autowired
    ClassInfoMapper classInfoMapper;
    @Autowired
    ReaderInfoMapper readerInfoMapper;
    @PostMapping("/login")
    public ResultVo login(ReaderCard readerCard , HttpServletRequest request,String code){
        HttpSession session = request.getSession();
        //获取正确验证码与传入验证码比较
        String code2 = (String) session.getAttribute(KAPTCHA_SESSION_KEY);
        if(!code2.equalsIgnoreCase(code)){
            ResultVo resultVo = new ResultVo();
            resultVo.setCode(-3);
            resultVo.setData("验证码错误");
            return  resultVo;
        }
        ResultVo login = readerCardService.login(readerCard);
        if (login.getCode() == 0){
            //如果登入成功将用户信息存入Session域中
            session.setAttribute("readercard",login.getData());
        }
        return login;
    }
    @RequestMapping("/list")
    public ModelAndView list(ModelAndView mav,HttpServletRequest request,Integer pageNum){
        HttpSession session = request.getSession();
        ReaderCard reader = (ReaderCard) session.getAttribute("readercard");
        //将所有数据访问
        Page<BookInfo> bookInfoList = bookInfoService.findAll(pageNum);
        mav.addObject("page",bookInfoList);
        //查出用户的借阅书籍
        List<LendList> ReaderId = lendListMapper.findLendByReaderId(Integer.parseInt(reader.getReaderId().toString()));
        mav.addObject("myLendList",ReaderId);
        mav.setViewName("reader_books");
        return mav;
    }
    @RequestMapping("/search")
    public ModelAndView search(ModelAndView mav,HttpServletRequest request ,Integer pageNum){
        HttpSession session = request.getSession();
        ReaderCard reader = (ReaderCard) session.getAttribute("readercard");
        String searchWord = request.getParameter("searchWord");
        //将所有数据访问
        Page<BookInfo> bookInfoList = bookInfoService.findAllByLike(searchWord,pageNum);
        bookInfoList.setUrl("/library/readerCard/search");
        mav.addObject("page",bookInfoList);
        //查出用户的借阅书籍
        List<LendList> ReaderId = lendListMapper.findLendByReaderId(Integer.parseInt(reader.getReaderId().toString()));
        mav.addObject("myLendList",ReaderId);
        mav.setViewName("reader_books");
        return mav;
    }

    @PostMapping("/lend")
    public ResultVo lend(Integer bookId,HttpServletRequest request ,String backDate ){
        HttpSession session = request.getSession();
        ReaderCard readerCard = (ReaderCard) session.getAttribute("readercard");
        boolean lend = readerCardService.lend(bookId, Math.toIntExact(readerCard.getReaderId()),backDate);
        if (!lend){
            return ResultVoUtils.getFailed();
        }
        BookInfo bookById = bookInfoMapper.findBookById(bookId);
        ReaderInfo readerInfo = readerInfoService.findById(Math.toIntExact(readerCard.getReaderId()));
        //对应的金额也要改变
        Integer price = Integer.parseInt(readerInfo.getBalance().toString()) - Integer.parseInt(bookById.getPrice().toString());
        if (price < 0){
            return ResultVoUtils.getFailed();
        }
        //大于0则余额够用修改余额
        readerInfoMapper.updateBalanceDiv(price, Math.toIntExact(readerInfo.getReaderId()));
        return ResultVoUtils.getSuccess(null);
    }

    @PostMapping("/return")
    public ResultVo returnBook(Integer bookId, HttpServletRequest request){
        HttpSession session = request.getSession();
        ReaderCard readerCard = (ReaderCard) session.getAttribute("readercard");
        boolean returnBook = readerCardService.returnBook(bookId, Math.toIntExact(readerCard.getReaderId()));
        if (!returnBook){
            return ResultVoUtils.getFailed();
        }
        return ResultVoUtils.getSuccess(null);

    }

    @GetMapping("/detail")
    public ModelAndView detail(ModelAndView mav,HttpServletRequest request){
        String bookId = request.getParameter("bookId");
        BookInfo bookById = bookInfoMapper.findBookById(Integer.parseInt(bookId));
        //将类别号转化为类别名
        BookVo bookVo = new BookVo();
        BeanUtils.copyProperties(bookById,bookVo);
        ClassInfo classInfoById = classInfoMapper.findClassInfoById(Math.toIntExact(bookById.getClassId()));
        bookVo.setClassName(classInfoById.getClassName());
        mav.addObject("detail",bookVo);
        mav.setViewName("reader_book_detail");
        return mav;
    }
    @GetMapping("/readerInfo")
    public ModelAndView readerInfo(ModelAndView mav , HttpServletRequest request){
        HttpSession session = request.getSession();
        ReaderCard readercard = (ReaderCard) session.getAttribute("readercard");
        ReaderInfo result = readerInfoService.findById(Math.toIntExact(readercard.getReaderId()));
        mav.addObject("readerinfo",result);
        mav.setViewName("reader_info");
        return mav;
    }
    @GetMapping("/findById")
    public ModelAndView findById(ModelAndView mav , HttpServletRequest request){
        HttpSession session = request.getSession();
        ReaderCard readercard = (ReaderCard) session.getAttribute("readercard");
        ReaderInfo result = readerInfoService.findById(Math.toIntExact(readercard.getReaderId()));
        mav.addObject("readerinfo",result);
        mav.setViewName("reader_info_edit");
        return mav;
    }
    @PostMapping("/updateReaderInfo")
    public ResultVo updateReaderInfo(ReaderInfo readerInfo){
        Integer integer = readerInfoService.updateById(readerInfo);
        if (integer != 1){
            return ResultVoUtils.getFailed();
        }
        return ResultVoUtils.getSuccess(null);

    }

    @GetMapping("/lendList")
    public ModelAndView lendList(ModelAndView mav,HttpServletRequest request , Integer pageNum){
        HttpSession session = request.getSession();
        ReaderCard readercard = (ReaderCard) session.getAttribute("readercard");
        PageHelper.startPage(pageNum,5);
        List<ReaderLendVo> readerLendVos = lendListMapper.findReaderLendVoById(Integer.parseInt(readercard.getReaderId().toString()));
        PageInfo<ReaderLendVo> info = new PageInfo<>(readerLendVos, 5);
        Page<ReaderLendVo> readerLendVoPage = new Page<>();
        readerLendVoPage.setItems(readerLendVos);
        readerLendVoPage.setPageTotal(info.getPages());
        readerLendVoPage.setPageTotalCount((int) info.getTotal());
        readerLendVoPage.setPageNo(info.getPageNum());
        readerLendVoPage.setUrl("/library/readerCard/lendList");
        mav.addObject("page",readerLendVoPage);
        mav.setViewName("reader_lend_list");
        return mav;
    }
    @PostMapping("/updatePwd")
    public ResultVo updatePwd(HttpServletRequest request,String newPassword,String oldPassword){
        HttpSession session = request.getSession();
        ReaderCard readerCard = (ReaderCard) session.getAttribute("readercard");
        ResultVo resultVo = readerCardService.updatePwd(Integer.parseInt(readerCard.getReaderId().toString()), newPassword, oldPassword);
        return resultVo;
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,ModelAndView mav){
        HttpSession session = request.getSession();
        session.removeAttribute("readercard");
        mav.setViewName("index");
        return mav;
    }



}
