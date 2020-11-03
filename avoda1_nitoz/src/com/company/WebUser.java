package com.company;

import static com.company.UserState.New;

public class WebUser {
    private String login_id;
    private String password;
    private UserState state;
    private Customer customer;
    private ShoppingCart shoppingCart;

    public WebUser(String login_id, String password, Address address, String phone, String email, Boolean isPremium, int balance){
        this.login_id = login_id;
        this.password = password;
        this.state = New;
        this.shoppingCart=null;
        customer = new Customer(login_id, address, phone, email, balance, isPremium, this);
        customer.addWebUser(this);
      }

    public boolean checkPassword(String check){
        return this.password.equals(check);
    }

    public String getLogin_id() {
        return login_id;
    }

    public String getPassword() {
        return password;
    }

    public UserState getState() {
        return state;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        if(shoppingCart.getWebUser()==this && this.shoppingCart==null){
            this.shoppingCart=shoppingCart;
        }
     }

    public void setState(UserState state) {
        this.state = state;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }


    public boolean removeWebUser(){
        if (this.shoppingCart != null){
            boolean rs = shoppingCart.removeShoppingCart();
            if (rs)
                shoppingCart = null;
        }
        if (customer != null){
            customer.setWebUser(null);
            customer = null;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "WebUser";
    }
    
    public void printWebUser(){
        System.out.println("Login ID:"+login_id);
        System.out.println("Password:"+this.password);
        System.out.println("State:"+state);
        System.out.println(customer);
        if(shoppingCart!=null){
            System.out.println(shoppingCart);
        }
    }

    public Order getLastOrder(){
        return getCustomer().getAccount().getLastOrder();
    }

    public boolean isPremiumAccount(){
        return (getCustomer().getAccount() instanceof PremiumAccount);
    }

    public void addProductToPremium(Product product){
        ((PremiumAccount)getCustomer().getAccount()).addProduct(product);
    }
}
