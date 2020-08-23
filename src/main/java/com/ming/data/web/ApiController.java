package com.ming.data.web;

import com.ming.data.entity.Api;
import com.ming.data.entity.Apiexeresult;
import com.ming.data.entity.GroupsItem;
import com.ming.data.service.ApiService;
import com.ming.data.service.GroupsService;
import com.ming.data.utils.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author alun
 * @data 2020/5/18
 */
@RestController
@RequestMapping(value = "api")
public class ApiController {
    @Autowired
    ApiService apiService;

    @Autowired
    GroupsService groupsService;

    @GetMapping(value = "execute")
    public ApiResult<Apiexeresult> execute(@RequestParam int count){
        return ApiResult.success(apiService.apiExecute(count));
    }

    @GetMapping(value = "groups/findAll")
    public ApiResult findGroupsAll(){
        return ApiResult.success(groupsService.findAll());
    }


    @GetMapping(value = "findAll")
    public ApiResult<List<Api>> findAll(){
        List<Api> lists=apiService.findAll();
        if(lists.size()==0||lists==null){
            return ApiResult.fail("查找失败");
        }
        return ApiResult.success(apiService.findAll());
    }

    @PostMapping(value = "insert")
    public ApiResult insertToDb(@RequestBody @Valid Api api){
        int count=apiService.insert(api);
        if(count==1){
            return ApiResult.success(apiService.insert(api));
        }
        return ApiResult.fail("插入失败");
    }
}
