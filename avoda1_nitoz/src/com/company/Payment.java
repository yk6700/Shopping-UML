package com.company;

import java.util.Date;

public abstract class Payment {
    private String id;
    private Date paid;
    private float total;
    private String details;
    protected Account account;
    
    
    public Account getAccount() {
        return account;
    }
}
