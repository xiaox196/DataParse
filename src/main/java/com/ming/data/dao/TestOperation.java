package com.ming.data.dao;

/**
 * @author alun
 * @data 2020/3/25
 */
public class TestOperation extends Operation {


    @Override
    public double getResult(){
        return number1+number2;
    }
}
