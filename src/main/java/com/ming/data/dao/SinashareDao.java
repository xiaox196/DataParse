package com.ming.data.dao;

import com.ming.data.entity.Sinashare;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SinashareDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Sinashare record);

    int insertSelective(Sinashare record);

    Sinashare selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Sinashare record);

    int updateByPrimaryKey(Sinashare record);

    List<Sinashare> findSellBigDetail(@Param("beginTime")String start, @Param("endTime")String end, String sellType, @Param("name")String name);
    List<Sinashare> findBuyBigDetail( @Param("beginTime")String start, @Param("endTime")String end, String type,@Param("name")String name);
    int findBuySumByTime(@Param("beginTime")String beginTime,@Param("endTime")String endTime,@Param("name")String name);
    int findSellSumByTime(@Param("beginTime")String beginTime,@Param("endTime")String endTime,@Param("name")String name);

    Sinashare findOne(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("name")String name);
    int findBuyBig(@Param("beginTime")String beginTime,@Param("endTime")String endTime,@Param("type")String type,@Param("name")String name);
    int findSellBig(@Param("beginTime")String beginTime,@Param("endTime")String endTime,@Param("sellType")String sellType,@Param("name")String name);
}