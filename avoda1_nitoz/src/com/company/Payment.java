package com.company;

import java.util.Date;

public abstract class Payment {
    protected String id;
    protected Date paid;
    protected float total;
    protected String details;
    private Order order;
    private Account account;

    public Payment(String id, Date paid, float total, String details, Order order, Account account) {
        this.id = id;
        this.paid = paid;
        this.total = total;
        this.details = details;
        this.order = order;
        this.account = account;
        order.addPayment(this);
        account.addPayment(this);
    }

    public String getId() {
        return id;
    }

    public Date getPaid() {
        return paid;
    }

    public float getTotal() {
        return total;
    }

    public String getDetails() {
        return details;
    }

    public Order getOrder() {
        return order;
    }

    public Account getAccount() {
        return account;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPaid(Date paid) {
        this.paid = paid;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setAccount(Account account) {
        this.account = account;
        
    }

    public boolean removePayment(){
        account = null;
        order = null;
        return true;
    }
    
    
    public void printPayment(){
        System.out.println("Paid Date:"+paid.getTime());
        System.out.println("Total:"+total);
        System.out.println("Details:"+details);
        System.out.println(account);
        System.out.println(order);
    }
}
