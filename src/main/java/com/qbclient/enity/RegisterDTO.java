package com.qbclient.enity;


public class RegisterDTO {

    String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPasswordAgain() {
        return userPasswordAgain;
    }

    public void setUserPasswordAgain(String userPasswordAgain) {
        this.userPasswordAgain = userPasswordAgain;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    String userPassword;

    String userPasswordAgain;

    String userEmail;

    String code;

    public RegisterDTO(String userName, String userPassword, String userPasswordAgain, String userEmail, String code) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userPasswordAgain = userPasswordAgain;
        this.userEmail = userEmail;
        this.code = code;
    }
}
