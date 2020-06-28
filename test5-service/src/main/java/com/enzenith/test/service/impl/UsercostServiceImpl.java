package com.enzenith.test.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.enzenith.test.mapper.UsercostMapper;
import com.enzenith.test.entity.Usercost;
import com.enzenith.test.service.UsercostService;
/**
*
* @author: LinWeiDa
* @date: 2020-04-26 11:19
 */
@Service
public class UsercostServiceImpl implements UsercostService{

    @Resource
    private UsercostMapper usercostMapper;

    @Override
    public int deleteByPrimaryKey(String costId) {
        return usercostMapper.deleteByPrimaryKey(costId);
    }

    @Override
    public int insert(Usercost record) {
        return usercostMapper.insert(record);
    }

    @Override
    public int insertSelective(Usercost record) {
        return usercostMapper.insertSelective(record);
    }

    @Override
    public Usercost selectByPrimaryKey(String costId) {
        return usercostMapper.selectByPrimaryKey(costId);
    }

    @Override
    public int updateByPrimaryKeySelective(Usercost record) {
        return usercostMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Usercost record) {
        return usercostMapper.updateByPrimaryKey(record);
    }

}
