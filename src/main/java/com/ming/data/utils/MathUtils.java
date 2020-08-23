package com.ming.data.utils;

import java.text.DecimalFormat;

/**
 * @author alun
 * @data 2019/5/13
 */
public class MathUtils {
    public static String NumberFormat(double b,double a){
        DecimalFormat df = new DecimalFormat("0.00%");
        double c=(a-b)/b;
        return df.format(c);
    }

    public static void main(String[] args) {
        System.out.println(NumberFormat(1.1,1.13));
    }
}
