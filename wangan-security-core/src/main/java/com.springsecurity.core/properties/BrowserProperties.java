package com.springsecurity.core.properties;

public class BrowserProperties {
    private String loginUrl="/login.html";
    private LoginType loginType=LoginType.JSON;

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }
}
