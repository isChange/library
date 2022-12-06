package com.igeek.library.service;

import com.igeek.library.Vo.ResultVo;
import com.igeek.library.entity.ReaderCard;

public interface IReaderCardService {

    public ResultVo login(ReaderCard readerCard);

    public boolean lend(Integer bookId,Integer readerId,String backDate);

    boolean returnBook(Integer bookId, Integer readerId);

    public Integer delReaderCardById(Integer readerId);

    ResultVo updatePwd(Integer readerId, String newPassword, String oldPassword);
}
