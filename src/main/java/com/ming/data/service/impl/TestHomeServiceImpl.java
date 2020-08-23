package com.ming.data.service.impl;

import com.ming.data.dao.TestHomeDao;
import com.ming.data.entity.TestHome;
import com.ming.data.service.TestHomeService;
import com.ming.data.utils.CrawlingTestHome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author alun
 * @data 2019/10/8
 */

@Service
public class TestHomeServiceImpl implements TestHomeService {

    @Autowired
    TestHomeDao testHomeDao;

    @Override
    public List<TestHome> findByUrl(String url) {
        return null;
    }

    @Override
    public boolean save(List<TestHome> testHome) {
        try {
            for (TestHome entiy : testHome) {
                testHomeDao.save(entiy);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<TestHome> findAll() {
        return testHomeDao.findAll();
    }

    @Override
    public boolean saveOne(TestHome testHome) {
        try {
            testHomeDao.save(testHome);
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
