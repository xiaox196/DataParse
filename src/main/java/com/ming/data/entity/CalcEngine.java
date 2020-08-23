package com.ming.data.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * @author alun
 * @data 2020/5/8
 */
@Data
public class CalcEngine {
    Object result;
    @Excel(name = "名称")
    String name;
    @Excel(name="日期")
    String date;
    @Excel(name = "总买大")
    boolean buyDivideSellMultiple;
    @Excel(name = "特别买大")
    boolean spLage;
}
