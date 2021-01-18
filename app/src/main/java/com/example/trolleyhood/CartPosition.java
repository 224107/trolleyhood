package com.example.trolleyhood;

public class CartPosition {
    public Product product =  new Product();
    public double quantity;

    CartPosition(Product p, double q){
        this.product = p;
        this.quantity = q;
    }
    public String getProductName(){
        return product.name;
    }
}
