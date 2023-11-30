package com.qbclient.enity;



public class LoginDTO {
    String usernameOrEmail;
    String passwordOrCode;

    public LoginDTO(String usernameOrEmail, String passwordOrCode) {
        this.usernameOrEmail = usernameOrEmail;
        this.passwordOrCode = passwordOrCode;
    }

    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

    public String getPasswordOrCode() {
        return passwordOrCode;
    }

    public void setPasswordOrCode(String passwordOrCode) {
        this.passwordOrCode = passwordOrCode;
    }
}
