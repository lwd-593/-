/**
 * @author: ZhangRenHuo
 * @date: 2019-12-09 17:50
 */
package com.enzenith.common.sms;

import com.enzenith.common.sms.impl.AliyunSms;
import com.enzenith.common.sms.impl.JdyunSms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 *
 * @author: ZhangRenHuo
 * @date: 2019-12-09 17:50
 */
@Component
public class SmsContextInitializer {

    @Autowired
    private SmsConfig smsConfig;

    @PostConstruct
    public void init() {
        SmsUtil.setSmsConfig(smsConfig);
        AliyunSms.setSmsConfig(smsConfig);
        JdyunSms.setSmsConfig(smsConfig);
    }
}
