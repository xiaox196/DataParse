package com.ming.data.dao;

import com.ming.data.entity.User;

public interface UserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);
    User selectByUserAndPassword(User user);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}