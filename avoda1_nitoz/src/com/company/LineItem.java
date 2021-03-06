package com.company;

public class LineItem {
    private int quantity;
    private int price;
    private ShoppingCart shoppingCart;
    private Order order;
    private Product product;

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LineItem(int quantity, int price, ShoppingCart shoppingCart, Order order, Product product) {
        this.quantity = quantity;
        this.price = price;
        this.shoppingCart = shoppingCart;
        this.shoppingCart.addLineItem(this);
        //this.order = order;
        boolean o = order.addLineItem(this);
        
        this.product = product;
        boolean p = product.addLineItem(this);
        
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

    public boolean removeItem() {
        if (order != null){
            if (order.getLineArray() != null)
                order.getLineArray().remove(this);
            order = null;
        }
        if (shoppingCart != null){
            if (shoppingCart.getLineItems() != null)
                shoppingCart.getLineItems().remove(this);
            shoppingCart = null;
        }
        if (product != null){
            if (product.getLineItems() != null)
                product.getLineItems().remove(this);
            product = null;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "LineItem: "+product.getName();
    }
    
    public void printLineItem() {
        System.out.println("Quantity: "+quantity);
        System.out.println("Price: "+price);
        System.out.println(product);
        System.out.println(order);
        System.out.println(shoppingCart);
    }
}

