package com.company;

import java.util.Date;

public class Order {
    private String number;
    private Date Ordered;
    private Date shipprd;
    private Address ship_to;
    private OrderStatus status;
    private float total;
    private Account account;
    
    
    public Account getAccount() {
        return account;
    }
}
