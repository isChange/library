package com.igeek.library.service.imp;


import com.igeek.library.Vo.ResultVo;
import com.igeek.library.entity.Admin;
import com.igeek.library.mapper.AdminMapper;
import com.igeek.library.service.IAdminService;
import com.igeek.library.utils.PasswordUtils;
import com.igeek.library.utils.ResultVoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class AdminServiceImp implements IAdminService {
    @Autowired
    AdminMapper adminMapper;
    @Override
    public ResultVo login(Admin admin) {
        //根据用户名查找记录
        Admin admin1 = adminMapper.findAdminByName(admin.getUsername());
        //创建返回结果集
        ResultVo<Object> resultVo = new ResultVo<>();
        if (admin1 == null){
            //该用户名不存在
            resultVo.setCode(-1);
            return resultVo;
        }else {
            //获取盐值
            String salt = admin1.getSalt();
            //密码还原
            String md5Password = PasswordUtils.getMd5Password(admin.getPassword(), salt);
            if (!admin1.getPassword().equals(md5Password)){
                //密码错误
                resultVo.setCode(-2);
                return resultVo;
            }
        }
        resultVo.setCode(0);
        return resultVo;
    }

    @Override
    public List<Admin> findAllAdmin() {
        List<Admin> all = adminMapper.findAll();
        return all;
    }

    @Override
    public ResultVo update(String username, String newPassword , String oldPassword) {
        //先根据用户名找到该用户
        Admin adminByName = adminMapper.findAdminByName(username);
        String salt = adminByName.getSalt();
        String md5Password = PasswordUtils.getMd5Password(oldPassword, salt);
        //在判断该用户输入的原密码是否和记录的密码一样
        if (!adminByName.getPassword().equals(md5Password)){
            //原密码不相符这返回修改失败
            return ResultVoUtils.getFailed();
        }
        //与原密码相符后开始修改
        String md5Password1 = PasswordUtils.getMd5Password(newPassword, salt);
        Integer integer = adminMapper.updatePassword(username, md5Password1);
        if (integer != 1){
            return ResultVoUtils.getFailed();
        }
        return ResultVoUtils.getSuccess(null);
    }
}
