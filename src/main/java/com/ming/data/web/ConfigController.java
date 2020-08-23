package com.ming.data.web;

import com.ming.data.entity.CodeConfig;
import com.ming.data.service.CodeConfigService;
import com.ming.data.service.ConfigService;
import com.ming.data.utils.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author alun
 * @data 2020/1/15
 */
@RestController
@RequestMapping(value = "config")
public class ConfigController {

    @Autowired
    CodeConfigService codeConfigService;

    @Autowired
    ConfigService configService;

    @GetMapping(value = "getShares")
    public ApiResult<List<CodeConfig>> getCurrentValue(@RequestParam int enable) {
        return ApiResult.success(codeConfigService.findEnable(enable));
    }

    @PostMapping(value = "save")
    public ApiResult save(@RequestBody CodeConfig codeConfig){
        return ApiResult.success(codeConfigService.save(codeConfig));
    }

    @GetMapping(value = "findAll")
    public ApiResult findAll(){
        return ApiResult.success(configService.findAll());
    }

    @GetMapping(value = "test")
    public ApiResult test(@RequestParam String  key){
        return ApiResult.success(configService.selectByKey(key));
    }
}
