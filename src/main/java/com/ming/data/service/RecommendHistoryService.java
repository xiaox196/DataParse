package com.ming.data.service;


import com.ming.data.entity.Recommendhistory;

import java.util.List;

/**
 * @author alun
 * @data 2020/4/13
 */
public interface RecommendHistoryService {
    int deleteByPrimaryKey(Integer id);

    int insert(Recommendhistory record);

    int insertSelective(Recommendhistory record);

    Recommendhistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Recommendhistory record);

    int updateByPrimaryKey(Recommendhistory record);
    List<Recommendhistory> findByNameAndCreate(String name,
                                               String createTime);
    List<Recommendhistory> findRecommends();
}
