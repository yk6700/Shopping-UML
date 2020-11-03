package com.company;

import java.util.ArrayList;

public class Product {
    private String id;
    private String name;
    private Supplier supplier;
    private ArrayList<LineItem> lineItems;
    private PremiumAccount premiumAccount;

    public Product(String id, String name, Supplier supplier) {
        this.id = id;
        this.name = name;

        this.supplier = supplier;
        boolean added = this.supplier.addProduct(this);
        
        lineItems = new ArrayList<>();
    }

    public void setLineItems(ArrayList<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public ArrayList<LineItem> getLineItems() {
        return lineItems;
    }

    public PremiumAccount getPremuimAccount() {
        return premiumAccount;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public void setPremuimAccount(PremiumAccount premiumAccount) {
        this.premiumAccount = premiumAccount;
    }

    public boolean addPremuimAccount(PremiumAccount pa) {
        if (premiumAccount != null) {
            return false;
        }

        premiumAccount = pa;
        return true;
    }

    public boolean addLineItem(LineItem lineItem) {
        if (lineItems.contains(lineItem))
            return false;

        lineItems.add(lineItem);
        return true;
    }

    public boolean removeProduct(){
        if (lineItems != null){
            for (LineItem item:lineItems) {
                item.removeItem();
            }
            lineItems = null;
        }
        if (supplier != null){
            supplier.getProducts().remove(this.id);
            supplier = null;
        }
        if (premiumAccount != null){
            premiumAccount.getProducts().remove(this.id);
            premiumAccount = null;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Product: "+name;
    }
    
    public void printProduct(){
        System.out.println("ID: "+id);
        System.out.println("Name: "+name);
        System.out.println(supplier);
        for(LineItem l:lineItems){
            System.out.println(l);
        }
        if(premiumAccount !=null){
            System.out.println(premiumAccount);
        }
    }
}

