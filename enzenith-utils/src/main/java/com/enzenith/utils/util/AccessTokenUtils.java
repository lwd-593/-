package com.enzenith.utils.util;
 
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
 
/**
 * ClassName: 获取调用接口凭证access_token
 * @Description: TODO
 * @author hjb
 * @date 2019年1月11日
 */
public class AccessTokenUtils {	
	
	/**全局token 所有与微信有交互的前提 */
	public static String ACCESS_TOKEN;
	
	/**全局token上次获取事件 */
	public static long LASTTOKENTIME;

	/**
	 * 7000
	 */
	private final static int  SEVEN_THOUSAND = 7000;

	/**
	 * 1000
	 */
	private final static int  ONE_THOUSAND = 7000;

	/**全局ticket*/
	public static String JS_API_TICKET;

	/**全局ticket上次获取事件 */
	public static long LAST_TICKET_TIME;
	/**
	 * 获取全局token方法
	 * 该方法通过使用HttpClient发送http请求，HttpGet()发送请求
	 * 微信返回的json中access_token是我们的全局token
	   * @param appid
	 * @param secret
	 * @return java.lang.String
	 * @date 2019/4/9 0009 18:59
	 * @author hjb
	 */
	public static synchronized String getAccess_token(String appid,String secret){
		if(ACCESS_TOKEN == null || System.currentTimeMillis() - LASTTOKENTIME > SEVEN_THOUSAND*ONE_THOUSAND){
			try {
				//请求access_token地址
				String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+secret;
				//创建提交方式
				HttpGet httpGet = new HttpGet(url);
				//获取到httpclien
				HttpClient httpClient = new DefaultHttpClient();
				//发送请求并得到响应
				HttpResponse response = httpClient.execute(httpGet);
				//判断请求是否成功
				if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
					//将得到的响应转为String类型
					String str = EntityUtils.toString(response.getEntity(), "utf-8");
					//字符串转json
					JSONObject jsonObject = new JSONObject(str);
					//输出access_token
					System.out.println((String) jsonObject.get("access_token")+"时间"+String.valueOf(jsonObject.get("expires_in")));
					//给静态变量赋值，获取到access_token
					ACCESS_TOKEN = (String) jsonObject.get("access_token");
					//给获取access_token时间赋值，方便下此次获取时进行判断
					LASTTOKENTIME = System.currentTimeMillis();
				}
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return ACCESS_TOKEN;
	}

	/**
	 * 获取全局ticket方法
	 * 该方法通过使用HttpClient发送http请求，HttpGet()发送请求
	 */
	public static synchronized String getJsApiTicket(String appid,String secret){
		if(JS_API_TICKET == null || System.currentTimeMillis() - LAST_TICKET_TIME > SEVEN_THOUSAND*ONE_THOUSAND){
			try {
				String token=AccessTokenUtils.getAccess_token(appid,secret);
				//请求ticket地址
				String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+token+"&type=jsapi";
				//创建提交方式
				HttpGet httpGet = new HttpGet(url);
				/*HttpClient httpClient = new DefaultHttpClient();*/
				//获取DefaultHttpClient请求
				HttpClient httpClient = HttpClientBuilder.create().build();
				//发送请求并得到响应
				HttpResponse response = httpClient.execute(httpGet);
				//判断请求是否成功
				if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
					//将得到的响应转为String类型
					String str = EntityUtils.toString(response.getEntity(), "utf-8");
					//字符串转json
					JSONObject jsonObject = new JSONObject(str);
					//输出ticket
					System.out.println("***ticket***"+(String) jsonObject.get("ticket")+"***ticket时间***"+String.valueOf(jsonObject.get("expires_in")));
					//给静态变量赋值，获取到ticket
					JS_API_TICKET = (String) jsonObject.get("ticket");
					//给获取ticket时间赋值，方便下此次获取时进行判断
					LAST_TICKET_TIME = System.currentTimeMillis();
				}
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return JS_API_TICKET;
	}
}
