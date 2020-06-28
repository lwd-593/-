package com.enzenith.utils.image;

import java.awt.Color;


/**
 * 颜色相关的工具类
 * @author: Shupeng Lin
 * @date: 2019-11-14  上午 10:38
 **/
public final class ColorUtil {
    /**
     * 16进制转Color对象
     * color:RGB颜色
     *
     * @param str   字符串
     * @return
     */
    public final static Color String2Color(String str) {
        int i = Integer.parseInt(str.substring(1), 16);
        return new Color(i);
    }

    /**
     * Color对象转16进制
     *
     * @param color     颜色对象
     * @return
     */
    public final static String Color2String(Color color) {
        String red = Integer.toHexString(color.getRed());
        red = red.length() < 2 ? ('0' + red) : red;
        String blue = Integer.toHexString(color.getBlue());
        blue = blue.length() < 2 ? ('0' + blue) : blue;
        String green = Integer.toHexString(color.getGreen());
        green = green.length() < 2 ? ('0' + green) : green;
        return '#' + red + blue + green;
    }
}
