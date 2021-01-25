package com.example.trolleyhood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

public class SomeonesOrder extends AppCompatActivity implements View.OnClickListener{

    Cart cart;
    String userNr;
    Button help;
    TextView userName;
    List<CartPosition> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_someones_order);
        cart = (Cart) getApplicationContext();
        userNr = getIntent().getStringExtra("userNr");
        help = findViewById(R.id.orderBtn);
        userName = findViewById(R.id.user);
        list = cart.ordersRepo.findCart(userNr);
        userName.setText(cart.ordersRepo.findName(userNr) + "'s order:");
        help.setOnClickListener(this::onClick);
        if(cart.ordersRepo.findOrder(userNr).isTaken){
            help.setText("ALREADY TAKEN");
            help.setEnabled(true);
        }
        for(CartPosition position : list){
            addPosition(position.product.name,position.quantity);
        }
    }

    public void addPosition(String productName, double qty){
        TextView position = new TextView(this);
        TextView qtyText = new TextView(this);


        position.setText(productName);
        qtyText.setText(String.valueOf(qty));
        position.setGravity(Gravity.CENTER);
        qtyText.setGravity(Gravity.CENTER);
        position.setTextSize(25);
        qtyText.setTextSize(25);
        position.setTextColor(Color.parseColor("#25619B"));
        qtyText.setTextColor(Color.parseColor("#25619B"));
        position.setBackgroundResource(R.drawable.my_button_bg);
        qtyText.setBackgroundResource(R.drawable.my_button_bg);
        TableLayout ll = (TableLayout) findViewById(R.id.table_layout);
        TableRow tr=new TableRow(this);
        tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

        TableRow.LayoutParams params = new TableRow.LayoutParams(
                750 , 250);
        params.setMargins(10, 20, 10, 0);
        TableRow.LayoutParams params2 = new TableRow.LayoutParams(
                250 , 250);
        params2.setMargins(25,20, 10, 0);
        position.setLayoutParams(params);
        qtyText.setLayoutParams(params2);
        tr.addView(position);
        tr.addView(qtyText);
        ll.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
    }
    @Override
    public void onClick(View v) {
        cart.ordersRepo.findOrder(userNr).acceptOrder();
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }
}