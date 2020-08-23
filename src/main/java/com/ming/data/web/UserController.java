package com.ming.data.web;

import com.alibaba.fastjson.JSONObject;
import com.ming.data.entity.User;
import com.ming.data.entity.Login;
import com.ming.data.service.UserService;
import com.ming.data.utils.ApiResult;
import com.ming.data.utils.BaseCls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author alun
 * @data 2020/3/27
 */
@RestController
@RequestMapping(value = "user")
public class UserController extends BaseCls {

    @Autowired
    UserService userService;

    @PostMapping(value = "login")
    public ApiResult login(@RequestBody User user){
        HttpSession session = request.getSession();
        if(null!=user){
            if(userService.login(user)){
                JSONObject obj=new JSONObject();
                obj.put("token","test123");
                return ApiResult.success(obj,"登录成功");
            }else {
                return ApiResult.fail("登录失败");
            }
        }
        return ApiResult.fail("用户信息为空");
    }
}
