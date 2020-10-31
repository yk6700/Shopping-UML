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
    public static HashMap<String, WebUser> webUserHashMap = new HashMap<>();
    public static ArrayList<ShoppingCart> shoppingCarts = new ArrayList<>();
    
    
    public static void main(String[] args) {
        supplierHashMap.put("123", new Supplier("123", "Moshe"));
        productHashMap.put("Bamba", new Product("Bamba", "Bamba", supplierHashMap.get("123")));
        productHashMap.put("Ramen", new Product("Ramen", "Ramen", supplierHashMap.get("123")));

        webUserHashMap.put("Dani", new WebUser("Dani", "Dani123", new Address("fuck you"), "0521111111", "Dani@bgu", false, 0));
        webUserHashMap.put("Dana", new WebUser("Dana", "Dana123", new Address("Beer Sheba"), "0523456789", "Dana@bgu", true, 0));

        accountHashMap.put("Dani", webUserHashMap.get("dani").getCustomer().getAccount());
        accountHashMap.put("Dana", webUserHashMap.get("dana").getCustomer().getAccount());

        ((PremuimAccount)accountHashMap.get("Dana")).addProduct(productHashMap.get("Bamba"));
        
        String command="";
        Scanner in=new Scanner(System.in);
        do{
            System.out.println("Please enter your command or type exit to exit");
            command=in.nextLine();
            if(command.contains("Add WebUser")){
                String id=getId(command);
                createUser(id,in);
                continue;
            }
            if(command.contains("Remove WebUser")){
                String id=getId(command);
                removeUser(id);
                continue;
            }
            if(command.contains("Login WebUser")){
                String id=getId(command);
                String password=getPassword(in);
                login(id,password);
                continue;
            }
            if(command.contains("Logout WebUser")){
                String id=getId(command);
                logout(id);
                continue;
            }
            if(command.equals("Make order")){
                option5(in);
                continue;
            }
            if(command.equals("Display order")){
                displayOrder();
                continue;
            }
            if(command.contains("Link Product")){
                String productName=command.substring(command.lastIndexOf("Product")+7);
                linkToPremiumAccount(productName);
                continue;
            }
            if(command.equals("Add Product")){
                createProduct(in);
                continue;
            }
            if(command.contains("Delete Product")){
                String name=command.substring(command.lastIndexOf("Product")+7);
                deleteProduct(name);
                continue;
            }
            if(command.equals("ShowAllObjects")){
                displayObjects();
                continue;
            }
            if(command.contains("ShowObjectId")){
                String id=command.substring(command.lastIndexOf("Id")+3);
                displaySpecificObject(id);
            }
        }
        while(!command.equals("exit"));
    }
    
    private static String getId(String command){
        return command.substring(command.lastIndexOf("User")+5);
    }
    
    private static void createUser(String id,Scanner in){
        String input="";
        String password=getPassword(in);
        System.out.println("Please choose if you want to be premium account,type yes or no");
        input=in.nextLine();
        boolean premiumAccount=false;
        if(input.equals("yes")){
            premiumAccount=true;
        }
        System.out.println("Please enter your address");
        String addressStr=in.nextLine();
        Address address=new Address(addressStr);
        System.out.println("Please enter your phone number");
        String phone=in.nextLine();
        System.out.println("Please enter your email");
        String email=in.nextLine();
        System.out.println("Please enter your balance");
        String balanceStr=in.nextLine();
        int balance=Integer.parseInt(balanceStr);
        addUser(id,password,premiumAccount,address,phone,email,balance);
    }
    
    private static String getPassword(Scanner in){
        System.out.println("Please enter password");
        return in.nextLine();
    }
    
    private static void option5(Scanner in){
        System.out.println("please enter user id you want to buy from him");
        String userId=in.nextLine();
        
    }
    
    private static void createProduct(Scanner in){
        System.out.println("Please enter product id");
        String id=in.nextLine();
        System.out.println("Please enter product name");
        String productName=in.nextLine();
        System.out.println("Please enter supplier id");
        String supplierId=in.nextLine();
        System.out.println("Please enter supplier name");
        String supplierName=in.nextLine();
        addProduct(id,productName,supplierId,supplierName);
    }
    
    
    public static void addUser(String login_id, String password,boolean isPremium, Address address, String phone, String email, int balance ){
        WebUser webUser = new WebUser(login_id , password, address, phone, email, isPremium, balance);
        webUserHashMap.put(webUser.getLogin_id(), webUser);
        customerHashMap.put(webUser.getLogin_id(),webUser.getCustomer());
        accountHashMap.put(webUser.getLogin_id(),webUser.getCustomer().getAccount());
        shoppingCarts.add(webUser.getShoppingCart());
    }
    
    public static void removeUser(String id){
        WebUser webUser = webUserHashMap.get(id);
        if(webUser != null || webUser instanceof WebUser)return;
        //webUser.state = Banned;
        webUserHashMap.remove(id);
    }
    
    
    public static boolean login(String id,String password) { //TODO should i check if he is already logged in?
        if (webUserHashMap.containsKey(id)) {
            WebUser webuser = webUserHashMap.get(id);
            if (webuser.getLogin_id().equals(id) && webuser.checkPassword(password)) {
                webuser.setState(UserState.Active);
                return true;
            }
        }
        return false;
    }


    public static boolean logout(String id){ //TODO should i check if he is already logged out?
        if (webUserHashMap.containsKey(id)) {
            WebUser webuser = webUserHashMap.get(id);
            webuser.setState(UserState.Blocked);
            return true;
        }
        return false;
    }

    public static void makeOrder(){
        throw new NotImplementedException();
    }

    public static void displayOrder(){ //from here
        throw new NotImplementedException();
    }

    public static void linkToPremiumAccount(String name){
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

    public static void displaySpecificObject(String id){
        throw new NotImplementedException();
    }
}

