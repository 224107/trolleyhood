package com.example.trolleyhood;

public class Product {
    public ProductCategory category;
    public String name;
    public boolean isCountable;


    public Product(){}

    public Product(ProductCategory category, String name, boolean isCountable) {
        this.category = category;
        this.name = name;
        this.isCountable = isCountable;
    }

}
