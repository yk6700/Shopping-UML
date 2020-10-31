package com.company;

import java.util.ArrayList;
import java.util.Date;

public class PremuimAccount extends Account {
    
    private ArrayList<Product> products;
    
    public PremuimAccount(String id, String billing_address, boolean is_closed, Date open, Date closed, int balance, Customer customer) {
        super(id, billing_address, is_closed, open, closed, balance, customer);
        products=new ArrayList<>();
    }
    
    public void addProduct(Product p){
        this.products.add(p);
    }
}
