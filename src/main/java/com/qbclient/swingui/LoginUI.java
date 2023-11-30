package  com.qbclient.swingui;

import com.qbclient.enity.LoginDTO;
import com.qbclient.common.api.ApiResult;
import com.qbclient.common.utils.SentCodeUtils;
import com.qbclient.common.utils.UserUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginUI {
    ThreadLocal<String> threadLocal=new ThreadLocal<>();
    private JFrame frame;
    private JPanel cardPanel;
    private CardLayout cardLayout;


    // 标签字体大小
    Font labelFont = new Font("微软雅黑", Font.PLAIN, 30);

    // 输入框字体大小
    Font inputFont = new Font("微软雅黑", Font.PLAIN, 20);

    // 按钮字体大小
    Font buttonFont = new Font("微软雅黑", Font.BOLD, 22);

    public LoginUI() {

        frame = new JFrame();
        frame.setTitle("qb登录");
        frame.setSize(700, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        JPanel passwordLoginPanel = createPasswordLoginPanel();
        JPanel captchaLoginPanel = createCaptchaLoginPanel();

        cardPanel.add(passwordLoginPanel, "passwordLogin");
        cardPanel.add(captchaLoginPanel, "captchaLogin");

        frame.add(cardPanel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel createPasswordLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JPanel buttonPanel = new JPanel();
        JButton passwordLoginButton = new JButton("密码登录");
        passwordLoginButton.setFont(buttonFont);
        JButton captchaLoginButton = new JButton("验证码登录");
        captchaLoginButton.setFont(buttonFont);
        buttonPanel.add(passwordLoginButton);
        buttonPanel.add(captchaLoginButton);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel zhLabel=new JLabel("账号：");
        zhLabel.setFont(labelFont);
        inputPanel.add(zhLabel, gbc);
        gbc.gridx = 1;
        JTextField zhText = new JTextField(20);
        zhText.setFont(inputFont);
        inputPanel.add(zhText, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel passwordLabel=new JLabel("密码：");
        passwordLabel.setFont(labelFont);
        inputPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        JPasswordField passwordField=new JPasswordField(20);
        passwordField.setFont(inputFont);
        inputPanel.add(passwordField, gbc);

        JPanel actionPanel = new JPanel();
        JButton loginButton = new JButton("登录");
        loginButton.setFont(buttonFont);
        JButton registerButton = new JButton("注册");
        registerButton.setFont(buttonFont);
        actionPanel.add(loginButton);
        actionPanel.add(registerButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(buttonPanel, gbc);
        gbc.gridy = 1;
        panel.add(inputPanel, gbc);
        gbc.gridy = 2;
        panel.add(actionPanel, gbc);

        // 添加按钮的点击事件处理
        captchaLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "captchaLogin");
            }
        });

        /**
         * 监听注册按钮，打开注册页面
         */
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new RegisterUI();
            }
        });
        /**
         * 监听登录按钮（密码登录）
         */
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //在这里发送校验请求
                //拿到token后建立连接
                //打开聊天室窗口
                LoginDTO loginDTO=new LoginDTO(zhText.getText(),new String(passwordField.getPassword()));
                ApiResult<String> result= UserUtils.login(loginDTO,1);
                String token=result.getData();
                threadLocal.set(token);
                JOptionPane.showMessageDialog(frame,result.getMessage() , "提示", JOptionPane.INFORMATION_MESSAGE);
                if(!result.getMessage().equals("登录成功")){
                    return;
                }
                new ChatUI(token);
                frame.setVisible(false);
            }
        });

        return panel;
    }

    private JPanel createCaptchaLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JPanel buttonPanel = new JPanel();
        JButton passwordLoginButton = new JButton("密码登录");
        passwordLoginButton.setFont(buttonFont);
        JButton captchaLoginButton = new JButton("验证码登录");
        captchaLoginButton.setFont(buttonFont);
        buttonPanel.add(passwordLoginButton);
        buttonPanel.add(captchaLoginButton);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel emailLabel=new JLabel("邮箱：");
        emailLabel.setFont(labelFont);
        inputPanel.add(emailLabel, gbc);
        gbc.gridx = 1;
        JTextField emailField = new JTextField(20);
        emailField.setFont(inputFont);
        inputPanel.add(emailField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel passwordLabel=new JLabel("验证码：");
        passwordLabel.setFont(labelFont);
        inputPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        JTextField codeField=new JTextField(20);
        codeField.setFont(inputFont);
        inputPanel.add(codeField, gbc);

        JPanel captchaPanel = new JPanel(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        captchaPanel.add(inputPanel, gbc);
        gbc.gridx = 1;
        JButton setCodeButton=new JButton("发送验证码");
        setCodeButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
        captchaPanel.add(setCodeButton, gbc);

        JPanel actionPanel = new JPanel();
        JButton loginButton = new JButton("登录");
        loginButton.setFont(buttonFont);
        actionPanel.add(loginButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(buttonPanel, gbc);
        gbc.gridy = 1;
        panel.add(captchaPanel, gbc);
        gbc.gridy = 2;
        panel.add(actionPanel, gbc);

        /**
         * 监听发送邮件按钮
         */
        setCodeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email=emailField.getText();
                JOptionPane.showMessageDialog(frame, SentCodeUtils.sentCode(email,SentCodeUtils.Type_message_login) , "提示", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        /**
         * 注册按钮点击事件，跳转到注册页面
         */
        passwordLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "passwordLogin");
            }
        });
        /**
         * 登录按钮点击事件
         */
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //在这里发送校验请求
                //拿到token后建立连接
                //打开聊天室窗口
                LoginDTO loginDTO=new LoginDTO(emailField.getText(),codeField.getText());
                ApiResult<String> result=UserUtils.login(loginDTO,2);
                String token=result.getData();
                threadLocal.set(token);
                JOptionPane.showMessageDialog(frame,result.getMessage() , "提示", JOptionPane.INFORMATION_MESSAGE);
                if(!result.getMessage().equals("登录成功")){
                    return;
                }
                new ChatUI(token);
                frame.setVisible(false);
            }
        });
        return panel;
    }
}


