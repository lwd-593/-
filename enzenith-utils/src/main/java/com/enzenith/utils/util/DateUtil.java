package com.enzenith.utils.util;


import com.enzenith.UtilsConstant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author:Neptune
 * @Description:DateUtil 提供一些常用的时间想法的方法
 */
public final class DateUtil {


    //注意SimpleDateFormat不是线程安全的
    private static ThreadLocal<SimpleDateFormat> ThreadDateTime = new ThreadLocal<SimpleDateFormat>();
    private static ThreadLocal<SimpleDateFormat> ThreadDate = new ThreadLocal<SimpleDateFormat>();
    private static ThreadLocal<SimpleDateFormat> ThreadTime = new ThreadLocal<SimpleDateFormat>();

    private static SimpleDateFormat DateTimeInstance() {
        SimpleDateFormat df = ThreadDateTime.get();
        if (df == null) {
            df = new SimpleDateFormat(UtilsConstant.DATETIME_FORMAT);
            ThreadDateTime.set(df);
        }
        return df;
    }

    private static SimpleDateFormat DateInstance() {
        SimpleDateFormat df = ThreadDate.get();
        if (df == null) {
            df = new SimpleDateFormat(UtilsConstant.DATE_FORMAT);
            ThreadDate.set(df);
        }
        return df;
    }

    private static SimpleDateFormat TimeInstance() {
        SimpleDateFormat df = ThreadTime.get();
        if (df == null) {
            df = new SimpleDateFormat(UtilsConstant.TIME_FORMAT);
            ThreadTime.set(df);
        }
        return df;
    }



    /**
     * 获取当前日期时间
     * @return: java.lang.String    返回当前时间的字符串值
     * @author: Shupeng Lin
     * @date: 2019-11-13  下午 4:52
     **/
    public static String currentDateTime() {
        return DateTimeInstance().format(new Date());
    }


    /**
     * 将指定的时间格式化成出返回
     * @param date  时间对象
     * @return: java.lang.String    返回时间格式字符串
     * @author: Shupeng Lin
     * @date: 2019-11-13  下午 4:53
     **/
    public static String dateTime(Date date) {
        return DateTimeInstance().format(date);
    }


    /**
     * 将指定的字符串解析为时间类型
     * @param dateStr      时间格式的字符串
     * @return: java.util.Date  返回时间
     * @author: Shupeng Lin
     * @throws ParseException
     * @date: 2019-11-13  下午 4:53
     **/
    public static Date dateTime(String dateStr) throws ParseException {
        return DateTimeInstance().parse(dateStr);
    }


    /**
     * 获取当前的日期
     * @return: java.lang.String    返回当前时间
     * @author: Shupeng Lin
     * @date: 2019-11-13  下午 4:54
     **/
    public static String currentDate() {
        return DateInstance().format(new Date());
    }

    /**
     * 将指定的时间格式化成出返回
     * @param date      时间对象
     * @return: java.lang.String
     * @author: Shupeng Lin
     * @date: 2019-11-13  下午 4:54
     **/
    public static String date(Date date) {
        return DateInstance().format(date);
    }


    /**
     * 将指定的字符串解析为时间类型
     * @param dateStr       时间格式字符串
     * @return: java.util.Date
     * @author: Shupeng Lin
     * @date: 2019-11-13  下午 4:55
     **/
    public static Date date(String dateStr) throws ParseException {
        return DateInstance().parse(dateStr);
    }


    /**
     * 获取当前的时间
     * @return: java.lang.String
     * @author: Shupeng Lin
     * @date: 2019-11-13  下午 4:56
     **/
    public static String currentTime() {
        return TimeInstance().format(new Date());
    }


    /**
     * 定的时间格式化成出返回
     * @param date      时间对象
     * @return: java.lang.String
     * @author: Shupeng Lin
     * @date: 2019-11-13  下午 4:56
     **/
    public static String time(Date date) {
        return TimeInstance().format(date);
    }


    /**
     * 将指定的字符串解析为时间类型
     * @param dateStr       时间格式字符串
     * @return: java.util.Date
     * @author: Shupeng Lin
     * @date: 2019-11-13  下午 4:56
     **/
    public static Date time(String dateStr) throws ParseException {
        return TimeInstance().parse(dateStr);
    }



