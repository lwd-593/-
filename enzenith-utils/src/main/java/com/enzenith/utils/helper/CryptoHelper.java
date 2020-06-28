package com.enzenith.utils.helper;

import com.enzenith.utils.util.encrypt.ASEUtil;
import com.enzenith.utils.util.encrypt.Base64Ext;
import com.enzenith.utils.util.encrypt.DESUtil;
import com.enzenith.utils.util.encrypt.Decode;

import java.math.BigInteger;
import java.security.MessageDigest;


/**
 * 提供常见的加密方法
 * @author: Shupeng Lin
 * @date: 2019-11-14  上午 10:39
 **/
public class CryptoHelper {



    /**
     * BASE64解密
     * @param key   钥匙（以什么解密）
     * @return: byte[]  字节数组
     * @author: Shupeng Lin
     * @date: 2019-11-13  下午 2:08
     **/
    public static byte[] decryptBASE64(String key) {
        return Base64Ext.decode(key.getBytes(), Base64Ext.NO_WRAP);
    }

    /**
     * BASE64加密
     *
     * @param key
     * @return
     */
    /**
     * BASE64加密
     * @param key   钥匙（以什么加密）
     * @return: java.lang.String
     * @author: Shupeng Lin
     * @date: 2019-11-13  下午 2:09
     **/
    public static String encryptBASE64(byte[] key) {
        return new String(Base64Ext.encode(key, Base64Ext.NO_WRAP));
    }


    /**
     * 字符串加密函数MD5实现
     * @param password      字符串（所需加密字符串）
     * @return: java.lang.String
     * @author: Shupeng Lin
     * @date: 2019-11-13  下午 2:10
     **/
    public final static String md5(String password) {
        MessageDigest md;
        try {
            // 生成一个MD5加密计算摘要
            md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(password.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            String pwd = new BigInteger(1, md.digest()).toString(16);
            return pwd;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;
    }

    /**
     * 提供ASE加密算法
     *
     * @param secretKey 秘钥
     * @param str       加密的字符串
     * @return string
     */
    public static String aesEncrypt(String secretKey, String str) {
        return Decode.str2HexStr(ASEUtil.AESEncode(secretKey, str));
    }

    /**
     * 提供ASE解密算法
     *
     * @param secretKey 秘钥
     * @param str       解密的字符串
     * @return decode str or null
     */
    public static String aseDecode(String secretKey, String str) {
        return ASEUtil.AESDncode(secretKey, Decode.hexStr2Str(str));
    }


    /**
     * 提供des加密算法
     *
     * @param secretKey 秘钥
     * @param str       加密的字符串
     * @return string
     */
    public static String desEncrypt(String secretKey, String str) {
        try {
            return DESUtil.encrypt(secretKey, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 提供des解密算法
     *
     * @param secretKey 秘钥
     * @param str       解密的字符串
     * @return decode str or null
     */
    public static String dseDecode(String secretKey, String str) {
        try {
            return DESUtil.decode(secretKey, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
