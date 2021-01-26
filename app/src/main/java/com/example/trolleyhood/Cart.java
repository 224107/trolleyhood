package com.example.trolleyhood;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class Cart extends Application {

    //all data used across application should be in this class
    //cart of the user, and orders of others
    public List<CartPosition> cartPositions = new ArrayList<>();
    public OrdersRepo ordersRepo = new OrdersRepo();

    public void addPosition(CartPosition position){
        for(CartPosition cp : cartPositions){
            if(cp.product.name.equals(position.product.name)){
                cp.quantity = cp.quantity + position.quantity;
                return;
            }
        }
        cartPositions.add(position);
    }
    public void deletePosition(String name){
        for(CartPosition cp : cartPositions){
            if(cp.product.name.equals(name)){
                cartPositions.remove(cp);
                return;
            }
        }
    }
    public List<CartPosition> giveAll(){
        return cartPositions;
    }
}
