package com.qbclient.common.utils;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.qbclient.common.ConstantString;
import com.qbclient.common.api.ApiResult;
import com.qbclient.enity.LoginDTO;
import com.qbclient.enity.RegisterDTO;
import com.qbclient.enity.User;

import java.util.HashMap;
import java.util.Map;


public class UserUtils {
    public static String register(RegisterDTO registerDTO){
        HashMap<String, String> headers = new HashMap<>();
        Map<String, Object> map = new HashMap<>();//存放参数
        String body= JSONUtil.toJsonStr(registerDTO);
        String result= HttpUtil.createPost(ConstantString.REGISTER_URL).addHeaders(headers).form(map).body(body).execute().body();
        ApiResult r=JSONUtil.toBean(result, ApiResult.class);
        return r.getMessage();
    }

    public static ApiResult<String> login(LoginDTO loginDTO, Integer type){
        HashMap<String, String> headers = new HashMap<>();
        Map<String, Object> map = new HashMap<>();//存放参数
        String body= JSONUtil.toJsonStr(loginDTO);
        String url=type==1?ConstantString.LOGIN_PASSWORD_URL:ConstantString.LOGIN_CODE_URL;
        String result= HttpUtil.createPost(url).addHeaders(headers).form(map).body(body).execute().body();
        ApiResult<JSONObject> r=JSONUtil.toBean(result, ApiResult.class);
        User user=null;
        String token="";
        if(r.getCode()==200){
            user=JSONUtil.toBean(r.getData(),User.class);
            token=user.getLoginInformation().replace("token:","");
        }
        //放入线程共享数据
        UserHolder.saveUser(user);
        return ApiResult.success(token,r.getMessage());
    }
}
