package com.ming.data.utils;

import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    private static final Logger LOG = Logger.getLogger(StringUtil.class);
    /**
     * unicode编码转为字符串
     * @param str unicode编码
     * @return String 编码后的字符串
     */
    public static String unicodeToString(String str) {
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            str = str.replace(matcher.group(1), ch + "");
        }
        return str;
    }

    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public  static double toDouble(String number){
        try {
            if(!isEmpty(number)){
                return Double.parseDouble(number);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public  static int toInt(String number){
        try {
            if(!isEmpty(number)){
                return Integer.parseInt(number);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 字符串转化为浮点数Float，默认值为0（如转化出错，返回默认值）
     * @param digitalValue 数值字符串
     * @return  Float 浮点数
     */
    public static float toFloat(String digitalValue) {
        return toFloat(digitalValue, 0);
    }

    /**
     * 字符串转化为浮点数Float且指定默认值（如转化出错，返回默认值）
     * @param digitalValue 数值字符串
     * @return  Float 浮点数
     */
    public static float toFloat(String digitalValue, float defaultValue) {
        float result = defaultValue;
        try {
            if (!isEmpty(digitalValue)) {
                result = Float.parseFloat(digitalValue);
            }
        } catch (Exception e) {
            LOG.warn("exception: " + e.getMessage());
        }
        return result;
    }

    /**
     * 判断字符串是否为空
     * @param str 字符串
     * @return 判断结果
     */
    public static boolean isEmpty(String str) {
        if (str == null || str.trim().length() <= 0) {
            return true;
        }
        return false;
    }
}
