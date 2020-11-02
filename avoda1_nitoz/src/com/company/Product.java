package com.company;

import java.util.ArrayList;

public class Product {
    private String id;
    private String name;
    private Supplier supplier;
    private ArrayList<LineItem> lineItems;
    private PremuimAccount premuimAccount;

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

    public PremuimAccount getPremuimAccount() {
        return premuimAccount;
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

    public void setPremuimAccount(PremuimAccount premuimAccount) {
        this.premuimAccount = premuimAccount;
    }

    public boolean addPremuimAccount(PremuimAccount pa) {
        if (premuimAccount != null) {
            return false;
        }

        premuimAccount = pa;
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
        if (premuimAccount != null){
            premuimAccount.getProducts().remove(this.id);
            premuimAccount = null;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Product";
    }
    
    public void printProduct(){
        System.out.println("ID:"+id);
        System.out.println("Name:"+name);
        System.out.println(supplier);
        for(LineItem l:lineItems){
            System.out.println(l);
        }
        if(premuimAccount!=null){
            System.out.println(premuimAccount);
        }
    }
}

