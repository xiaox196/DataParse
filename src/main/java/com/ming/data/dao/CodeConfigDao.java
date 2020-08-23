package com.ming.data.dao;

import com.ming.data.entity.CodeConfig;

import java.util.List;

/**
 * @author alun
 * @data 2019/5/13
 */
public interface CodeConfigDao{
    List<CodeConfig> find(int active);
    boolean save(CodeConfig codeConfig);
    List<CodeConfig> findEnable(int enable);
}
