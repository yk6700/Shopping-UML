package com.company;

public class WebUser {
    private String login_id;
    private String password;
    private UserState state;

    private Customer customer;

    public WebUser(String login_id,String password){
        this.login_id = login_id;
        this.password = password;
        this.customer = Customer(/* ADD WHa */);
        this.state = New;
    }

    public checkPassword(String check){
        return this.password.equals(check);
    }

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
