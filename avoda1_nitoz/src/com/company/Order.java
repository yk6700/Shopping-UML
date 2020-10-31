package com.company;

import java.util.ArrayList;
import java.util.Date;

public class Order {
    private String number;
    private Date Ordered;
    private Date shipprd;
    private Address ship_to;
    private OrderStatus status;
    private float total;
    private Account account;
    private ArrayList<LineItem> lineArray;
    private ArrayList<Payment> paymentsArray;

    public Order(String number, Date ordered, Date shipprd, Address ship_to, OrderStatus status, float total, Account account) {
        this.number = number;
        Ordered = ordered;
        this.shipprd = shipprd;
        this.ship_to = ship_to;
        this.status = status;
        this.total = total;
        this.account = account;
        this.lineArray = new ArrayList<>();
        this.paymentsArray = new ArrayList<>();

        this.account.addOrder(this);
    }

    public Account getAccount() {
        return account;
    }

    public Boolean addLineItem(LineItem lineItem)
    {
        if (lineItem.getOrder() == null)
        {
            lineItem.setOrder(this);
            lineArray.add(lineItem);
            return true;
        }
        else
            return false;
    }

    public Boolean addPayment(Payment payment)
    {
        if (payment.getOrder() == null)
        {
            payment.setOrder(this);
            paymentsArray.add(payment);
            return true;
        }
        else
            return false;
    }
    
    public String getNumber() {
        return number;
    }
    
    public ArrayList<Payment> getPaymentsArray() {
        return paymentsArray;
    }
}
