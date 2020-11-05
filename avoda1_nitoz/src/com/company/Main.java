package com.company;

import java.time.LocalDate;
import java.util.*;


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
        p1.setPrice(1);
        p1.setQuantity(100);
        premiumAccount.addProduct(p1);
        ///////////////////////////////////////////////////
        String command="";
        Scanner in=new Scanner(System.in);
        do{
            System.out.println("Please type your command(not the number) or type exit to exit\nOptions:");
            System.out.println("1. Add WebUser [login_id]");
            System.out.println("2. Remove WebUser [login_id]");
            System.out.println("3. Login WebUser [login_id]");
            System.out.println("4. Logout WebUser [login_id]");
            System.out.println("5. Make order");
            System.out.println("6. Display order");
            System.out.println("7. Link Product [Product_name]");
            System.out.println("8. Add Product");
            System.out.println("9. Delete Product [Product_name]");
            System.out.println("10. ShowAllObjects");
            System.out.println("11. ShowObjectId [Id]");
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
                makeOrder(in);
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
            if(command.equals("ShowAllObjects")){
                displayObjects();
                continue;
            }
            if(command.contains("ShowObjectId")){
                String id=command.substring(command.lastIndexOf("Id")+3);
                displaySpecificObject(id);
            }
            else {
                System.out.println("Invalid command");
            }
        }
        while(!command.equals("exit"));
    }

    public static void addObject(Object o){
        objects.put(objectId,o);
        objectId++;
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
            orderHashMap.keySet().removeIf(order -> orderHashMap.get(order).getAccount() == null);
            paymentHashMap.keySet().removeIf(payment -> paymentHashMap.get(payment).getAccount() == null);
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


    public static boolean logout(String id){
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

        if (onlineUser == null){
            System.out.println("No one logged in");
            return;
        }

        System.out.println("Sellers list:");
        int i = 1;
        for (Account account:accountHashMap.values()) {
            if (account instanceof PremiumAccount && !account.getId().equals(onlineUser.getLogin_id())){
                System.out.println(i+") "+account.getId());
                i++;
            }
        }
        if (i == 1){
            System.out.println("There is no one to buy from");
            return;
        }

        System.out.println("Enter user name from the list above that you want to buy from:");
        String userName = in.nextLine();
        if (!accountHashMap.containsKey(userName)){
            System.out.println("User dose'nt exist");
            return;
        }
        Account seller = accountHashMap.get(userName);
        if (!(seller instanceof PremiumAccount)){
            System.out.println("User can't sell products");
            return;
        }
        PremiumAccount pSell = (PremiumAccount)seller;
        if (pSell.getProducts().size() == 0){
            System.out.println("Seller dose'nt have any products to sell at the moment");
            return;
        }

        ArrayList<LineItem> items = new ArrayList<>();
        Order order = new Order(onlineUser.getCustomer().getAddress(),OrderStatus.New,0,onlineUser.getCustomer().getAccount());
        float total = 0;
        boolean buy = true;

        do {
            System.out.println("Products list:");
            i = 1;
            for (Product product : pSell.getProducts()) {
                System.out.println(i + ") " + product.getName());
                System.out.println("\tPrice: "+product.getPrice());
                System.out.println("\tQuantity: "+product.getQuantity());
            }

            System.out.println("Choose one of the Products above that you would like to purchase: (Choose By Name)");
            String productName = in.nextLine();
            if (!productHashMap.containsKey(productName)) {
                System.out.println("Product dosent exist");
                continue;
            }
            Product product = productHashMap.get(productName);
            if (!pSell.getProducts().contains(product)) {
                System.out.println("Product not on the list");
                continue;
            }
            System.out.println("How many units of that product would you like to purchase:");
            String quantityS = in.nextLine();
            int quantity = 1;
            try {
                quantity = Integer.parseInt(quantityS);
            } catch (Exception e) {
                System.out.println("not a number so 1 it is");
            }

            if (quantity > product.getQuantity()){
                System.out.println("Not enough units available from that product");
                continue;
            }

            product.setQuantity(product.getQuantity()-quantity);
            LineItem item = new LineItem(quantity, product.getPrice(), onlineUser.getShoppingCart(), order, product);
            items.add(item);
            total += product.getPrice() * quantity;

            System.out.println("Would you like to continue buying? (Write yes OR no):");
            String ctb = in.nextLine();
            if (ctb.contains("yes")){
                buy = true;
            }
            else
                buy = false;

        }while (buy);


        ArrayList<Payment> pays = new ArrayList<>();
        System.out.println("choose method of payment: (1 for ImmediatePayment, and 2 for DelayedPayment)");
        String type = in.nextLine();
        boolean pay = true;
        if (type.contains("1")){
            pay = true;
        }
        else {
            pay = false;
        }

        System.out.println("choose in how many payments would you like to distribute the amount:");
        String amount = in.nextLine();

        int amt = 1;
        try {
            amt = Integer.parseInt(amount);
        } catch (Exception e) {
            System.out.println("not a number so 1 it is");
        }

        System.out.println("Enter payments massage:");
        String msg = in.nextLine();

        for (int j = 0; j<amt; j++){
            if (pay){
                Payment p = new ImmediatePayment(Calendar.getInstance().getTime(),(total/amt),msg,order,onlineUser.getCustomer().getAccount(),true);
                pays.add(p);
            }
            else {
                Payment p = new DelayedPayment(Calendar.getInstance().getTime(),(total/amt),msg,order,onlineUser.getCustomer().getAccount(), LocalDate.now().plusMonths(1));
                pays.add(p);
            }
        }

        order.setTotal(total);
        Account ac = onlineUser.getCustomer().getAccount();
        ac.setBalance(ac.getBalance() - (int)total);
        pSell.setBalance(seller.getBalance()+(int)total);

        addObject(order);
        orderHashMap.put(order.getNumber(),order);
        for (LineItem li:items) {
            addObject(li);
            LineItemsList.add(li);
        }
        for (Payment pa:pays) {
            addObject(pa);
            paymentHashMap.put(pa.getId(),pa);
        }

        System.out.println("Thank you for shopping");
    }

    public static void displayOrder(){
        if(onlineUser == null){
            System.out.println("There is no one logged in");
            return;
        }

        if(onlineUser.getLastOrder() == null){
            System.out.println("no last order for the online user");
            return;
        }

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
        Scanner in=new Scanner(System.in);
        String command="";
        System.out.println("Choose price for the product:");
        command = in.nextLine();
        int price = 1;
        try {
            price=Integer.parseInt(command);
        }catch (NumberFormatException e){
            System.out.println("Since you didnt write numbers we decided it will be: 1");
        }
        product.setPrice(price);

        System.out.println("Choose quantity for the product:");
        command = in.nextLine();
        int quantity = 100;
        try {
            quantity=Integer.parseInt(command);
        }catch (NumberFormatException e){
            System.out.println("Since you didnt write numbers we decided it will be: 100");
        }
        product.setQuantity(quantity);
        ((PremiumAccount)onlineUser.getCustomer().getAccount()).addProduct(product);
        return true;


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

        return true;
    }


    public static void displayObjects(){

        for (Integer in : objects.keySet()){
            System.out.println("Uniq ID: "+in.toString());
            System.out.println((objects.get(in)).toString());
            System.out.println();
        }

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

