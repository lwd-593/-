package com.enzenith.common.sms.impl;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.enzenith.common.sms.SmsConfig;
import com.enzenith.common.sms.SmsSer;
import com.enzenith.common.sms.enums.SmsParamEnum;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;


/**
 * 阿里云短信服务
 *
 * @author hjb
 * @date 2019年2月21日
 */
public class AliyunSms implements SmsSer{


    private static SmsConfig smsConfig;


    public static void setSmsConfig(SmsConfig smsConfig) {
        AliyunSms.smsConfig = smsConfig;
    }

    @Override
    public boolean sendAuthCode(String phone, String code,String templateId) throws ClientException, JSONException {
        //设置超时时间-可自行调整
//		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
//		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化ascClient需要的几个参数
        String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
        String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
        //替换成你的AK
//		String accessKeyId = properties.getProperty("accessKeyId");//你的accessKeyId,参考本文档步骤2
//		String accessKeySecret = properties.getProperty("accessKeySecret");//你的accessKeySecret，参考本文档步骤2
        //初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",smsConfig.getAccessKeyID(),
                smsConfig.getSecretAccessKey());
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为国际区号+号码，如“85200000000”
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(smsConfig.getSingId());
        //必填:短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
        request.setTemplateCode(templateId);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        //request.setTemplateParam("{\"name\":\"Tom\", \"code\":\"123\"}");
        JSONObject json = new JSONObject();
        json.put("code", code);
        request.setTemplateParam(json.toString());
        //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        //request.setOutId("yourOutId");
        //请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if (sendSmsResponse.getCode() != null && "OK".equals(sendSmsResponse.getCode())) {
            //请求成功
            return true;
        }
        return false;
    }

    @Override
    public boolean sendMessage(String phone, Map<SmsParamEnum, String> params, String templateId) throws ClientException {
        //设置超时时间-可自行调整
//		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
//		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化ascClient需要的几个参数
        String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
        String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
        //替换成你的AK
//		String accessKeyId = properties.getProperty("accessKeyId");//你的accessKeyId,参考本文档步骤2
//		String accessKeySecret = properties.getProperty("accessKeySecret");//你的accessKeySecret，参考本文档步骤2
        //初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",smsConfig.getAccessKeyID(),
                smsConfig.getSecretAccessKey());
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为国际区号+号码，如“85200000000”
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(smsConfig.getSingId());
        //必填:短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
        request.setTemplateCode(templateId);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        //request.setTemplateParam("{\"name\":\"Tom\", \"code\":\"123\"}");
        JSONObject json = new JSONObject();
        params.entrySet().forEach(e->{
            try {
                json.put(e.getKey().getParam(),e.getValue());
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
        });
        request.setTemplateParam(json.toString());
        //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        //request.setOutId("yourOutId");
        //请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if (sendSmsResponse.getCode() != null && "OK".equals(sendSmsResponse.getCode())) {
            //请求成功
            return true;
        }
        return false;
    }


}
