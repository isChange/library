package com.igeek.library.service.imp;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.igeek.library.entity.Page;
import com.igeek.library.entity.ReaderCard;
import com.igeek.library.entity.ReaderInfo;
import com.igeek.library.mapper.ReaderCardMapper;
import com.igeek.library.mapper.ReaderInfoMapper;
import com.igeek.library.service.IReaderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class ReaderInfoServiceImp implements IReaderInfoService {
    @Autowired
    ReaderInfoMapper readerInfoMapper;
    @Autowired
    ReaderCardMapper readerCardMapper;
    @Override
    public Page<ReaderInfo> list(Integer pageNum) {
        PageHelper.startPage(pageNum,5);
        List<ReaderInfo> list = readerInfoMapper.list();
        PageInfo<ReaderInfo> readerInfoPageInfo = new PageInfo<>(list, 5);
        Page<ReaderInfo> readerInfoPage = new Page<>();
        readerInfoPage.setItems(list);
        readerInfoPage.setPageTotal(readerInfoPageInfo.getPages());
        readerInfoPage.setPageTotalCount((int) readerInfoPageInfo.getTotal());
        readerInfoPage.setPageNo(readerInfoPageInfo.getPageNum());
        return readerInfoPage;
    }

    @Override
    public ReaderInfo findById(Integer id) {
        ReaderInfo reader = readerInfoMapper.findReaderById(id);
        return reader;
    }

    @Override
    public Integer updateById(ReaderInfo readerInfo) {
        //先找到对应readerInfo看名字是否更改了如果改了readerCard也对应这要改
        ReaderInfo readerById = readerInfoMapper.findReaderById(Math.toIntExact(readerInfo.getReaderId()));
        //不相同更改
        if (!readerById.getName().equals(readerInfo.getName())){
            Integer integer = readerCardMapper.updateInfo(Math.toIntExact(readerInfo.getReaderId()), readerInfo.getName());
            if (integer != 1){
                return integer;
            }
        }
        Integer integer = readerInfoMapper.updateById(readerInfo);
        return integer;
    }

    @Override
    public Integer delById(Integer id) {
        try {

            Integer integer = readerInfoMapper.delById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

        return id;
    }

    @Override
    public Integer addReader(ReaderInfo readerInfo , String password,String salt) {

        ReaderCard readerCard = new ReaderCard();
        readerCard.setSalt(salt);
        readerCard.setUsername(readerInfo.getName());
        readerCard.setPassword(password);
        readerCardMapper.addReaderCard(readerCard);
        readerInfo.setReaderId(readerCard.getReaderId());
        Integer integer = readerInfoMapper.addReader(readerInfo);
        return integer;
    }

}
