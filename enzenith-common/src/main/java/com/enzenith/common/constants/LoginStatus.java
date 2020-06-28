package com.enzenith.common.constants;

public class LoginStatus {

	public static final int[] STATE;
    public static final String[] STATE_NAME;
    public static final int	LOGON_SUCCESS;
	public static final int	USER_STATA_ABNORMAL;
	public static final int	USER_NAME_PASSWORD_WRONG;
	public static final int	USER_NAME_PASSWORD_NULL;
    
    static {
        STATE = new int[] { 1, 2, 3, 4 };
        STATE_NAME = new String[] { "登录成功", "用户状态异常,不能登录,请联系管理员!", "用户名或密码错误!", "用户名和密码不能为空!" };
        LOGON_SUCCESS = LoginStatus.STATE[0];
        USER_STATA_ABNORMAL = LoginStatus.STATE[1];
        USER_NAME_PASSWORD_WRONG = LoginStatus.STATE[2];
        USER_NAME_PASSWORD_NULL = LoginStatus.STATE[3];
    }
}
