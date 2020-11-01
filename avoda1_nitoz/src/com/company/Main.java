package com.company;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.sound.sampled.Line;
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
    public static HashMap<String,Order> orderHashMap=new HashMap<>();
    public static ArrayList<LineItem> LineItemsList = new ArrayList<>();
    
    public static void main(String[] args) {
        supplierHashMap.put("123", new Supplier("123", "Moshe"));
        productHashMap.put("Bamba", new Product("Bamba", "Bamba", supplierHashMap.get("123")));
        productHashMap.put("Ramen", new Product("Ramen", "Ramen", supplierHashMap.get("123")));

        webUserHashMap.put("Dani", new WebUser("Dani", "Dani123", new Address("Ashdod"), "0521111111", "Dani@bgu", false, 0));
        webUserHashMap.put("Dana", new WebUser("Dana", "Dana123", new Address("Beer Sheba"), "0523456789", "Dana@bgu", true, 0));

        accountHashMap.put("Dani", webUserHashMap.get("Dani").getCustomer().getAccount());
        accountHashMap.put("Dana", webUserHashMap.get("Dana").getCustomer().getAccount());

        ((PremuimAccount)accountHashMap.get("Dana")).addProduct(productHashMap.get("Bamba"));
        
        String command="";
        Scanner in=new Scanner(System.in);
        do{
            System.out.println("Please enter your command or type exit to exit\nOptions:");
            System.out.println("1. Add WebUser");
            System.out.println("2. Remove WebUser");
            System.out.println("3. Login WebUser");
            System.out.println("4. Logout WebUser");
            System.out.println("5. Make order");
            System.out.println("6. Display order");
            System.out.println("7. Link Product");
            System.out.println("8. Add Product");
            System.out.println("9. Delete Product");
            System.out.println("10. Show All Objects");
            System.out.println("11. Show Object Id");
            System.out.println("12. exit");

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
            if(command.equals("Show All Objects")){
                displayObjects();
                continue;
            }
            if(command.contains("Show Object Id")){
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
        if(input.equals("yes"))
            premiumAccount=true;

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
        //if(webUser != null || webUser instanceof WebUser)return;*********************************
        //webUser.state = Banned;
        shoppingCarts.remove(webUser.getShoppingCart());
        Account toRemove=webUser.getCustomer().getAccount();
        for(Order o:toRemove.orders){
            if(orderHashMap.containsKey(o.getNumber())){
                for(Payment p:o.getPaymentsArray()){
                    if(paymentHashMap.containsKey(p.id)){
                        paymentHashMap.remove(p.id);
                    }
                }
                orderHashMap.remove(o.getNumber());
            }
        }
        accountHashMap.remove(toRemove);
        customerHashMap.remove(webUser.getCustomer().getId());
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

    public static void makeOrder(Scanner in){
        System.out.println("Please enter user name");
        String userName=in.nextLine();
        ArrayList<Order> orders = accountHashMap.get(userName).getOrders();
        System.out.println("You can choose those products:");

        for(Order o:orders)
        {
            if (o.getStatus().compareTo(OrderStatus.Hold) == 0) //**********************TODO check this
            {
                for( LineItem lineItem:o.getLineArray())
                {
                    System.out.print("You can buy max of " + lineItem.getQuantity() + " of " + lineItem.getProduct().getName());
                    System.out.println(" the ID product is: " + lineItem.getProduct().getId());
                }
            }
        }



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
            supplierHashMap.put(supplier_id,supplier);
            return true;
        }
        else
            return false;
    }

    public static boolean deleteProduct(String name){
        if(productHashMap.containsKey(name)){
            Product product = productHashMap.get(name);
            ArrayList<LineItem> lineitems = product.getLineItems();
            for (LineItem i : lineitems) {
                i.getShoppingCart().removeLineItem(i);
                i.getOrder().removeLineItem(i);
                i.setProduct(null);
                LineItemsList.remove(i);
            }
            product.setLineItems(null);
            product.getSupplier().getProducts().remove(name);
            if(product.getPremuimAccount() != null)
                product.getPremuimAccount().getProducts().remove(product);
            productHashMap.remove(name);
            return true;
        }
        else
            return false;
    }


    public static void displayObjects(){
        for (Supplier s : supplierHashMap.values())
            s.toString();
        for (Product p : productHashMap.values())
            p.toString();
        for (Account a : accountHashMap.values())
            a.toString();
        for (Payment p : paymentHashMap.values())
            p.toString();
        for (Customer c : customerHashMap.values())
            c.toString();
        for (WebUser w : webUserHashMap.values())
            w.toString();
        for (Order o : orderHashMap.values())
            o.toString();
        for (ShoppingCart s : shoppingCarts)
            s.toString();
        for (LineItem l : LineItemsList)
            l.toString();
    }

    public static void displaySpecificObject(String id){
        for (Supplier s : supplierHashMap.values()) {
            if (s.getId().equals(id)) {
                s.printSupplier();
                return;
            }
        }
        for (Product p : productHashMap.values()) {
            if (p.getId().equals(id)) {
                p.printProduct();
                return;
            }
        }
        for (Account a : accountHashMap.values()) {
            if(a.getId().equals(id)) {
                a.printAccount();
                return;
            }
        }
        for (Payment p : paymentHashMap.values()){
            if(p.getId().equals(id)) {
                p.printPayment();
                return;
            }
        }
        for (Customer c : customerHashMap.values()){
            if(c.getId().equals(id)) {
                c.printCustomer();
                return;
            }
        }

        for (WebUser w : webUserHashMap.values()){
            if(w.getLogin_id().equals(id)) {
                w.printWebUser();
                return;
            }
        }
        for (Order o : orderHashMap.values()){
            if(o.getNumber().equals(id)) {
                o.printOrder();
                return;
            }
        }
    }
}

