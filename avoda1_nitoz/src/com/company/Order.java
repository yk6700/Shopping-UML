package com.company;

import java.util.ArrayList;
import java.util.Date;

public class Order {
    private String number;
    private Date ordered;
    private Date shipped;
    private Address ship_to;
    private OrderStatus status;
    private float total;
    private Account account;
    private ArrayList<LineItem> lineArray;
    private ArrayList<Payment> paymentsArray;

    public Order(String number, Date ordered, Date shipprd, Address ship_to, OrderStatus status, float total, Account account) {
        this.number = number;
        this.ordered = ordered;
        this.shipped = shipprd;
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

    public boolean removeLineItem(LineItem lineItem){
        if(lineItem != null && lineArray.contains(lineItem)) {
            lineArray.remove(lineItem);
            return true;
        }
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
    
    @Override
    public String toString() {
        return "Order"+number;
    }
    
    public void printOrder(){
        System.out.println(this);
        System.out.println("Ordered Date:"+ordered);
        System.out.println("shipped:"+shipped);
        System.out.println("ship to:"+ship_to.getAddress());
        System.out.println("Status:"+status.name());
        System.out.println("total:"+total);
        System.out.println(account);
        for(Payment p:paymentsArray){
            System.out.println(p);
        }
        
    }
}
