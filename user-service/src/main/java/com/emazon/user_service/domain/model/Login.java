package com.emazon.user_service.domain.model;

public class Login {
    private String email;
    private String password;

    public Login(String username, String password) {
        this.email = username;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
