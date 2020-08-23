package com.ming.data.service;

import com.ming.data.entity.Config;

import java.util.List;

/**
 * @author alun
 * @data 2020/1/14
 */


public interface ConfigService {
    int deleteByPrimaryKey(Integer id);

    int insert(Config record);

    int insertSelective(Config record);

    Config selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Config record);

    int updateByPrimaryKey(Config record);
    Config selectByKey(String key);
    List<Config> findAll();
}
