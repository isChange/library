package com.igeek.library.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String createDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String result = simpleDateFormat.format(new Date());
        return result;
    }
}
