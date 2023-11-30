package com.qbclient.common.utils;



import cn.hutool.json.JSONUtil;
import com.qbclient.enity.Message;
import org.java_websocket.client.WebSocketClient;

import org.java_websocket.handshake.ServerHandshake;


import javax.swing.*;
import java.net.URI;

public class MyWebSocketClient extends WebSocketClient {
    //聊天窗对象
    private JTextArea chatTextArea;

    public MyWebSocketClient(URI serverUri,JTextArea chatTextArea) {
        super(serverUri);
        this.chatTextArea = chatTextArea;
    }
    public MyWebSocketClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {

    }

    @Override
    public void onMessage(String message) {
        Message msg = JSONUtil.toBean(message, Message.class);
        //from和to都不是null的话就是正经的私聊消息
        if(msg.getTo()!=null&&msg.getFrom()!=null){
            chatTextArea.append("【私聊】来自:"+msg.getFrom()+"\n" +
                    msg.getMessage()+ "\n");
            chatTextArea.setCaretPosition(chatTextArea.getDocument().getLength());
        }
        //系统的私聊像是指令操作
        if(msg.getFrom()==null&&msg.getTo()!=null){
            chatTextArea.append("【系统回复】:\n" +
                    msg.getMessage()+ "\n");
            chatTextArea.setCaretPosition(chatTextArea.getDocument().getLength());
        }
        //聊天室群聊
        if(msg.getFrom()!=null&&msg.getTo()==null){
            chatTextArea.append("来自 "+msg.getFrom()+" 的消息：\n" +
                    msg.getMessage()+ "\n");
            chatTextArea.setCaretPosition(chatTextArea.getDocument().getLength());
        }
        //系统消息
        if(msg.getFrom()==null&&msg.getTo()==null){
            chatTextArea.append(msg.getMessage() + "\n");
            chatTextArea.setCaretPosition(chatTextArea.getDocument().getLength());
        }
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        System.out.println("断开连接！");
        chatTextArea.append("与服务器断开连接！请重新登录！\n");
        chatTextArea.setCaretPosition(chatTextArea.getDocument().getLength());
    }

    @Override
    public void onError(Exception e) {
        System.out.println("Oh no!发生了错误！");
    }
}

