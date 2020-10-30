package com.company;

import java.util.ArrayList;
import java.util.Date;

public class ShoppingCart {
    private Date created;
    private Account account;
    private WebUser webUser;
    private ArrayList<LineItem> lineItems;

    public ShoppingCart(Date created){
        this.created=created;
    }

    public void setWebUser(WebUser webUser){
        this.webUser = webUser;
    }


    public Date getCreated() {
        return created;
    }

}
