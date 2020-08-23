package com.ming.data.web;

import com.ming.data.entity.CodeConfig;
import com.ming.data.service.CodeConfigService;
import com.ming.data.utils.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author alun
 * @data 2020/5/13
 */
@RestController
@RequestMapping(value = "codeConfig")
public class CodeConfigController {

    @Autowired
    CodeConfigService codeConfigService;

    @GetMapping(value = "findAll")
    public ApiResult<List<CodeConfig>> findAll(){
        return ApiResult.success(codeConfigService.findEnable(1));
    }
}
