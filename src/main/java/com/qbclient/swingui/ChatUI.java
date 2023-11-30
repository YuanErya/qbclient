package com.qbclient.swingui;

import cn.hutool.core.util.StrUtil;
import com.qbclient.common.utils.MyWebSocketClient;
import com.qbclient.common.utils.SentMessageUtils;
import com.qbclient.common.utils.WebSocketUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatUI {
    private MyWebSocketClient client;
    private JFrame frame;
    private JTextArea messageArea;
    private JTextField inputField;
    private JButton sendButton;

    Font txtAreaFont = new Font("Helvetica", Font.PLAIN, 20);

    // 输入框字体大小
    Font inputFont = new Font("Helvetica", Font.PLAIN, 20);

    // 按钮字体大小
    Font buttonFont = new Font("Helvetica", Font.BOLD, 20);

    public ChatUI(String token) {
        frame = new JFrame();
        frame.setTitle("qb聊天室");
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        JPanel panel = new JPanel(new BorderLayout());

        messageArea = new JTextArea();
        messageArea.setFont(txtAreaFont);
        messageArea.setEditable(false);
        messageArea.setLineWrap(true); // 设置自动换行
        messageArea.setWrapStyleWord(true); // 设置在单词边界换行
        JScrollPane scrollPane = new JScrollPane(messageArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new BorderLayout());

        inputField = new JTextField();
        inputField.setFont(inputFont);
        inputPanel.add(inputField, BorderLayout.CENTER);

        sendButton = new JButton("发送");
        sendButton.setFont(buttonFont);
        inputPanel.add(sendButton, BorderLayout.EAST);
        frame.getRootPane().setDefaultButton(sendButton);
        panel.add(inputPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);

        client= WebSocketUtils.connect(token,messageArea);
        // 添加发送按钮的点击事件处理
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String message = inputField.getText();
                if (!StrUtil.isBlank(message)) {
                    client.send(SentMessageUtils.sentMessageHandler(message));
                    displayMessage("我： " + message);
                    inputField.setText("");
                }
            }
        });
    }
    private void displayMessage(String message) {
        messageArea.append(message + "\n");
    }
}
