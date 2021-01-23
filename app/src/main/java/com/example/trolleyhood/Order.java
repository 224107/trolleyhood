package com.example.trolleyhood;

import java.util.List;

public class Order {
    public User user;
    public List<CartPosition> cart;
    public boolean isTaken = false;

    public Order(User user, List<CartPosition> cart){
        this.user = user;
        this.cart = cart;
    }

    public void acceptOrder(){
        isTaken = true;
    }

}
