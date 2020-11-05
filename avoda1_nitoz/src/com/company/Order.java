package com.company;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Order {
    private String number;
    private LocalDate ordered;
    private LocalDate shipped;
    private Address ship_to;
    private OrderStatus status;
    private float total;
    private Account account;
    private ArrayList<LineItem> lineArray;
    private ArrayList<Payment> paymentsArray;
    private static int counter = 0;

    public Order(Address ship_to, OrderStatus status, float total, Account account) {
        this.number = String.valueOf(counter);
        counter++;
        this.ordered = LocalDate.now();
        this.shipped = LocalDate.now().plusDays(14);
        this.ship_to = ship_to;
        this.status = status;
        this.total = total;
        this.account = account;
        this.lineArray = new ArrayList<>();
        this.paymentsArray = new ArrayList<>();

        this.account.addOrder(this);
    }


    public void setNumber(String number) {
        this.number = number;
    }

    public void setOrdered(LocalDate ordered) {
        this.ordered = ordered;
    }

    public void setShipped(LocalDate shipped) {
        this.shipped = shipped;
    }

    public void setShip_to(Address ship_to) {
        this.ship_to = ship_to;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setLineArray(ArrayList<LineItem> lineArray) {
        this.lineArray = lineArray;
    }

    public void setPaymentsArray(ArrayList<Payment> paymentsArray) {
        this.paymentsArray = paymentsArray;
    }

    public LocalDate getOrdered() {
        return ordered;
    }

    public LocalDate getShipped() {
        return shipped;
    }

    public Address getShip_to() {
        return ship_to;
    }

    public float getTotal() {
        return total;
    }

    public Account getAccount() {
        return account;
    }

    public Boolean addLineItem(LineItem lineItem)
    {
        if (lineItem.getOrder() == null)
        {
            lineItem.setOrder(this);
            lineArray.add(lineItem);
            return true;
        }
        else
            return false;
    }

    public boolean removeLineItem(LineItem lineItem){
        if(lineItem != null && lineArray.contains(lineItem)) {
            lineArray.remove(lineItem);
            return true;
        }
        return false;
    }


    public boolean removeOrder() {
        account = null;
        if (paymentsArray != null) {
            for (Payment payment : paymentsArray) {
                payment.removePayment();
            }
            paymentsArray = null;
        }
        if (lineArray != null){
            while (lineArray.size() > 0){
                lineArray.get(0).removeItem();
            }
            lineArray = null;
        }
        return true;
    }


    public Boolean addPayment(Payment payment)
    {
        if (payment.getOrder() == this)
        {
            paymentsArray.add(payment);
            return true;
        }
        else
            return false;
    }
    
    public String getNumber() {
        return number;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public ArrayList<Payment> getPaymentsArray() {
        return paymentsArray;
    }
    
    @Override
    public String toString() {
        return "Order: "+number;
    }

    public ArrayList<LineItem> getLineArray() {
        return lineArray;
    }
    
    public void printOrder(){
        System.out.println("Ordered Date: "+ordered);
        System.out.println("shipped: "+shipped);
        System.out.println("ship to: "+ship_to.getAddress());
        System.out.println("Status: "+status.name());
        System.out.println("total: "+total);
        System.out.println(account);
        if (lineArray != null){
            System.out.println("Items:");
            for (LineItem item:lineArray) {
                System.out.println(item+" ,quantity: "+item.getQuantity()+" ,price: "+item.getPrice());
            }
        }
        System.out.println("Payments:");
        if (paymentsArray != null) {
            for (Payment p : paymentsArray) {
                System.out.println(p);
            }
        }
        
    }
}
