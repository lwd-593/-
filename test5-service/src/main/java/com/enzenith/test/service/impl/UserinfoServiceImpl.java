package com.enzenith.test.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.enzenith.test.entity.Userinfo;
import com.enzenith.test.mapper.UserinfoMapper;
import com.enzenith.test.service.UserinfoService;
/**
*
* @author: LinWeiDa
* @date: 2020-04-26 11:19
 */
@Service
public class UserinfoServiceImpl implements UserinfoService{

    @Resource
    private UserinfoMapper userinfoMapper;

    @Override
    public int deleteByPrimaryKey(String userId) {
        return userinfoMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public int insert(Userinfo record) {
        return userinfoMapper.insert(record);
    }

    @Override
    public int insertSelective(Userinfo record) {
        return userinfoMapper.insertSelective(record);
    }

    @Override
    public Userinfo selectByPrimaryKey(String userId) {
        return userinfoMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int updateByPrimaryKeySelective(Userinfo record) {
        return userinfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Userinfo record) {
        return userinfoMapper.updateByPrimaryKey(record);
    }

}
