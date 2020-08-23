package com.ming.data.service.impl;

import com.ming.data.dao.ParseDetailDao;
import com.ming.data.entity.CodeConfig;
import com.ming.data.entity.ParseDetail;
import com.ming.data.entity.ParseDetailCount;
import com.ming.data.service.CodeConfigService;
import com.ming.data.service.ConfigService;
import com.ming.data.service.ParseDetailService;
import com.ming.data.utils.BaseCls;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alun
 * @data 2020/4/30
 */
@Slf4j
@Service
public class ParseDetailServiceImpl extends BaseCls implements ParseDetailService {

    @Autowired
    ParseDetailDao parseDetailDao;

    @Autowired
    CodeConfigService codeConfigService;

    @Autowired
    ConfigService configService;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(ParseDetail record) {
        return parseDetailDao.insert(record);
    }

    @Override
    public int insertSelective(ParseDetail record) {
        return 0;
    }

    @Override
    public ParseDetail selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(ParseDetail record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(ParseDetail record) {
        return parseDetailDao.updateByPrimaryKey(record);
    }

    @Override
    public List<ParseDetail> findAllByName(String name) {
        return parseDetailDao.findAllByName(name);
    }

    @Override
    public ParseDetail selectByNameAndTime(String name, String time) {
        return parseDetailDao.selectByNameAndTime(name,time);
    }

    @Override
    public List<ParseDetailCount> parse() {
        List<ParseDetailCount> parseDetailCounts=new ArrayList<>();
        List<CodeConfig> lists = codeConfigService.findEnable(1);
        double multiple=Double.parseDouble(configService.selectByKey("calc_multiple").getValue());
        for (CodeConfig list : lists) {
            ParseDetailCount parseDetailCount=new ParseDetailCount();
            String name = list.getName();
            List<ParseDetail> parseDetails = parseDetailDao.findAllByName(name);
            int size=parseDetails.size();
            parseDetailCount.setTotal(size);
            parseDetailCount.setName(name);
            int buy_lager_sellCount=0;
            int sell_lager_buyCount=0;
            int sell_lager_buy_inTotal=0;
            int buy_lager_sell_inTotal=0;
            for(int i=0;i<size-1;i++){
                ParseDetail p=parseDetails.get(i);
                int totalBuy=p.getTotalBuy();
                int totalSell=p.getTotalSell();
                if(totalBuy<totalSell){
                    String rangeStr=parseDetails.get(i+1).getRange();
                    sell_lager_buy_inTotal++;
                    if(rangeStr!=null){
                        rangeStr=rangeStr.split("%")[0];
                        float range=Float.parseFloat(rangeStr);
                        if(range>0.5){
                            log.info("name:{};time:{}; totalSell:{} ; totalBuy:{}",name,p.getTime(),totalSell,totalBuy);
                            sell_lager_buyCount++;
                        }
                    }
                }else if(multiple*totalSell<totalBuy){

                }
            }
            parseDetailCount.setSell_lager_buy_inTotal(sell_lager_buy_inTotal);
            parseDetailCounts.add(parseDetailCount);
        }
        return parseDetailCounts;
    }
}
