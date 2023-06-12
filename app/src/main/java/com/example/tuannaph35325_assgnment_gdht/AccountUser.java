package com.example.tuannaph35325_assgnment_gdht;

import java.io.Serializable;

public class AccountUser implements Serializable {
    private String userName;
    private String passWord;

    public AccountUser(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }
    public AccountUser(){}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
