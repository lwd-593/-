package com.enzenith.common.file;


/**   
 * @ClassName:  FileBean   
 * @Description:上传文件bean    
 *   
 * @author: jikunle
 * @date:   2018年11月27日 下午4:22:16 
 */ 
public class FileBean {
	
	public FileBean(){
		
	}
	public FileBean(String fileName, String base64Str){
		this.fileName = fileName;
		this.base64Str = base64Str;
	}
	public FileBean(String fileName, String base64Str, String filePath){
		this.fileName = fileName;
		this.base64Str = base64Str;
		this.filePath = filePath;
	}

	/**   
	 * @Fields fileName : 文件名
	 */ 
	private String fileName;
	/**   
	 * @Fields base64Str : base64文件
	 */ 
	private String base64Str;
	/**   
	 * @Fields filePath : 文件路径
	 */ 
	private String filePath;
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getBase64Str() {
		return base64Str;
	}
	public void setBase64Str(String base64Str) {
		this.base64Str = base64Str;
	}
}
