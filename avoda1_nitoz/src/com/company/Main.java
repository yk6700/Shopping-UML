package com.company;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;


public class Main {

    public static Integer objectId = 1;
    public static HashMap<Integer,Object> objects = new HashMap<>();
    
    public static HashMap<String, Supplier> supplierHashMap = new HashMap<>();
    public static HashMap<String, Product> productHashMap = new HashMap<>();
    public static HashMap<String, Account> accountHashMap = new HashMap<>();
    public static HashMap<String, Payment> paymentHashMap = new HashMap<>();
    public static HashMap<String, Customer> customerHashMap = new HashMap<>();
    public static HashMap<String, WebUser> webUserHashMap = new HashMap<>();
    public static ArrayList<ShoppingCart> shoppingCarts = new ArrayList<>();
    public static HashMap<String,Order> orderHashMap=new HashMap<>();
    public static ArrayList<LineItem> LineItemsList = new ArrayList<>();
    public static WebUser onlineUser = null;
    
    public static void main(String[] args) {
        /*
        supplierHashMap.put("123", new Supplier("123", "Moshe"));
        productHashMap.put("Bamba", new Product("Bamba", "Bamba", supplierHashMap.get("123")));
        productHashMap.put("Ramen", new Product("Ramen", "Ramen", supplierHashMap.get("123")));

        webUserHashMap.put("Dani", new WebUser("Dani", "Dani123", new Address("Ashdod"), "0521111111", "Dani@bgu", false, 0));
        webUserHashMap.put("Dana", new WebUser("Dana", "Dana123", new Address("Beer Sheba"), "0523456789", "Dana@bgu", true, 0));

        accountHashMap.put("Dani", webUserHashMap.get("Dani").getCustomer().getAccount());
        accountHashMap.put("Dana", webUserHashMap.get("Dana").getCustomer().getAccount());

        ((PremuimAccount)accountHashMap.get("Dana")).addProduct(productHashMap.get("Bamba"));
        */
        Supplier s1 = new Supplier("123","Moshe");
        supplierHashMap.put("123",s1);
        objects.put(objectId,s1);
        objectId++;

        Product p1 = new Product("Bamba","Bamba",s1);
        productHashMap.put("Bamba",p1);
        objects.put(objectId,p1);
        objectId++;

        Product p2 = new Product("Ramen","Ramen",s1);
        productHashMap.put("Ramen",p2);
        objects.put(objectId,p2);
        objectId++;

        addUser("Dani", "Dani123", false, new Address("Ashdod"), "0521111111", "Dani@bgu",100);
        addUser("Dana", "Dana123",true, new Address("Beer Sheba"), "0523456789", "Dana@bgu",100);
        PremiumAccount premiumAccount = (PremiumAccount) accountHashMap.get("Dana");
        premiumAccount.addProduct(p1);
        ///////////////////////////////////////////////////
        String command="";
        Scanner in=new Scanner(System.in);
        do{
            System.out.println("Please type your command(not the number) or type exit to exit\nOptions:");
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
                if (!webUserHashMap.containsKey(id)){
                    System.out.println("Web User doesnt exist");
                }
                else {
                    String password=getPassword(in);
                    login(id,password);
                }
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
                String productName=command.substring(command.lastIndexOf("Product")+8);
                linkToPremiumAccount(productName);
                continue;
            }
            if(command.equals("Add Product")){
                createProduct(in);
                continue;
            }
            if(command.contains("Delete Product")){
                String name=command.substring(command.lastIndexOf("Product")+8);
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
        if (webUserHashMap.containsKey(id)){
            System.out.println("WebUser already exist");
            return;
        }

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
        System.out.println("Please enter your balance (numbers only)");
        String balanceStr=in.nextLine();
        int balance = 100;
        try {
            balance=Integer.parseInt(balanceStr);
        }catch (NumberFormatException e){
            System.out.println("Since you didnt write numbers we decided it will be: 100");
        }
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
        //System.out.println("Please enter supplier name");
        //String supplierName=in.nextLine();
        addProduct(id,productName,supplierId);
    }
    
    
    public static void addUser(String login_id, String password,boolean isPremium, Address address, String phone, String email, int balance ){
        WebUser webUser = new WebUser(login_id , password, address, phone, email, isPremium, balance);
        webUserHashMap.put(webUser.getLogin_id(), webUser);
        customerHashMap.put(webUser.getLogin_id(),webUser.getCustomer());
        accountHashMap.put(webUser.getLogin_id(),webUser.getCustomer().getAccount());
        shoppingCarts.add(webUser.getShoppingCart());

        objects.put(objectId,webUser);
        objectId++;
        objects.put(objectId,webUser.getCustomer());
        objectId++;
        objects.put(objectId,webUser.getCustomer().getAccount());
        objectId++;
        objects.put(objectId,webUser.getShoppingCart());
        objectId++;
    }

    public static void removeUser(String id){
        if (!webUserHashMap.containsKey(id)){
            System.out.println("Web User doesnt exist");
            return;
        }
        WebUser webUser = webUserHashMap.get(id);
        if (webUser == onlineUser)
            onlineUser = null;
        boolean removed = webUser.removeWebUser();
        if (removed){
            webUserHashMap.remove(webUser.getLogin_id());
            accountHashMap.remove(webUser.getLogin_id());
            customerHashMap.remove(webUser.getLogin_id());
            shoppingCarts.removeIf(shoppingCart -> shoppingCart.getWebUser() == null);
            orderHashMap.entrySet().removeIf(order -> orderHashMap.get(order).getAccount() == null);
            paymentHashMap.entrySet().removeIf(payment -> paymentHashMap.get(payment).getAccount() == null);
            LineItemsList.removeIf(lineItem -> lineItem.getShoppingCart() == null);

            objects.keySet().removeIf(o -> (objects.get(o) instanceof WebUser)&&(((WebUser)objects.get(o)).getCustomer() == null));
            objects.keySet().removeIf(o -> (objects.get(o) instanceof Customer)&&(((Customer)objects.get(o)).getAccount() == null));
            objects.keySet().removeIf(o -> (objects.get(o) instanceof Account)&&(((Account)objects.get(o)).getCustomer() == null));

            objects.keySet().removeIf(o -> (objects.get(o) instanceof LineItem)&&(((LineItem)objects.get(o)).getProduct() == null));
            objects.keySet().removeIf(o -> (objects.get(o) instanceof Payment)&&(((Payment)objects.get(o)).getAccount() == null));
            objects.keySet().removeIf(o -> (objects.get(o) instanceof Order)&&(((Order)objects.get(o)).getAccount() == null));
            objects.keySet().removeIf(o -> (objects.get(o) instanceof ShoppingCart)&&(((ShoppingCart)objects.get(o)).getAccount() == null));
        }

    }

    
    
    public static boolean login(String id,String password) { //TODO should i check if he is already logged in?
        if (!webUserHashMap.containsKey(id)){
            System.out.println("Web User doesnt exist");
            return false;
        }
        WebUser webUser = webUserHashMap.get(id);

        if (!password.equals(webUser.getPassword())){
            System.out.println("Incorrect password");
            return false;
        }
        if (onlineUser != null){
            onlineUser.setState(UserState.Blocked);
        }
        onlineUser = webUser;
        webUser.setState(UserState.Active);
        return true;
    }


    public static boolean logout(String id){ //TODO should i check if he is already logged out?
        if (!webUserHashMap.containsKey(id)){
            System.out.println("Web User doesnt exist");
            return false;
        }
        if (onlineUser == null || !onlineUser.getLogin_id().equals(id)){
            System.out.println("Web User already logged out");
            return false;
        }
        onlineUser.setState(UserState.Blocked);
        onlineUser = null;
        return true;
    }

    public static void makeOrder(Scanner in){
        if (onlineUser != null){
        System.out.println("Please enter user name that you want to buy from");
        Account sell = null;
        do {
            String userName = in.nextLine();
            //String Id = webUserHashMap.get(userName).getCustomer().getId();
            sell = accountHashMap.get(userName);
            if (!(sell instanceof PremiumAccount))
                System.out.println("Please choose a premium account");
        }while (!(sell instanceof PremiumAccount));

        ArrayList<Order> orders = sell.getOrders();

        System.out.println("You can choose those products:");


        //Print Options
        PremiumAccount sellPremuim = (PremiumAccount) sell;
        ArrayList<Product> products = sellPremuim.getProducts();
        for (Product product: products)
            product.printProduct();


        //Ask
        System.out.println("Which item ID would you want?");
        String id = in.nextLine();
        System.out.println("How much do you want to buy?");
        String quantilyStr = in.nextLine();

        int quantilyInt = 0;
        try{
        quantilyInt = Integer.parseInt(quantilyStr);}
        catch (Exception e){
            System.out.println("Please enter number"); }//TODO fix


        Order order = new Order(Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), onlineUser.getCustomer().getAddress(), OrderStatus.New, quantilyInt, onlineUser.getCustomer().getAccount());

        objects.put(objectId, order);
        objectId++;

        //LineItem lineItem = new LineItem(quantilyInt, 666, sell.getShoppingCart(), order, );

        onlineUser.getCustomer().getAccount().addOrder(order);

        System.out.println("Would you want to buy more? (yes or no)");
        String anser = in.nextLine();
    }}

