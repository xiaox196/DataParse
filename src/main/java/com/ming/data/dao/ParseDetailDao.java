package com.ming.data.dao;


import com.ming.data.entity.ParseDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ParseDetailDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ParseDetail record);

    int insertSelective(ParseDetail record);

    ParseDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ParseDetail record);

    int updateByPrimaryKey(ParseDetail record);

    List<ParseDetail> findAllByName(String name);
    ParseDetail selectByNameAndTime(@Param("name") String name, @Param("time")String time);

}