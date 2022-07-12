package com.basicbankingmanagement.Model;

public class UserModel {
    private String name;
    private String account;
    private String balance;
    private String mobile;

    public UserModel(String name, String account, String balance, String mobile) {
        this.name = name;
        this.account = account;
        this.balance = balance;
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public String getAccount() {
        return account;
    }

    public String getBalance() {
        return balance;
    }

    public String getMobile() {
        return mobile;
    }
}