    public static void displayOrder(){
        onlineUser.getLastOrder().printOrder();
    }
    

    public static boolean linkToPremiumAccount(String id){


        if (!productHashMap.containsKey(id)){
            System.out.println("Product "+id+" dose'nt exist");
            return false;
        }
        if (onlineUser == null || !(onlineUser.getCustomer().getAccount() instanceof PremiumAccount)){
            System.out.println("There are no premium accounts that are connected right now");
            return false;
        }
        Product product = productHashMap.get(id);
        if (product.getPremuimAccount() != null){
            System.out.println("Product "+id+" already connected to a Premium account");
            return false;
        }
        ((PremiumAccount)onlineUser.getCustomer().getAccount()).addProduct(product);
        return true;

        /*
        if(onlineUser.isPremiumAccount()){
            onlineUser.addProductToPremium((Product)objects.get(Integer.parseInt(id)));
            return true;
        }
        return false;
        */
    }

    public static boolean addProduct(String id, String name, String supplier_id){
        if (productHashMap.containsKey(name)){
            System.out.println("Product "+name+ " already exist");
            return false;
        }
        if (!supplierHashMap.containsKey(supplier_id)){
            System.out.println("Supplier dose'nt exist");
            return false;
        }
        Supplier supplier = supplierHashMap.get(supplier_id);
        Product product = new Product(id,name,supplier);
        productHashMap.put(name, product);
        objects.put(objectId,product);
        objectId++;
        return true;
    }

