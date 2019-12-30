package com.kunpeng.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeHelper {
    public static final String FORMAT_REFRESH = "更新于 HH:mm";
    public static final String FORMAT1 = "HH:mm";
    public static final String FORMAT2 = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT3 = "yyyy-MM-dd HH:mm";
    public static final String FORMAT4 = "yyyy-MM-dd";
    public static final String FORMAT5 = "MM-dd";
    public static final String FORMAT6 = "MM月";
    public static final String FORMAT7 = "yyyy-MM";

    public static long getTimeFromSecond() {
        return getTimeStamp() / 1000;
    }

    public static long getTimeStamp() {
        return System.currentTimeMillis();
    }

    public static String getTimeFromMillis(String type, long time) {
        return new SimpleDateFormat(type, Locale.SIMPLIFIED_CHINESE).format(new Date(time));
    }
    public static String getTimeFromDate(String type, Date date) {
        return new SimpleDateFormat(type, Locale.SIMPLIFIED_CHINESE).format(date);
    }

    public static String getTimeFromCurrentMillis(String type) {
        return new SimpleDateFormat(type, Locale.SIMPLIFIED_CHINESE).format(new Date(getTimeStamp()));
    }

    public static DateFormat getDateFormat(String type) {
        return new SimpleDateFormat(type, Locale.SIMPLIFIED_CHINESE);
    }
}
