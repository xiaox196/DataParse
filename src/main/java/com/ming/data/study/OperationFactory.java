package com.ming.data.study;

import com.ming.data.dao.Operation;

/**
 * @author alun
 * @data 2020/3/25
 */
public class OperationFactory {

    public static Operation createOperation(String operation){
        Operation op=null;
        switch (operation){
            case ("+"):
                op=new OperationAdd();
                break;
            case ("-"):
                op=new OperationSub();
                break;
        }

        return op;
    }
}
