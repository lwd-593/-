package com.enzenith.test.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
*
* @author: LinWeiDa
* @date: 2020-04-26 11:19
 */
@ApiModel(value="com-enzenith-test-entity-Usercost")
@Data
public class Usercost implements Serializable {
    /**
    * 费用id
    */
    @ApiModelProperty(value="费用id")
    private String costId;

    /**
    * 水费
    */
    @ApiModelProperty(value="水费")
    private Double costWater;

    /**
    * 电费
    */
    @ApiModelProperty(value="电费")
    private Double costElectricity;

    /**
    * 物业费
    */
    @ApiModelProperty(value="物业费")
    private Double costTenement;

    /**
    * 公摊费
    */
    @ApiModelProperty(value="公摊费")
    private Double costAnnouncement;

    /**
    * 用户id
    */
    @ApiModelProperty(value="用户id")
    private String userId;

    /**
    * 创建时间
    */
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    private static final long serialVersionUID = 1L;
}