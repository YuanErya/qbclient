package com.qbclient.common.utils;

import com.qbclient.enity.Message;

public class SentMessageUtils {
    /**
     * 用于将消息包装成Message对象
     * @param message
     * @return
     */
    public static String sentMessageHandler(String message){
        String to=null;
        if(message.startsWith("/sl ")){
            message=message.replace("/sl ","");
            int i=0;
            while(message.charAt(i)!=32&&i<message.length()){
                i++;
            }
            to=message.substring(0,i);
            message=message.replace(to+" ","");
        }
        System.out.println((new Message(to,UserHolder.getUser().getUserName(),message)));
        return (new Message(to,UserHolder.getUser().getUserName(),message)).toString();
    }
}
