package com.enzenith.utils.web;

import com.enzenith.utils.util.JsonUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 发送消息到前端
 * @title: SendMsgUtil
 * @Package: com.enzenith.utils.web
 * @description: TODO
 * @author jikunle
 * @date 2019/3/26 10:44
 */
public class SendMsgUtil {

    /**
     * 发送消息 text/html;charset=utf-8
     * @param response
     * @param str
     * @return void
     * @author jikunle
     * @date 2019/3/26 10:46
     */
    public static void sendMessage(HttpServletResponse response, String str) throws Exception {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(str);
        writer.close();
        response.flushBuffer();
    }

    /**
     * 将某个对象转换成json格式并发送到客户端
     * @param response
     * @param obj
     * @return void
     * @author jikunle
     * @date 2019/3/26 10:46
     */
    public static void sendJsonMessage(HttpServletResponse response, Object obj) throws Exception {
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        /**
         * 版本一: 返回jsonStr 为数组，应当调整为对象json
         * @Author: XuZhiFeng
         * @date: 2019/9/24 0024  下午 5:27
         **/
        //writer.print(JsonUtils.toJSONString(obj));

        /**
         *
         * 版本二： 返回对象调整为对象json
         * @Author: XuZhiFeng
         * @date: 2019/9/24 0024  下午 5:27
         **/
        String returnObjStr = JsonUtils.objectToJson(obj);
        writer.print(returnObjStr);
        writer.close();
        response.flushBuffer();
    }
}
