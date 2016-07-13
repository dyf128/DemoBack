package com.dmeos.test.androidart.utils;

/**
 * 定义常量值
 */
public final class Constants {

    public static final boolean IS_DEBUG = true;
    public static final boolean IS_SEC_REQUEST = false;

    public static final String SSL_INFO_PWD = "test123";
    public static final int SSL_INFO_PORT = 44401;

    public static final String INTENT_DATA_USER_ID = "user_id";


    public static final int NET_REQUEST_ERROR_CODE_500 = 500;
    public static final String NET_REQUEST_ERROR_MSG_500 = "服务器出错了";
    public static final String NET_REQUEST_ERROR_MSG_UNKNOW = "出现未知异常，暂时无法获取数据";
    public static final String NET_REQUEST_ERROR_MSG_PARSE_ERROR = "解析数据出现异常";
    public static final String NET_REQUEST_ERROR_MSG_TIMEOUT_ERROR = "请求超时";
    public static final String NET_REQUEST_ERROR_MSG_AUTHFAILUREERROR = "未登录";
    public static final String NET_REQUEST_ERROR_MSG_CLIENTERROR = "请求出错了";
    public static final String NET_REQUEST_ERROR_MSG_SERVERERROR = "服务器出错了";
    public static final String NET_REQUEST_ERROR_MSG_NETWORKERROR = "网络异常";
    public static final String NET_REQUEST_ERROR_MSG_NOCONNECTIONERROR = "未链接网络，请检查网络设置";
}
