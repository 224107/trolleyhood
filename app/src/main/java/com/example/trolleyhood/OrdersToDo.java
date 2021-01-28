package com.example.trolleyhood;



import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OrdersToDo extends AppCompatActivity implements View.OnClickListener{

    View myView;
    FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_screen_slide_page2);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();

        ref = db.getReference();

        ref.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot userIdDb : snapshot.getChildren()) {
                    if(userIdDb.child("Offers").child("acceptedUserId").exists() ){

                        String aUser = userIdDb.child("Offers").child("acceptedUserId").getValue().toString();
                        String user = mAuth.getCurrentUser().getUid();
                        if (userIdDb.child("Offers").child("acceptedUserId").getValue().equals(mAuth.getCurrentUser().getUid())) {
                            String name = userIdDb.child("name").getValue().toString();
                            String id = userIdDb.getKey();
                            addPosition(name, id);
                        }
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }



    public void addPosition(String name, String id){
        TextView position = new TextView(this);


        position.setText(name);
        position.setGravity(Gravity.CENTER);
        position.setTextSize(25);
        position.setTextColor(Color.parseColor("#25619B"));
        position.setBackgroundResource(R.drawable.my_button_bg);
        TableLayout ll = (TableLayout) findViewById(R.id.table_layout);
        TableRow tr=new TableRow(this);
        tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

        TableRow.LayoutParams params = new TableRow.LayoutParams(
                950 , 200);
        params.setMargins(30, 20, 10, 0);
        params.setMarginStart(65);
        position.setLayoutParams(params);
        position.setOnClickListener(this::onClick);
        position.setTag(id);
        tr.addView(position);
        ll.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), SomeonesOrder.class);

        String userNr = v.getTag().toString();
        intent.putExtra("userId", userNr);
        startActivity(intent);
    }
}