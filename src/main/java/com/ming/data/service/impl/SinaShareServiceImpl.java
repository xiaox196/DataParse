package com.ming.data.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;

import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.ming.data.dao.SinashareDao;
import com.ming.data.entity.*;
import com.ming.data.service.*;
import com.ming.data.utils.*;
import com.ming.data.utils.Config;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.ming.data.utils.HttpClientUtil.httpGet;

/**
 * @author alun
 * @data 2019/4/16
 */

@Slf4j
@Service
public class SinaShareServiceImpl extends BaseCls implements SinaShareService {

    @Autowired
    SinashareDao sinaShareDao;

    @Autowired
    ParseDetailService parseDetailService;

    @Autowired
    RecommendHistoryService recommendHistoryService;

    static List<CodeConfig> codeConfigs = null;

    @Autowired
    CodeConfigService codeConfigService;

    @Autowired
    ConfigService configService;

    public String sell_range = null;


    @Value("${sina.url}")
    private String host;
    private String name;


    public Sinashare get(String which) {
        String data = null;
        String url = null;
        Sinashare sinaShare = null;
        url = host + which;
        String result = httpGet(url);
        log.info("request url:{} ,result:{}", url, result);
        if (null != result) {
            sinaShare = ParseSinaData.getSinaShareData(result);
        }
        return sinaShare;
    }

    @Override
    public List<Sinashare> getSinaShare(int active) {
        codeConfigs = codeConfigService.findEnable(1);
        return getSinaShareByEnable(1);
    }


    //获取最近几天数据
    @Override
    public List<List<Parse>> getParses(int count) {
        List<CodeConfig> lists = codeConfigService.findEnable(1);
        List<List<Parse>> parsesList = new ArrayList<>();
        List<String> days = TimeUtils.getDaysBetwwen(count);
        for (int i = 0; i < lists.size(); i++) {
            List<Parse> listsName = new ArrayList<>();
            String name = lists.get(i).getName();
            for (String day : days) {
                Parse parse = new Parse();
                setParseByTime(day, name, parse);
                listsName.add(parse);
            }
            parsesList.add(listsName);
        }
        return parsesList;
    }


