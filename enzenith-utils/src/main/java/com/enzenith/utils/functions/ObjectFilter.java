package com.enzenith.utils.functions;

/**
 * 对象过滤接口
 *
 * @param <T>
 * @author admin
 */
public interface ObjectFilter<T> {
    /**
     * 过滤
     * @param t  对象
     * @return: boolean
     * @author: Shupeng Lin
     * @date: 2019-11-14  下午 2:20
     **/
    boolean filter(T t);
}
