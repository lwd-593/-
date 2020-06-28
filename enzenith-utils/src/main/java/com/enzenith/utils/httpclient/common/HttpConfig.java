package com.enzenith.utils.httpclient.common;

import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.protocol.HttpContext;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**   
 * 请求配置类
 *
 * @author: jikunle
 * @date:   2018年11月1日 下午2:47:37 
 */ 
public class HttpConfig {
	
	private HttpConfig(){};
	
	/**
	 * 获取实例
	 * @return	返回当前对象
	 */
	public static HttpConfig custom(){
		return new HttpConfig();
	}

	/**
	 * HttpClient对象
	 */
	private HttpClient client;
	
	/**
	 * Header头信息
	 */
	private Header[] headers;
	
	/**
	 * 是否返回response的headers
	 */
	private boolean isReturnRespHeaders;

	/**
	 * 请求方法
	 */
	private HttpMethods method=HttpMethods.GET;
	
	/**
	 * 请求方法名称
	 */
	private String methodName;

	/**
	 * 用于cookie操作
	 */
	private HttpContext context;

	
	/**
	 * 以json格式作为输入参数
	 */
	private String json;

	/**
	 * 输入输出编码
	 */
	private String encoding=Charset.defaultCharset().displayName();

	/**
	 * 输入编码
	 */
	private String inEnc;

	/**
	 * 输出编码
	 */
	private String outEnc;
	
	/**
	 * 设置RequestConfig
	 */
	private RequestConfig requestConfig;


	/**
	 * 解决多线程下载时，strean被close的问题
	 */
	private static final ThreadLocal<OutputStream> OUTS = new ThreadLocal<>();
	
	/**
	 * 解决多线程处理时，url被覆盖问题
	 */
	private static final ThreadLocal<String> URLS = new ThreadLocal<>();
	
	/**
	 * 解决多线程处理时，url被覆盖问题
	 */
	private static final ThreadLocal<Map<String,Object>> MAPS = new ThreadLocal<>();

	/**
	 * @param client	HttpClient对象
	 * @return 返回当前对象
	 */
	public HttpConfig client(HttpClient client) {
		this.client = client;
		return this;
	}
	
	/**
	 * @param url	资源url
	 * @return	返回当前对象
	 */
	public HttpConfig url(String url) {
		URLS.set(url);
		return this;
	}
	
	/**
	 * @param headers	Header头信息
	 * @return	返回当前对象
	 */
	public HttpConfig headers(Header[] headers) {
		this.headers = headers;
		return this;
	}
	
	/**
	 * Header头信息(是否返回response中的headers)
	 * 
	 * @param headers	Header头信息
	 * @param isReturnRespHeaders	是否返回response中的headers
	 * @return	返回当前对象
	 */
	public HttpConfig headers(Header[] headers, boolean isReturnRespHeaders) {
		this.headers = headers;
		this.isReturnRespHeaders=isReturnRespHeaders;
		return this;
	}
	
	/**
	 * @param method	请求方法
	 * @return	返回当前对象
	 */
	public HttpConfig method(HttpMethods method) {
		this.method = method;
		return this;
	}
	
	/**
	 * @param methodName	请求方法
	 * @return	返回当前对象
	 */
	public HttpConfig methodName(String methodName) {
		this.methodName = methodName;
		return this;
	}
	
	/**
	 * @param context	cookie操作相关
	 * @return	返回当前对象
	 */
	public HttpConfig context(HttpContext context) {
		this.context = context;
		return this;
	}
	
	/**
	 * @param map	传递参数
	 * @return	返回当前对象
	 */
	public HttpConfig map(Map<String, Object> map) {
		Map<String, Object> m = MAPS.get();
		if(m==null || m==null || map==null){
			m = map;
		}else {
			m.putAll(map);
		}
		MAPS.set(m);
		return this;
	}

	/**
	 * @param json	以json格式字符串作为参数
	 * @return	返回当前对象
	 */
	public HttpConfig json(String json) {
		this.json = json;
		Map<String, Object> map = new HashMap<>(16);
		map.put(Utils.ENTITY_JSON, json);
		MAPS.set(map);
		return this;
	}

	/**
	 * 以inputStream格式字符串作为参数
	 * @param inputStream inputStream流
	 * @return com.enzenith.utils.httpclient.common.HttpConfig 当前对象
	 * @author jikunle
	 * @date 2019/10/7 9:42
	 */
	public HttpConfig inputStream(InputStream inputStream) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Utils.ENTITY_INPUTSTREAM, inputStream);
		MAPS.set(map);
		return this;
	}

	/**
	 * 构造引征自定义文件服务器参数
	 * @param inputStream 文件流
	 * @param json 附加实体参数（文件描述，权限等）
	 * @return com.enzenith.utils.httpclient.common.HttpConfig
	 * @author jikunle
	 * @date 2020/4/20 11:29
	 */
