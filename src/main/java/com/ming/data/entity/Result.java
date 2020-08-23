package com.ming.data.entity;

import java.util.Map;

/**
 * @author alun
 * @data 2019/1/14
 */
public class Result {
    private int total;
    private Map<Integer,Integer> number;
    private Map<String,Integer> note;
    private Map<String,Integer> nc;
    private Map<String,Integer> hk;

    public void setTotal(int total) {
        this.total = total;
    }

    public void setNumber(Map<Integer, Integer> number) {
        this.number = number;
    }

    public void setNote(Map<String, Integer> note) {
        this.note = note;
    }

    public int getTotal() {
        return total;
    }

    public Map<Integer, Integer> getNumber() {
        return number;
    }

    public Map<String, Integer> getNote() {
        return note;
    }

    public Map<String, Integer> getNc() {
        return nc;
    }

    public Map<String, Integer> getHk() {
        return hk;
    }

    public void setNc(Map<String, Integer> nc) {
        this.nc = nc;
    }

    public void setHk(Map<String, Integer> hk) {
        this.hk = hk;
    }
}
