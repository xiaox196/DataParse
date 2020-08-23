package com.ming.data.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ming.data.entity.CalcEngine;
import com.ming.data.entity.CodeConfig;
import com.ming.data.entity.ParseDetail;
import com.ming.data.service.CalcEngineService;
import com.ming.data.service.CodeConfigService;
import com.ming.data.service.ConfigService;
import com.ming.data.service.ParseDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alun
 * @data 2020/5/8
 */
@Service
@Slf4j
public class CalcEngineServiceImpl implements CalcEngineService {

    @Autowired
    ParseDetailService parseDetailService;

    @Autowired
    ConfigService configService;

    @Autowired
    CodeConfigService codeConfigService;

    /*
    * 数据库获取值并计算相应的结果
    * */
    @Override
    public List<CalcEngine> calcAllDatas() {
        List<CodeConfig> lists=codeConfigService.findEnable(1);
        List<CalcEngine> returnResults=new ArrayList<>();
        float buyDivideSellMultipleNum=Float.parseFloat(configService.selectByKey("calc_divide_multiple").getValue());
        float spBuyLgSellMultipleNum=Float.parseFloat(configService.selectByKey("sp_buy_lg_sell_multiple").getValue());
        log.info("calc_divide_multiple:{} sp_buy_lg_sell_multiple:{}",buyDivideSellMultipleNum ,spBuyLgSellMultipleNum);
        for (CodeConfig codeConfig:lists){
            String name=codeConfig.getName();
            List<ParseDetail> parseDetails= parseDetailService.findAllByName(name);
            for(ParseDetail p:parseDetails){
                int totalBuy=p.getTotalBuy();
                int totalSell=p.getTotalSell();
                int spBuy=p.getSpBuy();
                int spSell=p.getSpSell();

                String range=p.getRange();
                if(range!=null){
                    double rangeFloat=Float.parseFloat(range.split("%")[0]);
                    String date=p.getTime();
                    float calcSpResult=calcMultiple(spBuy,spSell);
                    if(calcMultiple(totalBuy,totalSell)>=buyDivideSellMultipleNum){
                        if(rangeFloat<7){
                            log.info("buyDivideSellMultiple name:{} date:{} range:{}",name,date,range);
                            CalcEngine calcEngine=new CalcEngine();
                            calcEngine.setName(name);
                            calcEngine.setDate(date);
                            calcEngine.setBuyDivideSellMultiple(true);
                            returnResults.add(calcEngine);
                        }
                    }else if (calcSpResult>=spBuyLgSellMultipleNum){
                        if(rangeFloat<6.5&&rangeFloat>-6.5){
                            log.info("spBuyLgSellMultipleNum name:{} date:{} range:{}",name,date,range);
                            CalcEngine calcEngine=new CalcEngine();
                            calcEngine.setName(name);
                            calcEngine.setDate(date);
                            calcEngine.setSpLage(true);
                            returnResults.add(calcEngine);
                        }
                    }
                }
            }
        }
        return returnResults;
    }

    public float calcMultiple(int a,int b){
        if(a!=0&&b!=0){
            float num=(float)a/b;
            return num;
        }
        return 0;
    }

}
