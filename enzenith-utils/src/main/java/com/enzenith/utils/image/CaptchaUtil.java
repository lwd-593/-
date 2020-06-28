package com.enzenith.utils.image;

import com.enzenith.utils.util.encrypt.Base64Ext;
import com.enzenith.utils.image.gif.GifEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;


/**
 * 具体用于实现生成验证码图片的方法
 *
 * @author: Shupeng Lin
 * @date: 2019-11-14  上午 10:36
 **/
public final class CaptchaUtil {

    /**
     * int型：255
     */
    private final static int NUM_TWO_FIV_FIV = 255;

    /**
     * int型：15
     */
    private final static int NUM_ONE_FIV = 255;

    /**
     * 字体
     */
    protected static Font font = new Font("Verdana", Font.ITALIC | Font.BOLD, 28);

    /**
     * 产生0--num的随机数,不包括num
     *
     * @param num 数字
     * @return int 随机数字
     */
    public static int num(int num) {
        return (new Random()).nextInt(num);
    }

    /**
     * 给定范围获得随机颜色
     *
     * @return Color 随机颜色
     */
    protected static Color color(int fc, int bc) {
        if (fc > NUM_TWO_FIV_FIV) {
            fc = 255;
        }
        if (bc > NUM_TWO_FIV_FIV) {
            bc = 255;
        }
        int r = fc + num(bc - fc);
        int g = fc + num(bc - fc);
        int b = fc + num(bc - fc);
        return new Color(r, g, b);
    }

    /**
     * 生成PNG的验证图片
     *
     * @param randomStr 随机字符串
     * @param width     宽度
     * @param height    高度
     * @param file      文件对象
     * @return
     */
    public static boolean pngCaptcha(String randomStr, int width, int height, String file) {
        char[] strs = randomStr.toCharArray();
        try (OutputStream out = new FileOutputStream(file)) {
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = (Graphics2D) bi.getGraphics();
            AlphaComposite ac3;
            Color color;
            int len = strs.length;
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, width, height);
            for (int i = 0; i < NUM_ONE_FIV; i++) {
                color = color(150, 250);
                g.setColor(color);
                g.drawOval(num(width), num(height), 5 + num(10), 5 + num(10));
            }
            g.setFont(font);
            int h = height - ((height - font.getSize()) >> 1),
                    w = width / len,
                    size = w - font.getSize() + 1;
            for (int i = 0; i < len; i++) {
                // 指定透明度
                ac3 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f);
                g.setComposite(ac3);
                // 对每个字符都用随机颜色
                color = new Color(20 + num(110), 30 + num(110), 30 + num(110));
                g.setColor(color);
                g.drawString(strs[i] + "", (width - (len - i) * w) + size, h - 4);
            }
            ImageIO.write(bi, "png", out);
            out.flush();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean gifCaptcha(String randomStr, int width, int height, String file) {
        char[] rands = randomStr.toCharArray();
        int len = rands.length;
        try (OutputStream out = new FileOutputStream(file)) {
            // gif编码类，这个利用了洋人写的编码类，所有类都在附件中
            GifEncoder gifEncoder = new GifEncoder();
            //生成字符
            gifEncoder.start(out);
            gifEncoder.setQuality(180);
            gifEncoder.setDelay(100);
            gifEncoder.setRepeat(0);
            BufferedImage frame;
            Color[] fontcolor = new Color[len];
            for (int i = 0; i < len; i++) {
                fontcolor[i] = new Color(20 + num(110), 20 + num(110), 20 + num(110));
            }
            for (int i = 0; i < len; i++) {
                frame = graphicsImage(fontcolor, rands, i, width, height, len);
                gifEncoder.addFrame(frame);
                frame.flush();
            }
            gifEncoder.finish();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 生成PNG的验证图片
     *
     * @param randomStr 随机字符串
     * @param width     宽度
     * @param height    高度
     * @return
     */
    public static String pngCaptchaBase64(String randomStr, int width, int height) {
        char[] strs = randomStr.toCharArray();
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = (Graphics2D) bi.getGraphics();
            AlphaComposite ac3;
            Color color;
            int len = strs.length;
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, width, height);
            for (int i = 0; i < NUM_ONE_FIV; i++) {
                color = color(150, 250);
                g.setColor(color);
                g.drawOval(num(width), num(height), 5 + num(10), 5 + num(10));
            }
            g.setFont(font);
            int h = height - ((height - font.getSize()) >> 1),
                    w = width / len,
                    size = w - font.getSize() + 1;
            for (int i = 0; i < len; i++) {
                // 指定透明度
                ac3 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f);
                g.setComposite(ac3);
                // 对每个字符都用随机颜色
                color = new Color(20 + num(110), 30 + num(110), 30 + num(110));
                g.setColor(color);
                g.drawString(strs[i] + "", (width - (len - i) * w) + size, h - 4);
            }
            ImageIO.write(bi, "png", out);
            return "data:image/png;base64," + new String(Base64Ext.encode(out.toByteArray(), Base64Ext.NO_WRAP));
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 生成GIF的验证图片
     *
     * @param randomStr 验证长度
     * @param width     宽度
     * @param height    高度
     * @return: java.lang.String
     * @author: Shupeng Lin
     * @date: 2019-11-13  下午 3:05
     **/
    public static String gifCaptchaBase64(String randomStr, int width, int height) {
        char[] rands = randomStr.toCharArray();
        int len = rands.length;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            // gif编码类，这个利用了洋人写的编码类，所有类都在附件中
            GifEncoder gifEncoder = new GifEncoder();
            //生成字符
            gifEncoder.start(out);
            gifEncoder.setQuality(180);
            gifEncoder.setDelay(100);
            gifEncoder.setRepeat(0);
            BufferedImage frame;
            Color[] fontcolor = new Color[len];
            for (int i = 0; i < len; i++) {
                fontcolor[i] = new Color(20 + num(110), 20 + num(110), 20 + num(110));
            }
            for (int i = 0; i < len; i++) {
                frame = graphicsImage(fontcolor, rands, i, width, height, len);
                gifEncoder.addFrame(frame);
                frame.flush();
            }
            gifEncoder.finish();
            return "data:image/gif;base64," + new String(Base64Ext.encode(out.toByteArray(), Base64Ext.NO_WRAP));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 画随机码图
     *
     * @param fontcolor 随机字体颜色
     * @param strs      字符数组
     * @param flag      透明度使用
     * @return BufferedImage
     */
    private static BufferedImage graphicsImage(Color[] fontcolor, char[] strs, int flag, int width, int height, int len) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //或得图形上下文
        Graphics2D g2d = (Graphics2D) image.getGraphics();
        //利用指定颜色填充背景
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);
        AlphaComposite ac3;
        int h = height - ((height - font.getSize()) >> 1);
        int w = width / len;
        g2d.setFont(font);
        for (int i = 0; i < len; i++) {
            ac3 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, getAlpha(flag, i, len));
            g2d.setComposite(ac3);
            g2d.setColor(fontcolor[i]);
            g2d.drawOval(num(width), num(height), 5 + num(10), 5 + num(10));
            g2d.drawString(strs[i] + "", (width - (len - i) * w) + (w - font.getSize()) + 1, h - 4);
        }
        g2d.dispose();
        return image;
    }

    /**
     * 获取透明度,从0到1,自动计算步长
     *
     * @return float 透明度
     */
    private static float getAlpha(int i, int j, int len) {
        int num = i + j;
        float r = (float) 1 / len, s = (len + 1) * r;
        return num > len ? (num * r - s) : num * r;
    }
}
