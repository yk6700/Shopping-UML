package com.company;

import java.util.ArrayList;
import java.util.Date;

public class ShoppingCart {
    private Date created;
    private Account account;
    private WebUser webUser;
    private ArrayList<LineItem> lineItems;

    public ShoppingCart(Date created,Account account){
        this.created=created;
        lineItems=new ArrayList<>();
        if(account.getShoppingCart()==null){
            this.account=account;
        }
        else{
            throw new RuntimeException("Shopping cart can only be connected to one account");
        }
    }

    public void setWebUser(WebUser webUser){
        if(webUser.getShoppingCart()==null){
            this.webUser=webUser;
        }
        else{
            throw new RuntimeException("Shopping cart can be connected only to one web user");
        }
    }


    public Date getCreated() {
        return created;
    }
    
    public Account getAccount() {
        return account;
    }
    
    public WebUser getWebUser() {
        return webUser;
    }
    
    public void addLineItem(LineItem item){
        if(item.getShoppingCart()==null){
            lineItems.add(item);
        }
        else{
            throw new RuntimeException("line item can be added only to one shopping cart");
        }
    }
}
