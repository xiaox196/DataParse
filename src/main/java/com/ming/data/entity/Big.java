package com.ming.data.entity;

/**
 * @author alun
 * @data 2020/1/6
 */
public class Big {

    public String name;
    public int sellCount;
    public int buyCount;
    public String date;

    public String getName() {
        return name;
    }

    public void setSellCount(int sellCount) {
        this.sellCount = sellCount;
    }

    public void setBuyCount(int buyCount) {
        this.buyCount = buyCount;
    }

    public int getSellCount() {
        return sellCount;
    }

    public int getBuyCount() {
        return buyCount;
    }

    public String getDate() {
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setDate(String date) {
        this.date = date;
    }
}
