package com.enzenith.utils.algorithm.impl;

import com.enzenith.UtilsConstant;
import com.enzenith.utils.util.SysUtil;

import java.io.UnsupportedEncodingException;


/**
 * 对比俩个字符串的相似度
 * @author: Shupeng Lin
 * @date: 2019-11-14  上午 10:50
 **/
public class StringImpl {

    /**
     * 对比俩个字符串的相似度
     *    第一种实现方式
     * @param strOne        字符串1
     * @param strTwo        字符串2
     * @return: java.lang.String
     * @author: Shupeng Lin
     * @date: 2019-11-14  上午 11:55
     **/
    private static String longestCommonSubstring(String strOne, String strTwo) {
        char[] charsStrOne = strOne.toCharArray();
        char[] charsStrTwo = strTwo.toCharArray();
        int m = charsStrOne.length;
        int n = charsStrTwo.length;
        int[][] matrix = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (charsStrOne[i - 1] == charsStrTwo[j - 1]) {
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                } else {
                    matrix[i][j] = Math.max(matrix[i][j - 1], matrix[i - 1][j]);
                }
            }
        }
        char[] result = new char[matrix[m][n]];
        int currentIndex = result.length - 1;
        while (matrix[m][n] != 0) {
            if (matrix[n] == matrix[n - 1]) {
                n--;
            } else if (matrix[m][n] == matrix[m - 1][n]) {
                m--;
            } else {
                result[currentIndex] = charsStrOne[m - 1];
                currentIndex--;
                n--;
                m--;
            }
        }
        return new String(result);
    }

    private static boolean charReg(char charValue) {
        return (charValue >= 0x4E00 && charValue <= 0X9FA5) || (charValue >= 'a' && charValue <= 'z') || (charValue >= 'A' && charValue <= 'Z') || (charValue >= '0' && charValue <= '9');
    }

    private static String removeSign(String str) {
        StringBuffer sb = new StringBuffer();
        for (char item : str.toCharArray()) {
            if (charReg(item)) {
                sb.append(item);
            }
        }
        return sb.toString();
    }

    /**
     * 快速比较俩个字符串的相似度
     *
     * @param charsStrOne 较长的字符串
     * @param charsStrTwo 较短的字符串
     * @return 俩个字符串的相似度
     * <p>summary</p>:较长的字符串放到前面有助于提交效率
     */
    public static double SimilarDegree(String charsStrOne, String charsStrTwo) {
        String newStrOne = removeSign(charsStrOne);
        String newStrTwo = removeSign(charsStrTwo);
        int temp = Math.max(newStrOne.length(), newStrTwo.length());
        int temp2 = longestCommonSubstring(newStrOne, newStrTwo).length();
        return temp2 * 1.0 / temp;
    }


    /**
     * 对比俩个字符串的相似度
     *      第二种实现方式
     * @param str       字符串
     * @param target    目标字符串
     * @return: int
     * @author: Shupeng Lin
     * @date: 2019-11-14  上午 11:56
     **/
    private static int compare(String str, String target) {
        // 矩阵
        int[][] d;
        int n = str.length();
        int m = target.length();
        // 遍历str的
        int i;
        // 遍历target的
        int j;
        // str的
        char ch1;
        // target的
        char ch2;
        // 记录相同字符,在某个矩阵位置值的增量,不是0就是1
        int temp;
        if (n == 0) {
            return m;
        }
        if (m == 0) {
            return n;
        }
        d = new int[n + 1][m + 1];
        // 初始化第一列
        for (i = 0; i <= n; i++) {
            d[i][0] = i;
        }

        // 初始化第一行
        for (j = 0; j <= m; j++) {
            d[0][j] = j;
        }

        // 遍历str
        for (i = 1; i <= n; i++) {
            ch1 = str.charAt(i - 1);
            // 去匹配target
            for (j = 1; j <= m; j++) {
                ch2 = target.charAt(j - 1);
                if (ch1 == ch2) {
                    temp = 0;
                } else {
                    temp = 1;
                }

                // 左边+1,上边+1, 左上角+temp取最小
                d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + temp);
            }
        }
        return d[n][m];
    }

    private static int min(int one, int two, int three) {
        return (one = one < two ? one : two) < three ? one : three;
    }

    /**
     * 获取字符串的相似度
     *
     * @param str
     * @param target
     * @return
     */
    public static double SimilarityRatio(String str, String target) {
        return 1 - (double) compare(str, target) / Math.max(str.length(), target.length());
    }


    /**
     * 获取字符串编码
     *
     * @param str 需要处理的字符串
     */
    public static String simpleEncoding(String str) {
        try {
            byte[] bs = str.getBytes(SysUtil.JVM_ENCODING);
            if (str.equals(new String(bs, UtilsConstant.UTF_8))) {
                return UtilsConstant.UTF_8;
            }
            if (str.equals(new String(bs, UtilsConstant.GBK))) {
                return UtilsConstant.GBK;
            }
            if (str.equals(new String(bs, UtilsConstant.ISO_8859_1))) {
                return UtilsConstant.ISO_8859_1;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String encode = "GB2312";

        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                return encode;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                return encode;
            }
        } catch (UnsupportedEncodingException exception1) {
            exception1.printStackTrace();
        }
        encode = UtilsConstant.UTF_8;
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                return encode;
            }
        } catch (UnsupportedEncodingException exception1) {
            exception1.printStackTrace();
        }
        encode = "GBK";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                return encode;

            }
        } catch (UnsupportedEncodingException exception1) {
            exception1.printStackTrace();
        }
        return "";
    }


}
