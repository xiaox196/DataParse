package com.ming.data.service;

import com.ming.data.entity.Api;
import com.ming.data.entity.Apiexeresult;

import java.util.List;

/**
 * @author alun
 * @data 2020/5/18
 */
public interface ApiService {
    int deleteByPrimaryKey(Integer id);

    int insert(Api record);

    int insertSelective(Api record);

    Api selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Api record);

    int updateByPrimaryKey(Api record);
    List<Api> selectByPath(String path);
    Apiexeresult apiExecute(Integer id);
    List<Api> findAll();
}
