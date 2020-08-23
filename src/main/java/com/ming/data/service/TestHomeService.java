package com.ming.data.service;

import com.ming.data.entity.TestHome;

import java.util.List;

/**
 * @author alun
 * @data 2019/10/8
 */
public interface TestHomeService {
    List<TestHome> findByUrl(String url);
    boolean save(List<TestHome> testHome);
    List<TestHome> findAll();
    boolean saveOne(TestHome testHome);
}
