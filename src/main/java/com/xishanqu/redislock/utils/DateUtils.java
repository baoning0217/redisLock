package com.xishanqu.redislock.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {

    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

    private DateUtils() {
    }

    public static final String DEFAULT_DATE_PATTERN = "yyyyMMddHHmmss";// 日期格式
    public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";// 日期格式
    public static final String YYYYMMDD = "yyyyMMdd";// 日期格式
    public static final String YYYYMMDDHHMMSS_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";// 日期格式

    public static final String YYYYMMDD_DATE_PATTERN2 = "yyyy-MM-dd";
    public static final String YYYYMMDD_DATE_PATTERN3 = "yyyy/MM/dd";
    public final static String PATTERN_SEPARATOR_BY_SLASH2 = "yyyy/MM/dd HH:mm:ss"; // 默认日期格式

    /**
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     * @Title: getNowDate
     * @Description: YYYY-MM-dd格式的
     */
    public static String getNowDate() {
        return new SimpleDateFormat(YYYYMMDDHHMMSS_DATE_PATTERN).format(new Date());
    }

    public static Date getNowDateYYYYMMDD() {
        String now = now3();
        return strToDate(now, YYYYMMDD_DATE_PATTERN2);
    }

    public static Date strToDate(String datestring, String format) {
        Date result = null;
        if (datestring != null) {
            try {
                result = new SimpleDateFormat(format).parse(datestring);
            } catch (Exception e) {
                logger.error("日期转化'" + datestring + "'转换异常" + e);
            }
        }
        return result;
    }

    /**
     * 获取当天时间(传入格式)
     *
     * @return
     */
    public static String getCurrentDate(String pattern) {
        DateFormat defaultFormat = new SimpleDateFormat(pattern);
        Date currentDate = new Date();
        return defaultFormat.format(currentDate);
    }

    /**
     * 解析日期（默认格式：yyyyMMddHHmmss）
     *
     * @param source
     * @return
     * @throws ParseException
     */
    public static Date parseToDate(String source) throws ParseException {
        DateFormat defaultFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        return defaultFormat.parse(source);
    }

    /**
     * 解析日期（传入格式）
     *
     * @param source
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date parseToDate(String source, String pattern) throws ParseException {
        DateFormat defaultFormat = new SimpleDateFormat(pattern);
        return defaultFormat.parse(source);
    }

    /**
     * 解析日期
     *
     * @param source        原日期
     * @param sourcePattern 原日期格式
     * @param targetPattern 目标日期格式
     * @return
     * @throws ParseException
     */
    public static String parseDateToStr(String source, String sourcePattern, String targetPattern) throws ParseException {
        if (source == null || source.equals("")) {
            return "";
        }
        DateFormat sourceFormat = new SimpleDateFormat(sourcePattern);
        Date date = sourceFormat.parse(source);
        DateFormat targetFormat = new SimpleDateFormat(targetPattern);
        return targetFormat.format(date);
    }

    /**
     * 获得系统的当前时间,格式"yyyy-MM-dd HH:mm:ss";
     *
     * @return
     */
    public static String now() {
        return getCurrentDate(YYYYMMDDHHMMSS_DATE_PATTERN);
    }

    /**
     * 获得系统的当前时间,格式"yyyy/MM/dd HH:mm:ss";
     *
     * @return
     */
    public static String defaultNow() {
        return getCurrentDate(PATTERN_SEPARATOR_BY_SLASH2);
    }

    public static String now2() {
        return getCurrentDate(YYYYMMDD_DATE_PATTERN3);
    }

    public static String now3() {
        return getCurrentDate(YYYYMMDD_DATE_PATTERN2);
    }

    public static String now4() {
        return getCurrentDate(DEFAULT_DATE_PATTERN);
    }

    /**
     * 获取昨天的日期的开始时间,格式yyyy-MM-dd 00:00:00
     *
     * @return
     */
    @SuppressWarnings("static-access")
    public static String getYesterdayStarted() {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(YYYYMMDD_DATE_PATTERN2 + " 00:00:00");
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 获取今天的日期的开始时间,格式yyyy-MM-dd 00:00:00
     *
     * @return
     */
    @SuppressWarnings("static-access")
    public static String getTodayStarted() {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 0);
        date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(YYYYMMDD_DATE_PATTERN2 + " 00:00:00");
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 获取今天的开始时间  date格式
     *
     * @return
     */
    public static Date getTodayDateStarted() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取昨天的开始时间  date格式
     *
     * @return
     */
    public static Date getYesterdayDateStarted() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getTodayDateStarted());
        calendar.add(Calendar.DATE, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取昨天的结束时间  date格式
     *
     * @return
     */
    public static Date getYesterdayDateEnded() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getTodayDateStarted());
        calendar.add(Calendar.DATE, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 获取7天前的日期时间
     *
     * @return
     */
    public static Date getTodayBeforeSevenDaysDate() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(getTodayDateStarted());
        calendar.add(Calendar.DATE, -6);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取本月的第一天的日期
     *
     * @return
     */
    public static Date getTheCurrentMonthFirstDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /***
     * 获取距离当天几个月前的日期
     *
     * @param time
     * @return
     */
    public static Date getBeforeMonth(int time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        time = -time;
        calendar.add(Calendar.MONTH, time);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /***
     * 获取距离当天几天后的日期 yyyy-MM-dd 23:59:59
     *
     * @param day
     * @return
     */
    public static Date getAfterDay(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, day);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 获取某天的开始时间,格式yyyy-MM-dd 00:00:00
     *
     * @param dateTime 格式yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getDayStarted(String dateTime) {

        String[] datas = dateTime.split(" ");
        return datas[0] + " 00:00:00";
    }

    /**
     * 获取某天的结束时间 格式"yyyy-MM-dd 23:59:59"
     *
     * @param dateTime 格式"yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static String getDayEnded(String dateTime) {
        String[] datas = dateTime.split(" ");
        return datas[0] + " 23:59:59";
    }

    /**
     * 获取某天的开始时间转日期
     *
     * @param startTime yyyy-MM-dd
     * @return
     */
    public static Date getDateStart(String startTime) {
        return strToDate(getDayStarted(startTime), YYYYMMDDHHMMSS_DATE_PATTERN);
    }

    /**
     * 获取某天的结束时间转日期
     *
     * @param endTime yyyy-MM-dd
     * @return
     */
    public static Date getDateEnd(String endTime) {
        return strToDate(getDayEnded(endTime), YYYYMMDDHHMMSS_DATE_PATTERN);
    }


    /**
     * 获取某天的结束时间 格式"yyyy-MM-dd 23:59:59"
     *
     * @param dateTime 格式"yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static Date getDayEnded(Date dateTime) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return parseToDate(sdf.format(dateTime) + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取某天的开始时间 格式"yyyy-MM-dd 23:59:59"
     *
     * @param dateTime 格式"yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static Date getDayStarted(Date dateTime) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return parseToDate(sdf.format(dateTime) + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 时间比较
     *
     * @param source
     * @param target
     * @return
     * @throws ParseException
     */
    public static boolean compareTime(String source, String target) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(PATTERN_SEPARATOR_BY_SLASH2);
            long startTimeMill = format.parse(source).getTime();
            long endTimeMill = format.parse(target).getTime();
            long diff = endTimeMill - startTimeMill;
            if (diff >= 0) {
                return true;
            }
            return false;
        } catch (ParseException e) {
            e.printStackTrace();
            logger.error("compareTime转换异常！");
            return false;
        }
    }

    /**
     * 获取某个时间7天前的时间
     *
     * @param dateTime 格式yyyy-MM-dd HH:mm:ss
     * @return 格式yyyy-MM-dd HH:mm:ss
     * @throws ParseException
     */
    @SuppressWarnings("static-access")
    public static String beforeSevenDayTime(String dateTime) throws ParseException {
        Date date = parseToDate(dateTime, YYYYMMDDHHMMSS_DATE_PATTERN);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -6);
        date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(YYYYMMDDHHMMSS_DATE_PATTERN);
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 获取某个时间30天前的时间
     *
     * @param dateTime 格式yyyy-MM-dd HH:mm:ss
     * @return
     * @throws ParseException
     */
    @SuppressWarnings("static-access")
    public static String before30DayTime(String dateTime) throws ParseException {
        Date date = parseToDate(dateTime, YYYYMMDDHHMMSS_DATE_PATTERN);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -29);
        date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(YYYYMMDDHHMMSS_DATE_PATTERN);
        String dateString = formatter.format(date);
        return dateString;
    }

    /***
     * 获取距离date，time天的日期
     *
     * @param dateStr
     * @param time
     * @return
     */
    public static String getBeforeDate(String dateStr, int time) {
        Date date = null;
        try {
            date = parseToDate(dateStr, YYYYMMDD_DATE_PATTERN3);
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
        }
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        time = -time;
        calendar.add(Calendar.DATE, time);
        date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(YYYYMMDD_DATE_PATTERN3);
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 计算两个时间差距多少秒
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     * @throws ParseException
     */
    public static long timeDiffSecond(String startTime, String endTime) throws ParseException {
        Date begin = parseToDate(startTime, YYYYMMDDHHMMSS_DATE_PATTERN);
        Date end = parseToDate(endTime, YYYYMMDDHHMMSS_DATE_PATTERN);
        long gap = (end.getTime() - begin.getTime()) / 1000;
        return gap;
    }

    /**
     * 计算两个时间差，差为几天
     *
     * @param startTime 开始时间 格式yyyy-MM-dd HH:mm:ss
     * @param endTime   结束时间 格式yyyy-MM-dd HH:mm:ss
     * @return
     * @throws ParseException
     */
    public static long timeDiffDay(String startTime, String endTime) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(YYYYMMDDHHMMSS_DATE_PATTERN);
        long startTimeMill = format.parse(startTime).getTime();
        long endTimeMill = format.parse(endTime).getTime();
        long diff = Math.abs(endTimeMill - startTimeMill);
        long diffDay = diff / (1000 * 24 * 60 * 60);
        return diffDay;
    }

    /**
     * 计算两个时间差，差为几天
     *
     * @return
     * @throws ParseException
     */
    public static long timeDiffDay(Date startTime, Date endTime) {
        long startTimeMill = startTime.getTime();
        long endTimeMill = endTime.getTime();
        long diff = Math.abs(endTimeMill - startTimeMill);
        long diffDay = diff / (1000 * 24 * 60 * 60);
        return diffDay;
    }

    /**
     * 计算两个时间差，差为几小时
     *
     * @param startTime 开始时间 格式yyyy-MM-dd HH:mm:ss
     * @param endTime   结束时间 格式yyyy-MM-dd HH:mm:ss
     * @return
     * @throws ParseException
     */
    public static long timeDiffHour(String startTime, String endTime) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(YYYYMMDDHHMMSS_DATE_PATTERN);
        long startTimeMill = format.parse(startTime).getTime();
        long endTimeMill = format.parse(endTime).getTime();
        long diff = Math.abs(endTimeMill - startTimeMill);
        long diffHour = diff / (1000 * 60 * 60);
        return diffHour;
    }

    public static String getyyyymmddByDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(YYYYMMDD_DATE_PATTERN2);
        return format.format(date);
    }

    /**
     * 获取距离某一天的几天后的日期 yyyy-MM-dd 23:59:59
     *
     * @param date
     * @param day
     * @return
     */
    public static Date getAfterDayEnd(String date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getDateEnd(date));
        calendar.add(Calendar.DAY_OF_YEAR, day);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 本周的第一天
     */
    public static Date getFirstDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 转化成指定的日期格式
     *
     * @param date
     * @param pattern
     * @return
     * @throws ParseException
     * @author zhouxinlei
     * @date 2019/7/3
     */
    public static Date formatDate(Date date, String pattern){
        try {
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(pattern);
            String dateStr = simpleDateFormat1.format(date);
            Date sourceDate = simpleDateFormat1.parse(dateStr);
            return sourceDate;
        } catch (ParseException e) {
            e.printStackTrace();
            return date;
        }
    }

    /**
     * 判断是否是前天->昨天->今天
     *
     * @param oldTime
     * @param newTime
     * @return java.lang.String -1 ：同一天.  0：昨天 .  1 ：至少是前天.
     * @author zhouxinlei
     * @date 2019/7/3
     */
    public static int isYeaterday(Date oldTime, Date newTime){
        if (newTime == null) {
            newTime = new Date();
        }
        //将下面的 理解成  yyyy-MM-dd 00：00：00 更好理解点
        Date today = formatDate(newTime, "yyyy-MM-dd");
        Date sourceDate = formatDate(oldTime, "yyyy-MM-dd HH:mm:sss");
        //昨天 86400000=24*60*60*1000 一天
        if ((today.getTime() - sourceDate.getTime()) > 0 && (today.getTime() - sourceDate.getTime()) <= 86400000) {
            return 0;
        } else if ((today.getTime() - sourceDate.getTime()) <= 0) { //至少是今天
            return -1;
        } else { //至少是前天
            return 1;
        }

    }

    /**
     * 判断日期是否在本周
     *
     * @param date
     * @return boolean
     * @author zhouxinlei
     * @date 2019/6/20
     */
    public static boolean isThisWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        calendar.setTime(date);
        int paramWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        if (paramWeek == currentWeek) {
            return true;
        }
        return false;
    }

    /**
     * 获取上周的第一天开始Date
     */
    public static Date getPreWeekStartDate() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, 2);
        c.add(Calendar.DATE, -7);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * 获取上周的最后一天
     *
     * @return
     */
    public static Date getPreWeekEndDate() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, 2);
        c.add(Calendar.DATE, -1);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTime();
    }

    /**
     * 获取本月的第一天
     *
     * @return
     */
    public static Date getPreMonthStartDate() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.getActualMinimum(Calendar.DAY_OF_MONTH), 0, 0, 0);
        return c.getTime();
    }

    /**
     * 获取本月的最后一天
     *
     * @return
     */
    public static Date getPreMonthEndDate() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.getActualMaximum(Calendar.DAY_OF_MONTH), 23, 59, 59);
        return c.getTime();
    }


	/**
	 * 获取年份
	 *
	 * @param date
	 * @return java.lang.String
	 * @author zhouxinlei
	 * @date 2019/6/20
	 */
	public static String getYear(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		return sdf.format(date);
	}

    /**
     * 获取年份
     *
     * @return java.lang.String
     * @author zhouxinlei
     * @date 2019/6/20
     */
    public static int getNowMonths() {
        Calendar n = Calendar.getInstance();
        int month = n.get(Calendar.MONTH)+1;
        return month;
    }

	/**
	 * 判断日期是否在今年
	 *
	 * @param date
	 * @return boolean
	 * @author zhouxinlei
	 * @date 2019/6/20
	 */
	public static boolean judgeNowYear(Date date) {
		Calendar now = Calendar.getInstance();
		int a = now.get(Calendar.YEAR);
		if (a == Integer.valueOf(getYear(date))) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 日期转string
	 *
	 * @param date
	 * @param pattern
	 * @return java.lang.String
	 * @author zhouxinlei
	 * @date 2019/6/20
	 */
	public static String dateToString(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

}
