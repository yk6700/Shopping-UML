package com.company;

import java.util.Calendar;
import java.util.Date;

public class Customer {
    private String id;
    private Address address;
    private String phone;
    private String email;
    private Account account;
    private WebUser webUser;

    public Customer(String id, Address address, String phone, String email, int balance, Boolean isPremium) {
        this.id = id;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.webUser=null;

        if (isPremium)
            account = new PremuimAccount(id, null, true, Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), balance, this);
        else
            account = new Account(id, null, true, Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), balance,  this);
    }
    
    public String getId() {
        return id;
    }
    
    public Address getAddress() {
        return address;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public String getEmail() {
        return email;
    }
    
    public Account getAccount() {
        return account;
    }
    
    public void addWebUser(WebUser w){
        if(w.getCustomer()==this && this.webUser==null){
            webUser=w;
        }
      }
    
    public WebUser getWebUser() {
        return webUser;
    }
    
    @Override
    public String toString() {
        return "Customer"+this.id;
    }
    
    public void printCustomer(){
        System.out.println(this);
        System.out.println("Address:"+this.address.getAddress());
        System.out.println("Phone:"+phone);
        System.out.println("Email:"+email);
        System.out.println(account);
        if(webUser!=null){
            System.out.println(webUser);
        }
    }
}
