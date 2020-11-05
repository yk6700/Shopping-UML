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
        this.account = account;
        this.webUser = webUser;

        this.account.setShoppingCart(this);
        this.webUser.setShoppingCart(this);
        lineItems=new ArrayList<>();


    }


    public Date getCreated() {
        return created;
    }

    public ArrayList<LineItem> getLineItems() {
        return lineItems;
    }

    public boolean removeLineItem(LineItem lineItem){
        if(lineItem != null && lineItems.contains(lineItem)) {
            lineItems.remove(lineItem);
            return true;
        }
        return false;
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

    public boolean removeShoppingCart() {

        if (lineItems != null){
            while (lineItems.size() > 0){
                boolean ir = lineItems.get(0).removeItem();
            }
            lineItems = null;
        }


        if (account != null){
            boolean ar = true;
            if (account instanceof PremiumAccount){
                ar = ((PremiumAccount)account).removePremuimAccount();
            }
            else {
                ar = account.removeAccount();
            }

            if (ar){
                account.setShoppingCart(null);
                account = null;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        return "Shopping Cart: "+getWebUser().getLogin_id();
    }

    public void printShoppingCart(){
        System.out.println("created: "+created.toString());
        System.out.println(account);
        System.out.println(webUser);
        for(LineItem lineItem:lineItems){
            System.out.println(lineItem);
        }
    }

}
