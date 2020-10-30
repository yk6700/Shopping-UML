package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

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
        if (!added){
            throw new RuntimeException("Product already contained in supplier");
        }

        lineItems = new ArrayList<>();
    }

    public Supplier getSupplier(){
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

    public boolean addPremuimAccount(PremuimAccount pa){
        if (premuimAccount != null){
            return false;
        }

        premuimAccount = pa;
        return true;
    }

    public boolean addLineItem(LineItem lineItem){
        if (lineItems.contains(lineItem))
            return false;

        lineItems.add(lineItem);
        return true;
    }
}
