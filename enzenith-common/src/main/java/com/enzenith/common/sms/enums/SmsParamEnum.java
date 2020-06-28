package com.enzenith.common.sms.enums;

/**
 * @author: ZhangRenHuo
 * @date: 2019-12-25 17:15
 */
public enum SmsParamEnum {

    //名称
    name("name"),
    //号码
    code("code"),
    //时间
    date("date"),
    //时间
    time("time"),
    //地址名称
    address("address"),
    //订单号
    orderNum("ordernum"),
    ;

    private String param;

    SmsParamEnum(String param){
        this.param = param;
    }

    public String getParam() {
        return param;
    }
}
