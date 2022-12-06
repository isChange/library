package com.igeek.library.service.imp;


import com.igeek.library.Vo.ResultVo;
import com.igeek.library.entity.LendList;
import com.igeek.library.entity.ReaderCard;
import com.igeek.library.mapper.BookInfoMapper;
import com.igeek.library.mapper.LendListMapper;
import com.igeek.library.mapper.ReaderCardMapper;
import com.igeek.library.service.IReaderCardService;
import com.igeek.library.utils.DateUtils;
import com.igeek.library.utils.PasswordUtils;
import com.igeek.library.utils.ResultVoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
@Transactional
@Service
public class ReaderCardServiceImp implements IReaderCardService {
    @Autowired
    ReaderCardMapper readerCardMapper;
    @Autowired
    LendListMapper lendListMapper;
    @Autowired
    BookInfoMapper bookInfoMapper;
    @Override
    public ResultVo login(ReaderCard readerCard) {
        //先查找有没有该用户
        ReaderCard readerById = readerCardMapper.findReaderById(Math.toIntExact(readerCard.getReaderId()));
        ResultVo<ReaderCard> resultVo = new ResultVo<>();
        if (readerById == null){
            //该用户名不存在
            resultVo.setCode(-1);
            return resultVo;
        }
        //获取盐值得到从页面输入的密码的加密密码
        String salt = readerById.getSalt();
        String md5Password = PasswordUtils.getMd5Password(readerCard.getPassword(), salt);
        //如果用户存在比较密码是否正确
        if (!md5Password.equals(readerById.getPassword())){
            resultVo.setCode(-2);
            return resultVo;
        }
        resultVo.setCode(0);
        resultVo.setData(readerById);
        return resultVo;
    }

    @Override
    public boolean lend(Integer bookId, Integer readerId , String backDate) {
        //借阅表中增加记录
        LendList lendList = new LendList();
        lendList.setBookId(Long.valueOf(bookId));
        lendList.setReaderId(Long.valueOf(readerId));
        lendList.setBackDate(backDate);
        lendList.setLendDate(DateUtils.createDate());
        Integer integer = lendListMapper.addLend(lendList);
        if (integer != 1){
            return false;
        }
        //在图书中该书籍的数量-1
        Integer integer1 = bookInfoMapper.delNumberByBookId(bookId);
        if (integer1 != 1){
            return false;
        }
        return true;
    }

    @Override
    public boolean returnBook(Integer bookId, Integer readerId) {
        //将归还的书籍在借阅记录上加上归还日期
        Integer integer = lendListMapper.delByReaderIdAndBookId(readerId,bookId);
        if (integer != 1){
            return false;
        }
        //将该书籍的数量+1
        Integer integer1 = bookInfoMapper.addNumberByBookId(bookId);
        if (integer1 != 1){
            return false;
        }
        return true;
    }

    @Override
    public Integer delReaderCardById(Integer readerId) {
        Integer integer = readerCardMapper.delReaderCardById(readerId);
        return integer;
    }

    @Override
    public ResultVo updatePwd(Integer readerId, String newPassword, String oldPassword) {
        //先根据用户id找到该用户
        ReaderCard readerCard = readerCardMapper.findReaderById(readerId);
        //在判断该用户输入的原密码是否和记录的密码一样
        String salt = readerCard.getSalt();
        String md5Password = PasswordUtils.getMd5Password(oldPassword, salt);
        if (!readerCard.getPassword().equals(md5Password)){
            //原密码不相符这返回修改失败
            return ResultVoUtils.getFailed();
        }
        //与原密码相符后开始修改
        String md5Password1 = PasswordUtils.getMd5Password(newPassword, salt);
        Integer integer = readerCardMapper.updatePassword(readerId, md5Password1);
        if (integer != 1){
            return ResultVoUtils.getFailed();
        }
        return ResultVoUtils.getSuccess(null);
    }


}
