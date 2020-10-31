package com.company;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static HashMap<String, Supplier> supplierHashMap = new HashMap<>();
    public static HashMap<String, Product> productHashMap = new HashMap<>();
    public static HashMap<String, Account> accountHashMap = new HashMap<>();
    public static HashMap<String, Payment> paymentHashMap = new HashMap<>();
    public static HashMap<String, Customer> customerHashMap = new HashMap<>();

    public static void main(String[] args) {
        supplierHashMap.put("123", new Supplier("123", "Moshe"));
        productHashMap.put("Bamba", new Product("Bamba", "Bamba", supplierHashMap.get("Moshe")));


    }

    
    
    public static void addUser(){
    }
    
    public static void removeUser(){

    }

    public static void login(String id, String password){
        
    }
    
    public static void logout(){
    
    }
    
    public static void makeOrder(){
    
    }
    
    public static void displayOrder(){ //from here
        throw new NotImplementedException();
    }
    
    public static void linkToPremiumAccount(){
        throw new NotImplementedException();
    }
    
    public static boolean addProduct(String id, String name, String supplier_id, String supplier_name){
        if(!productHashMap.containsKey(id)){
            Supplier supplier = new Supplier(supplier_id,supplier_name);
            productHashMap.put(id, new Product(id, name, supplier));
            return true;
        }
        else
            return false;
    }
    
    public static boolean deleteProduct(String id){
        if(productHashMap.containsKey(id)){
            productHashMap.remove(id);
            return true;
        }
        else
            return false;
        }

    
    public static void displayObjects(){
        throw new NotImplementedException();
    }
    
    public static void displaySpecificObject(){
        throw new NotImplementedException();
    }
}
