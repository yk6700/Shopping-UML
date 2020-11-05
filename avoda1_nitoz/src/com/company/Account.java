package com.company;

import java.util.ArrayList;
import java.util.Calendar;
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
    private Order lastOrder;

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getBilling_address() {
        return billing_address;
    }

    public Account(String id, String billing_address, boolean is_closed, Date open, Date closed, int balance, Customer customer) {
        this.id = id;
        this.billing_address = billing_address;
        this.is_closed = is_closed;
        this.open = open;
        this.closed = closed;
        this.balance = balance;
        this.customer = customer;
        payments=new ArrayList<>();
        orders=new ArrayList<>();
        shoppingCart = new ShoppingCart(Calendar.getInstance().getTime(), this, customer.getWebUser());
        lastOrder = null;

    }

    public boolean isIs_closed() {
        return is_closed;
    }

    public Date getOpen() {
        return open;
    }

    public Date getClosed() {
        return closed;
    }

    public int getBalance() {
        return balance;
    }

    public ArrayList<Payment> getPayments() {
        return payments;
    }

    public void setBilling_address(String billing_address) {
        this.billing_address = billing_address;
    }

    public void setIs_closed(boolean is_closed) {
        this.is_closed = is_closed;
    }

    public void setOpen(Date open) {
        this.open = open;
    }

    public void setClosed(Date closed) {
        this.closed = closed;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setPayments(ArrayList<Payment> payments) {
        this.payments = payments;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public void addPayment(Payment p){
        if(p.getAccount()==this){
            payments.add(p);
        }
    }

    public Order getLastOrder() {
        return lastOrder;
    }
    
    public void addOrder(Order o){
        if(o.getAccount()==this){
            this.orders.add(o);
            lastOrder = o;
        }
    }

    public boolean removeAccount() {
        if (customer != null){
            boolean cr = customer.removeCustomer();
            if (cr){
                customer = null;
            }
        }
        if (payments != null){
            for (Payment pay : payments) {
                boolean pr = pay.removePayment();
            }
            payments = null;
        }
        if (orders != null){
            for (Order order : orders) {
                boolean or = order.removeOrder();
            }
            orders = null;
            lastOrder = null;
        }

        return true;
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
    
    @Override
    public String toString() {
        return "Account: " + id;
    }
    
    public void printAccount(){
        System.out.println("Billing Address: "+billing_address);
        System.out.println("Balance: "+balance);
        System.out.println("is closed: "+is_closed);
        System.out.println("Open: "+open);
        System.out.println(shoppingCart);
        System.out.println(customer);
        for(Order o :orders){
            System.out.println(o);
        }
        for(Payment p :payments){
            System.out.println(p);
        }
    }
}
