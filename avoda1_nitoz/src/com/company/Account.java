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
        /*if(shoppingCart.getAccount()==null && customer.getAccount()==null){
            this.shoppingCart = shoppingCart;
            this.customer = customer;
        }
        else{
            throw new RuntimeException("Account can only be connected to one shopping cart and one customer");
        }*/
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
        System.out.println("Closed: "+closed);
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
