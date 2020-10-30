package com.company;

import java.util.Date;

public class ImmediatePayment extends Payment {
    private Boolean phoneConfirmation;

    public ImmediatePayment(String id, Date paid, float total, String details, Order order, Account account,Boolean phoneConfirmation) {
        super(id,paid,total, details, order, account);
        this.phoneConfirmation = phoneConfirmation;
    }

    public Boolean getPhoneConfirmation() {
        return phoneConfirmation;
    }
}
