package com.enzenith.common.file;

import com.enzenith.common.config.SysProp;
import com.enzenith.common.constants.ResultBean;
import com.enzenith.utils.httpclient.HttpClientUtil;
import com.enzenith.utils.httpclient.builder.HCB;
import com.enzenith.utils.httpclient.common.HttpConfig;
import com.enzenith.utils.httpclient.exception.HttpProcessException;
import com.enzenith.utils.util.FileUtil;
import com.enzenith.utils.util.JsonUtils;
import com.enzenith.utils.util.StringUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 文件工具类
 * @author: jikunle
 * @date: 2019-11-12  下午 5:39
 **/
public class HttpFileUtils {

    private static SysProp sysprop;

    public static void setSysProp(SysProp sysprop) {
        HttpFileUtils.sysprop = sysprop;
    }
	 

	/**
	 * 发送post请求
	 * @param fileJsonStr	上传文件Json（文件名，base64字符串）
	 * @return: java.lang.String	返回字符串
	 * @throws HttpProcessException
	 * @throws IOException
	 * @author: jikunle
	 * @date: 2019-11-12  下午 5:30
	 **/
	public static String httpPost(String fileJsonStr) throws HttpProcessException, IOException{
		String url = sysprop.getFileServerUrl() + "uploadNew";
		HCB hcb = HCB.custom();
		String result = HttpClientUtil.post(HttpConfig.custom()
									.url(url)
									.encoding("UTF-8")
									.json(fileJsonStr)
									.client(hcb.build()));
		return result;
	}
	

	/**
	 * 发送get请求
	 * @param uri
	 * @return: java.lang.String
	 * @throws HttpProcessException
	 * @throws IOException
	 * @author: jikunle
	 * @date: 2019-11-12  下午 5:31
	 **/
	public static String httpGet(String uri) throws HttpProcessException, IOException{
		HCB hcb = HCB.custom();
		String result = HttpClientUtil.get(HttpConfig.custom()
									.url(uri)
									.encoding("UTF-8")
									.client(hcb.build()));
		return result;
	}
	
	

	/**
	 * 反射httpResultBean
	 * @param httpResp		上传文件后文件服务器的返回信息
	 * @return: com.enzenith.common.constants.ResultBean<java.util.List<com.enzenith.common.file.FileBean>>
	 * @author: jikunle
	 * @date: 2019-11-12  下午 5:32
	 **/
	public static ResultBean<List<FileBean>> getHttpResultBean(String httpResp){
		if(null == httpResp || httpResp.isEmpty()){
			return null;
		}
		return JsonUtils.jsonTypeReferenceToPojo(httpResp, new TypeReference<ResultBean<List<FileBean>>>() {
		});
	}
	

	/**
	 * 通过文件名获取上传的文件路径
	 * @param httpResp				上传文件后文件服务器的返回信息
	 * @param targetFileName		要查询的文件名
	 * @return: java.lang.String	文件路径
	 * @author: jikunle
	 * @date: 2019-11-12  下午 5:32
	 **/
	public static String getFilePath(String httpResp, String targetFileName){
		ResultBean<List<FileBean>> resultBean = getHttpResultBean(httpResp);
        if(null == resultBean){
        	return null;
        }
		return getFilePath(resultBean, targetFileName);
	}
	

	/**
	 * 通过文件名获取上传的文件路径
	 * @param httpResp				上传文件后文件服务器的返回信息
	 * @param targetFileName		要查询的文件名
	 * @return: java.lang.String		返回字符串
	 * @author: jikunle
	 * @date: 2019-11-12  下午 5:33
	 **/
	private static String getFilePath(ResultBean<List<FileBean>> httpResp, String targetFileName){
	    String filePath = null;
	    
    	List<FileBean> respFileList = httpResp.getData();
    	if(null != respFileList && respFileList.size() > 0){
    		if(targetFileName != null){
    			for(FileBean file : respFileList){
    				String fileName = file.getFileName();
    				if(fileName.equals(targetFileName.replaceAll("\\s+", ""))){
    					filePath = file.getFilePath();
    					break;
    				}
    			}
    		}else{
    			if(respFileList.size() == 1){
    				FileBean file = respFileList.get(0);
    				if(null != file){
    					return file.getFilePath();
    				}
    			}
    		}
    	}
		return filePath;
	}
	

	/**
	 * 获取resource.properties内属性值
	 * @param key		key值
	 * @return: java.lang.String	返回字符串
	 * @author: jikunle
	 * @throws IOException
	 * @date: 2019-11-12  下午 5:33
	 **/
	private static String getResourcePropertiesValue(String key) throws IOException{
		InputStream in = HttpFileUtils.class.getClassLoader().getResourceAsStream("resource/resource.properties");
		Properties p = new Properties();
		p.load(in);
		String value = p.getProperty(key);
		return value;
	}

