package com.qbclient.enity;

import java.util.Date;

/**
 * 不包含密码敏感信息
 */
public class User {
    Long userId;
    String userName;
    String userEmail;
    /**
     * 用户创建时间
     */
    Date createTime;
    Long userInfoId;
    String userNickname;
    String userIntroduction;
    Integer userSex;
    /**
     * 用户修改详细信息的时间
     */
    Date updateTime;
    /**
     * 登陆时返回UUID和该账号同时在线信息
     */
    String loginInformation;
    /**
     * 此账号登录在线的人数
     */
    String onlineNumber;

    public User(Long userId, String userName, String userEmail, Date createTime, Long userInfoId, String userNickname, String userIntroduction, Integer userSex, Date updateTime, String loginInformation, String onlineNumber) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.createTime = createTime;
        this.userInfoId = userInfoId;
        this.userNickname = userNickname;
        this.userIntroduction = userIntroduction;
        this.userSex = userSex;
        this.updateTime = updateTime;
        this.loginInformation = loginInformation;
        this.onlineNumber = onlineNumber;
    }

    public User() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Long userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getUserIntroduction() {
        return userIntroduction;
    }

    public void setUserIntroduction(String userIntroduction) {
        this.userIntroduction = userIntroduction;
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getLoginInformation() {
        return loginInformation;
    }

    public void setLoginInformation(String loginInformation) {
        this.loginInformation = loginInformation;
    }

    public String getOnlineNumber() {
        return onlineNumber;
    }

    public void setOnlineNumber(String onlineNumber) {
        this.onlineNumber = onlineNumber;
    }
}
