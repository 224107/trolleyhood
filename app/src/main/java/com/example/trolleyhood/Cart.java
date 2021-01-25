package com.example.trolleyhood;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class Cart extends Application {

    public List<CartPosition> cartPositions = new ArrayList<>();

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
}
