package com.ming.data.web;

import com.ming.data.entity.*;
import com.ming.data.service.CodeConfigService;
import com.ming.data.service.ParseDetailService;
import com.ming.data.service.SinaShareService;
import com.ming.data.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @author alun
 * @data 2019/5/13
 */

@RestController
@RequestMapping(value = "/share")
public class SharesController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    SinaShareService sinaShareService;

    @Autowired
    CodeConfigService codeConfigService;

    @Autowired
    ParseDetailService parseDetailService;


    @GetMapping(value = "getCurrent")
    public ApiResult getCurrentValue(@RequestParam String code) {
        if ("".equals(code)) {
            return ApiResult.fail("请输入字段");
        }
        return ApiResult.success(sinaShareService.findValueByCode(code));
    }

    @GetMapping(value = "config")
    public ApiResult<List<CodeConfig>> getConfig(@RequestParam int enable) {

        return ApiResult.success(codeConfigService.findEnable(enable));
    }

    @PostMapping(value = "saveConfig")
    public ApiResult saveConfig(@RequestParam @Valid CodeConfig codeConfig) {
        return ApiResult.success(codeConfigService.save(codeConfig));
    }


    /*
     *
     * 最近几天的历史数据
     *
     * */
    @GetMapping(value = "history")
    public ApiResult<List<List<ParseDetail>>> showHistory() {
        List<List<ParseDetail>> lists = sinaShareService.showHistory();
        if (lists == null) {
            return ApiResult.fail("失败");
        }
        return ApiResult.success(lists);
    }

    @GetMapping(value = "buyBigDetail")
    public ApiResult<List<Sinashare>> findBigDetail(@RequestParam String beginTime,@RequestParam String endTime,
                                                    @RequestParam String sellType,@RequestParam String name){
        List<Sinashare> list=sinaShareService.findBuyBigDetail(beginTime,endTime,sellType,name);
        return ApiResult.success(list);
    }


    @GetMapping(value = "/downloadExcel")
    public void downloadTemplate(HttpServletResponse response) throws Exception {
        sinaShareService.downloadExcel(response);
    }

    /*
     *
     * 大资金数据
     * */
    @GetMapping(value = "showBig")
    public ApiResult<List<List<Big>>> showBig(@RequestParam int count, @RequestParam String type) {
        List<List<Big>> lists = sinaShareService.getBig(count, type);

        if (lists == null) {
            return ApiResult.fail("失败");
        }
        return ApiResult.success(lists);
    }


    /*
     *
     * 入库Excel的数据
     * */
    @GetMapping(value = "analysis")
    public ApiResult anylsis(@RequestParam int count) {
        if(!sinaShareService.witerToExcel(count)){
            return ApiResult.fail("写入失败");
        }
        return ApiResult.success("写入成功");
    }

    /*
     *
     * 实时数据
     * */
    @GetMapping(value = "parse")
    public ApiResult<List<Parse>> parseRealTimeData(@RequestParam String time) {
        List<Parse> lists = sinaShareService.parseRealTimeData(time);
        return ApiResult.success(lists);
    }

    /*
     * 推荐*/
    @GetMapping(value = "recommend")
    public ApiResult<List<Recommend>> RecommendShare(@RequestParam String time) {
        List<Recommend> lists=sinaShareService.RecommendShare(time);
        return ApiResult.success(lists);
    }

    /*
    * recommend数据跑批*/
    @GetMapping(value = "recommend/parse")
    public ApiResult toDb(@RequestParam int count){
        List<String> days = TimeUtils.getDaysBetwwen(count);
        for(String day:days){
            sinaShareService.RecommendShare(day);
        }
        return null;
    }

    @GetMapping(value = "addDetail")
    public void adddetail(){
        sinaShareService.addDetailToDB();
    }

    /*
     * passe数据跑批*/
    @GetMapping(value = "passe/update")
    public ApiResult parseToDb(@RequestParam int count){
        List<List<Parse>> lists=sinaShareService.getParses(count);
        for(List<Parse> list:lists){
            for(Parse parse:list){
                ParseDetail p=new ParseDetail();
                int num=parse.getAmBuy();
                if(num!=0){
                    p.setAmBuy(num);
                    p.setAmSell(parse.getAmSell());
                    p.setAspBuy(parse.getAspBuy());
                    p.setAspSell(parse.getAspSell());
                    p.setPmBuy(parse.getPmBuy());
                    p.setPmSell(parse.getPmSell());
                    p.setSpBuy(parse.getSpBuy());
                    p.setSpSell(parse.getSpSell());
                    p.setTotalBuy(parse.getTotalBuy());
                    p.setTotalSell(parse.getTotalSell());
                    p.setName(parse.getName());
                    p.setRange(parse.getRange());
                    p.setTime(parse.getDate());
                    p.setBuybigcount(parse.getBuyBigCount());
                    p.setSellbigcount(parse.getSellBigCount());
                    parseDetailService.insert(p);
                }
            }
        }
        sinaShareService.addDetailToDB();
        return null;
    }

    @GetMapping(value = "test")
    public ApiResult<List<Recommend>> getdata(){
        List<Recommend> re=sinaShareService.RecommendShare("2020-6-19");
        return ApiResult.success(re);
    }

    //133 0880 7127
    @GetMapping(value = "find")
    public List<CodeConfig> find(@RequestParam int active) {
        return codeConfigService.find(active);
    }
}
