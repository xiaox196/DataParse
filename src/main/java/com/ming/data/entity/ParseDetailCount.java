package com.ming.data.entity;

import lombok.Data;

/**
 * @author alun
 * @data 2020/6/11
 */

@Data
public class ParseDetailCount {
    String name;
    //买大于卖总量
    int buy_lager_sell_total;
    int sell_lager_buy_total;
    //卖大于卖命中规则量
    int sell_lager_buy_inTotal;
    int buy_lager_sell_inTotal;
    int total;
}
