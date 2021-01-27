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
    Double lat1, lng1, lat2, lng2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
        cart = (Cart) getApplicationContext();


        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();


        DatabaseReference ref = db.getReference();
        final String userId = mAuth.getCurrentUser().getUid();


        ref.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot userIdDb : snapshot.getChildren()) {
                    if (userIdDb.getKey().equals(userId)) {
                        if (userIdDb.child("Location").child("latLng").exists()) {
                            lat2 = Double.parseDouble(userIdDb.child("Location").child("latLng").child("latitude").getValue().toString());
                            lng2 = Double.parseDouble(userIdDb.child("Location").child("latLng").child("longitude").getValue().toString());
                        }
                    }
                }
                for (DataSnapshot userIdDb : snapshot.getChildren()) {
                    if (!userIdDb.getKey().equals(userId)) {
                        if(userIdDb.child("Offers").exists()){
                            Boolean isAccepted = Boolean.parseBoolean(userIdDb.child("Offers").child("isAccepted").getValue().toString());
                            if (!isAccepted) {
                                DataSnapshot cartDb = userIdDb.child("Offers").child("Cart");

                                //User
                                String name = userIdDb.child("name").getValue().toString();
                                String email = userIdDb.child("email").getValue().toString();
                                String phone = userIdDb.child("phone").getValue().toString();

                                User user = new User(name, email, phone);
                                user.id = userIdDb.getKey();
                                double dist = 0.0;
                                //Location
                                if (userIdDb.child("Location").child("latLng").exists()){
                                    lat1 = Double.parseDouble(userIdDb.child("Location").child("latLng").child("latitude").getValue().toString());
                                    lng1 = Double.parseDouble(userIdDb.child("Location").child("latLng").child("longitude").getValue().toString());
                                    dist = distance(lat1, lat2, lng1, lng2, 0.0, 0.0);
                                }
                                addPosition(user, dist);

                            }
                        }
                    }
                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void addPosition(User user, double dist){
        TextView position = new TextView(this);
        TextView distance = new TextView(this);
        position.setText(user.name);
        position.setGravity(Gravity.CENTER);
        position.setTextSize(25);
        position.setTextColor(Color.parseColor("#25619B"));
        position.setBackgroundResource(R.drawable.my_button_bg);
        distance.setGravity(Gravity.CENTER);
        distance.setTextSize(25);
        distance.setTextColor(Color.parseColor("#25619B"));
        distance.setBackgroundResource(R.drawable.my_button_bg);
        if( dist != 0.0){
            distance.setText(String.valueOf((int)dist) + " m");
        } else {
            distance.setText("NA");
        }

        TableLayout ll = (TableLayout) findViewById(R.id.table_layout);
        TableRow tr=new TableRow(this);
        tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

        TableRow.LayoutParams params = new TableRow.LayoutParams(
                620 , 200);
        params.setMargins(30, 20, 10, 0);
        TableRow.LayoutParams params2 = new TableRow.LayoutParams(
                300 , 200);
        params2.setMargins(30, 20, 10, 0);
        params.setMarginStart(65);
        position.setLayoutParams(params);
        distance.setLayoutParams(params2);
        tr.setTag(user.id);
        tr.addView(position);
        tr.addView(distance);
        tr.setOnClickListener(this::onClick);
        ll.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), SomeonesOrder.class);

                String userNr = v.getTag().toString();
                intent.putExtra("userId", userNr);
                startActivity(intent);
    }

    /**
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     *
     * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
     * el2 End altitude in meters
     * @returns Distance in Meters
     */
    public static double distance(double lat1, double lat2, double lon1,
                                  double lon2, double el1, double el2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }
}
