package com.enzenith.common.constants;

/**
 * @Description: 系统常量
 * @author jikunle
 */
public class Constant {
	/**
	 * 是否时超管
	 */
	public static final String	IS_SUPER_ADMIN	= "IsSuperAdmin";

	/**
	 * 超管ID
	 */
	public static final String	SUPER_ADMIN_ID	= "1111";

	/**
	 *登录用户自身节点orgId
	 */
	public  static final String SESSION_ORG_ID = "loginOrgId";
	/**
	 * 登录用户所属公司orgId
	 */
	public  static final String SESSION_COMPANY_ID = "loginCompanyId";

	/**
	 * @Fields: 登录用户ID
	 */
	public  static final String SESSION_USER_ID = "loginUserId";
	/**
	 * @Fields: 登录用户昵称
	 */
	public  static final String SESSION_USER_NICK_NAME= "loginUserNickName";
	/**
	 * 登录用户的租户id
	 * @author huangxiaodong
	 * @date 2019-03-26 19:29
	 */
	public  static final String SESSION_TENANT_ID = "loginTenantId";
	/**
	 * sessionToken
	 */
	public static final String	SESSION_TOKEN = "sessionToken";
	/**
	 * session/redis过期时间
	 */
	public static final long	SESSION_TIMEOUT = 1800;
	/**
	 * session/redis续期时间
	 */
	public static final int	SESSION_RENEWAL = 1800;
	
	/**
	 * 系统用户默认密码
	 */
	public static final String	USER_DEFAULT_PASSWORD = "888888";

	/**
	 * 字符串;1
	 */
	public static  final String STR_ONE = "1";
}
