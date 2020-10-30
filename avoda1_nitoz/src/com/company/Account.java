package com.company;

import java.util.ArrayList;
import java.util.Date;

public class Account {
    protected String id;
    protected String billing_address;
    protected boolean is_closed;
    protected Date open;
    protected Date closed;
    protected int balance;
    protected ShoppingCart shoppingCart;
    protected Customer customer;
    protected ArrayList<Payment> payments;
    protected ArrayList<Order> orders;
    
    public Account(String id, String billing_address, boolean is_closed, Date open, Date closed, int balance, ShoppingCart shoppingCart, Customer customer) {
        this.id = id;
        this.billing_address = billing_address;
        this.is_closed = is_closed;
        this.open = open;
        this.closed = closed;
        this.balance = balance;
        payments=new ArrayList<>();
        orders=new ArrayList<>();
        if(shoppingCart.getAccount()==null && customer.getAccount()==null){
            this.shoppingCart = shoppingCart;
            this.customer = customer;
        }
        else{
            throw new RuntimeException("Account can only be connected to one shopping cart and one customer");
        }
    }
    
    public void addPayment(Payment p){
        if(p.getAccount()==null){
            payments.add(p);
        }
        else{
            throw new RuntimeException("Payment can be connected only to one account");
        }
    }
    
    public void addOrder(Order o){
        if(o.getAccount()==null){
            orders.add(o);
        }
        else{
            throw new RuntimeException("Order can be connected only to one account");
        }
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
}
