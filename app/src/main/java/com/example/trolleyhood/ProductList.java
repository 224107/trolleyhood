package com.example.trolleyhood;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class ProductList {
    public List<Product> allProducts = new ArrayList<Product>();

    ProductList(){
        allProducts.add(new Product(ProductCategory.fruits,"banana",false));
        allProducts.add(new Product(ProductCategory.fruits,"apple",false));
        allProducts.add(new Product(ProductCategory.fruits,"watermelon",true));
        allProducts.add(new Product(ProductCategory.fruits,"coconut",true));
        allProducts.add(new Product(ProductCategory.fruits,"banana",false));
        allProducts.add(new Product(ProductCategory.fruits,"apple",false));
        allProducts.add(new Product(ProductCategory.fruits,"watermelon",true));
        allProducts.add(new Product(ProductCategory.fruits,"coconut",true));
        allProducts.add(new Product(ProductCategory.fruits,"banana",false));
        allProducts.add(new Product(ProductCategory.fruits,"apple",false));
        allProducts.add(new Product(ProductCategory.fruits,"watermelon",true));
        allProducts.add(new Product(ProductCategory.fruits,"coconut",true));
        allProducts.add(new Product(ProductCategory.fruits,"banana",false));
        allProducts.add(new Product(ProductCategory.fruits,"apple",false));
        allProducts.add(new Product(ProductCategory.fruits,"watermelon",true));
        allProducts.add(new Product(ProductCategory.fruits,"coconut",true));
        allProducts.add(new Product(ProductCategory.vegetables,"carrot",false));
        allProducts.add(new Product(ProductCategory.vegetables,"tomato",false));
        allProducts.add(new Product(ProductCategory.vegetables,"potato",false));
        allProducts.add(new Product(ProductCategory.vegetables,"cucumber",true));
        allProducts.add(new Product(ProductCategory.meat,"chicken",false));
        allProducts.add(new Product(ProductCategory.meat,"sausage",true));
        allProducts.add(new Product(ProductCategory.meat,"beef",false));
        allProducts.add(new Product(ProductCategory.meat,"becon",false));
        allProducts.add(new Product(ProductCategory.bread,"roll",true));
        allProducts.add(new Product(ProductCategory.bread,"croissant",true));
        allProducts.add(new Product(ProductCategory.bread,"loaf",true));
        allProducts.add(new Product(ProductCategory.bread,"baguette",true));
        allProducts.add(new Product(ProductCategory.sweet,"chocolate",true));
        allProducts.add(new Product(ProductCategory.sweet,"lolipop",true));
        allProducts.add(new Product(ProductCategory.sweet,"chocolate bar",true));
        allProducts.add(new Product(ProductCategory.sweet,"candy",true));
        allProducts.add(new Product(ProductCategory.fish,"tuna",false));
        allProducts.add(new Product(ProductCategory.fish,"carp",false));
        allProducts.add(new Product(ProductCategory.fish,"salmon",false));
        allProducts.add(new Product(ProductCategory.fish,"clams",true));
        allProducts.add(new Product(ProductCategory.drinks,"orange juice",true));
        allProducts.add(new Product(ProductCategory.drinks,"bebis",true));
        allProducts.add(new Product(ProductCategory.drinks,"apple juice",true));
        allProducts.add(new Product(ProductCategory.drinks,"water",true));
        allProducts.add(new Product(ProductCategory.cosmetics,"toothpaste",true));
        allProducts.add(new Product(ProductCategory.cosmetics,"shampoo",true));
        allProducts.add(new Product(ProductCategory.cosmetics,"deodorant",true));
        allProducts.add(new Product(ProductCategory.cosmetics,"shaving cream",true));
        allProducts.add(new Product(ProductCategory.dry,"rice",true));
        allProducts.add(new Product(ProductCategory.dry,"noodle",true));
        allProducts.add(new Product(ProductCategory.dry,"sugar",true));
        allProducts.add(new Product(ProductCategory.dry,"salt",true));
        allProducts.add(new Product(ProductCategory.dairy,"milk",true));
        allProducts.add(new Product(ProductCategory.dairy,"yoghurt",true));
        allProducts.add(new Product(ProductCategory.dairy,"kefir",true));
        allProducts.add(new Product(ProductCategory.dairy,"eggs",true));

    }
    public boolean getCountableByName(String passname){
        boolean pr = true;
        for(Product product : allProducts){
            if(product.name.equals(passname))
                pr = product.isCountable;
        }
        return pr;
    }
    public Product getByName(String name){
        Product p = new Product();
        for(Product product : allProducts){
            if(product.name.equals(name))
                p = product;
        }
        return p;
    }
}
