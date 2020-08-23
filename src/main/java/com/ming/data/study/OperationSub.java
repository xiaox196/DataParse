package com.ming.data.study;

import com.ming.data.dao.Operation;

/**
 * @author alun
 * @data 2020/3/25
 */
public class OperationSub extends Operation {

    @Override
    public double getResult() {
        return number1-number2;
    }
}
