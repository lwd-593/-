package com.enzenith.common.constants;

/**
 * 通信状态码
 * @author jikunle
 * @title: HttpStatus
 * @description: 通信状态码
 * @date 2019/2/28 16:52
 */
public class HttpStatus {

    /**
     * 表明该请求被成功地完成，所请求的资源发送回客户端
     */
    public static final int OK = 200; 

    /**
     * 提示知道新文件的UR
     */
    public static final int CREATED = 201; 

    /**
     * 接受和处理、但处理未完成
     */
    public static final int ACCEPTED = 202; 

    /**
     * 请求收到，但返回信息为空
     */
    public static final int NO_CONTENT = 204;   

    /**
     * 本网页被永久性转移到另一个URL
     */
    public static final int MOVED_PERMANENTLY = 301;    

    /**
     * 请求的网页被转移到一个新的地址，但客户访问仍继续通过原始URL地址，重定向，新的URL会在response中的Location中返回，浏览器将会使用新的URL发出新的Request。
     */
    public static final int FOUND = 302;    
    /**
     * 建议客户访问其他URL或访问方式
     */
    public static final int SEE_OTHER = 303;    

    /**
     * 自从上次请求后，请求的网页未修改过，服务器返回此响应时，不会返回网页内容，代表上次的文档已经被缓存了，还可以继续使用
     */
    public static final int NOT_MODIFIED = 304; 

    /**
     * 申明请求的资源临时性删除
     */
    public static final int TEMPORARY_REDIRECT = 307;   

    /**
     * 客户端请求有语法错误，不能被服务器所理解
     */
    public static final int BAD_REQUEST = 400;  

    /**
     * 请求未经授权，这个状态代码必须和WWW-Authenticate报头域一起使用
     */
    public static final int UNAUTHORIZED = 401; 

    /**
     * 禁止访问，服务器收到请求，但是拒绝提供服务
     */
    public static final int FORBIDDEN = 403;    

    /**
     * 一个404错误表明可连接服务器，但服务器无法取得所请求的网页，请求资源不存在。eg：输入了错误的URL
     */
    public static final int NOT_FOUND = 404;    

    /**
     * 用户在Request-Line字段定义的方法不允许
     */
    public static final int METHOD_NOT_ALLOWED = 405;   

    /**
     * 根据用户发送的Accept拖，请求资源不可访问
     */
    public static final int NOT_ACCEPTABLE = 406;   

    /**
     * 一个或多个请求头字段在当前请求中错误
     */
    public static final int PRECONDITION_FAILED = 412;  

    /**
     * 请求资源不支持请求项目格式
     */
    public static final int UNSUPPORTED_MEDIA_TYPE = 415;   

    /**
     * 服务器遇到错误，无法完成请求
     */
    public static final int INTERNAL_SERVER_ERROR = 500;    

    /**
     * 未实现
     */
    public static final int NOT_IMPLEMENTED = 501;  

    /**
     * 网关错误
     */
    public static final int BAD_GATEWAY = 502;  

    /**
     * 由于超载或停机维护，服务器目前无法使用，一段时间后可能恢复正常
     */
    public static final int SERVICE_UNAVAILABLE = 503;  

    /**
     * 用于返回业务信息
     */
    public static final int RETURN_MSG = 600;  

}
