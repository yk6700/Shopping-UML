package com.company;

import java.util.Date;

public class ImmediatePayment extends Payment {
    private Boolean phoneConfirimation;

    public ImmediatePayment(String id, Date paid, float total, String details, Order order, Account account,Boolean phoneConfirimation) {
        super(id,paid,total, details, order, account);
        this.phoneConfirimation = phoneConfirimation;
    }

    public Boolean getPhoneConfirimation() {
        return phoneConfirimation;
    }
}
