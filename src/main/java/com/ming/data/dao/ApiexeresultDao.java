package com.ming.data.dao;

import com.ming.data.entity.Apiexeresult;

public interface ApiexeresultDao {
    int insert(Apiexeresult record);

    int insertSelective(Apiexeresult record);
}