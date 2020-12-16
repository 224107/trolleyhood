package com.example.trolleyhood;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProductsSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_selection);
        ProductList list = new ProductList();
        String passedCategory = getIntent().getStringExtra("category");
        TextView category = (TextView) findViewById(R.id.category);
        category.setText(passedCategory);
        for(Product product : list.allProducts){
            if(passedCategory.equals(product.category.name()))
                addButton(product.name);
        }

    }
    public void addButton(String productName){
        Button myButton = new Button(this);
        myButton.setText(productName);
        LinearLayout ll = (LinearLayout)findViewById(R.id.linear_layout);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ll.addView(myButton, lp);
    }
}