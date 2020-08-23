package com.ming.data.entity;

import java.io.Serializable;

/**
 * @author alun
 * @data 2019/5/13
 */
public class CodeConfig implements Serializable {
    private String code;
    private String name;
    private int intervalTime;
    //0不启用，1启用
    private int active;
    //0不启用，1启用
    private int enable;

    private double goal;

    public double getGoal() {
        return goal;
    }

    public void setGoal(double goal) {
        this.goal = goal;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getIntervalTime() {
        return intervalTime;
    }

    public int getActive() {
        return active;
    }
}
