package com.company;

import java.util.ArrayList;
import java.util.Date;

public class PremiumAccount extends Account {
    
    private ArrayList<Product> products;
    
    public PremiumAccount(String id, Address billing_address, boolean is_closed, Date open, Date closed, int balance, Customer customer) {
        super(id, billing_address.getAddress(), is_closed, open, closed, balance, customer);
        products=new ArrayList<>();
    }

    public boolean removePremuimAccount() {
        this.removeAccount();
        if (products != null){
            for (Product product:products) {
                product.setPremuimAccount(null);
            }
            products = null;
        }
        return true;
    }


    
    @Override
    public String toString() {
        return "PremiumAccount: "+id;
    }
    
    @Override
    public void printAccount() {
        System.out.println(this);
        super.printAccount();
        System.out.println("Products:");
        for(Product p:products){
            System.out.println(p);
        }
    }
    
    public boolean addProduct(Product product){
        if(product.getPremuimAccount() != null || products.contains(product))
            return false;

        products.add(product);
        product.addPremuimAccount(this);
        return true;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
}
