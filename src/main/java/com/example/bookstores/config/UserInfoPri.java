package com.example.bookstores.config;

import java.security.Principal;

public class UserInfoPri implements Principal {
    private String userName;

    @Override
    public String getName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}