package com.ming.data.dao;

import com.ming.data.entity.TestHome;

import java.util.List;

/**
 * @author alun
 * @data 2019/10/8
 */
public interface TestHomeDao {
    boolean save(TestHome testHome);
    List<TestHome> findAll();
    List<TestHome> findByUrl(String url);
}
