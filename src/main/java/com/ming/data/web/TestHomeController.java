package com.ming.data.web;

import com.ming.data.entity.TestHome;
import com.ming.data.service.TestHomeService;
import com.ming.data.utils.ApiResult;
import com.ming.data.utils.CrawlingTestHome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author alun
 * @data 2019/10/8
 */

@RestController
@RequestMapping(value = "testhome")
public class TestHomeController {

    @Autowired
    TestHomeService testHomeService;
    // https://testerhome.com/topics/popular?page=2，58

    @RequestMapping(value = "/crawling")
    public ApiResult crawling() {
        String url = "https://testerhome.com/topics/popular?page=";
        for (int i = 2; i < 59; i++) {
            System.out.println(url + i);
            List<TestHome> lists = CrawlingTestHome.home(url + i);
            if (lists.size() != 0 && lists != null) {
                testHomeService.save(lists);
            }
        }
        return ApiResult.success("");
    }


    @RequestMapping(value="/findall")
    public ApiResult<List<TestHome>> findAll(){
        List<TestHome> testHomes=testHomeService.findAll();
        if(testHomes!=null){
            return ApiResult.success(testHomes);
        }
        return null;
    }

    @RequestMapping(value = "/crawlingNew")
    public ApiResult crawlingNew() {
        String url = "https://testerhome.com/topics/popular";
        List<TestHome> lists = CrawlingTestHome.home(url);
        if (lists.size() != 0 && lists != null) {
            for(int i=0;i<lists.size();i++){
                String urls=lists.get(i).getUrl();
                List<TestHome> datas=testHomeService.findByUrl(urls);
                if(datas.size()==0){
                    testHomeService.saveOne(lists.get(i));
                }
            }
        }
        return ApiResult.success("");
    }
}
