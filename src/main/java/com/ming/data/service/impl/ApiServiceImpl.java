package com.ming.data.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ming.data.dao.ApiDao;
import com.ming.data.entity.Api;
import com.ming.data.entity.Apiexeresult;
import com.ming.data.service.ApiService;
import com.ming.data.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author alun
 * @data 2020/5/18
 */
@Service
@Slf4j
public class ApiServiceImpl implements ApiService {

    @Autowired
    ApiDao apiDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Api record) {
        return apiDao.insert(record);
    }

    @Override
    public int insertSelective(Api record) {
        return 0;
    }

    @Override
    public Api selectByPrimaryKey(Integer id) {
        return apiDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Api record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Api record) {
        return 0;
    }

    @Override
    public List<Api> selectByPath(String path) {
        return apiDao.selectByPath(path);
    }

    @Override
    public Apiexeresult apiExecute(Integer id) {
        Api api=apiDao.selectByPrimaryKey(id);
        String path=api.getPath();
        String params=api.getParams();
        String method=api.getMethod();
        String root=api.getRoot();
        Integer port=api.getPort();
        JSONObject result=null;
        Apiexeresult apiexeresult=new Apiexeresult();
        String url=buildUrl(root,port,path,method);
        log.info("execute url:"+url);
        if(method.equalsIgnoreCase("post")){
            result=HttpClientUtil.httpPost(url,JSONObject.parseObject(params));
        }else if(method.equalsIgnoreCase("get")){
            result=JSONObject.parseObject(HttpClientUtil.httpGet(url));
        }
        apiexeresult.setApiId(id);
        apiexeresult.setResult(result.toJSONString());
        return apiexeresult;
    }

    @Override
    public List<Api> findAll() {
        return apiDao.findAll();
    }

    public String buildUrl(String url,int port,String path,String method){
        if(!url.contains("http://")) {
            url="http://"+url;
        }
        if(port!=0) {
            url=url+":"+port+path;
        }else {
            url=url+path;
        }
        return url;
    }
}