/*	public HttpConfig eos(InputStream inputStream, String json) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Utils.ENTITY_EOS + ".inputStream", inputStream);
		map.put(Utils.ENTITY_EOS + ".body", json);
		MAPS.set(map);
		return this;
	}*/
	
	/**
	 * @param filePaths	待上传文件所在路径
	 * @return	返回当前对象
	 */
	public HttpConfig files(String[] filePaths) {
		return files(filePaths, "file");
	}
	/**
	 * 上传文件时用到
	 * @param filePaths	待上传文件所在路径
	 * @param inputName	即file input 标签的name值，默认为file
	 * @return	返回当前对象
	 */
	public HttpConfig files(String[] filePaths, String inputName) {
		return files(filePaths, inputName, false);
	}
	/**
	 * 上传文件时用到
	 * @param filePaths		待上传文件所在路径
	 * @param inputName		即file input 标签的name值，默认为file
	 * @param forceRemoveContentTypeChraset	是否强制一处content-type中设置的编码类型
	 * @return	返回当前对象
	 */
	public HttpConfig files(String[] filePaths, String inputName, boolean forceRemoveContentTypeChraset) {
		Map<String, Object> m = MAPS.get();
		if(m==null || m==null){
			m = new HashMap<>(16);
		}
		m.put(Utils.ENTITY_MULTIPART, filePaths);
		m.put(Utils.ENTITY_MULTIPART +".name", inputName);
		m.put(Utils.ENTITY_MULTIPART +".rmCharset", forceRemoveContentTypeChraset);
		MAPS.set(m);
		return this;
	}

	/**
	 * 构造File类型的httpConfig
	 * @param file 上传的文件
	 * @return com.enzenith.utils.httpclient.common.HttpConfig
	 * @author jikunle
	 * @date 2019/10/7 17:17
	 */
	public HttpConfig files(File file) {
		Map<String, Object> m = MAPS.get();
		if(m==null || m==null){
			m = new HashMap<String, Object>();
		}
		m.put(Utils.ENTITY_FILE, file);
		MAPS.set(m);
		return this;
	}
	
	/**
	 * @param encoding	输入输出编码
	 * @return	返回当前对象
	 */
	public HttpConfig encoding(String encoding) {
		//设置输入输出
		inEnc(encoding);
		outEnc(encoding);
		this.encoding = encoding;
		return this;
	}
	
	/**
	 * @param inEnc	输入编码
	 * @return	返回当前对象
	 */
	public HttpConfig inEnc(String inEnc) {
		this.inEnc = inEnc;
		return this;
	}
	
	/**
	 * @param outEnc	输出编码
	 * @return	返回当前对象
	 */
	public HttpConfig outEnc(String outEnc) {
		this.outEnc = outEnc;
		return this;
	}
	
	/**
	 * @param out	输出流对象
	 * @return	返回当前对象
	 */
	public HttpConfig out(OutputStream out) {
		OUTS.set(out);
		return this;
	}
	
	/**
	 * 设置超时时间
	 * 
	 * @param timeout		超市时间，单位-毫秒
	 * @return	返回当前对象
	 */
	public HttpConfig timeout(int timeout){
		return timeout(timeout, true);
	}
	
	/**
	 * 设置超时时间以及是否允许网页重定向（自动跳转 302）
	 * 
	 * @param timeout		超时时间，单位-毫秒
	 * @param redirectEnable		自动跳转
	 * @return	返回当前对象
	 */
	public HttpConfig timeout(int timeout,  boolean redirectEnable){
		// 配置请求的超时设置
		RequestConfig config = RequestConfig.custom()
				.setConnectionRequestTimeout(timeout)
				.setConnectTimeout(timeout)
				.setSocketTimeout(timeout)
				.setRedirectsEnabled(redirectEnable)
				.build();
		return timeout(config);
	}
	
	/**
	 * 设置代理、超时时间、允许网页重定向等
	 * 
	 * @param requestConfig		超时时间，单位-毫秒
	 * @return	返回当前对象
	 */
	public HttpConfig timeout(RequestConfig requestConfig){
		this.requestConfig = requestConfig;
		return this;
	}
	
	public HttpClient client() {
		return client;
	}
	
	public Header[] headers() {
		return headers;
	}
	public boolean isReturnRespHeaders() {
		return isReturnRespHeaders;
	}
	
	public String url() {
		return URLS.get();
	}

	public HttpMethods method() {
		return method;
	}

	public String methodName() {
		return methodName;
	}

	public HttpContext context() {
		return context;
	}

	public Map<String, Object> map() {
		return MAPS.get();
	}

	public String json() {
		return json;
	}
	
	public String encoding() {
		return encoding;
	}

	public String inEnc() {
		return inEnc == null ? encoding : inEnc;
	}

	public String outEnc() {
		return outEnc == null ? encoding : outEnc;
	}

	public OutputStream out() {
		return OUTS.get();
	}

	public RequestConfig requestConfig() {
		return requestConfig;
	}
}