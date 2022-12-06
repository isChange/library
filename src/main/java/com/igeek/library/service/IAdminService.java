package com.igeek.library.service;

import com.igeek.library.Vo.ResultVo;
import com.igeek.library.entity.Admin;

import java.util.List;

public interface IAdminService {

    public ResultVo login(Admin admin);

    public List<Admin> findAllAdmin();

    public ResultVo update(String username,String newPassword , String oldPassword);
}
