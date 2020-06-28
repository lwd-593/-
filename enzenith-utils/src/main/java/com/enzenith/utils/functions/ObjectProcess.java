package com.enzenith.utils.functions;


/**
 *定义一些处理接口，便于优雅的处理一些数据,实现的方法返回数据
 * @author: Shupeng Lin
 * @date: 2019-11-14  上午 10:47
 **/
public interface ObjectProcess<T, E> {
    /**
     * process
     * @param t  对象
     * @return: E
     * @author: Shupeng Lin
     * @date: 2019-11-14  下午 2:21
     **/
    E process(T t);
}
