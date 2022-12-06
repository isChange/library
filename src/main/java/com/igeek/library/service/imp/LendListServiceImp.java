package com.igeek.library.service.imp;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.igeek.library.entity.LendList;
import com.igeek.library.entity.Page;
import com.igeek.library.mapper.LendListMapper;
import com.igeek.library.service.ILendListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class LendListServiceImp implements ILendListService {
    @Autowired
    LendListMapper lendListMapper;

    @Override
    public Page<LendList> list(Integer pageNum) {
        PageHelper.startPage(pageNum,5);
        List<LendList> list = lendListMapper.list();
        PageInfo<LendList> lendListPageInfo = new PageInfo<>(list, 5);
        Page<LendList> lendListPage = new Page<>();
        lendListPage.setItems(list);
        lendListPage.setPageTotal(lendListPageInfo.getPages());
        lendListPage.setPageTotalCount((int) lendListPageInfo.getTotal());
        lendListPage.setPageNo(lendListPageInfo.getPageNum());
        return lendListPage;
    }

    @Override
    public Integer del(Integer serName) {
        Integer del = null;
        LendList bySerNum = lendListMapper.findBySerNum(serName);
        if (bySerNum != null){
            return -1;
        }
        try {
            del = lendListMapper.del(serName);
            return del;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}
