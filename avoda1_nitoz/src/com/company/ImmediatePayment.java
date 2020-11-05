package com.company;

import java.util.Date;

public class ImmediatePayment extends Payment {
    private Boolean phoneConfirmation;

    public ImmediatePayment(Date paid, float total, String details, Order order, Account account,Boolean phoneConfirmation) {
        super(paid,total, details, order, account);
        this.phoneConfirmation = phoneConfirmation;
    }

    public Boolean getPhoneConfirmation() {
        return phoneConfirmation;
    }
    
    @Override
    public String toString() {
        return "ImmediatePayment: "+id;
    }
    
    public void printPayment(){
        super.printPayment();
        System.out.println("Phone confirmation: "+phoneConfirmation);
    }
}
