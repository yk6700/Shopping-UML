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
    
    public Customer(String id, Address address, String phone, String email, Boolean isPremium, int balance) {
        this.id = id;
        this.address = address;
        this.phone = phone;
        this.email = email;

        if (isPremium)
            account = new PremuimAccount(id, address.getAddress(), false, Calendar.getInstance().getTime(), null, balance, this);
        else
            account = new Account(id, address.getAddress(), false, Calendar.getInstance().getTime(), null, balance, this);
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
        if(w.getCustomer()==null){
            webUser=w;
        }
        else{
            throw new RuntimeException("Web user can be connected only to one customer");
        }
    }
    
    public WebUser getWebUser() {
        return webUser;
    }
}
