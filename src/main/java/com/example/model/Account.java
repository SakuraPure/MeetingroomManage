package com.example.model;

import lombok.Data;

@Data
public class Account {
    private int id;
    private String username;
    private String password;
    private String role;
    private String phone;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = "user";
    }
}
