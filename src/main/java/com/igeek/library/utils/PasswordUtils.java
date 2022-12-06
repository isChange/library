package com.igeek.library.utils;

import org.springframework.util.DigestUtils;

public class PasswordUtils {

    public static String getMd5Password(String password , String salt){
        for (int i = 0; i < 3; i++) {
            //md5加密
            password = DigestUtils.md5DigestAsHex((salt+password+salt).getBytes());
        }
        return password;
    }
}