    /*
    具体解析数据为parse的方法
    * */
    public Parse setParseByTime(String time, String name, Parse parse) {
        long start = System.currentTimeMillis();
        Sinashare sinaShare = null;
        //am 设置
        int amBuy = findBuySumByTime(time + Config.totalStart,
                time + Config.amEnd, name);
        int amSell=findSellSumByTime(time + Config.totalStart,time + Config.amEnd, name);
        parse.setAmBuy(amBuy);
        parse.setAmSell(amSell);

        //asp设置
        int aspBuy=findBuySumByTime(time + Config.totalStart,
                time + Config.aspEnd, name);

        int aspSell=findSellSumByTime(time + Config.totalStart,
                time + Config.aspEnd, name);
        parse.setAspBuy(aspBuy);
        parse.setAspSell(aspSell);

        //pm设置
        int pmBuy=findBuySumByTime(time + Config.pmStart,
                time + Config.totalEnd, name);
        int pmSell=findSellSumByTime(time + Config.pmStart,
                time + Config.totalEnd, name);
        parse.setPmBuy(pmBuy);
        parse.setPmSell(pmSell);

        //sp设置
        int spBuy=findBuySumByTime(time + Config.spStart,
                time + Config.totalEnd, name);
        int spSell=findSellSumByTime(time + Config.spStart,
                time + Config.totalEnd, name);
        parse.setSpBuy(spBuy);
        parse.setSpSell(spSell);

        //total设置

        int totalSell=findSellSumByTime(time + Config.totalStart, time + Config.totalEnd, name);
        int totalBuy=findBuySumByTime(time + Config.totalStart, time + Config.totalEnd, name);
        parse.setTotalBuy(totalBuy);
        parse.setTotalSell(totalSell);
        int buyShareCount = sinaShareDao.findBuyBig(time + Config.totalStart, time + Config.totalEnd, "B", name);
        int sellShareCount = sinaShareDao.findSellBig(time + Config.totalStart, time + Config.totalEnd, "B", name);
        if (TimeUtils.belongCalendar(new Date(), Config.totalStart, Config.totalEnd) && TimeUtils.isCurrentTime(time)) {
            Date data = null;
            try {
                data = df.parse(df.format(new Date()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String dataStart = null;
            String dataEnd = null;
            Calendar c1 = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();
            c1.setTime(data);
//            推迟20s
            c1.add(Calendar.SECOND, -30);
            c2.setTime(data);
            c2.add(Calendar.SECOND, -10);
            dataStart = df.format(c1.getTime());
            dataEnd = df.format(c2.getTime());

            sinaShare = sinaShareDao.findOne(time + " " + dataStart, time + " " + dataEnd, name);
        } else {
            sinaShare = sinaShareDao.findOne(time + Config.rangeStart, time + Config.totalEnd, name);
        }
        if (sinaShare != null) {
            parse.setRange(sinaShare.getRange());
        }

        long end = System.currentTimeMillis();
        parse.setSellBigCount(sellShareCount);
        parse.setBuyBigCount(buyShareCount);
        parse.setDate(time);
        parse.setName(name);
        log.info("setParseByTime耗时:" + (end - start));
        return parse;
    }


    public static boolean isDisplay(Sinashare sinaShare) {
        for (CodeConfig cc : codeConfigs) {
            int active = cc.getActive();
            if (active == 1 && sinaShare != null) {
                if (sinaShare.getName().equals(cc.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Sinashare findOne(String beginTime, String endTime, String name) {
        return sinaShareDao.findOne(beginTime, endTime, name);
    }

    @Override
    public int findBuyBig(String beginTime, String endTime, String type, String name) {
        return 0;
    }

    @Override
    public int findSellBig(String beginTime, String endTime, String sellType, String name) {
        return 0;
    }


    public List<Sinashare> getSinaShareByEnable(int enable) {
        List<Sinashare> lists = new ArrayList<>();
        String params = "";
        Sinashare sinaShare = null;
        for (int i = 0; i < codeConfigs.size(); i++) {
            if (codeConfigs.size() - 1 == i) {
                params = params + codeConfigs.get(i).getCode();
            } else {
                params = params + codeConfigs.get(i).getCode() + ",";
            }
        }
        String url = host + params;
        String result = httpGet(url);
        if (result != null) {
            String[] results = result.split(";");
            for (String str : results) {
                if (!str.equals("") && str.contains("=")) {
                    sinaShare = ParseSinaData.getSinaShareData(str);
                    lists.add(sinaShare);
                }
            }
        }
        return lists;
    }

    @Override
    public int save(Sinashare sinaShare) {
        return sinaShareDao.insert(sinaShare);
    }


    @Override
    public String findValueByCode(String code) {
        Sinashare sinaShare = get(code);
        return sinaShare.getRange();
    }

    public double toSinaGetRange(Sinashare sinaShare) {
        if (sinaShare != null) {
            String rangeStr = sinaShare.getRange();
            if (rangeStr == null) {
                return 0;
            }
            rangeStr = rangeStr.split("%")[0];
            return StringUtil.toDouble(rangeStr);
        }
        return 0;
    }


    @Override
    public List<Recommend> RecommendShare(String time) {
        long s = System.currentTimeMillis();
        List<Parse> parses = parseRealTimeData(time);
        long e = System.currentTimeMillis();
        log.info("parseRealTimeData :" + (e - s));
        float score_goal = Float.parseFloat(configService.selectByKey("score_goal").getValue());
        String score_start = configService.selectByKey("score_start").getValue();

        List<Recommend> recommends = new ArrayList<>();
        for (Parse parse : parses) {
            boolean inInserd = false;
            String name = parse.getName();
            Recommendhistory recommendhistory = new Recommendhistory();
            Sinashare endSinaShare = sinaShareDao.findOne(time + " 14:52:00", time + " 14:52:20", name);
            Sinashare startSinaShare = sinaShareDao.findOne(time + " 13:31:00", time + " 13:31:20", name);

            Recommend recommend = new Recommend();
            DataEngine dataEngine = new DataEngine();
            dataEngine.modelC_value_score = 0;
            dataEngine.range_positive_count = 0;
            dataEngine.range_negative_count = 0;
            double amBuy = parse.getAmBuy();
            double amSell = parse.getAmSell();

            double pmBuy = parse.getPmBuy();
            double pmSell = parse.getPmSell();
            double spBuy = parse.getSpBuy();
            double spSell = parse.getSpSell();
            double totalBuy = parse.getTotalBuy();
            double totalSell = parse.getTotalSell();
            int buyBig = parse.getBuyBigCount();
            int sellBig = parse.getSellBigCount();

            double spMultiple = spBuy / spSell;
            recommend.setName(name);
            double range = toSinaGetRange(endSinaShare);
            double startRange = toSinaGetRange(startSinaShare);
            double num = range - startRange;
            //评分卡A
            if (range < -0.1) {
                if (num < 1.5 && num > 0.5) {
                    dataEngine.range_negative_count = 1;
                } else if (num > 1.5 && num < 3) {
                    dataEngine.range_negative_count = 2;
                } else if (num > 3 && num < 7) {
                    dataEngine.range_negative_count = 2.5;
                }
            }

            //评分卡B
            if (range > 0.1 && totalBuy > totalSell) {
                if (num < 1.5 && num > 0.5) {
                    dataEngine.range_positive_count = 1;
                } else if (num > 1.5 && num < 3) {
                    dataEngine.range_positive_count = 2;
                } else if (num > 3 && num < 7) {
                    dataEngine.range_positive_count = 2.5;
                }
            }

            //评分卡E
            if (buyBig > sellBig) {
                dataEngine.bigB_calc_value_score = 1;
            } else {
                dataEngine.bigB_calc_value_score = 0;
            }

            if ((amSell > amBuy * 1.8) && range > 3) {
                inInserd = true;
                recommend.setSellLargeBuyAndRangeL2(true);
                recommend.setParse(parse);
                recommendhistory.setSelllargebuyandrangel2(1);
                setRecommend(recommendhistory, parse.getName(), time);
            }
            if (amBuy < amSell && pmBuy > pmSell) {
                double model_rangeB = Double.parseDouble(configService.selectByKey("model_rangeB").getValue());
                if (range < model_rangeB) {
                    inInserd = true;
                    recommend.setAmSellLeBuyAndAfterBuyLeSell(true);
                    //评分卡C
                    dataEngine.modelC_value_score = 3;
                    recommend.setParse(parse);
                    recommendhistory.setAmselllebuyandafterbuylesell(1);
                    setRecommend(recommendhistory, parse.getName(), time);
                }
            }

            if (endSinaShare != null) {
                recommend.setRange(endSinaShare.getRange());
            }
            double total = dataEngine.getTotal();
            recommendhistory.setScore(total);

            recommend.setDataEngine(dataEngine);
            if (total > score_goal) {
                inInserd = true;
                recommend.setParse(parse);
                setRecommend(recommendhistory, parse.getName(), time);
            }
            if (inInserd && !TimeUtils.belongCalendar(new Date(), Config.totalStart, Config.recommend_end)) {
                if (recommendHistoryService.findByNameAndCreate(name, time).size() < 1) {
                    recommendHistoryService.insert(recommendhistory);
                    log.info("inserd recommend success");
                }
            }

            recommends.add(recommend);
        }

        return recommends;
    }

    public Recommendhistory setRecommend(Recommendhistory recommend, String name, String time) {
        recommend.setName(name);
        try {
            recommend.setCreatetime(df_year.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return recommend;
    }


    @Override
    public int update(Sinashare sinaShare) {
        return sinaShareDao.updateByPrimaryKey(sinaShare);
    }

    @Override
    public int findBuySumByTime(String start, String end, String name) {
        return sinaShareDao.findBuySumByTime(start, end, name);
    }

    @Override
    public int findSellSumByTime(String beginTime, String endTime, String name) {
        return sinaShareDao.findSellSumByTime(beginTime, endTime, name);
    }

    /*
     * 获取最近几天分析数据
     * */
    @Override
    public List<List<ParseDetail>> showHistory() {
        List<CodeConfig> lists = codeConfigService.findEnable(1);
        List<List<ParseDetail>> data = new ArrayList<>();
        for (CodeConfig list : lists) {
            String name = list.getName();
            List<ParseDetail> parseDetails = parseDetailService.findAllByName(name);
            data.add(parseDetails);
        }
        return data;
    }

    //获取大资金数据
    @Override
    public List<List<Big>> getBig(int count, String type) {
        List<CodeConfig> lists = codeConfigService.findEnable(1);
        List<List<Big>> sinaList = new ArrayList<>();
        List<String> days = TimeUtils.getDaysBetwwen(count);
        for (int i = 0; i < lists.size(); i++) {
            String name = lists.get(i).getName();
            List<Big> listsName = new ArrayList<>();
            for (String day : days) {
                Big big = new Big();
                String beginTime = day + Config.totalStart;
                String endTime = day + Config.totalEnd;
                int countBuy = sinaShareDao.findBuyBig(beginTime, endTime, type, name);
                int countSell = sinaShareDao.findSellBig(beginTime, endTime, type, name);
                big.setDate(day);
                big.setBuyCount(countBuy);
                big.setSellCount(countSell);
                big.setName(name);
                listsName.add(big);
            }
            sinaList.add(listsName);
        }
        return sinaList;
    }


    //    实时数据
    @Override
    public List<Parse> parseRealTimeData(String time) {
        List<Parse> parses = new ArrayList<>();
        List<CodeConfig> lists = codeConfigService.findEnable(1);
        String beginTime = null;
        String endTime = null;
        if (lists != null & lists.size() != 0) {
            for (CodeConfig codeConfig : lists) {
                String name = codeConfig.getName();
                Parse parse = new Parse();
                parse.setName(name);
                if (time.equals("")) {
                    parse = setParseByTime(TimeUtils.formatTime(new Date()), name, parse);
                } else {
                    parse = setParseByTime(time, name, parse);
                }
                parses.add(parse);
            }
        }
        return parses;
    }

    @Override
    public List<Sinashare> findSinasData(String start, String end, String name) {
        return null;
    }


    /*
     * 使用easypoi对历史数据批量写入Excel里面*/
    @Override
    public boolean witerToExcel(int count) {
        try {
            List<CodeConfig> names = codeConfigService.findEnable(1);
            Workbook workbook = null;
            String path = null;
            List<Map<String, Object>> datas = new ArrayList<>();
            for (CodeConfig codeConfig : names) {
                String name = codeConfig.getName();
                List<ParseDetail> list = parseDetailService.findAllByName(name);
                Map<String, Object> map = new HashMap<>();
                ExportParams title = new ExportParams();
                title.setSheetName(name);
                map.put("title", title);
                map.put("entity", ParseDetail.class);
                map.put("data", list);
                datas.add(map);
            }
            workbook = ExcelExportUtil.exportExcel(datas, ExcelType.HSSF);
            String osNmae = System.getProperty("os.name");
            if (osNmae.contains("Mac")) {
                path = "/Users/abc/";
            } else if (osNmae.contains("win")) {
                path = "/Users/abc/";
            } else {
                path = configService.selectByKey("excel_file_path").getValue();
            }
            path = path + df_year.format(new Date()) + ".xls";
            log.info("excel_file_path:" + path);
            ExcelUtils.save(workbook, path);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean addDetailToDB() {
        findParseByDetail();
        return false;
    }

    @Override
    public Parse parseRealTimeDataBuyName(String name) {
        return setParseByTime(TimeUtils.formatTime(new Date()), name, new Parse());
    }

    @Override
    public List<Sinashare> findSellBigDetail(String start, String end, String sellType, String name) {
        return sinaShareDao.findSellBigDetail(start, end, sellType, name);
    }

    @Override
    public List<Sinashare> findBuyBigDetail(String start, String end, String type, String name) {
        return sinaShareDao.findBuyBigDetail(start, end, type, name);
    }

    @Override
    public void downloadExcel(HttpServletResponse response) {
        OutputStream out = null;
        String path = configService.selectByKey("excel_path").getValue() + df_year.format(new Date()) + ".xls";
        File file = new File(path);
        log.info("download excel file path:" + path);
        try {
            if (!file.exists()) {
                witerToExcel(2);
            }
            response.addHeader("content-disposition", "attachment;filename="
                    + java.net.URLEncoder.encode(df_year.format(new Date()) + ".xls", "utf-8"));

            out = response.getOutputStream();

            // inputStream：读文件，前提是这个文件必须存在，要不就会报错
            InputStream is = new FileInputStream(path);

            byte[] b = new byte[4096];
            int size = is.read(b);
            while (size > 0) {
                out.write(b, 0, size);
                size = is.read(b);
            }
            out.close();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * 通过parse——detail来获取历史数据，提高速度
     * 用于计算最近5天总和
     * */
    public List<ParseDetail> findParseByDetail() {
        List<CodeConfig> lists = codeConfigService.findEnable(1);
        for (CodeConfig list : lists) {
            String name = list.getName();
            List<ParseDetail> parseDetails = parseDetailService.findAllByName(name);
            int len = parseDetails.size();
            if (len > 5) {
                ParseDetail parseDetail = null;
                for (int i = len; i > 0; i--) {
                    parseDetail = parseDetails.get(i - 1);
                    int buy_sum = 0;
                    int sell_sum = 0;
                    if (i >= 5) {
                        for (int j = 0; j < 5; j++) {
                            int num = i - j - 1;
                            ParseDetail p = parseDetails.get(num);
                            int buyTotal = p.getTotalBuy();
                            int sellTotal = p.getTotalSell();
                            buy_sum = buy_sum + buyTotal;
                            sell_sum = sell_sum + sellTotal;
                            log.info("time:" + p.getTime() + " name:" + p.getName() + buyTotal + " sellTotal:" + sellTotal);
                        }
                    }

                    if (sell_sum != 0 & buy_sum != 0) {
                        parseDetail.setLater5Buy(buy_sum);
                        parseDetail.setLater5Sell(sell_sum);
                        float ave = (float) buy_sum / sell_sum;
                        parseDetail.setLater5Ave(Double.parseDouble(de.format(ave)));
                        parseDetailService.updateByPrimaryKey(parseDetail);
                    }
                }
            }
        }
        return null;
    }

}
