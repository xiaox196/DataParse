package com.ming.data.utils;

import java.net.URLEncoder;

/**
 * @author alun
 * @data 2019/1/15
 */
public class PushBear {
    public static String url="https://pushbear.ftqq.com/sub";
    public static String sendkey="8514-87972ec6bbb9067033bbb7c61892d88c";

    public  String returnUrl(String text,String desp){
        return url+"?text="+text+"&sendkey="+sendkey+"&desp="+URLEncoder.encode(desp);
    }
}
