package com.ming.data.dao;


import com.ming.data.entity.Config;

import java.util.List;

public interface ConfigDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Config record);

    int insertSelective(Config record);

    Config selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Config record);

    int updateByPrimaryKey(Config record);
    Config selectByKey(String key);

    List<Config> findAll();
}