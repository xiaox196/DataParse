package com.ming.data.service.impl;

import com.ming.data.dao.CodeConfigDao;
import com.ming.data.entity.CodeConfig;
import com.ming.data.service.CodeConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author alun
 * @data 2019/5/13
 */
@Service
public class CodeConfigServiceImpl implements CodeConfigService {


    @Autowired
    CodeConfigDao codeConfigDao;

    @Override
    public List<CodeConfig> find(int active) {
        return codeConfigDao.find(active);
    }

    @Override
    public boolean save(CodeConfig codeConfig) {
        String code=codeConfig.getCode();
        String name=codeConfig.getName().trim();
        if(code==null||name.equals("")){
            return false;
        }
        if(code.startsWith("00")){
            code="sz"+code;
        }else if(code.startsWith("60")){
            code="sh"+code;
        }else {
            return false;
        }
        codeConfig.setCode(code);
        return codeConfigDao.save(codeConfig);
    }

    @Override
    public List<CodeConfig> findEnable(int enable) {
        return codeConfigDao.findEnable(enable);
    }
}
