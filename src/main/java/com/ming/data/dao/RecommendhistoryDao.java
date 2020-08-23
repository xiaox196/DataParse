package com.ming.data.dao;

import com.ming.data.entity.Recommendhistory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecommendhistoryDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Recommendhistory record);

    int insertSelective(Recommendhistory record);

    Recommendhistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Recommendhistory record);

    int updateByPrimaryKey(Recommendhistory record);
    List<Recommendhistory> findRecommends();
    List<Recommendhistory> findByNameAndCreate(@Param("name") String name,
                                               @Param("createTime")String createTime);

}