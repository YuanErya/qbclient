package com.qbclient.swingui;

import com.qbclient.common.utils.SentCodeUtils;
import com.qbclient.common.utils.UserUtils;
import com.qbclient.enity.RegisterDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegisterUI {
    private JFrame frame;

    // 字体大小
    private Font labelFont = new Font("微软雅黑", Font.PLAIN, 25);
    private Font inputFont = new Font("微软雅黑", Font.PLAIN, 20);
    private Font buttonFont = new Font("微软雅黑", Font.BOLD, 22);

    public RegisterUI() {
        frame = new JFrame();
        frame.setTitle("注册");
        frame.setSize(700, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel usernameLabel = new JLabel("用户名：");
        usernameLabel.setFont(labelFont);
        panel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        JTextField usernameField = new JTextField(20);
        usernameField.setFont(inputFont);
        panel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel passwordLabel = new JLabel("密码：");
        passwordLabel.setFont(labelFont);
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setFont(inputFont);
        panel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel confirmPasswordLabel = new JLabel("确认密码：");
        confirmPasswordLabel.setFont(labelFont);
        panel.add(confirmPasswordLabel, gbc);

        gbc.gridx = 1;
        JPasswordField confirmPasswordField = new JPasswordField(20);
        confirmPasswordField.setFont(inputFont);
        panel.add(confirmPasswordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel emailLabel = new JLabel("邮箱：");
        emailLabel.setFont(labelFont);
        panel.add(emailLabel, gbc);

        gbc.gridx = 1;
        JTextField emailField = new JTextField(20);
        emailField.setFont(inputFont);
        panel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel verificationCodeLabel = new JLabel("验证码：");
        verificationCodeLabel.setFont(labelFont);
        panel.add(verificationCodeLabel, gbc);

        gbc.gridx = 1;
        JTextField verificationCodeField = new JTextField(20);
        verificationCodeField.setFont(inputFont);
        panel.add(verificationCodeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        JButton sendCodeButton = new JButton("发送验证码");
        sendCodeButton.setFont(buttonFont);
        gbc.anchor = GridBagConstraints.EAST; // 将组件向右对齐
        panel.add(sendCodeButton, gbc);

        gbc.gridx = 1;

        JButton registerButton = new JButton("注册");
        registerButton.setFont(buttonFont);
        panel.add(registerButton, gbc);

        frame.add(panel);
        frame.setLocationRelativeTo(null); // 将窗口定位在屏幕中央
        frame.setVisible(true);
        /**
         * 注册按钮点击事件
         */
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //包装body
                RegisterDTO registerDTO=new RegisterDTO(usernameField.getText(),
                        new String(passwordField.getPassword()),
                        new String(confirmPasswordField.getPassword()),
                        emailField.getText(),
                        verificationCodeField.getText());
                //发起注册请求
                JOptionPane.showMessageDialog(frame, UserUtils.register(registerDTO), "提示", JOptionPane.INFORMATION_MESSAGE);
                //注册成功就返回到登录页面
                //frame.setVisible(false);
            }
        });
        /**
         * 发送验证码按钮点击事件
         */
        sendCodeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email=emailField.getText();
                JOptionPane.showMessageDialog(frame, SentCodeUtils.sentCode(email,SentCodeUtils.Type_message_register) , "提示", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
