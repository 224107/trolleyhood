package com.example.trolleyhood;

import androidx.annotation.NonNull;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SomeonesOrder extends AppCompatActivity implements View.OnClickListener{

    Cart cart;
    String userId, userName;
    Button help;
    TextView userNameTextView;
    List<CartPosition> cartPositions;
    FirebaseAuth mAuth;
    FirebaseDatabase db;
    Boolean isAccepted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_someones_order);
        cart = (Cart) getApplicationContext();
        userId = getIntent().getStringExtra("userId");
        help = findViewById(R.id.orderBtn);
        help.setOnClickListener(this::onClick);
        userNameTextView = findViewById(R.id.user);
        cartPositions = new ArrayList<>();

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();


        DatabaseReference ref = db.getReference();

        System.out.println(userId);
        ref.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot userIdDb : snapshot.getChildren()) {
                    if (userIdDb.getKey().equals(userId)) {
                        DataSnapshot cartDb = userIdDb.child("Offers").child("Cart");

                        //isAccepted = false;
                        isAccepted = Boolean.parseBoolean(userIdDb.child("Offers").child("isAccepted").getValue().toString());
                        //User
                        userName = userIdDb.child("name").getValue().toString();
                        System.out.println(userName);
                        //Order
                        for (DataSnapshot productId : cartDb.getChildren()) {
                            String productCategory = productId.child("product").child("category").getValue().toString();
                            Boolean productIsCountable = Boolean.parseBoolean(productId.child("product").child("isCountable").getValue().toString()) ;
                            String productName = productId.child("product").child("name").getValue().toString();

                            Double productQuantity = Double.parseDouble(productId.child("quantity").getValue().toString());
                            System.out.println(productCategory);
                            System.out.println(productName);
                            System.out.println(productQuantity);
                            System.out.println(productIsCountable);
                            Product product = new Product(ProductCategory.parseCategory(productCategory), productName, productIsCountable);
                            CartPosition cartPosition = new CartPosition(product, productQuantity);
                            cartPositions.add(cartPosition);

                        }
                    }
                }
                userNameTextView.setText(userName + "'s order:");

                if(isAccepted){
                    help.setText("ALREADY TAKEN");
                    help.setEnabled(true);
                }
                for(CartPosition position : cartPositions){
                    addPosition(position.product.name,position.quantity);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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
        //cart.ordersRepo.findOrder(userId).acceptOrder();
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }
}