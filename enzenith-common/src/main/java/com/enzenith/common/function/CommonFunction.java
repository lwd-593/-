package com.enzenith.common.function;

import com.enzenith.common.constants.Constant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *  系统公共方法
 * @author jikunle
 * @date 2019/3/30 9:38
 */
public class CommonFunction {

    /**
     * 判断是否为超管以及获取登录用户id
     * 超管返回loginUserId为null
     * @param request
     * @return java.lang.String
     * @author jikunle
     * @date 2019/3/30 9:45
     */
    public static String getUserId(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(null == session || null == session.getAttribute(Constant.IS_SUPER_ADMIN)){
            return null;
        }
        String loginUserId = null;
        if(!session.getAttribute(Constant.IS_SUPER_ADMIN).equals(true)){
            loginUserId = (String)session.getAttribute(Constant.SESSION_USER_ID);
        }
        return loginUserId;
    }
}
