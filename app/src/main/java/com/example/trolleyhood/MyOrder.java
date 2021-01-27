package com.example.trolleyhood;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyOrder extends AppCompatActivity {

    Cart cart;
    TextView text;
    FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_screen_slide_page);
        cart = (Cart) getApplicationContext();
        text = (TextView) findViewById(R.id.status);


        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();


        ref = db.getReference();


        ref.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot thisUser = snapshot.child(mAuth.getCurrentUser().getUid()).child("Offers");
                Boolean isAccepted = Boolean.parseBoolean(thisUser.child("isAccepted").getValue().toString());
                String name = "", phone = "";
                if (isAccepted){
                    String aUser = thisUser.child("acceptedUserId").getValue().toString();
                    for (DataSnapshot userIdDb : snapshot.getChildren()) {
                        if ( userIdDb.getKey().equals(aUser)) {
                            name = userIdDb.child("name").getValue().toString();
                            phone = userIdDb.child("phone").getValue().toString();
                        }
                    }

                    text.setText("Accepted by: " + name + "\nPhone: " + phone);
                    text.setTextSize(24);
                    text.setBackgroundResource(R.drawable.cart_button);
                    text.setTextColor(Color.WHITE);
                }

                for (DataSnapshot productId : thisUser.child("Cart").getChildren()) {
                    String productName = productId.child("product").child("name").getValue().toString();
                    Double productQuantity = Double.parseDouble(productId.child("quantity").getValue().toString());

                    addPosition(productName, productQuantity);
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
        TableLayout ll = (TableLayout) findViewById(R.id.table_layout); //getView().findViewById(R.id.table_layout);
        TableRow tr=new TableRow(this);
        tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

        TableRow.LayoutParams params = new TableRow.LayoutParams(
                720 , 200);
        params.setMargins(10, 20, 10, 0);
        params.setMarginStart(65);
        TableRow.LayoutParams params2 = new TableRow.LayoutParams(
                200 , 200);
        params2.setMargins(30, 20, 10, 0);
        position.setLayoutParams(params);
        qtyText.setLayoutParams(params2);
        tr.addView(position);
        tr.addView(qtyText);
        ll.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

    }

}