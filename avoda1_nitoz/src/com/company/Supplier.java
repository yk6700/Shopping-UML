package com.company;

import javafx.util.Pair;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    
    @Override
    public String toString() {
        return "Supplier: "+id;
    }
    
    public void printSupplier(){
        System.out.println("ID:"+id);
        System.out.println("Name:"+name);
        for(Map.Entry<String,Product> product:products.entrySet()){
            System.out.println(product.getValue());
        }
    }

}
