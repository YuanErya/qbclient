package com.qbclient.common.utils;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.http.HttpUtil;
import com.qbclient.common.ConstantString;


import java.util.HashMap;
import java.util.Map;

public class SentCodeUtils {
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
    public static final String md5_salt="we are winner";
    public final static Integer Type_message_register = 1;
    public final static Integer Type_message_login = 2;
    public final static Integer Type_message_find_password = 3;
    public final static Integer Type_message_change_password = 4;


    public static String sentCode(String email,Integer type){
        if(!email.matches(EMAIL_REGEX)){
            return "邮箱格式错误！";
        }
        HashMap<String, String> headers = new HashMap<>();
        Map<String, Object> map = new HashMap<>();//存放参数
        long timestamp = System.currentTimeMillis();
        String md5token= DigestUtil.md5Hex(email+md5_salt+type.toString()+md5_salt+timestamp);
        map.put("email", email);
        map.put("checkType", type);
        map.put("token",md5token);
        map.put("time_stamp",timestamp);
        //开启一条新线程发起发送邮件请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result= HttpUtil.createPost(ConstantString.SENT_CODE_URL).addHeaders(headers).form(map).execute().body();
            }
        }).start();
        return "邮件已成功投递:"+email;
    }
}
