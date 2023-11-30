package com.qbclient.common.utils;

import com.qbclient.enity.User;


public class UserHolder {
    private  static final ThreadLocal<User> tl=new ThreadLocal<User>();

    public static Boolean saveUser(User user){
        tl.set(user);
        return true;
    }
    public  static User getUser(){
        return tl.get();
    }
    public static Boolean removeUser(){
        tl.remove();
        return true;
    }
}
