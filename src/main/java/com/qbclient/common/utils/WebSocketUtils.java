package com.qbclient.common.utils;



import com.qbclient.common.ConstantString;

import javax.swing.*;
import java.net.URI;
import java.net.URISyntaxException;

public class WebSocketUtils {

    public static MyWebSocketClient connect(String token, JTextArea jTextArea){
        URI uri=null;
        try {
            uri=new URI(ConstantString.BASE_URI+token) ;
        } catch (URISyntaxException e) {
            System.out.println("连接异常！检查uri!");
        }
        if(uri==null){
            return null;
        }
        MyWebSocketClient myClient = new MyWebSocketClient(uri,jTextArea);
        myClient.connect();
        return myClient;
    }
}
