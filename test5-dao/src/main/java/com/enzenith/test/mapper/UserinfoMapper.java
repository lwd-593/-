package com.enzenith.test.mapper;

import com.enzenith.test.entity.Userinfo;

/**
*
* @author: LinWeiDa
* @date: 2020-04-26 11:19
 */
public interface UserinfoMapper {
    int deleteByPrimaryKey(String userId);

    int insert(Userinfo record);

    int insertSelective(Userinfo record);

    Userinfo selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(Userinfo record);

    int updateByPrimaryKey(Userinfo record);
}