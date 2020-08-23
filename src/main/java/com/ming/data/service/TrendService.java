package com.ming.data.service;

import com.ming.data.entity.Trend;

import java.util.List;

/**
 * @author alun
 * @data 2018/12/28
 */
public interface TrendService {

    boolean save(Trend trend);
    Trend findByYearAndPeriods(String year, String periods);
    List<Trend> findAllByYear(String year);
}
