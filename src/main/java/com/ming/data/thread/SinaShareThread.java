package com.ming.data.thread;

import com.ming.data.entity.Parse;
import com.ming.data.service.impl.SinaShareServiceImpl;
import com.ming.data.utils.BaseCls;
import com.ming.data.utils.Config;
import com.ming.data.utils.TimeUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author alun
 * @data 2020/4/26
 */
@Slf4j
public class SinaShareThread extends BaseCls implements Callable<List<Parse>> {
    int count;
    String name;
    SinaShareServiceImpl sinaShareService;

    public SinaShareThread(int count, String name, SinaShareServiceImpl sinaShareService) {
        this.count = count;
        this.name = name;
        this.sinaShareService = sinaShareService;
    }

    @Override
    public List<Parse> call() throws Exception {
        long start=System.currentTimeMillis();
        List<String> days = TimeUtils.getDaysBetwwen(count);
        List<Parse> lists = new ArrayList<>();
        for (String day : days) {
            Parse parse = new Parse();
            sinaShareService.setParseByTime(day, name, parse);
            lists.add(parse);
        }
        long end=System.currentTimeMillis();
        log.info("SinaShareThread:"+Thread.currentThread().getName()+" 耗时："+(end-start));
        return lists;
    }
}
