package com.company;

import java.util.ArrayList;

public class Product {
    private String id;
    private String name;
    private Supplier supplier;
    private ArrayList<LineItem> lineItems;
    private PremiumAccount premiumAccount;
    private int price;
    private int quantity;

    public Product(String id, String name, Supplier supplier) {
        this.id = id;
        this.name = name;
        price = 1;
        quantity = 0;
        this.supplier = supplier;
        boolean added = this.supplier.addProduct(this);
        
        lineItems = new ArrayList<>();
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPremiumAccount(PremiumAccount premiumAccount) {
        this.premiumAccount = premiumAccount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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
            while (lineItems.size() > 0){
                lineItems.get(0).removeItem();
            }
            lineItems = null;
        }
        if (supplier != null){
            supplier.getProducts().remove(this.id);
            supplier = null;
        }
        if (premiumAccount != null){
            premiumAccount.getProducts().remove(this);
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
        System.out.println("Price: "+price);
        System.out.println("Quantity: "+quantity);
        System.out.println(supplier);
        for(LineItem l:lineItems){
            System.out.println(l);
        }
        if(premiumAccount !=null){
            System.out.println(premiumAccount);
        }
    }
}

