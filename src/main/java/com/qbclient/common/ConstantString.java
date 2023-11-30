package com.qbclient.common;

public class ConstantString {
    //ws的连接配置
    public static final String HOST="localhost";
    public static final String PORT="13268";
    public static final String BASE_URI="ws://"+HOST+":"+PORT+"/qbserver/";

    //服务端http请求的配置
    public static final String baseUrl="http://"+HOST+":"+PORT;
    public static final String SENT_CODE_URL=baseUrl+"/qbserver/api/sentEmailCode";
    public static final String LOGIN_PASSWORD_URL=baseUrl+"/api/qbserver/user/login/password";
    public static final String LOGIN_CODE_URL=baseUrl+"/api/qbserver/user/login/code";
    public static final String REGISTER_URL=baseUrl+"/api/qbserver/user/register";
}
