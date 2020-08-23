package com.ming.data.entity;

import lombok.Data;

/**
 * @author alun
 * @data 2020/4/20
 */
@Data
public class DataEngine {
    //range为负range波动数量
    public double range_negative_count = -1;
    //range为正range波动数量
    public double range_positive_count = -1;
    //modelC值计算得分
    public int modelC_value_score = -1;
    //最近五天totalB相加除以最近五天totalS的值
    public int totalB_divide_totalS_value = -1;
    public int bigB_calc_value_score = -1;
    //总分
    public double total=-1;

    public double getTotal(){
        total=range_negative_count+range_positive_count+modelC_value_score+bigB_calc_value_score;
        return total;
    }
}