	/**
	 * 	到文件服务器获取,根据文件名称获取文件图片路径
	 * @param fileName		文件名
	 * @return: java.lang.String
	 * @author: jikunle
	 * @throws IOException
	 * @throws HttpProcessException
	 * @date: 2019-11-12  下午 5:34
	 **/
	public static String getFileUriByFileName(String fileName) throws IOException, HttpProcessException{
		String filePath = sysprop.getFileServerUrl() + "getFileUri" + "/";
		String fileGetPath = filePath + fileName;
		String fileUri = null;
		//根据图片名称获得图片uri
		String httpResp = httpGet(fileGetPath);
		if(null == httpResp || httpResp.isEmpty()){
			return null;
		}
		ResultBean<String> resultBean = JsonUtils.jsonTypeReferenceToPojo(httpResp, new TypeReference<ResultBean<String>>() {
		});
		if(null != resultBean){
			fileUri = resultBean.getData();
		}
		return fileUri;
	}
	
	

	/**
	 * 多MultipartFile文件转上传json字符串
	 * @param files			文件对象集合
	 * @return: java.lang.String	返回json
	 * @author: jikunle
	 * @date: 2019-11-12  下午 5:35
	 **/
	public static String makeFileJsonStr(List<MultipartFile> files){
		if(null == files){
			return null;
		}
		List<FileBean> fileBeanList = new ArrayList<FileBean>();
		fileBeanList = constructFileBeanList(fileBeanList, files);
		String jsonStr = JsonUtils.objectToJson(fileBeanList);
		return jsonStr;
	}
	

	/**
	 * byte数组转上传json字符串
	 * @param fileByte		文件字节
	 * @param fileName		文件名
	 * @return: java.lang.String
	 * @author: jikunle
	 * @date: 2019-11-12  下午 5:36
	 **/
	public static String makeFileJsonStr(byte[] fileByte, String fileName){
		if(null == fileByte || fileByte.length == 0 || null == fileName || fileName.isEmpty()){
			return null;
		}
		String base64Str = FileUtil.byteToBase64(fileByte);
		String jsonStr = makeFileJsonStr(base64Str, fileName);
		return jsonStr;
	}
	

	/**
	 * baseB4文件转上传json字符串
	 * @param base64Str		base64Str字符串
	 * @param fileName		文件名
	 * @return: java.lang.String	放回json字符串
	 * @author: jikunle
	 * @date: 2019-11-12  下午 5:36
	 **/
	public static String makeFileJsonStr(String base64Str, String fileName){
		if(null == base64Str || base64Str.isEmpty() || null == fileName || fileName.isEmpty()){
			return null;
		}
		List<FileBean> fileBeanList = new ArrayList<FileBean>();
		FileBean fileBean = new FileBean(fileName, base64Str);
		fileBeanList.add(fileBean);
		String jsonStr = JsonUtils.objectToJson(fileBeanList);
		return jsonStr;
	}

	/**
	 * MultipartFile文件转上传json字符串
	 * @param file			文件对象
	 * @return: java.lang.String	返回json字符串
	 * @author: jikunle
	 * @date: 2019-11-12  下午 5:37
	 **/
	public static String makeFileJsonStr(MultipartFile file){
		if(null == file){
			return null;
		}
		List<MultipartFile> files = new ArrayList<>();
		files.add(file);
        String jsonStr = makeFileJsonStr(files);
        return jsonStr;
	}

    /**
	 * MultipartFile文件转上传json字符串,自定义文件名
     * @param file		文件对象
     * @param fileName 自定义文件名
     * @return java.lang.String
     * @author jikunle
     * @date 2019/4/16 10:27
     */
    public static String makeFileJsonStr(MultipartFile file, String fileName){
        if(null == file || StringUtil.isEmpty(fileName)){
            return null;
        }
        String base64Str = FileUtil.multipartFileToBase64(file);
        String jsonStr = makeFileJsonStr(base64Str, fileName);
        return jsonStr;
    }
	
	
 
	/**
	 * 构建List<FileBean>
	 * @param fileBeanList		文件对象实体类
	 * @param files				文件对象集合
	 * @return: java.util.List<com.enzenith.common.file.FileBean>
	 * @author: jikunle
	 * @date: 2019-11-12  下午 5:37 
	 **/
	private static List<FileBean> constructFileBeanList(List<FileBean> fileBeanList, List<MultipartFile> files){
		for(MultipartFile file : files){
			fileBeanList = constructFileBeanList(fileBeanList, file);
		}
		return fileBeanList;
		
	}
	
	 
	/**
	 * 构建List<FileBean>
	 * @param fileBeanList		文件对象实体类
	 * @param file				文件对象
	 * @return: java.util.List<com.enzenith.common.file.FileBean>
	 * @author: jikunle
	 * @date: 2019-11-12  下午 5:38 
	 **/
	private static List<FileBean> constructFileBeanList(List<FileBean> fileBeanList, MultipartFile file){
		String fileName = file.getOriginalFilename().replaceAll("\\s+", "");
		String base64Str = FileUtil.multipartFileToBase64(file);
		FileBean fileBean = new FileBean(fileName, base64Str);
		fileBeanList.add(fileBean);
		return fileBeanList;
	}

}
