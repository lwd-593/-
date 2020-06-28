/**
 * @author: ZhangRenHuo
 * @date: 2019-12-09 10:25
 */
package com.enzenith.common.sms.enums;

import com.enzenith.common.sms.SmsSer;
import com.enzenith.common.sms.impl.AliyunSms;
import com.enzenith.common.sms.impl.JdyunSms;

/**
 *
 * @author: ZhangRenHuo
 * @date: 2019-12-09 10:25
 */
public enum SmsEnum {
    //阿里云
    ALIYUN(0,new AliyunSms()),
    //京东云
    JDYUN(1,new JdyunSms());

    SmsEnum(Integer index, SmsSer smsSer) {
        this.index = index;
        this.smsSer = smsSer;
    }

    private Integer index;

    private SmsSer smsSer;

    public  static SmsSer getSmsSer(Integer index){
        for (SmsEnum value : SmsEnum.values()) {
            if (value.index.equals(index)){
                return value.smsSer;
            }
        }
        return null;
    }

}
