package com.example.trolleyhood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProductsSelection extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_selection);
        Button cartBtn = (Button) findViewById(R.id.cartBtn);
        cartBtn.setOnClickListener(this);
        cartBtn.setBackgroundResource(R.drawable.cart_button);
        ProductList list = new ProductList();
        String passedCategory = getIntent().getStringExtra("category");

        for(Product product : list.allProducts){
            if(passedCategory.equals(product.category.name()))
                addButton(product.name);
        }

    }
    public void addButton(String productName){
        Button myButton = new Button(this);
        myButton.setText(productName);
        myButton.setTag(productName);
        myButton.setTextSize(30);
        myButton.setTextColor(Color.parseColor("#25619B"));
        myButton.setBackgroundResource(R.drawable.my_button_bg);
       // myButton.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        myButton.setOnClickListener(this::onClick);
        LinearLayout ll = (LinearLayout)findViewById(R.id.linear_layout);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 250);
        params.setMargins(20, 40, 20, 0);
        params.setMarginStart(65);
        params.setMarginEnd(65);
        myButton.setLayoutParams(params);
        ll.addView(myButton);
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(getApplicationContext(), SetQty.class);
        switch(v.getId()){
            case R.id.cartBtn:
                startActivity(new Intent(getApplicationContext(), UserCart.class));
                break;
            default:
                String product = v.getTag().toString();
                intent.putExtra("product", product);
                startActivity(intent);
        }
    }
}