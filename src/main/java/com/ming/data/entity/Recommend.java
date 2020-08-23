package com.ming.data.entity;

import lombok.Data;

/**
 * @author alun
 * @data 2020/4/8
 */

@Data
public class Recommend {
    //卖大于买，并且range大于2
    boolean sellLargeBuyAndRangeL2=false;
    boolean amSellLeBuyAndAfterBuyLeSell=false;
    boolean spBuyLgSellTwo=false;
    Parse parse;
    String range;
    String name;
    DataEngine dataEngine;
}
