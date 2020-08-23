package com.ming.data.dao;

import com.ming.data.entity.Groups;

import java.util.List;

public interface GroupsDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Groups record);

    int insertSelective(Groups record);

    Groups selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Groups record);

    int updateByPrimaryKey(Groups record);

    List<Groups> findAll();
}