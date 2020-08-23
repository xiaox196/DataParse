package com.ming.data.scheduler;

import com.ming.data.service.SinaShareService;
import com.ming.data.utils.BaseCls;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author alun
 * @data 2020/4/16
 */
@Component
@Slf4j
public class RecommendHistoryScheduler extends BaseCls {

    @Autowired
    SinaShareService sinaShareService;

    @Scheduled(cron ="0 52 14 * * ? " )
    public void doRecommend() {
        try {
            String time=df_year.format(new Date());
            log.info("{} update recommend ",time);
            sinaShareService.RecommendShare(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
