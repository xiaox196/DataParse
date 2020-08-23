package com.ming.data.service.impl;

import com.ming.data.dao.TrendDao;
import com.ming.data.entity.Trend;
import com.ming.data.service.TrendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author alun
 * @data 2018/12/28
 */

@Service
public class TrendServiceImpl implements TrendService {

    @Autowired
    TrendDao trendDao;

    @Override
    public boolean save(Trend trend) {
        return trendDao.save(trend);
    }

    @Override
    public Trend findByYearAndPeriods(String year, String periods) {
        return trendDao.findByYearAndPeriods(year,periods);
    }

    @Override
    public List<Trend> findAllByYear(String year) {
        return null;
    }
}
