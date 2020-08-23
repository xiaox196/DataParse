package com.ming.data.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.io.Serializable;

/**
 * parse_detail
 * @author 
 */
@Data
public class ParseDetail implements Serializable {
    private Integer id;
    @Excel(name = "名称")
    private String name;
    @Excel(name = "时间")
    private String time;
    @Excel(name="早买")
    private Integer amBuy;
    @Excel(name="早卖")
    private Integer amSell;
    @Excel(name="早紧买")
    private Integer aspBuy;
    @Excel(name="早紧卖")
    private Integer aspSell;
    @Excel(name="午买")
    private Integer pmBuy;
    @Excel(name="午卖")
    private Integer pmSell;
    @Excel(name="午紧买")
    private Integer spBuy;
    @Excel(name="午紧卖")
    private Integer spSell;
    @Excel(name="总买")
    private Integer totalBuy;
    @Excel(name="总卖")
    private Integer totalSell;
    @Excel(name = "波动")
    private String range;
    @Excel(name = "大资卖")
    private Integer sellbigcount;
    @Excel(name="大资买")
    private Integer buybigcount;
    @Excel(name="五总买")
    private Integer later5Buy=0;
    @Excel(name="五总卖")
    private Integer later5Sell=0;

    private Integer later10Buy=0;

    private Integer later10Sell=0;

    /**
     * 买除以卖的值，取小数点2位
     */
    @Excel(name="五除")
    private Double later5Ave;

    private static final long serialVersionUID = 1L;
}