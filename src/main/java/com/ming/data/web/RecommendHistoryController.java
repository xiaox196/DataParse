package com.ming.data.web;

import com.ming.data.entity.Recommendhistory;
import com.ming.data.service.RecommendHistoryService;
import com.ming.data.utils.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author alun
 * @data 2020/4/16
 */
@RestController
@RequestMapping(value = "recommend")
public class RecommendHistoryController {

    @Autowired
    RecommendHistoryService recommendHistoryService;

    @GetMapping(value = "findAll")
    public ApiResult<List<Recommendhistory>> findAll(){
        return ApiResult.success(recommendHistoryService.findRecommends());
    }

}
