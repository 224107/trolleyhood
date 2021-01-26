package com.example.trolleyhood;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Help extends AppCompatActivity implements View.OnClickListener {

    Cart cart;
    FirebaseAuth mAuth;
    FirebaseDatabase db;
    List<Order> orders;
    List<User> users;
    Integer i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
        cart = (Cart) getApplicationContext();
        for(Order order : cart.ordersRepo.allOrders){
          addPosition(order.user);
        }

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        orders = new ArrayList<>();
        users = new ArrayList<>();

        DatabaseReference ref = db.getReference();
        final String userId = mAuth.getCurrentUser().getUid();


        ref.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot userIdDb : snapshot.getChildren()) {
                    System.out.println(userIdDb.getKey());
                    if(!userIdDb.getKey().equals(userId))
                        i = 0;
                        DataSnapshot cartDb = userIdDb.child("Offers").child("Cart");

                        if(cartDb.child("isAccepted").getValue().toString().equals("false")) {
                        String childProductId = "product" + i.toString();
                        String name = userIdDb.child("name").getValue().toString();
                        String email = userIdDb.child("email").getValue().toString();
                        String phone = userIdDb.child("phone").getValue().toString();

                        for(DataSnapshot product : cartDb.getChildren()){
                            continue;
                        }
                        String productCategory = cartDb.child(childProductId).child("product").child("category").getValue().toString();
                        String productIsCountable = cartDb.child(childProductId).child("product").child("isCountable").getValue().toString();
                        String productName = cartDb.child(childProductId).child("product").child("name").getValue().toString();
                        String productQuantity = cartDb.child(childProductId).child("Quantity").getValue().toString();

                        i++;
                        }
                    }

                }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //db.getReference("Offers").child()
    }

    public void addPosition(User user){
        TextView position = new TextView(this);
        ImageView icon = new ImageView(this);
        TextView distance = new TextView(this);
        icon.setImageResource(R.drawable.user);

        position.setText(user.name);
        position.setGravity(Gravity.CENTER);
        position.setTextSize(25);
        position.setTextColor(Color.parseColor("#25619B"));
        position.setBackgroundResource(R.drawable.my_button_bg);
        //todo: distance have to be calculated
        double distanceCal = 12.5d;
        distance.setGravity(Gravity.CENTER);
        distance.setTextSize(25);
        distance.setTextColor(Color.parseColor("#25619B"));
        distance.setBackgroundResource(R.drawable.my_button_bg);
        distance.setText(String.valueOf(distanceCal) + " m");
        icon.setBackgroundResource(R.drawable.my_button_bg);
        TableLayout ll = (TableLayout) findViewById(R.id.table_layout);
        TableRow tr=new TableRow(this);
        tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

        TableRow.LayoutParams params = new TableRow.LayoutParams(
                500 , 250);
        params.setMargins(10, 20, 10, 0);
        TableRow.LayoutParams params2 = new TableRow.LayoutParams(
                250 , 250);
        params2.setMargins(10, 20, 10, 0);
        position.setLayoutParams(params);
        icon.setLayoutParams(params2);
        distance.setLayoutParams(params2);
        tr.setTag(user.phone);
        tr.addView(icon);
        tr.addView(position);
        tr.addView(distance);
        tr.setOnClickListener(this::onClick);
        ll.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), SomeonesOrder.class);

                String userNr = v.getTag().toString();
                intent.putExtra("userNr", userNr);
                startActivity(intent);
    }
}
