package com.enzenith.utils.functions;


/**
 * 定义一些处理接口，便于优雅的处理一些数据,实现的方法不返回数据
 * @author: Shupeng Lin
 * @date: 2019-11-14  上午 10:47
 **/
public interface ObjectHandler<T> {

    /**
     * 处理
     * @param t     对象
     * @return: void
     * @author: Shupeng Lin
     * @date: 2019-11-14  下午 2:20
     **/
    void handler(T t);


}
