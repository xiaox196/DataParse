package com.ming.data.service.impl;

import com.ming.data.dao.ConfigDao;
import com.ming.data.entity.Config;
import com.ming.data.service.ConfigService;
import com.ming.data.utils.BaseCls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author alun
 * @data 2020/1/14
 */

@Service
public class ConfigServiceImpl extends BaseCls implements ConfigService {

    @Autowired
    ConfigDao configDao;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Config record) {
        return 0;
    }

    @Override
    public int insertSelective(Config record) {
        return 0;
    }

    @Override
    public Config selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Config record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Config record) {
        return 0;
    }

    @Override
    public Config selectByKey(String key) {
        return configDao.selectByKey(key);
    }

    @Override
    public List<Config> findAll() {
        return configDao.findAll();
    }
}
