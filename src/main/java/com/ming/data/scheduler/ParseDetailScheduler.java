package com.ming.data.scheduler;

import com.ming.data.entity.CodeConfig;
import com.ming.data.entity.Parse;
import com.ming.data.entity.ParseDetail;
import com.ming.data.entity.Sinashare;
import com.ming.data.service.CodeConfigService;
import com.ming.data.service.ParseDetailService;
import com.ming.data.service.SinaShareService;
import com.ming.data.utils.BaseCls;
import com.ming.data.utils.ParseSinaData;
import com.ming.data.utils.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

import static com.ming.data.utils.HttpClientUtil.httpGet;

/**
 * @author alun
 * @data 2020/5/2
 */
@Component
@Slf4j
public class ParseDetailScheduler extends BaseCls {

    @Value("${sina.url}")
    private String host;

    @Autowired
    SinaShareService sinaShareService;

    @Autowired
    ParseDetailService parseDetailService;

    @Autowired
    CodeConfigService codeConfigService;

    /*
     * 定时任务每天跑parse
     * */
    @Scheduled(cron = "0 30 15 * * ? ")
    public void exc() {
        if (!TimeUtils.isWeek()) {
            List<CodeConfig> names = codeConfigService.findEnable(1);
            Sinashare sinaShare = null;
            for (CodeConfig codeConfig : names) {
                String code = codeConfig.getCode();
                String url = null;
                url=host + code;
                String resultStr = httpGet(url);
                log.info("request url:{} ,result:{}", url, resultStr);
                if (null != resultStr) {
                    sinaShare = ParseSinaData.getSinaShareData(resultStr);
                }
                String name = codeConfig.getName();
                List<ParseDetail> lists = parseDetailService.findAllByName(name);
                Parse currentData = sinaShareService.parseRealTimeDataBuyName(name);
                int len = lists.size();
                int buy_sum = 0;
                int sell_sum = 0;
                ParseDetail result = parseDetailService.selectByNameAndTime(name, TimeUtils.formatTime(new Date()));
                if (len >= 5) {
                    /*获取第二个*/
                    for (int j = 0; j < 4; j++) {
                        int num = len - j - 1;
                        ParseDetail p=lists.get(num);
                        int totalBuy=p.getTotalBuy();
                        int totalSell=p.getTotalSell();
                        buy_sum=buy_sum+totalBuy;
                        sell_sum=sell_sum+totalSell;
                    }
                    buy_sum=buy_sum+currentData.getTotalBuy();
                    sell_sum=sell_sum+currentData.getTotalSell();
                    log.info("later buy_sum：{},later sell_sum:{}",buy_sum,sell_sum);
                }

                if (result == null) {
                    ParseDetail p = new ParseDetail();
                    p.setAmBuy(currentData.getAmBuy());
                    p.setAmSell(currentData.getAmSell());
                    p.setAspBuy(currentData.getAspBuy());
                    p.setAspSell(currentData.getAspSell());
                    p.setPmBuy(currentData.getPmBuy());
                    p.setPmSell(currentData.getPmSell());
                    p.setSpBuy(currentData.getSpBuy());
                    p.setSpSell(currentData.getSpSell());
                    p.setTotalBuy(currentData.getTotalBuy());
                    p.setTotalSell(currentData.getTotalSell());
                    p.setName(currentData.getName());
                    p.setRange(currentData.getRange());
                    p.setTime(currentData.getDate());
                    p.setBuybigcount(currentData.getBuyBigCount());
                    p.setSellbigcount(currentData.getSellBigCount());
                    p.setLater5Sell(sell_sum);
                    p.setLater5Buy(buy_sum);
                    if(sell_sum!=0&&buy_sum!=0){
                        p.setLater5Ave(Double.parseDouble(de.format(buy_sum/sell_sum)));
                    }
                    parseDetailService.insert(p);
                } else {
                    log.info("数据库里已存在");
                }
            }
        }
    }

}