    public static boolean deleteProduct(String name){

        if (!productHashMap.containsKey(name)){
            System.out.println("Product "+name+ " dose'nt exist");
            return false;
        }
        Product p = productHashMap.get(name);
        p.removeProduct();
        productHashMap.remove(name);
        LineItemsList.removeIf(lineItem -> lineItem.getShoppingCart() == null);

        objects.keySet().removeIf(o -> (objects.get(o) instanceof Product)&&(((Product)objects.get(o)).getSupplier() == null));
        objects.keySet().removeIf(o -> (objects.get(o) instanceof LineItem)&&(((LineItem)objects.get(o)).getProduct() == null));


        /*
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
            */
        return true;
    }


    public static void displayObjects(){

        for (Integer in : objects.keySet()){
            System.out.println("Uniq ID: "+in.toString());
            System.out.println((objects.get(in)).toString());
            System.out.println();
        }

        /*
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
       */
    }

    public static void displaySpecificObject(String id){
        for (Integer in : objects.keySet()) {
            if (in.toString().equals(id)){
                Object o = objects.get(in);

                if (o instanceof WebUser)
                    ((WebUser)o).printWebUser();
                else if (o instanceof Customer)
                    ((Customer)o).printCustomer();
                else if (o instanceof Account)
                    ((Account)o).printAccount();
                else if (o instanceof ShoppingCart)
                    ((ShoppingCart)o).printShoppingCart();
                else if (o instanceof Product)
                    ((Product)o).printProduct();
                else if (o instanceof LineItem)
                    ((LineItem)o).printLineItem();
                else if (o instanceof Order)
                    ((Order)o).printOrder();
                else if (o instanceof Supplier)
                    ((Supplier)o).printSupplier();
                else if (o instanceof Payment)
                    ((Payment)o).printPayment();

                return;
            }
        }
    }
}

