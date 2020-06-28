/**
 * @author: ZhangRenHuo
 * @date: 2019-12-09 16:50
 */
package com.enzenith.common.sms;

import com.aliyuncs.exceptions.ClientException;
import com.enzenith.common.sms.enums.SmsEnum;
import com.enzenith.common.sms.enums.SmsParamEnum;
import org.json.JSONException;

import java.util.Map;

/**
 *
 * @author: ZhangRenHuo
 * @date: 2019-12-09 16:50
 */
public class SmsUtil {
    /**
     *短信配置信息
     */
    private static SmsConfig smsConfig;

    public static void setSmsConfig(SmsConfig smsConfig) {
        SmsUtil.smsConfig = smsConfig;
    }
    /**
     *发送短信验证码
     * @param phone
     * @param code
     * @param templateId
     * @return: boolean
     * @author: HuangJinBao
     * @date: 2019-12-20  11:19
     **/
    public static boolean sendAuthCode(String phone,String code,String templateId) throws ClientException, JSONException {
        SmsSer smsSer = SmsEnum.getSmsSer(smsConfig.getIndex());
        boolean b=smsSer.sendAuthCode(phone,code,templateId);
        return b;
    }
    /**
     *发送短信通知信息
     * @param phone
     * @param params
     * @param templateId
     * @return: boolean
     * @author: HuangJinBao
     * @date: 2019-12-20  11:19
     **/
    public static boolean sendMessage(String phone, Map<SmsParamEnum,String> params, String templateId) throws ClientException, JSONException {
        SmsSer smsSer = SmsEnum.getSmsSer(smsConfig.getIndex());
        boolean b=smsSer.sendMessage(phone,params,templateId);
        return b;
    }

}
