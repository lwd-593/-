/**
 * @author: ZhangRenHuo
 * @date: 2019-11-27 14:29
 */
package com.enzenith.common.sms.impl;

import com.aliyuncs.exceptions.ClientException;
import com.enzenith.common.sms.SmsConfig;
import com.enzenith.common.sms.SmsSer;
import com.enzenith.common.sms.enums.SmsParamEnum;
import com.jdcloud.sdk.auth.CredentialsProvider;
import com.jdcloud.sdk.auth.StaticCredentialsProvider;
import com.jdcloud.sdk.http.HttpRequestConfig;
import com.jdcloud.sdk.http.Protocol;
import com.jdcloud.sdk.service.sms.client.SmsClient;
import com.jdcloud.sdk.service.sms.model.BatchSendRequest;
import com.jdcloud.sdk.service.sms.model.BatchSendResponse;
import com.jdcloud.sdk.service.sms.model.BatchSendResult;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author: ZhangRenHuo
 * @date: 2019-11-27 14:29
 */
public class JdyunSms implements SmsSer{

    private static SmsConfig smsConfig;


    public static void setSmsConfig(SmsConfig smsConfig) {
        JdyunSms.smsConfig = smsConfig;
    }

    @Override
    public boolean sendAuthCode(String phone,String code,String templateId) {
        //创建SmsClient
        CredentialsProvider credentialsProvider = new StaticCredentialsProvider(smsConfig.getAccessKeyID(), smsConfig.getSecretAccessKey());
        SmsClient smsClient = SmsClient.builder()
                .credentialsProvider(credentialsProvider)
                .httpRequestConfig(new HttpRequestConfig.Builder().protocol(Protocol.HTTPS).build()) //默认为HTTPS
                .build();
        //设置请求参数
        BatchSendRequest request = new BatchSendRequest();
        request.regionId("cn-north-1");
        //签名id
        request.setSignId(smsConfig.getSingId());
        //短信模板id
        request.templateId(templateId);
        ArrayList<String> param = new ArrayList<String>();
        param.add(code);
        request.setParams(param);

        ArrayList<String> phoneList = new ArrayList<String>();
        phoneList.add(phone);
        //手机号码集合
        request.setPhoneList(phoneList);

        //4. 执行请求
        BatchSendResponse response = smsClient.batchSend(request);
        BatchSendResult result = response.getResult();
        return result.getStatus();
    }


    @Override
    public boolean sendMessage(String phone, Map<SmsParamEnum, String> params, String templateId) throws ClientException, JSONException {
        //创建SmsClient
        CredentialsProvider credentialsProvider = new StaticCredentialsProvider(smsConfig.getAccessKeyID(), smsConfig.getSecretAccessKey());
        SmsClient smsClient = SmsClient.builder()
                .credentialsProvider(credentialsProvider)
                .httpRequestConfig(new HttpRequestConfig.Builder().protocol(Protocol.HTTPS).build()) //默认为HTTPS
                .build();
        //设置请求参数
        BatchSendRequest request = new BatchSendRequest();
        request.regionId("cn-north-1");
        //签名id
        request.setSignId(smsConfig.getSingId());
        //短信模板id
        request.templateId(templateId);
        Collection<String> values = params.values();
        List<String> asList = new ArrayList<>(values);
        request.setParams(asList);
        ArrayList<String> phoneList = new ArrayList<String>();
        phoneList.add(phone);
        //手机号码集合
        request.setPhoneList(phoneList);
        //4. 执行请求
        BatchSendResponse response = smsClient.batchSend(request);
        BatchSendResult result = response.getResult();
        return result.getStatus();
    }

}
