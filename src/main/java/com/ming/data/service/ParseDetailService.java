package com.ming.data.service;

import com.ming.data.entity.ParseDetail;
import com.ming.data.entity.ParseDetailCount;

import java.util.List;

/**
 * @author alun
 * @data 2020/4/30
 */
public interface ParseDetailService {
    int deleteByPrimaryKey(Integer id);

    int insert(ParseDetail record);

    int insertSelective(ParseDetail record);

    ParseDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ParseDetail record);

    int updateByPrimaryKey(ParseDetail record);

    List<ParseDetail> findAllByName(String name);
    ParseDetail selectByNameAndTime(String name,String time);
    List<ParseDetailCount> parse();
}
