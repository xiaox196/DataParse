package com.ming.data.dao;

import com.ming.data.entity.Trend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author alun
 * @data 2018/12/28
 */
public interface TrendDao {
    boolean save(Trend trend);
    Trend findByYearAndPeriods(@Param("year")String year, @Param("periods")String periods);
    List<Trend> findAllByYear(String year);
}
