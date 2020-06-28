package com.enzenith.utils.helper;

import com.enzenith.utils.image.CaptchaUtil;

import javax.imageio.ImageIO;
import java.io.File;

/**
 *图片工具类
 * @author: Shupeng Lin
 * @date: 2019-11-14  上午 10:47
 **/
public final class ImageHelper {

    /**
     * 判断呢是否是图片
     * @param filePath  图片地址
     * @return: boolean 返回是否是图片
     * @author: Shupeng Lin
     * @date: 2019-11-13  下午 2:35
     **/
    public static boolean isImage(String filePath) {
        File imageFile = new File(filePath);
        if (!imageFile.exists()) {
            return false;
        }
        Image img = null;
        try {
            img = ImageIO.read(imageFile);
            if (img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            img = null;
        }
    }


    /**
     * 生产一张png格式的验证图片在指定的位置
     * @param randomStr 验证码
     * @param width  图片宽带
     * @param height 图片高度
     * @param file   文件位置
     * @return: boolean    是否成功
     * @author: Shupeng Lin
     * @date: 2019-11-13  下午 2:36
     **/
    public static boolean pngCaptcha(String randomStr, int width, int height, String file) {
        boolean pngCaptcha = CaptchaUtil.pngCaptcha(randomStr, width, height, file);
        return pngCaptcha;
    }


    /**
     * 生成一张png格式的验证码图片以base64编码返回
     * @param randomStr 验证码
     * @param width  图片宽带
     * @param height 图片高度
     * @return: java.lang.String    base64编码返回
     * @author: Shupeng Lin
     * @date: 2019-11-13  下午 2:37
     **/
    public static String pngCaptchaBase64(String randomStr, int width,int height) {
        String data = CaptchaUtil.pngCaptchaBase64(randomStr, width, height);
        return data;
    }

    /**
     * 生成一张gif的验证码
     * @param randomStr 验证码
     * @param width  图片宽带
     * @param height 图片高度
     * @param file   文件位置
     * @return: boolean    是否成功
     * @author: Shupeng Lin
     * @date: 2019-11-13  下午 2:37
     **/
    public static boolean gifCaptch(String randomStr,int width,int height, String file) {
        boolean gifCaptcha = CaptchaUtil.gifCaptcha(randomStr, width, height, file);
        return gifCaptcha;
    }

    /**
     * 生成一张gif的验证码
     * @param randomStr 验证码
     * @param width  图片宽带
     * @param height 图片高度
     * @return: java.lang.String    返回base64编码
     * @author: Shupeng Lin
     * @date: 2019-11-13  下午 2:39
     **/
    public static String gifCaptchBase64(String randomStr,int width,int height) {
        String data = CaptchaUtil.gifCaptchaBase64(randomStr, width, height);
        return data;
    }
}
