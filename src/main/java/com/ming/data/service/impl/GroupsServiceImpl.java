package com.ming.data.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ming.data.dao.ApiDao;
import com.ming.data.dao.GroupsDao;
import com.ming.data.entity.Api;
import com.ming.data.entity.Groups;
import com.ming.data.entity.GroupsItem;
import com.ming.data.service.GroupsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alun
 * @data 2020/5/18
 */
@Service
@Slf4j
public class GroupsServiceImpl implements GroupsService {

    @Autowired
    GroupsDao groupsDao;

    @Autowired
    ApiDao apiDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Groups record) {
        return 0;
    }

    @Override
    public int insertSelective(Groups record) {
        return 0;
    }

    @Override
    public Groups selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Groups record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Groups record) {
        return 0;
    }

    @Override
    public List<GroupsItem> findAll() {
        List<Groups> groups=groupsDao.findAll();
        List<GroupsItem> lists=new ArrayList<>();
        if(groups.size()!=0){
            int count=0;
            for(Groups g:groups){
                GroupsItem groupsItem=new GroupsItem();
                int groupId=g.getId();
                List<Api> apis=apiDao.selectByGroupId(groupId);
                groupsItem.setId(groupId+count);
                groupsItem.setLabel(g.getName());
                groupsItem.setChildren(setChirdren(apis));
                lists.add(groupsItem);
                count++;
            }
        }
        return lists;
    }

    public JSONArray setChirdren(List<Api> apis){
        JSONArray obj=new JSONArray();
        for(Api api:apis){
            JSONObject subObj=new JSONObject();
            subObj.put("label",api.getName());
            subObj.put("id",api.getId());
            obj.add(subObj);
        }
        return obj;
    }
}
