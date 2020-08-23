package com.ming.data.study;


import java.text.DecimalFormat;

/**
 * @author alun
 * @data 2020/3/25
 */
public class MyTest {

    public static void main(String[] args) {
        DecimalFormat de = new DecimalFormat("0.00");
        float a=(float)21233/12343;
        System.out.println(Double.parseDouble(de.format(a)));
    }
}