    /**
     * 在当前时间的基础上加或减去year年
     * @param year      几年
     * @return: java.util.Date
     * @author: Shupeng Lin
     * @date: 2019-11-13  下午 4:57
     **/
    public static Date year(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, year);
        return calendar.getTime();
    }


    /**
     *  在指定的时间上加或减去几年
     * @param date      时间
     * @param year      几年
     * @return: java.util.Date
     * @author: Shupeng Lin
     * @date: 2019-11-13  下午 4:57
     **/
    public static Date year(Date date, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, year);
        return calendar.getTime();
    }


    /**
     * 在当前时间的基础上加或减去几月
     * @param month     几月
     * @return: java.util.Date
     * @author: Shupeng Lin
     * @date: 2019-11-13  下午 4:58
     **/
    public static Date month(int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    /**
     * 在指定的时间上加或减去几月
     * @param date      指定时间
     * @param month     几个月
     * @return: java.util.Date
     * @author: Shupeng Lin
     * @date: 2019-11-13  下午 4:58
     **/
    public static Date month(Date date, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }


    /**
     * 在当前时间的基础上加或减去几天
     * @param day       天数
     * @return: java.util.Date
     * @author: Shupeng Lin
     * @date: 2019-11-13  下午 4:59
     **/
    public static Date day(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, day);
        return calendar.getTime();
    }


    /**
     * 在指定的时间上加或减去几天
     * @param date      日期
     * @param day       天数
     * @return: java.util.Date
     * @author: Shupeng Lin
     * @date: 2019-11-13  下午 4:59
     **/
    public static Date day(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, day);
        return calendar.getTime();
    }


    /**
     *  在当前时间的基础上加或减去几小时-支持浮点数
     * @param hour      几小时
     * @return: java.util.Date
     * @author: Shupeng Lin
     * @date: 2019-11-13  下午 5:00
     **/
    public static Date hour(float hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, (int) (hour * 60));
        return calendar.getTime();
    }


    /**
     * 在制定的时间上加或减去几小时-支持浮点数
     * @param date      日期
     * @param hour      几小时
     * @return: java.util.Date
     * @author: Shupeng Lin
     * @date: 2019-11-13  下午 5:00
     **/
    public static Date hour(Date date, float hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, (int) (hour * 60));
        return calendar.getTime();
    }

    /**
     * 在当前时间的基础上加或减去几分钟
     * @param minute        几分钟
     * @return: java.util.Date
     * @author: Shupeng Lin
     * @date: 2019-11-13  下午 5:00
     **/
    public static Date minute(int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }


    /**
     * 在制定的时间上加或减去几分钟
     * @param date      日期
     * @param minute    加或减去几分钟
     * @return: java.util.Date
     * @author: Shupeng Lin
     * @date: 2019-11-13  下午 5:01
     **/
    public static Date minute(Date date, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }



    /**
     * 判断字符串是否为日期字符串
     * @param date  日期字符串
     * @return: boolean
     * @author: Shupeng Lin
     * @date: 2019-11-13  下午 5:02
     **/
    public static boolean isDate(String date) {
        try {
            DateTimeInstance().parse(date);
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }



    /**
     * 时间date1和date2的时间差-单位秒
     * @param date1     时间1
     * @param date2     时间2
     * @return: long
     * @author: Shupeng Lin
     * @date: 2019-11-13  下午 5:02
     **/
    public static long subtract(Date date1, Date date2) {
        long cha = (date2.getTime() - date1.getTime()) / 1000;
        return cha;
    }


    /**
     * 时间date1和date2的时间差-单位秒
     * @param date1     时间1
     * @param date2     时间2
     * @return: long
     * @author: Shupeng Lin
     * @date: 2019-11-13  下午 5:03
     **/
    public static long subtract(String date1, String date2) {
        long rs = 0;
        try {
            Date start = DateTimeInstance().parse(date1);
            Date end = DateTimeInstance().parse(date2);
            long cha = (end.getTime() - start.getTime()) / 1000;
            rs = cha;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs;
    }



    /**
     *  时间date1和date2的时间差 -单位分钟
     * @param date1     时间1
     * @param date2     时间2
     * @return: int
     * @author: Shupeng Lin
     * @date: 2019-11-13  下午 5:03
     **/
    public static int subtractMinute(String date1, String date2) {
        int rs = 0;
        try {
            Date start = DateTimeInstance().parse(date1);
            Date end = DateTimeInstance().parse(date2);
            long cha = (end.getTime() - start.getTime()) / 1000;
            rs = (int) cha / (60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs;
    }


    /**
     *  时间date1和date2的时间差-单位分钟
     * @param date1     时间1
     * @param date2     时间2
     * @return: int
     * @author: Shupeng Lin
     * @date: 2019-11-13  下午 5:03
     **/
    public static int subtractMinute(Date date1, Date date2) {
        long cha = date2.getTime() - date1.getTime();
        return (int) cha / (1000 * 60);
    }

    /**
     *   时间date1和date2的时间差-单位小时
     * @param date1     时间1
     * @param date2     时间2
     * @return: int
     * @author: Shupeng Lin
     * @date: 2019-11-13  下午 5:03
     **/
    public static int subtractHour(Date date1, Date date2) {
        long cha = (date2.getTime() - date1.getTime()) / 1000;
        return (int) cha / (60 * 60);
    }

    /**
     *  时间date1和date2的时间差-单位小时
     * @param date1     时间1
     * @param date2     时间2
     * @return: int
     * @author: Shupeng Lin
     * @date: 2019-11-13  下午 5:03
     **/
    public static int subtractHour(String date1, String date2) {
        int rs = 0;
        try {
            Date start = DateTimeInstance().parse(date1);
            Date end = DateTimeInstance().parse(date2);
            long cha = (end.getTime() - start.getTime()) / 1000;
            rs = (int) cha / (60 * 60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs;
    }


    /**
     * 时间date1和date2的时间差-单位天
     * @param date1     时间1
     * @param date2     时间2
     * @return: int
     * @author: Shupeng Lin
     * @date: 2019-11-13  下午 5:03
     **/
    public static int subtractDay(String date1, String date2) {
        int rs = 0;
        try {
            Date start = DateTimeInstance().parse(date1);
            Date end = DateTimeInstance().parse(date2);
            long sss = (end.getTime() - start.getTime()) / 1000;
            rs = (int) sss / (60 * 60 * 24);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs;
    }


    /**
     * 时间date1和date2的时间差-单位天
     * @param date1     时间1
     * @param date2     时间2
     * @return: int
     * @author: Shupeng Lin
     * @date: 2019-11-13  下午 5:03
     **/
    public static int subtractDay(Date date1, Date date2) {
        long cha = date2.getTime() - date1.getTime();
        return (int) cha / (1000 * 60 * 60 * 24);
    }

    /**
     * 时间date1和date2的时间差-单位月
     * @param date1     时间1
     * @param date2     时间2
     * @return: int
     * @author: Shupeng Lin
     * @date: 2019-11-13  下午 5:03
     **/
    public static int subtractMonth(String date1, String date2) {
        int result;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(DateInstance().parse(date1));
            c2.setTime(DateInstance().parse(date2));
            int year1 = c1.get(Calendar.YEAR);
            int month1 = c1.get(Calendar.MONTH);
            int year2 = c2.get(Calendar.YEAR);
            int month2 = c2.get(Calendar.MONTH);
            if (year1 == year2) {
                result = month2 - month1;
            } else {
                result = 12 * (year2 - year1) + month2 - month1;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            result = -1;
        }
        return result;
    }


    /**
     * 时间date1和date2的时间差-单位月
     * @param date1     时间1
     * @param date2     时间2
     * @return: int
     * @author: Shupeng Lin
     * @date: 2019-11-13  下午 5:03
     **/
    public static int subtractMonth(Date date1, Date date2) {
        int result;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);
        int year1 = c1.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH);
        int year2 = c2.get(Calendar.YEAR);
        int month2 = c2.get(Calendar.MONTH);
        if (year1 == year2) {
            result = month2 - month1;
        } else {
            result = 12 * (year2 - year1) + month2 - month1;
        }
        return result;
    }


    /**
     *  时间date1和date2的时间差-单位年
     * @param date1     时间1
     * @param date2     时间2
     * @return: int
     * @author: Shupeng Lin
     * @date: 2019-11-13  下午 5:03
     **/
    public static int subtractYear(String date1, String date2) {
        int result;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(DateInstance().parse(date1));
            c2.setTime(DateInstance().parse(date2));
            int year1 = c1.get(Calendar.YEAR);
            int year2 = c2.get(Calendar.YEAR);
            result = year2 - year1;
        } catch (ParseException e) {
            e.printStackTrace();
            result = -1;
        }
        return result;
    }

    /**
     *  时间date1和date2的时间差-单位年
     * @param date1
     * @param date2
     * @return: int
     * @author: Shupeng Lin
     * @date: 2019-11-13  下午 5:06
     **/
    public static int subtractYear(Date date1, Date date2) {
        int result;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        result = year2 - year1;
        return result;
    }

    /**
     * 获取俩个时间的查结果用时秒表示
     *
     * @param date1
     * @param date2
     * @return 几小时:几分钟:几秒钟
     * @Summary:此处可以讲计算结果包装成一个结构体返回便于格式化
     */

    public static String subtractTime(String date1, String date2) {
        String result = "";
        try {
            Date start = DateTimeInstance().parse(date1);
            Date end = DateTimeInstance().parse(date2);
            long sss = (end.getTime() - start.getTime()) / 1000;
            int hh = (int) sss / (60 * 60);
            int mm = (int) (sss - hh * 60 * 60) / (60);
            int ss = (int) (sss - hh * 60 * 60 - mm * 60);
            result = hh + ":" + mm + ":" + ss;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取俩个时间的查结果用时秒表示
     *
     * @param date1
     * @param date2
     * @return 几天-几小时:几分钟:几秒钟
     * @Summary:此处可以讲计算结果包装成一个结构体返回便于格式化
     */
    public static String subtractDate(String date1, String date2) {
        String result = "";
        try {
            Date start = DateTimeInstance().parse(date1);
            Date end = DateTimeInstance().parse(date2);
            long sss = (end.getTime() - start.getTime()) / 1000;
            int dd = (int) sss / (60 * 60 * 24);
            int hh = (int) (sss - dd * 60 * 60 * 24) / (60 * 60);
            int mm = (int) (sss - dd * 60 * 60 * 24 - hh * 60 * 60) / (60);
            int ss = (int) (sss - dd * 60 * 60 * 24 - hh * 60 * 60 - mm * 60);
            result = dd + "-" + hh + ":" + mm + ":" + ss;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取俩个时间之前的相隔的天数
     *
     * @param startTime
     * @param endTime
     * @return
     * @throws ParseException
     */
    public static int subDay(Date startTime, Date endTime) {
        int days = 0;
        Calendar can1 = Calendar.getInstance();
        can1.setTime(startTime);
        Calendar can2 = Calendar.getInstance();
        can2.setTime(endTime);
        int year1 = can1.get(Calendar.YEAR);
        int year2 = can2.get(Calendar.YEAR);

        Calendar can = null;
        if (can1.before(can2)) {
            days -= can1.get(Calendar.DAY_OF_YEAR);
            days += can2.get(Calendar.DAY_OF_YEAR);
            can = can1;
        } else {
            days -= can2.get(Calendar.DAY_OF_YEAR);
            days += can1.get(Calendar.DAY_OF_YEAR);
            can = can2;
        }
        for (int i = 0; i < Math.abs(year2 - year1); i++) {
            days += can.getActualMaximum(Calendar.DAY_OF_YEAR);
            can.add(Calendar.YEAR, 1);
        }

        return days;
    }

    /**
     * 获取俩个时间之前的相隔的天数
     *
     * @param startTime
     * @param endTime
     * @return
     * @throws ParseException
     */
    public static int subDay(String startTime, String endTime) {
        int days = 0;
        try {
            Date date1 = DateInstance().parse(DateInstance().format(DateTimeInstance().parse(startTime)));
            Date date2 = DateInstance().parse(DateInstance().format(DateTimeInstance().parse(endTime)));
            Calendar can1 = Calendar.getInstance();
            can1.setTime(date1);
            Calendar can2 = Calendar.getInstance();
            can2.setTime(date2);
            int year1 = can1.get(Calendar.YEAR);
            int year2 = can2.get(Calendar.YEAR);

            Calendar can = null;
            if (can1.before(can2)) {
                days -= can1.get(Calendar.DAY_OF_YEAR);
                days += can2.get(Calendar.DAY_OF_YEAR);
                can = can1;
            } else {
                days -= can2.get(Calendar.DAY_OF_YEAR);
                days += can1.get(Calendar.DAY_OF_YEAR);
                can = can2;
            }
            for (int i = 0; i < Math.abs(year2 - year1); i++) {
                days += can.getActualMaximum(Calendar.DAY_OF_YEAR);
                can.add(Calendar.YEAR, 1);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }

    /**
     * 返回俩个时间在时间段(例如每天的08:00-18:00)的时长-单位秒
     *
     * @param startDate
     * @param endDate
     * @param timeBurst 只就按该时间段内的08:00-18:00时长
     * @return 计算后的秒数
     * @throws ParseException
     * @summary 格式错误返回0
     */
    public static long subtimeBurst(String startDate, String endDate, String timeBurst)
            throws ParseException {
        Date start = DateTimeInstance().parse(startDate);
        Date end = DateTimeInstance().parse(endDate);
        return subtimeBurst(start, end, timeBurst);
    }

    /**
     * 返回俩个时间在时间段(例如每天的08:00-18:00)的时长-单位秒
     *
     * @param startDate
     * @param endDate
     * @param timeBurst 只就按该时间段内的08:00-18:00时长
     * @return 计算后的秒数
     * @throws ParseException
     */
    public static long subtimeBurst(Date startDate, Date endDate, String timeBurst)
            throws ParseException {
        long second = 0;
        Pattern p = Pattern.compile("^\\d{2}:\\d{2}-\\d{2}:\\d{2}");
        Matcher m = p.matcher(timeBurst);
        boolean falg = false;
        if (startDate.after(endDate)) {
            Date temp = startDate;
            startDate = endDate;
            endDate = temp;
            falg = true;
        }
        if (m.matches()) {
            String[] a = timeBurst.split("-");
            int day = subDay(startDate, endDate);
            if (day > 0) {
                long firstMintues = 0;
                long lastMintues = 0;
                long daySecond = 0;
                String strDayStart = DateInstance().format(startDate) + " " + a[0] + ":00";
                String strDayEnd = DateInstance().format(startDate) + " " + a[1] + ":00";
                Date dayStart = DateTimeInstance().parse(strDayStart);
                Date dayEnd = DateTimeInstance().parse(strDayEnd);
                daySecond = subtract(dayStart, dayEnd);
                if ((startDate.after(dayStart) || startDate.equals(dayStart))
                        && startDate.before(dayEnd)) {
                    firstMintues = (dayEnd.getTime() - startDate.getTime()) / 1000;
                } else if (startDate.before(dayStart)) {
                    firstMintues = (dayEnd.getTime() - dayStart.getTime()) / 1000;
                }
                dayStart = DateTimeInstance().parse(DateInstance().format(endDate) + " " + a[0] + ":00");
                dayEnd = DateTimeInstance().parse(DateInstance().format(endDate) + " " + a[1] + ":00");
                if (endDate.after(dayStart) && (endDate.before(dayEnd) || endDate.equals(dayEnd))) {
                    lastMintues = (endDate.getTime() - dayStart.getTime()) / 1000;
                } else if (endDate.after(dayEnd)) {
                    lastMintues = (dayEnd.getTime() - dayStart.getTime()) / 1000;
                }
                //第一天的秒数 + 最好一天的秒数 + 天数*全天的秒数
                second = firstMintues + lastMintues;
                second += (day - 1) * daySecond;
            } else {
                String strDayStart = DateInstance().format(startDate) + " " + a[0] + ":00";
                String strDayEnd = DateInstance().format(startDate) + " " + a[1] + ":00";
                Date dayStart = DateTimeInstance().parse(strDayStart);
                Date dayEnd = DateTimeInstance().parse(strDayEnd);
                if ((startDate.after(dayStart) || startDate.equals(dayStart))
                        && startDate.before(dayEnd) && endDate.after(dayStart)
                        && (endDate.before(dayEnd) || endDate.equals(dayEnd))) {
                    second = (endDate.getTime() - startDate.getTime()) / 1000;
                } else {
                    if (startDate.before(dayStart)) {
                        if (endDate.before(dayEnd)) {
                            second = (endDate.getTime() - dayStart.getTime()) / 1000;
                        } else {
                            second = (dayEnd.getTime() - dayStart.getTime()) / 1000;
                        }
                    }
                    if (startDate.after(dayStart)) {
                        if (endDate.before(dayEnd)) {
                            second = (endDate.getTime() - startDate.getTime()) / 1000;
                        } else {
                            second = (dayEnd.getTime() - startDate.getTime()) / 1000;
                        }
                    }
                }
                if ((startDate.before(dayStart) && endDate.before(dayStart))
                        || startDate.after(dayEnd) && endDate.after(dayEnd)) {
                    second = 0;
                }
            }
        } else {
            second = (endDate.getTime() - startDate.getTime()) / 1000;
        }
        if (falg) {
            second = Long.parseLong("-" + second);
        }
        return second;
    }

    /**
     * 时间Date在时间段(例如每天的08:00-18:00)上增加或减去second秒
     *
     * @param date
     * @param second
     * @param timeBurst
     * @return 计算后的时间
     * @Suumary 指定的格式错误后返回原数据
     */
    public static Date calculate(String date, int second, String timeBurst) {
        Date start = null;
        try {
            start = DateTimeInstance().parse(date);
            return calculate(start, second, timeBurst);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    /**
     * 时间Date在时间段(例如每天的08:00-18:00)上增加或减去second秒
     *
     * @param date
     * @param second
     * @param timeBurst
     * @return 计算后的时间
     * @Suumary 指定的格式错误后返回原数据
     */
    public static Date calculate(Date date, int second, String timeBurst) {
        Pattern p = Pattern.compile("^\\d{2}:\\d{2}-\\d{2}:\\d{2}");
        Matcher m = p.matcher(timeBurst);
        Calendar cal = Calendar.getInstance();
        if (m.matches()) {
            String[] a = timeBurst.split("-");
            try {
                Date dayStart = DateTimeInstance().parse(DateInstance().format(date) + " " + a[0] + ":00");
                Date dayEnd = DateTimeInstance().parse(DateInstance().format(date) + " " + a[1] + ":00");
                int daySecond = (int) subtract(dayStart, dayEnd);
                int toDaySecond = (int) subtract(dayStart, dayEnd);
                if (second >= 0) {
                    if ((date.after(dayStart) || date.equals(dayStart))
                            && (date.before(dayEnd) || date.equals(dayEnd))) {
                        cal.setTime(date);
                        toDaySecond = (int) subtract(date, dayEnd);
                    }
                    if (date.before(dayStart)) {
                        cal.setTime(dayStart);
                        toDaySecond = (int) subtract(dayStart, dayEnd);
                    }
                    if (date.after(dayEnd)) {
                        cal.setTime(day(dayStart, 1));
                        toDaySecond = 0;
                    }

                    if (second > toDaySecond) {
                        int day = (second - toDaySecond) / daySecond;
                        int remainder = (second - toDaySecond) % daySecond;
                        cal.setTime(day(dayStart, 1));
                        cal.add(Calendar.DAY_OF_YEAR, day);
                        cal.add(Calendar.SECOND, remainder);
                    } else {
                        cal.add(Calendar.SECOND, second);
                    }

                } else {
                    if ((date.after(dayStart) || date.equals(dayStart))
                            && (date.before(dayEnd) || date.equals(dayEnd))) {
                        cal.setTime(date);
                        toDaySecond = (int) subtract(date, dayStart);
                    }
                    if (date.before(dayStart)) {
                        cal.setTime(day(dayEnd, -1));
                        toDaySecond = 0;
                    }
                    if (date.after(dayEnd)) {
                        cal.setTime(dayEnd);
                        toDaySecond = (int) subtract(dayStart, dayEnd);
                    }
                    if (Math.abs(second) > Math.abs(toDaySecond)) {
                        int day = (Math.abs(second) - Math.abs(toDaySecond)) / daySecond;
                        int remainder = (Math.abs(second) - Math.abs(toDaySecond)) % daySecond;
                        cal.setTime(day(dayEnd, -1));
                        cal.add(Calendar.DAY_OF_YEAR, Integer.valueOf("-" + day));
                        cal.add(Calendar.SECOND, Integer.valueOf("-" + remainder));
                    } else {
                        cal.add(Calendar.SECOND, second);
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            cal.setTime(date);
        }
        return cal.getTime();
    }

    /**
     * 判断是否在某个时间段内
     *
     * @param startTime
     * @param endTime
     * @param date
     * @return
     * @throws ParseException
     */
    public static boolean between(String startTime, String endTime, Date date)
            throws ParseException {
        return between(dateTime(startTime), dateTime(endTime), date);
    }

    /**
     * 判断在某个时间内
     *
     * @param startTime
     * @param endTime
     * @param date
     * @return
     */
    public static boolean between(Date startTime, Date endTime, Date date) {
        return date.after(startTime) && date.before(endTime);
    }


    /**
     * 时间转换（LocalDateTime ==》 Date）
     * @param nowDayStart   当前时间
     * @return: java.util.Date
     * @author: Shupeng Lin
     * @date: 2019-11-27  上午 9:04
     **/
    public static Date localDateTime2Date(LocalDateTime nowDayStart) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = nowDayStart.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 时间转换（LocalDate ==》 Date）
     *
     * @param localDate
     * @return: java.util.Date
     * @author: Shupeng Lin
     * @date: 2020-01-06  下午 3:53
     **/
    public static Date localDate2Date(LocalDate localDate) {
        if(null == localDate) {
            return null;
        }
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * 时间转换（Date ==》 LocalDateTime）
     *
     * @param date
     * @return: java.time.LocalDateTime
     * @author: Shupeng Lin
     * @date: 2020-01-06  下午 4:07
     **/
    public static LocalDateTime date2LocalDateTime(Date date){
        Instant instant = date.toInstant();//An instantaneous point on the time-line.(时间线上的一个瞬时点。)
        ZoneId zoneId = ZoneId.systemDefault();//A time-zone ID, such as {@code Europe/Paris}.(时区)
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();

        return localDateTime;
    }

    /**
     * 时间转换（Date ==》 LocalDate）
     *
     * @param date
     * @return: java.time.LocalDateTime
     * @author: Shupeng Lin
     * @date: 2020-01-06  下午 4:07
     **/
    public static LocalDate date2LocalDate(Date date){
        Instant instant = date.toInstant();//An instantaneous point on the time-line.(时间线上的一个瞬时点。)
        ZoneId zoneId = ZoneId.systemDefault();//A time-zone ID, such as {@code Europe/Paris}.(时区)
        LocalDate localDate = instant.atZone(zoneId).toLocalDate();

        return localDate;
    }

    /**
     * 日期相隔天数
     *
     * @param startDateInclusive
     * @param endDateExclusive
     * @return
     */
    public static int periodDays(LocalDate startDateInclusive, LocalDate endDateExclusive) {
        return Period.between(startDateInclusive, endDateExclusive).getDays();
    }

    /**
     * 日期相隔小时
     *
     * @param startInclusive
     * @param endExclusive
     * @return
     */
    public static long durationHours(Temporal startInclusive, Temporal endExclusive) {
        return Duration.between(startInclusive, endExclusive).toHours();
    }

    /**
     * 日期相隔分钟
     *
     * @param startInclusive
     * @param endExclusive
     * @return
     */
    public static long durationMinutes(Temporal startInclusive, Temporal endExclusive) {
        return Duration.between(startInclusive, endExclusive).toMinutes();
    }

    /**
     * 日期相隔毫秒数
     *
     * @param startInclusive
     * @param endExclusive
     * @return
     */
    public static long durationMillis(Temporal startInclusive, Temporal endExclusive) {
        return Duration.between(startInclusive, endExclusive).toMillis();
    }

    /**
     * 字符串转成日期（年-月-日）
     *  字符串格式必须与需转格式一致！
     * @param timeStr      日期字符串
     * @param pattern      需转换的日期格式
     * @return: java.util.Date
     * @author: Shupeng Lin
     **/
    public static Date string2Date(String timeStr, String pattern){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        LocalDate parse = LocalDate.parse(timeStr, dateTimeFormatter);
        return  localDate2Date(parse);
    }

    /**
     * 字符串转成日期（年-月-日 时-分-秒）
     *  字符串格式必须与需转格式一致！
     * @param timeStr      日期字符串
     * @param pattern      需转换的日期格式
     * @return: java.util.Date
     * @author: Shupeng Lin
     **/
    public static Date string2DateTime(String timeStr, String pattern){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime parse = LocalDateTime.parse(timeStr, dateTimeFormatter);
        return  localDateTime2Date(parse);
    }
}

