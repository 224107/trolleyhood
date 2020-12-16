package com.example.trolleyhood;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class ProductList extends Application {
    public List<Product> allProducts = new ArrayList<Product>();

    ProductList(){
        allProducts.add(new Product(ProductCategory.fruits,"banana",false));
    }
}
