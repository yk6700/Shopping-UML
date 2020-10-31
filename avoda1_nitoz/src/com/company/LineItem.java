package com.company;

public class LineItem {
    private int quantity;
    private int price;
    private ShoppingCart shoppingCart;
    private Order order;
    private Product product;
    
    public LineItem(int quantity, int price, ShoppingCart shoppingCart, Order order, Product product) {
        this.quantity = quantity;
        this.price = price;
        this.shoppingCart = shoppingCart;
        boolean s = shoppingCart.addLineItem(this);
        if(!s)
            throw new RuntimeException("line item already exist");
        
        this.order = order;
        boolean o = order.addLineItem(this);
        if(!o)
            throw new RuntimeException("line item already exist");
        
        this.product = product;
        boolean p = product.addLineItem(this);
        if(!p)
            throw new RuntimeException("line item already exist");
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public int getPrice() {
        return price;
    }
    
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
    
    public Order getOrder() {
        return order;
    }
    
    public Product getProduct() {
        return product;
    }
}

