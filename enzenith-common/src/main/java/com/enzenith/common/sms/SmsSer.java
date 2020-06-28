/**
 * @author: ZhangRenHuo
 * @date: 2019-12-09 14:05
 */
package com.enzenith.common.sms;

import com.aliyuncs.exceptions.ClientException;
import com.enzenith.common.sms.enums.SmsParamEnum;
import org.json.JSONException;

import java.util.Map;

/**
 *
 * @author: ZhangRenHuo
 * @date: 2019-12-09 14:05
 */
public interface SmsSer {

    /**
     * 验证码
     * @param phone
     * @param code
     * @return: boolean
     * @author: ZhangRenHuo
     * @date: 2019/12/11  10:29
     **/
    boolean sendAuthCode(String phone,String code,String templateId) throws ClientException, JSONException;

    /**
     * 通知短信
     * @param phone
     * @param params
     * @return: boolean
     * @author: ZhangRenHuo
     * @date: 2019/12/11  10:29
     **/
    boolean sendMessage(String phone, Map<SmsParamEnum,String> params, String templateId) throws ClientException, JSONException;
}
