package com.company;

import java.util.ArrayList;
import java.util.Date;

public class ShoppingCart {
    private Date created;
    private Account account;
    private WebUser webUser;
    private ArrayList<LineItem> lineItems;
    
    public ShoppingCart(Date created, Account account, WebUser webUser) {
        this.created = created;
        lineItems=new ArrayList<>();
        if(account.getShoppingCart()==null && webUser.getShoppingCart()==null){
            this.webUser=webUser;
            this.webUser.setShoppingCart(this);
            this.account=account;
        }
        else{
            throw new RuntimeException("Shopping cart can be connected only to one account and one web user");
        }
    }
    
    public boolean addLineItem(LineItem l){
        if(this.lineItems.contains(l)){
            return false;
        }
        lineItems.add(l);
        return true;
    }
    
    public Account getAccount() {
        return account;
    }
    
    public WebUser getWebUser() {
        return webUser;
    }
}
