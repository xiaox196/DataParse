package com.ming.data.service;

import com.ming.data.entity.Groups;
import com.ming.data.entity.GroupsItem;

import java.util.List;

/**
 * @author alun
 * @data 2020/5/18
 */
public interface GroupsService {
    int deleteByPrimaryKey(Integer id);

    int insert(Groups record);

    int insertSelective(Groups record);

    Groups selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Groups record);

    int updateByPrimaryKey(Groups record);
    List<GroupsItem> findAll();
}
