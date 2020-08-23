package com.ming.data.web;


import com.ming.data.service.CalcEngineService;
import com.ming.data.utils.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author alun
 * @data 2020/5/8
 */
@RestController
@RequestMapping(value = "engine")
public class CalcEngineController {

    @Autowired
    CalcEngineService calcEngineService;

    @GetMapping(value = "execute")
    public ApiResult calcEngine(){
        return ApiResult.success(calcEngineService.calcAllDatas());
    }
}
