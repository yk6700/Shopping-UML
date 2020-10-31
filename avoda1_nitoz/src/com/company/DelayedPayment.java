package com.company;

import java.util.Date;

public class DelayedPayment extends Payment{
    private Date paymentDate;

    public DelayedPayment(String id, Date paid, float total, String details, Order order, Account account,Date paymentDate) {
        super(id, paid, total, details, order, account);
        this.paymentDate = paymentDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }
    
    @Override
    public String toString() {
        return "DelayedPayment"+id;
    }
    
    public void printPayment(){
        super.printPayment();
        System.out.println(this);
        System.out.println("Payment Date:"+paymentDate);
    }
}
