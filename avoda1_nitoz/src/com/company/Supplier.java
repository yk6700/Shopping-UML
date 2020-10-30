package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class Supplier {
    private String id;
    private String name;
    private HashMap<String ,Product> products;

    public Supplier(String id, String name) {
        this.id = id;
        this.name = name;
        products = new HashMap<>();
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public HashMap<String ,Product> getProducts(){
        return products;
    }

    public boolean addProduct(Product product){
        if (products.containsKey(product.getId())){
            return false;
        }

        products.put(product.getId(), product);
        return true;
    }

}
