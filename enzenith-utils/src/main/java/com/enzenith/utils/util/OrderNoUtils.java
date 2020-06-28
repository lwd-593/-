package com.enzenith.utils.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *  生成订单号类
 * @author: Shupeng Lin
 * @date: 2019-11-14  上午 10:47
 **/
public class OrderNoUtils { 


	private OrderNoUtils() {}

	/**
	 *  创建一个空实例对象,类需要用的时候才赋值
	 *
	 */
	private static OrderNoUtils g = null; 

	/**
	 * 单例模式--懒汉模式
	 * @return: com.enzenith.utils.util.OrderNoUtils
	 * @author: Shupeng Lin
	 * @date: 2019-11-14  上午 11:51
	 **/
	public static synchronized OrderNoUtils getInstance() {
		if (g == null) { 
		g = new OrderNoUtils(); 
		} 
		return g; 
	}
	/**
	 * 	全局自增数
 	 */
	private static int count = 0;
	/**
	 * 每毫秒秒最多生成多少订单(最好是像9999这种准备进位的值)
	 */
	private static final int TOTAL = 9999;
	/**
	 * 格式化的时间字符串
	 */
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");


	/**
	 * 获取当前时间年月日时分秒毫秒字符串
	 */
	private static String getNowDateStr() {
		return  LocalDateTime.now().format(DATE_TIME_FORMATTER);
	}

	/**
	 * 记录上一次的时间,用来判断是否需要递增全局数
 	 */
	private static String now = null; 

	/**
	 * 生成一个订单号
	 * @return: java.lang.String
	 * @author: Shupeng Lin
	 * @date: 2019-11-14  上午 11:52
	 **/
	public synchronized String GenerateOrder() {
		String datastr = getNowDateStr(); 
		if (datastr.equals(now)) { 
		// 自增
		count++;
		} else {
		count = 1; 
		now = datastr; 
		} 
		// 算补位
		int countInteger = String.valueOf(TOTAL).length() - String.valueOf(count).length();
		// 补字符串
		String bu = "";
		for (int i = 0; i < countInteger; i++) {
		bu += "0"; 
		} 
		bu += String.valueOf(count); 
		if (count >= TOTAL) {
		count = 0; 
		} 
		return datastr + bu; 
	} 
} 