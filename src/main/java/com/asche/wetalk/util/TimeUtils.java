package com.asche.wetalk.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    /**
     * 获取当前系统时间
     *
     * @return 形如：2018-12-03 19:12:15
     */
    public static String getCurrentTime() {
        return format.format(new Date());
    }

    public static String getTime(String timestamp){
        return format.format(new Date(timestamp));
    }
}
