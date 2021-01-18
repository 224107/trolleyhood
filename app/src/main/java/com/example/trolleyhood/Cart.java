package com.example.trolleyhood;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class Cart extends Application {

    public List<CartPosition> cart = new ArrayList<>();

    public void addPosition(CartPosition position){
        for(CartPosition cp : cart){
            if(cp.product.name.equals(position.product.name)){
                cp.quantity = cp.quantity + position.quantity;
                return;
            }
        }
        cart.add(position);
    }
    public void deletePosition(String name){
        for(CartPosition cp : cart){
            if(cp.product.name.equals(name)){
                cart.remove(cp);
                return;
            }
        }
    }
}
