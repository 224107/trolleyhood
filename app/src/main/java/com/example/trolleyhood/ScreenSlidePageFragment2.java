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
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ScreenSlidePageFragment2 extends Fragment implements View.OnClickListener {

    View myView;
    FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference ref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.fragment_screen_slide_page2, container, false);

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
                        System.out.println(aUser);
                        System.out.println(user);
                        if (userIdDb.child("Offers").child("acceptedUserId").getValue().equals(mAuth.getCurrentUser().getUid())) {
                            System.out.println("JEST GIT12231");
                            String name = userIdDb.child("name").getValue().toString();
                            addPosition(name);
                        }
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return myView;
    }

    public void addPosition(String name){
        TextView position = new TextView(this.getActivity());
        ImageView icon = new ImageView(this.getActivity());
        icon.setImageResource(R.drawable.check);

        position.setText(name);
        position.setGravity(Gravity.CENTER);
        position.setTextSize(25);
        position.setTextColor(Color.parseColor("#25619B"));
        position.setBackgroundResource(R.drawable.my_button_bg);
        TableLayout ll = (TableLayout) myView.findViewById(R.id.table_layout);
        TableRow tr=new TableRow(this.getActivity());
        tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

        TableRow.LayoutParams params = new TableRow.LayoutParams(
                720 , 200);
        params.setMargins(30, 20, 10, 0);
        TableRow.LayoutParams params2 = new TableRow.LayoutParams(
                200 , 200);
        params2.setMargins(10, 20, 10, 0);
        params2.setMarginStart(65);
        position.setLayoutParams(params);
        position.setOnClickListener(this::onClick);
        icon.setLayoutParams(params2);
        icon.setOnClickListener(this::onClick);
        //position.setTag(user.phone);
        //icon.setTag(user.phone);
        tr.addView(icon);
        tr.addView(position);
        ll.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
    }

    @Override
    public void onClick(View v) {

    }
}