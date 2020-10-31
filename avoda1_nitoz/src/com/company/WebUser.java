package com.company;

import static com.company.UserState.New;

public class WebUser {
    private String login_id;
    private String password;
    private UserState state;
    private Customer customer;
    private ShoppingCart shoppingCart;

    public WebUser(String login_id,String password,Customer c){
        this.login_id = login_id;
        this.password = password;
        this.state = New;
        if(c.getWebUser()==null){
            customer=c;
            customer.addWebUser(this);
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
    
    public void setShoppingCart(ShoppingCart shoppingCart) {
        if(shoppingCart.getWebUser()==null){
            this.shoppingCart=shoppingCart;
        }
        else{
            throw new RuntimeException("Shopping cart cannot be connected to more than one web user");
        }
    }
    
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
}
