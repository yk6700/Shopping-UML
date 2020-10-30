package com.company;

import static com.company.UserState.New;

public class WebUser {
    private String login_id;
    private String password;
    private UserState state;

    private Customer customer;

    public WebUser(String login_id,String password,Customer c){
        this.login_id = login_id;
        this.password = password;
        this.state = New;
        if(c.getWebUser()==null){
            customer=c;
        }
        else{
            throw new RuntimeException("Web user cannot be connected to more than one customer");
        }
    }

    public boolean checkPassword(String check){
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
    
    public Customer getCustomer() {
        return customer;
    }
}
