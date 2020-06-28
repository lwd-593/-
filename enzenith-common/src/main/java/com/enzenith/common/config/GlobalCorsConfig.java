package com.enzenith.common.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局跨域
 * @author jikunle
 * @date 2019/3/3 17:02
 */
@Component
public class GlobalCorsConfig implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers","Origin,Content-Type,Authorization,Accept,token,X-Requested-With");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        return true;
    }
}
