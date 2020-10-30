package com.company;

public class WebUser {
    private String login_id;
    private String password;
    private UserState state;

    private Customer customer;

    public String getLogin_id() {
        return login_id;
    }

    public String getPassword() {
        return password;
    }

    public UserState getState() {
        return state;
    }

}
