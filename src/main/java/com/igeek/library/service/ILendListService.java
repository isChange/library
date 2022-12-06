package com.igeek.library.service;

import com.igeek.library.entity.LendList;
import com.igeek.library.entity.Page;

import java.util.List;

public interface ILendListService {
    public Page<LendList> list(Integer pageNum);

    public Integer del(Integer serName);
}
