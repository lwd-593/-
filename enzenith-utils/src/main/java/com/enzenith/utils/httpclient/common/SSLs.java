package com.enzenith.utils.httpclient.common;

import com.enzenith.utils.httpclient.exception.HttpProcessException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.nio.conn.ssl.SSLIOSessionStrategy;
import org.apache.http.ssl.SSLContexts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

/**   
 * 设置ssl
 *
 * @author: jikunle
 * @date:   2018年11月1日 下午2:50:59 
 */ 
public class SSLs {

    private static final SSLHandler  SIMPLE_VERIFIER = new SSLHandler();
	private static SSLSocketFactory sslFactory ;
	private static SSLConnectionSocketFactory sslConnFactory ;
	private static SSLIOSessionStrategy sslIOSessionStrategy ;
	private static SSLs sslutil = new SSLs();
	private SSLContext sc;
	
	public static SSLs getInstance(){
		return sslutil;
	}
	public static SSLs custom(){
		return new SSLs();
	}

	/**
	 * 	重写X509TrustManager的三个方法,信任服务器证书
 	 */
    private static class SSLHandler implements  X509TrustManager, HostnameVerifier{
		
		@Override
		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			return new java.security.cert.X509Certificate[]{};
		}
		
		@Override
		public void checkServerTrusted(java.security.cert.X509Certificate[] chain,
				String authType) throws CertificateException {
		}
		
		@Override
		public void checkClientTrusted(java.security.cert.X509Certificate[] chain,
				String authType) throws CertificateException {
		}

		@Override
		public boolean verify(String paramString, SSLSession paramSSLSession) {
			return true;
		}
	};
    

	/**
	 * 信任主机
	 * @return: javax.net.ssl.HostnameVerifier
	 * @author: Shupeng Lin
	 * @date: 2019-11-14  上午 11:55
	 **/
    public static HostnameVerifier getVerifier() {
        return SIMPLE_VERIFIER;
    }
    
    public synchronized SSLSocketFactory getSSLSF(SSLProtocolVersion sslpv) throws HttpProcessException {
        if (sslFactory != null) {
			return sslFactory;
		}
		try {
			SSLContext sc = getSSLContext(sslpv);
			sc.init(null, new TrustManager[] { SIMPLE_VERIFIER }, null);
			sslFactory = sc.getSocketFactory();
		} catch (KeyManagementException e) {
			throw new HttpProcessException(e);
		}
        return sslFactory;
    }
    
    public synchronized SSLConnectionSocketFactory getSSLCONNSF(SSLProtocolVersion sslpv) throws HttpProcessException {
    	if (sslConnFactory != null) {
			return sslConnFactory;
		}
    	try {
	    	SSLContext sc = getSSLContext(sslpv);
	    	sc.init(null, new TrustManager[] { SIMPLE_VERIFIER }, new java.security.SecureRandom());
	    	sslConnFactory = new SSLConnectionSocketFactory(sc, SIMPLE_VERIFIER);
		} catch (KeyManagementException e) {
			throw new HttpProcessException(e);
		}
    	return sslConnFactory;
    }
    
    public synchronized SSLIOSessionStrategy getSSLIOSS(SSLProtocolVersion sslpv) throws HttpProcessException {
    	if (sslIOSessionStrategy != null) {
			return sslIOSessionStrategy;
		}
		try {
			SSLContext sc = getSSLContext(sslpv);
	    	sc.init(null, new TrustManager[] { SIMPLE_VERIFIER }, new java.security.SecureRandom());
			sslIOSessionStrategy = new SSLIOSessionStrategy(sc, SIMPLE_VERIFIER);
		} catch (KeyManagementException e) {
			throw new HttpProcessException(e);
		}
    	return sslIOSessionStrategy;
    }
    
    public SSLs customSSL(String keyStorePath, String keyStorepass) throws HttpProcessException{
    	FileInputStream instream =null;
    	KeyStore trustStore = null; 
		try {
			trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			instream = new FileInputStream(new File(keyStorePath));
			trustStore.load(instream, keyStorepass.toCharArray());
			// 相信自己的CA和所有自签名的证书
			sc= SSLContexts.custom().loadTrustMaterial(trustStore, new TrustSelfSignedStrategy()) .build();
		} catch (KeyManagementException e) {
			throw new HttpProcessException(e);
		} catch (KeyStoreException e) {
			throw new HttpProcessException(e);
		} catch (FileNotFoundException e) {
			throw new HttpProcessException(e);
		} catch (NoSuchAlgorithmException e) {
			throw new HttpProcessException(e);
		} catch (CertificateException e) {
			throw new HttpProcessException(e);
		} catch (IOException e) {
			throw new HttpProcessException(e);
		}finally{
			try {
				instream.close();
			} catch (IOException e) {}
		}
		return this;
    }
    
    public SSLContext getSSLContext(SSLProtocolVersion sslpv) throws HttpProcessException{
    	try {
    		if(sc==null){
    			sc = SSLContext.getInstance(sslpv.getName());
    		}
    		return sc;
    	} catch (NoSuchAlgorithmException e) {
    		throw new HttpProcessException(e);
    	}
    }
    
    /**
     * The SSL protocol version (SSLv3, TLSv1, TLSv1.1, TLSv1.2)
     * 
     * @version 1.0
     */
    public static enum SSLProtocolVersion{
		/**
		 * SSL
		 */
		SSL("SSL"),
		/**
		 * SSLv3
		 */
    	SSLv3("SSLv3"),
		/**
		 * TLSv1
		 */
    	TLSv1("TLSv1"),

		/**
		 * TLSv1.1
		 */
    	TLSv1_1("TLSv1.1"),
		/**
		 * TLSv1.2
		 */
    	TLSv1_2("TLSv1.2"),
    	;
    	private String name;
    	private SSLProtocolVersion(String name){
    		this.name = name;
    	}
    	public String getName(){
    		return this.name;
    	}
    	public static SSLProtocolVersion find(String name){
    		for (SSLProtocolVersion pv : SSLProtocolVersion.values()) {
				if(pv.getName().toUpperCase().equals(name.toUpperCase())){
					return pv;
				}
			}
    		throw new RuntimeException("未支持当前ssl版本号："+name);
    	}
    	
    }
}