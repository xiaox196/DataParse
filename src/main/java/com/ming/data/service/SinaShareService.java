package com.ming.data.service;


import com.ming.data.entity.*;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author alun
 * @data 2019/4/16
 */
public interface SinaShareService {
    List<Sinashare> getSinaShare(int active);
    int save(Sinashare sinaShare);
    String findValueByCode(String code);
    List<Recommend> RecommendShare(String time);
    int update(Sinashare sinaShare);
    List<List<ParseDetail>> showHistory();
    List<List<Big>> getBig(int count,String type);
    List<Parse> parseRealTimeData(String time);
    List<Sinashare> findSinasData( String start, String end, String name);
    boolean witerToExcel(int count);
    boolean addDetailToDB();
    Parse parseRealTimeDataBuyName(String name);
    void downloadExcel(HttpServletResponse response);


    List<Sinashare> findSellBigDetail(String start, String end, String sellType, String name);
    List<Sinashare> findBuyBigDetail( String start, String end, String type,String name);
    int findBuySumByTime(String beginTime,String endTime,String name);
    int findSellSumByTime(String beginTime,String endTime,String name);
    Sinashare findOne(String beginTime,String endTime, String name);
    int findBuyBig(String beginTime,String endTime,String type,String name);
    int findSellBig(String beginTime,String endTime,String sellType,String name);

    /*
    * 用于parse数据跑批*/
    List<List<Parse>> getParses(int count);
}
