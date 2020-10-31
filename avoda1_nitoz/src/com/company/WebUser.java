package com.company;

import java.util.Calendar;

import static com.company.UserState.New;

public class WebUser {
    private String login_id;
    private String password;
    private UserState state;
    private Customer customer;
    private ShoppingCart shoppingCart;

    public WebUser(String login_id, String password, Address address, String phone, String email, Boolean isPremium, int balance){
        this.login_id = login_id;
        this.password = password;
        this.state = New;

        customer = new Customer(login_id, address, phone, email, balance, isPremium);
        shoppingCart = new ShoppingCart(Calendar.getInstance().getTime(), customer.getAccount(), this);

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

    public void setState(UserState state) {
        this.state = state;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
    
    @Override
    public String toString() {
        return "WebUser"+this.login_id;
    }
    
    public void printWebUser(){
        System.out.println(this);
        System.out.println("Password:"+this.password);
        System.out.println("State:"+state);
        System.out.println(customer);
        if(shoppingCart!=null){
            System.out.println(shoppingCart);
        }
    }
}
