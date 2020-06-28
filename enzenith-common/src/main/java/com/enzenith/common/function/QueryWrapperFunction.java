package com.enzenith.common.function;/**
 * @author: hjb
 * @date: 2019-11-13 14:54
 */

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.TableNameParser;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.api.R;
import com.sun.org.apache.bcel.internal.generic.NEW;
import net.sf.jsqlparser.schema.Column;


import javax.swing.table.TableColumn;
import java.lang.reflect.Field;
import java.util.*;

/**
 *QueryWrapper处理
 * @author: hjb
 * @date: 2019-11-13 14:54
 */
public class QueryWrapperFunction<T,R> {
    /**
     *QueryWrapper处理所有字段
     * @param clazz 反向生成的实体类 要有@TableField或者@TableId注解
     * @param queryObj 查询对象的字段名称要和实体类的字段名称一致
     * @return: com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<T>
     * @Author: HuangJinBao
     * @date: 2019-11-14  18:39
     **/
    public  static<T,R> QueryWrapper<T> getQueryWrapper(Class<T> clazz, R queryObj) throws IllegalAccessException {
        QueryWrapper<T> queryWrapper= new QueryWrapper<>();
        Field[] queryObjFields = queryObj.getClass().getDeclaredFields();
        Field[] entityObjFields = clazz.getDeclaredFields();
        for(Field queryField : queryObjFields){
            //允许访问私有变量
            queryField.setAccessible(true);
            //获取属性
            String queryFieldName = queryField.getName();
            //获取属性值
            Object queryFieldValue = queryField.get(queryObj);
            if (null!=queryFieldValue){
                //判断是否是String类型 过滤“”的情况
                if (queryFieldValue instanceof String){
                    if (String.valueOf(queryFieldValue).equals("")){
                        continue;
                    }
                }
                for (Field entityField : entityObjFields){
                    //是否有注解
                    boolean tableFieldFlag = entityField.isAnnotationPresent(TableField.class);
                    boolean tableFieldIdFlag = entityField.isAnnotationPresent(TableId.class);
                    if (tableFieldFlag||tableFieldIdFlag){
                        entityField.setAccessible(true);
                        String entityFieldName = entityField.getName();
                        if (entityFieldName.equals(queryFieldName)){
                            //注解的值
                            String value=null;
                            if (tableFieldFlag){
                                value = entityField.getAnnotation(TableField.class).value();
                            }
                            if (tableFieldIdFlag){
                                value = entityField.getAnnotation(TableId.class).value();
                            }
                            queryWrapper.eq(value,queryFieldValue);
                        }
                    }
                }
            }
        }
        return queryWrapper;
    }
    /**
     *QueryWrapper过滤处理字段
     * @param clazz 反向生成的实体类 要有@TableField或者@TableId注解
     * @param queryObj 查询对象的字段名称要和实体类的字段名称一致
     * @param filters 过滤字段要跟查询对象的字段名称一致
     * @return: com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<T>
     * @Author: HuangJinBao
     * @date: 2019-11-14  18:56
     **/
    public  static<T,R> QueryWrapper<T> getQueryWrapperFilter(Class<T> clazz, R queryObj,List<String> filters) throws IllegalAccessException {
        if (null==filters||filters.size()==0){
            return getQueryWrapper(clazz,queryObj);
        }
        QueryWrapper<T> queryWrapper= new QueryWrapper<>();
        Field[] queryObjFields = queryObj.getClass().getDeclaredFields();
        Field[] entityObjFields = clazz.getDeclaredFields();
        outterLoop : for(Field queryField : queryObjFields){
            //允许访问私有变量
            queryField.setAccessible(true);
            //获取属性
            String queryFieldName = queryField.getName();
            //获取属性值
            Object queryFieldValue = queryField.get(queryObj);
            if (null!=queryFieldValue){
                //判断是否过滤参数
                for (String filter : filters){
                    if (null==queryFieldName||queryFieldName.equals(filter)){
                        continue outterLoop;
                    }
                }
                //判断是否是String类型 过滤“”的情况
                if (queryFieldValue instanceof String){
                    if (String.valueOf(queryFieldValue).equals("")){
                        continue outterLoop;
                    }
                }
                for (Field entityField : entityObjFields){
                    //是否有注解
                    boolean tableFieldFlag = entityField.isAnnotationPresent(TableField.class);
                    boolean tableFieldIdFlag = entityField.isAnnotationPresent(TableId.class);
                    if (tableFieldFlag||tableFieldIdFlag){
                        entityField.setAccessible(true);
                        String entityFieldName = entityField.getName();
                        if (entityFieldName.equals(queryFieldName)){
                            //注解的值
                            String value=null;
                            if (tableFieldFlag){
                                value = entityField.getAnnotation(TableField.class).value();
                            }
                            if (tableFieldIdFlag){
                                value = entityField.getAnnotation(TableId.class).value();
                            }
                            queryWrapper.eq(value,queryFieldValue);
                        }
                    }
                }
            }
        }
        return queryWrapper;
    }
}