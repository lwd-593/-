package com.enzenith.utils.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 方法级上的注解
 * @author: Shupeng Lin
 * @date: 2019-11-14  上午 10:46
 **/
@Target(value=ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodInfo {
	//方法类型
	public String type() default "";
	//方法标识
	public String name() default "";
}
