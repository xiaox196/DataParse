package com.ming.data.scheduler;

import com.ming.data.entity.Sinashare;
import com.ming.data.service.SinaShareService;
import com.ming.data.service.impl.SinaShareServiceImpl;
import com.ming.data.utils.TimeUtils;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * @author alun
 * @data 2019/5/11
 */

@Component
@Slf4j
public class SinaShareScheduler {

    @Autowired
    SinaShareService sinaShareService;

    @Scheduled(cron = "0/8 * 9-14 * * ?")
    public void execute() {
        if (!TimeUtils.isWeekAndWorkTime()) {
            List<Sinashare> sinaShares = sinaShareService.getSinaShare(1);
            if (null != sinaShares && sinaShares.size() > 0) {
                for (Sinashare sinaShare : sinaShares) {
                    if (sinaShare != null) {
                        if (SinaShareServiceImpl.isDisplay(sinaShare)) {
                            log.info("{} range:{} ,buy1:{} ,price:{}, buy2:{},price:{}, Num:{},Money:{}",
                                    sinaShare.getName(), sinaShare.getRange(), sinaShare.getBuy1(), sinaShare.getBuy1price(),
                                    sinaShare.getBuy2(), sinaShare.getBuy2price(), sinaShare.getBuynum(), sinaShare.getMoney());
                        }
                        try {
                            sinaShareService.save(sinaShare);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                log.info("search fail");
            }
        }
    }

}
