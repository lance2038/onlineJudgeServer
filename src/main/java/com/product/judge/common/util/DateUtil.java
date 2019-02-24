package com.product.judge.common.util;


import jodd.bean.BeanCopy;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author haiyan
 * @version v0.0.1
 * @project judgeApi
 * @describe 时间处理类
 * @since 2018/5/10
 **/
public class DateUtil extends BeanCopy
{
    public DateUtil(Object source, Object destination)
    {
        super(source, destination);
    }

    public static final DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    public static final DateFormat formatw = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final DateFormat formatm = new SimpleDateFormat("MM-dd HH:mm");
    public static final DateFormat formats = new SimpleDateFormat("MM-dd");

    /**
     * 取得当前年
     */
    public static String getCurrentYear()
    {
        GregorianCalendar gCalendar = new GregorianCalendar();
        int year = gCalendar.get(Calendar.YEAR);
        return Integer.toString(year);
    }

    /**
     * 取得当前月份
     */
    public static String getCurrentMonth()
    {
        GregorianCalendar gCalendar = new GregorianCalendar();
        int month = gCalendar.get(Calendar.MONTH) + 1;
        String strMonth = StringUtil.addChar(Integer.toString(month), '0', 2);
        return strMonth;
    }

    /**
     * 取得当前天
     */
    public static String getCurrentDay()
    {
        GregorianCalendar gCalendar = new GregorianCalendar();
        int day = gCalendar.get(Calendar.DAY_OF_MONTH);
        String strDay = StringUtil.addChar(Integer.toString(day), '0', 2);
        return strDay;
    }

    /**
     * 获取当前系统日期
     */
    public static String getCurrentDate()
    {
        GregorianCalendar lgc = new GregorianCalendar();
        String year = String.valueOf(lgc.get(Calendar.YEAR));
        String month = String.valueOf(lgc.get(Calendar.MONTH) + 1);
        if (month.length() == 1)
        {
            month = "0" + month;
        }
        String date = String.valueOf(lgc.get(Calendar.DATE));
        if (date.length() == 1)
        {
            date = "0" + date;
        }
        return year + "-" + month + "-" + date;
    }

    /**
     * 获取当前系统时间
     */
    public static String getCurrentTime()
    {
        Calendar rightNow = Calendar.getInstance();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sysDatetime = fmt.format(rightNow.getTime());
        return sysDatetime;
    }

    /**
     * 获取当前时
     */
    public static String getCurrentTimeHH()
    {
        Calendar rightNow = Calendar.getInstance();
        SimpleDateFormat fmt = new SimpleDateFormat("HH");
        String sysDatetime = fmt.format(rightNow.getTime());
        return sysDatetime;
    }

    /**
     * 获取当前分
     */
    public static String getCurrentTimeMM()
    {
        Calendar rightNow = Calendar.getInstance();
        SimpleDateFormat fmt = new SimpleDateFormat("mm");
        String sysDatetime = fmt.format(rightNow.getTime());
        return sysDatetime;
    }

    /**
     * 获取当前秒
     */
    public static String getCurrentTimeSS()
    {
        Calendar rightNow = Calendar.getInstance();
        SimpleDateFormat fmt = new SimpleDateFormat("ss");
        String sysDatetime = fmt.format(rightNow.getTime());
        return sysDatetime;
    }

    /**
     * 日期转化为字符格式
     */
    public static String date2Str(Date date)
    {
        if (date == null)
        {
            return "";
        }
        else
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(date);
        }
    }

    /**
     * 格式化日期。yyyy-MM-dd
     */
    public static String dateFormat(Date date)
    {
        return format.format(date);
    }

}
