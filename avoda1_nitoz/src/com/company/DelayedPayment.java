package com.company;

import java.time.LocalDate;
import java.util.Date;

public class DelayedPayment extends Payment{
    private LocalDate paymentDate;

    public DelayedPayment(Date paid, float total, String details, Order order, Account account,LocalDate paymentDate) {
        super(paid, total, details, order, account);
        this.paymentDate = paymentDate;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }
    
    @Override
    public String toString() {
        return "DelayedPayment: "+id;
    }
    
    public void printPayment(){
        super.printPayment();
        System.out.println("Payment Date: "+paymentDate);
    }
}
