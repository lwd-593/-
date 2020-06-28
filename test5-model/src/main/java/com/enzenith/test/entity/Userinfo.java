package com.enzenith.test.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
*
* @author: LinWeiDa
* @date: 2020-04-26 11:19
 */
@ApiModel(value="com-enzenith-test-entity-Userinfo")
@Data
public class Userinfo implements Serializable {
    /**
    * id
    */
    @ApiModelProperty(value="id")
    private String userId;

    /**
    * 用户名
    */
    @ApiModelProperty(value="用户名")
    private String userName;

    /**
    * 密码
    */
    @ApiModelProperty(value="密码")
    private String userPassword;

    /**
    * 电子邮箱
    */
    @ApiModelProperty(value="电子邮箱")
    private String userEmail;

    /**
    * 家庭住址
    */
    @ApiModelProperty(value="家庭住址")
    private String userAddress;

    /**
    * 性别
    */
    @ApiModelProperty(value="性别")
    private String userSex;

    /**
    * 状态
    */
    @ApiModelProperty(value="状态")
    private String userStatus;

    /**
    * 创建时间
    */
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Userinfo(String userId, String userName, String userPassword, String userEmail, String userAddress, String userSex, String userStatus, Date createTime) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userAddress = userAddress;
        this.userSex = userSex;
        this.userStatus = userStatus;
        this.createTime = createTime;
    }

    public Userinfo() {
    }
}