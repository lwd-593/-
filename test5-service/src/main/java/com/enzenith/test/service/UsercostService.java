package com.enzenith.test.service;

import com.enzenith.test.entity.Usercost;
    /**
*
* @author: LinWeiDa
* @date: 2020-04-26 11:19
 */
public interface UsercostService{


    int deleteByPrimaryKey(String costId);

    int insert(Usercost record);

    int insertSelective(Usercost record);

    Usercost selectByPrimaryKey(String costId);

    int updateByPrimaryKeySelective(Usercost record);

    int updateByPrimaryKey(Usercost record);

}
