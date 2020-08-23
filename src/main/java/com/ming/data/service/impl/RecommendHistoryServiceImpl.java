package com.ming.data.service.impl;

import com.ming.data.dao.RecommendhistoryDao;
import com.ming.data.entity.Recommendhistory;
import com.ming.data.service.RecommendHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author alun
 * @data 2020/4/14
 */
@Service
public class RecommendHistoryServiceImpl implements RecommendHistoryService {

    @Autowired
    RecommendhistoryDao recommendhistoryDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Recommendhistory record) {
        if(record==null){
            return 0;
        }
        return recommendhistoryDao.insert(record);
    }

    @Override
    public int insertSelective(Recommendhistory record) {
        return 0;
    }

    @Override
    public Recommendhistory selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Recommendhistory record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Recommendhistory record) {
        return 0;
    }

    @Override
    public List<Recommendhistory> findByNameAndCreate(String name, String createTime) {
        return recommendhistoryDao.findByNameAndCreate(name,createTime);
    }

    @Override
    public List<Recommendhistory> findRecommends() {
        return recommendhistoryDao.findRecommends();
    }
}
