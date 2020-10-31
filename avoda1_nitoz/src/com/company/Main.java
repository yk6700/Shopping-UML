package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static HashMap<String, Supplier> supplierHashMap = new HashMap<>();
    public static HashMap<String, Product> productHashMap = new HashMap<>();
    public static HashMap<String, Account> accountHashMap = new HashMap<>();
    public static HashMap<String, Payment> paymentHashMap = new HashMap<>();
    public static HashMap<String, Customer> customerHashMap = new HashMap<>();
    public static HashMap<String, WebUser> webUserHashMap = new HashMap<>();
    public static ArrayList<ShoppingCart> shoppingCarts = new ArrayList<>();


    public static void main(String[] args) {
        supplierHashMap.put("123", new Supplier("123", "Moshe"));
        productHashMap.put("Bamba", new Product("Bamba", "Bamba", supplierHashMap.get("Moshe")));
        //customerHashMap.put("Dani", new Customer("Dani", null, null, null));
        //webUserHashMap.put("Dani", new WebUser("Dani", "Dani123", new Address("fuck you"), "0527778888", "t@bgu"));

    }


    public static void addUser(String login_id, String password, Address address, String phone, String email, int balance, boolean isPremium){
        WebUser webUser = new WebUser(login_id , password, address, phone, email, balance, isPremium);
        webUserHashMap.put(webUser.getLogin_id(), webUser);
        customerHashMap.put(webUser.getLogin_id(),webUser.getCustomer());
        accountHashMap.put(webUser.getLogin_id(),webUser.getCustomer().getAccount());
        shoppingCarts.add(webUser.getShoppingCart());
    }

    public static void removeUser(){

    }
    
    public static void login(){
    
    }
    
    public static void logout(){
    
    }
    
    public static void makeOrder(){
    
    }
    
    public static void displayOrder(){
    
    }
    
    public static void linkToPremiumAccount(){
    
    }
    
    public static void addProduct(){
    
    }
    
    public static void deleteProduct(){
    
    }
    
    public static void displayObjects(){
    
    }
    
    public static void displaySpecificObject(){
    
    }
}
