package com.ming.data.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author alun
 * @data 2019/11/18
 */
public class TimeUtils {
    static Random random = new Random();
    static SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");

    private static ThreadLocal<SimpleDateFormat> t = ThreadLocal.withInitial(() -> new SimpleDateFormat("HH:mm:ss"));


    private  static SimpleDateFormat getSimpleDateFormat() {
        return t.get();
    }


    private static Date getDateAdd(int days) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -days);
        return c.getTime();
    }

//    当前时间为true，非当前为false
    public static boolean isCurrentTime(String time){
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            String currentTime=sf.format(new Date());
            if(time.equals(currentTime)){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    /**
     * 判断时间是否在时间段内
     *  在区间内返回true
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean belongCalendar(Date nowTime, String beginTime,
                                         String  endTime) {
        Date now = null;
        Date beginTimeB = null;
        Date endTimeB = null;
        try {
            now = getSimpleDateFormat().parse(getSimpleDateFormat().format(nowTime));
            beginTimeB = getSimpleDateFormat().parse(beginTime);
            endTimeB = getSimpleDateFormat().parse(endTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Calendar date = Calendar.getInstance();
        date.setTime(now);

        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTimeB);

        Calendar end = Calendar.getInstance();
        end.setTime(endTimeB);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    /*
        获取最近几天时间，休息日不计入
    */
    public static List<String> getDaysBetwwen(int days) {
        List<String> dayss = new ArrayList<>();
        Calendar start = Calendar.getInstance();
        start.setTime(getDateAdd(days));
        Long startTIme = start.getTimeInMillis();
        Calendar end = Calendar.getInstance();
        end.setTime(new Date());
        Long endTime = end.getTimeInMillis();
        Long oneDay = 1000 * 60 * 60 * 24L;
        Long time = startTIme;
        while (time <= endTime) {
            Date d = new Date(time);
            Calendar cal = Calendar.getInstance();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            cal.setTime(d);
            boolean isWeekend = false;
            isWeekend = cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
            String newDay = df.format(d);
            if (!isWeekend) {
                dayss.add(newDay);
            }
            time += oneDay;
        }
        return dayss;
    }

    public static boolean isWeek(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int time = calendar.get(Calendar.DAY_OF_WEEK);
        if (Calendar.SUNDAY == time || Calendar.SATURDAY == time) {
            return true;
        }
        return false;
    }


    public static boolean isWeekAndWorkTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int time = calendar.get(Calendar.DAY_OF_WEEK);
        if (Calendar.SUNDAY == time || Calendar.SATURDAY == time) {
            return true;
        }

        if (hour == 9 && minute < 25 || hour == 11 && minute > 30 || hour == 12) {
            return true;
        }
        return false;
    }

    public static String formatTime(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

    public static void main(String[] args) throws ParseException {
        isCurrentTime("");

    }
}

