package com.example.library.util;

import com.example.library.constant.AopConstant;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil {

    public static String getDayTime() {
        return getCurrentTime(new Date(System.currentTimeMillis()), AopConstant.DateFormat.DATE_FORMAT_DAY);
    }

    public static String getCurrentTime() {
        return getCurrentTime(new Date(System.currentTimeMillis()), AopConstant.DateFormat.DATE_FORMAT_ALL);
    }

    public static String getCurrentTime(Date date, String regex) {
        return new SimpleDateFormat(regex, Locale.getDefault()).format(date);
    }

    /**
     * 获得东八区时间
     *
     * @return
     */
    public static String getChinaTime() {
        TimeZone timeZone = TimeZone.getTimeZone(AopConstant.DateFormat.GMT8);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AopConstant.DateFormat.DATE);
        simpleDateFormat.setTimeZone(timeZone);
        return simpleDateFormat.format(System.currentTimeMillis()).replace(" ","T")+"Z";
    }
}
