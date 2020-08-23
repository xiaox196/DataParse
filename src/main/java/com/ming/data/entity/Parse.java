package com.ming.data.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author alun
 * @data 2019/10/23
 */
@Data
public class Parse implements Serializable {
    @Excel(name = "名称")
    private String name;
    @Excel(name = "时间")
    String date;
    @Excel(name="早买")
    private int amBuy;
    @Excel(name="早卖")
    private int amSell;
    @Excel(name="早紧买")
    private int aspBuy;
    @Excel(name="早紧卖")
    private int aspSell;
    @Excel(name="午买")
    private int pmBuy;
    @Excel(name="午卖")
    private int pmSell;
    @Excel(name="午紧买")
    private int spBuy;
    @Excel(name="午紧卖")
    private int spSell;
    @Excel(name="总买")
    private int totalBuy;
    @Excel(name="总卖")
    private int totalSell;
    private String state;
    @Excel(name = "波动")
    String range;
    @Excel(name = "大资卖")
    public int sellBigCount;
    @Excel(name="大资买")
    public int buyBigCount;
    @Excel(name="五总买")
    private int later5DayBuy;
    @Excel(name="五总卖")
    private int later5DaySell;

}
