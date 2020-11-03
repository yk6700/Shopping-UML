package com.company;

import java.util.Calendar;

public class Customer {
    private String id;
    private Address address;
    private String phone;
    private String email;
    private Account account;
    private WebUser webUser;

    public Customer(String id, Address address, String phone, String email, int balance, Boolean isPremium, WebUser webUser) {
        this.id = id;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.webUser=webUser;

        if (isPremium)
            account = new PremiumAccount(id, address, true, Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), balance, this);
        else
            account = new Account(id, address.getAddress(), true, Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), balance,  this);
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

    public void setId(String id) {
        this.id = id;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setWebUser(WebUser webUser) {
        this.webUser = webUser;
    }

    public void addWebUser(WebUser w){
        if(w.getCustomer()==this && this.webUser==null){
            webUser=w;
        }
      }

    public boolean removeCustomer() {
        webUser = null;
        account = null;
        return true;
    }
    
    public WebUser getWebUser() {
        return webUser;
    }
    
    @Override
    public String toString() {
        return "Customer";
    }
    
    public void printCustomer(){
        System.out.println("Address:"+this.address.getAddress());
        System.out.println("Phone:"+phone);
        System.out.println("Email:"+email);
        System.out.println(account);
        if(webUser!=null){
            System.out.println(webUser);
        }
    }
}
