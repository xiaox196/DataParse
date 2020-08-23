package com.ming.data.dao;

import com.ming.data.entity.Api;

import java.util.List;

public interface ApiDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Api record);

    int insertSelective(Api record);

    Api selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Api record);

    int updateByPrimaryKey(Api record);
    List<Api> selectByPath(String path);
    List<Api> findAll();
    List<Api> selectByGroupId(Integer id);
}