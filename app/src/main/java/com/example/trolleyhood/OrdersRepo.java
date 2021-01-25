package com.example.trolleyhood;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class OrdersRepo {

    List<Order> allOrders = new ArrayList<>();

    OrdersRepo(){
        // here in constructor data about users and their orders should be added to allOrders
        //for now instrumentation:
        List<CartPosition> list = new ArrayList<>();
        list.add(new CartPosition(new Product(ProductCategory.fruits,"banana",false),2.9));
        allOrders.add(new Order(new User("Joao", "mail", "0000000"), list));
        allOrders.add(new Order(new User("Grębosz","mail","0000001"), list ));
    }
    public List<CartPosition> findCart(String number){
        for(Order order : allOrders){
            if (order.user.phone.equals(number)){
                return order.cart;
            }
        }
        return null;
    }
    public String findName(String number){
        for(Order order : allOrders){
            if (order.user.phone.equals(number)){
                return order.user.name;
            }
        }
        return null;
    }
    public Order findOrder(String number){
        for(Order order : allOrders){
            if (order.user.phone.equals(number)){
                return order;
            }
        }
        return null;
    }
}
