package com.dafa.qipai.dafaqipai.util;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {
    public static final long daySpan = 24 * 60 * 60 * 1000;
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String TIME_FORMAT_NORMAL = "yyyy-MM-dd HH:mm:ss";
    /**
     * yyyy-MM-dd
     */
    public static final String DATE_FORMAT_NORMAL = "yyyy-MM-dd";
    /**
     * yyyy.MM.dd
     */
    public static final String DATE_FORMAT_DOT = "yyyy.MM.dd";
    /**
     * yyyyMMdd
     */
    public static final String DATE_FORMAT_NO_MINUS = "yyyyMMdd";
    /**
     * yyMMdd
     */
    public static final String DATE_FORMAT_NO_MINUS_SHORT = "yyMMdd";
    /**
     * yyyy-MM
     */
    public static final String MONTH_FORMAT_NORMAL = "yyyy-MM";
    /**
     * MM-dd
     */
    public static final String MONTH_DAY_FORMAT = "MM-dd";
    /**
     * yyyyMMdd
     */
    public static final String DATE_FORMAT_SHORT = "yyyyMMdd";
    /**
     * yyyyMM
     */
    public static final String MONTH_FORMAT = "yyyyMM";
    /**
     * yyyy.MM
     */
    public static final String MONTH_FORMAT_DOT = "yyyy.MM";
    /**
     * yyyyMMddHHmm
     */
    public static final String DATE_FORMAT_MINUTE = "yyyyMMddHHmm";
    /**
     * MM/dd/yyyy HH:mm:ss
     **/
    public static final String MONTH_DAY_YEAR_HOUR_MINUTE = "MM/dd/yyyy HH:mm:ss";
    private final static String[] CN_Digits = {"〇", "一", "二", "三", "四", "五",
            "六", "七", "八", "九", "十"};
    /**
     * yyyyMMddHHmmss
     */
    private static final String TIME_FORMAT_SHORT = "yyyyMMddHHmmss";

    public static String getString2UNIX(long unix) {
        Date d = new Date(unix);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        return sdf.format(d);
    }

    public static String getString2HMS(long unix) {
        Date d = new Date(unix);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        return sdf.format(d);
    }


    public static String getString2Y4MD(long unix) {
        Date d = new Date(unix);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(d);
    }

    public static String getString2Y2MD(long unix) {
        Date d = new Date(unix);
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        return sdf.format(d);
    }


    public static String getCurrentStringTime() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String formatDate = format.format(date);
        return formatDate;
    }

    public static String getCurrentTimeSecond(long unix) {
        Date date = new Date(unix);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String formatDate = format.format(date);
        return formatDate;
    }

    /**
     * yyyy-MM-dd HH:mm:ss格式串转换为日期
     *
     * @param -MM-dd HH:mm:ss 格式日期
     * @return Date日期
     */
    public static Date paseDate(String formatDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(formatDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String getCurrentTimeMilliSecond() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        String formatDate = format.format(date);
        return formatDate;
    }

    public static String getCurrentMonth() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        String formatDate = format.format(date);
        return formatDate;
    }

    /**
     * 获取当前日期（格式为20110802）
     *
     * @return
     */
    public static String getCurrentDay() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String formatDate = format.format(date);
        return formatDate;
    }

    /**
     * 获取当前时间
     *
     * @param format 时间格式，例如：yyyy-MM-dd
     * @param count  增加或减少的天数
     * @return
     */
    public static String getCurrentDate(String format, Integer count) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, count);//增加或减少的天数
        Log.e("xi", "getCurrentDate: " + cal.getTime().getTime());
        String currentDate = df.format(cal.getTime());
        return currentDate;
    }


    public static String getDateNextDay(String date, Integer count) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(paseDate(date));
        cal.add(Calendar.DATE, count);//增加或减少的天数
        String currentDate = df.format(cal.getTime());
        return currentDate;
    }

    public static String getDayforCurry(String date, Integer count) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(paseDate(date));
        cal.add(Calendar.DATE, count);//增加或减少的天数
        Log.e("xi", "getDayforCurry: " + cal.getTime().getTime());
        String currentDate = df.format(cal.getTime());
        return currentDate;
    }


    /**
     * 增加月份后的日期数
     *
     * @param
     * @return
     */
    public static String getDateAddMoney(String dateStr, int m) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = df.parse(dateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.MONTH, m);
            return df.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String format(Date date) {
        if (null == date) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatDate = format.format(date);
        return formatDate;
    }

    public static String format2(Date date) {
        if (null == date) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String formatDate = format.format(date);
        return formatDate;
    }


    public static String getTimeForCreatTime(long date) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatDate = format.format(date);
        return formatDate;
    }

    public static String format(Date date, String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        String formatDate = format.format(date);
        return formatDate;
    }

    public static String format4Null(Date date, String formatStr) {

        if (date == null) {
            return null;
        } else {

            return format(date, formatStr);
        }
    }

    /**
     * 得到2个字符串日期之间的日期差,返回结果以秒为单位
     *
     * @param beginTime
     * @param endTime
     * @return
     * @throws ParseException
     */
    public static Long getOffTime(String beginTime, String endTime) {
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            java.util.Date begin = dfs.parse(beginTime);
            java.util.Date end = dfs.parse(endTime);
            long between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒
            return between;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getYesterday() {
        return getCurrentDate("yyyy-MM-dd 00:00", -1);
    }

    public static String getThisMonth() {
        return getCurrentDate("yyyy-MM-01 00:00", 0);
    }

    public static String getToday() {
        return getCurrentDate("yyyy-MM-dd HH:mm", 0);
    }

    public static String getTodayZero() {
        return getCurrentDate("yyyy-MM-dd 00:00", 0);
    }


    public static String getNextday() {
        return getCurrentDate("yyyy-MM-dd HH:mm", +1);
    }

    public static void main(String args[]) {
        String yesterday = getThisMonth();
        System.out.print(yesterday);
    }

    public static String getDayBeforeYesterday() {
        return getCurrentDate("yyyy-MM-dd 00:00", -2);
    }


    /***
     * 计算时间差，返回天数
     *
     * @param b_date 大时间
     * @param s_date 小时间
     * @return 天数
     */
//    public static long dateSubtract(Date b_date, Date s_date) {
//        if (StringUtils.blank(s_date) || StringUtils.blank(b_date)) {
//            return 0;
//        } else {
//            long dateLong1 = b_date.getTime();
//            long dateLong2 = s_date.getTime();
//            long day = (dateLong1 - dateLong2) / 1000 / 60 / 60 / 24;
//            return day;
//        }
//    }

    /**
     * 北京时间时区
     * @return
     */
    public static Calendar getBeijingCalendar() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    /**
     * 今日开始
     * @return
     */
    public static Date getTodayStart() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     * 今日结束（明日开始）
     * @return
     */
    public static Date getTodayEnd() {
        Date date = getTodayStart();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        // calendar.add(Calendar.SECOND, -1);

        return calendar.getTime();
    }

    /**
     * 昨日开始（前日结束）
     * @return
     */
    public static Date getYesterdayStart() {
        Date date = getTodayStart();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }

    /**
     * 昨日结束（今日开始）
     * @return
     */
    public static Date getYesterdayEnd() {
        Date date = getTodayEnd();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        // calendar.add(Calendar.SECOND, -1);

        return calendar.getTime();
    }

    /**
     * 获取传入时间的 下一天
     * @param date
     * @return
     */
    public static Date getNextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, +1);
        // calendar.add(Calendar.SECOND, -1);

        return calendar.getTime();
    }



    public static Date getQianriStart()
    {
        Date date = getYesterdayStart();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        // calendar.add(Calendar.SECOND, -1);

        return calendar.getTime();
    }
    public static Date getQianriEnd()
    {
        Date date = getYesterdayEnd();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        // calendar.add(Calendar.SECOND, -1);

        return calendar.getTime();
    }

    /**
     * 本周开始
     * @return
     */
    public static Date getWeekStart() {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayOfWeek == 0) {
            dayOfWeek = 7;
        }
        calendar.add(Calendar.DAY_OF_WEEK, 1 - dayOfWeek);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     * 本周结束（下周开始）
     * @return
     */
    public static Date getWeekEnd() {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayOfWeek == 0) {
            dayOfWeek = 7;
        }
        calendar.add(Calendar.DAY_OF_WEEK, 7 - dayOfWeek + 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        // calendar.add(Calendar.SECOND, -1);

        return calendar.getTime();
    }

    /**
     * 上周开始
     * @return
     */
    public static Date getLastWeekStart() {
        Date date = getWeekStart();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -7);

        return calendar.getTime();
    }

    public static Date getLastWeekEnd() {
        Date date = getWeekEnd();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -7);
        // calendar.add(Calendar.SECOND, -1);

        return calendar.getTime();
    }

    /**
     * 本月开始（上月结束）
     * @return
     */
    public static Date getMonthStart() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     * 本月结束（下月开始）
     * @return
     */
    public static Date getMonthEnd() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        // calendar.add(Calendar.SECOND, -1);

        return calendar.getTime();
    }

    /**
     * 上月开始（上上月结束）
     * @return
     */
    public static Date getLastMonthStart() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     * 上月结束（本月开始）
     * @return
     */
    public static Date getLastMonthEnd() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        // calendar.add(Calendar.SECOND, -1);

        return calendar.getTime();
    }

    /**
     * 获取前日 时间
     * @return
     */
    public static Date getQiantian()
    {  Calendar calendar= Calendar.getInstance();
       calendar.add(Calendar.DATE, -3);
       return calendar.getTime();
    }

    /**
     * 时间字符串转成Date
     * @param s
     * @return
     */
    public static Date string2Date(String s)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(s);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 计算两个时间戳的间隔
     * @return 返回小时
     */
    public static int timeCha(long startTime,long endTime ){
        if(endTime<startTime){
           return 0;
        }
        return (int)(endTime-startTime)/(1000*60*60);

    }

}