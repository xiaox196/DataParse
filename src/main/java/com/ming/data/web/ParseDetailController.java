package com.ming.data.web;

import com.ming.data.entity.ParseDetail;
import com.ming.data.service.ParseDetailService;
import com.ming.data.utils.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author alun
 * @data 2020/4/30
 */

@RestController
@RequestMapping(value = "detail")
public class ParseDetailController {
    @Autowired
    ParseDetailService parseDetailService;

    @GetMapping(value = "parse")
    public ApiResult parseDetail(){
        return ApiResult.success(parseDetailService.parse());
    }

    @GetMapping(value = "findAll")
    public List<ParseDetail> find(@RequestParam String name){
        return parseDetailService.findAllByName(name);
    }
}
