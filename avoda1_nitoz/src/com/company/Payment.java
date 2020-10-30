package com.company;

import java.util.Date;

public abstract class Payment {
    protected String id;
    protected Date paid;
    protected float total;
    protected String details;
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
    private Order order;
    private Account account;

    public Payment(String id, Date paid, float total, String details, Order order, Account account) {
        this.id = id;
        this.paid = paid;
        this.total = total;
        this.details = details;
        this.order = order;
        this.account = account;
    }

    public String getId() {
        return id;
    }

    public Date getPaid() {
        return paid;
    }

    public float getTotal() {
        return total;
    }

    public String getDetails() {
        return details;
    }

    public Order getOrder() {
        return order;
    }

    public Account getAccount() {
        return account;
    }
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
}
