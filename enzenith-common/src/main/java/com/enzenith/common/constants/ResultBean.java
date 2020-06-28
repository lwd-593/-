package com.enzenith.common.constants;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 自定义响应结构
 */
@ApiModel(value="返回类")
public class ResultBean<T> implements Serializable{
	private static final long serialVersionUID = 1L;

    // 响应业务状态
    @ApiModelProperty(value = "响应业务状态", name = "status")
    private Integer status;

    // 响应消息
    @ApiModelProperty(value = "响应消息", name = "msg")
    private String msg;

    // 响应中的数据
    @ApiModelProperty(value = "数据对象", name = "data")
    private T data;

    public static<T> ResultBean build(Integer status, String msg, T data) {
        return new ResultBean(status, msg, data);
    }

    public static<T> ResultBean build(String msg, T data) {
    	
        return new ResultBean(HttpStatus.OK, msg, data);
    }
    
    public static ResultBean build(Integer status, String msg) {
        return new ResultBean(status, msg, null);
    }

    public static<T> ResultBean build(T data) {
        return new ResultBean(data);
    }

    public static ResultBean build() {
        return new ResultBean(null);
    }

    public ResultBean() {

    }

    public ResultBean(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ResultBean(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ResultBean(T data) {
        this.status = HttpStatus.OK;
        this.msg = "OK";
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
