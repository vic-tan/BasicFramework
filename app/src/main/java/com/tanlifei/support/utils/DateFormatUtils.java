package com.tanlifei.support.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * String 时间 转化格式化
 * Created by tanlifei on 16/4/12.
 */
public class DateFormatUtils {

    private static final long ONE_MINUTE = 60000L;
    private static final long ONE_HOUR = 3600000L;
    private static final long ONE_DAY = 86400000L;
    private static final long ONE_WEEK = 604800000L;

    private static final String ONE_JUST_AGO = "刚刚";
    private static final String ONE_MINUTE_AGO = "分钟前";
    private static final String ONE_HOUR_AGO = "小时前";
    private static final String ONE_DAY_AGO = "天前";
    private static final String ONE_WEEK_AGO = "周前";




    public static String sequence(Date date,String str) {
        long delta = new Date().getTime() - date.getTime();
        if (toSeconds(delta) <= 60) {
            return ONE_JUST_AGO;
        }
        if (delta < 1L * ONE_MINUTE) {
            long seconds = toSeconds(delta);
            return (seconds <= 0 ? 1 : seconds) + ONE_MINUTE_AGO;
        }
        if (delta < 60L * ONE_MINUTE) {
            long minutes = toMinutes(delta);
            return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
        }
        if (delta < 24L * ONE_HOUR) {
            long hours = toHours(delta);
            return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;
        }
        if (delta < 7L * ONE_DAY) {
            long days = toDays(delta);
            return (days <= 0 ? 1 : days) + ONE_DAY_AGO;
        }
        if (delta < 4L * ONE_WEEK) {
            long week = toWeek(delta);
            return (week <= 0 ? 1 : week) + ONE_WEEK_AGO;
        } else {
            return format(str, FormatType.DAY);
        }
    }

    private static long toSeconds(long date) {
        return date / 1000L;
    }

    private static long toMinutes(long date) {
        return toSeconds(date) / 60L;
    }

    private static long toHours(long date) {
        return toMinutes(date) / 60L;
    }

    private static long toDays(long date) {
        return toHours(date) / 24L;
    }

    private static long toWeek(long date) {
        return toDays(date) / 7L;
    }


    public static String format(String date) {
        if (StringUtils.isEmpty(date)) {
            return date;
        }
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            return df.format(df.parse(date));
        } catch (Exception e) {
            e.printStackTrace();
            return date;
        }
    }

    public static String format(String date, FormatType formatType) {
        if (StringUtils.isEmpty(date)) {
            return date;
        }
        String format = "yyyy-MM-dd hh:mm:ss";
        try {
            if (formatType == FormatType.DAY) {//显示日期
                format = "yyyy-MM-dd";
            } else if (formatType == FormatType.HOURS) {// 显示年月日，时
                format = "yyyy-MM-dd hh";
            } else if (formatType == FormatType.MINUTES) {//显示 年月日，时分
                format = "yyyy-MM-dd hh:mm";
            } else if (formatType == FormatType.SECONDS) {//显示 年月日，时分秒
                format = "yyyy-MM-dd hh:mm:ss";
            }
            SimpleDateFormat df = new SimpleDateFormat(format);
            return df.format(df.parse(date));
        } catch (Exception e) {
            e.printStackTrace();
            return date;
        }
    }

    /**
     * 几个小时之前，几天之前，几周之前
     * @param dateStr
     * @param formatType
     * @return
     */
    public static String sequenceFormat(String dateStr, FormatType formatType) {
        Date getDate = null;
        if (StringUtils.isEmpty(dateStr)) {
            getDate = new Date();
        } else {
            try {
                String format = "yyyy-MM-dd hh:mm:ss";
                if (formatType == FormatType.DAY) {//显示日期
                    format = "yyyy-MM-dd";
                } else if (formatType == FormatType.HOURS) {// 显示年月日，时
                    format = "yyyy-MM-dd hh";
                } else if (formatType == FormatType.MINUTES) {//显示 年月日，时分
                    format = "yyyy-MM-dd hh:mm";
                } else if (formatType == FormatType.SECONDS) {//显示 年月日，时分秒
                    format = "yyyy-MM-dd hh:mm:ss";
                } else if(formatType == FormatType.TITME){//显示 时分秒
                    format = "hh:mm:ss";
                }
                getDate = new SimpleDateFormat(format).parse(dateStr);
            } catch (Exception e) {
                getDate = new Date();
            }
        }
        return sequence(getDate, dateStr);
    }


    public enum FormatType {
        DAY,//显示日期 yyyy-MM-dd
        HOURS,// 显示年月日，时 yyyy-MM-dd hh
        MINUTES,//显示 年月日，时分 yyyy-MM-dd hh:mm
        SECONDS,//显示 年月日，时分秒 yyyy-MM-dd hh:mm:ss
        TITME,//显示 时分秒 hh:mm:ss
    }
}
