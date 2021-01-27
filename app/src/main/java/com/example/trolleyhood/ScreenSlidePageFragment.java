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

public class ScreenSlidePageFragment extends Fragment {

    Cart cart;
    View myView;
    TextView text;
    FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference ref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.fragment_screen_slide_page, container, false);
        cart = (Cart) getContext().getApplicationContext();
        text = (TextView) myView.findViewById(R.id.status);


        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();


        ref = db.getReference();


        ref.child("Users").child(mAuth.getCurrentUser().getUid()).child("Offers").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Boolean isAccepted = Boolean.parseBoolean(snapshot.child("isAccepted").getValue().toString());
                System.out.println(snapshot.child("isAccepted").getValue().toString());
                if (isAccepted){
                    text.setText("Accepted");
                    text.setBackgroundResource(R.drawable.cart_button);
                    text.setTextColor(Color.WHITE);
                }

                for (DataSnapshot productId : snapshot.child("Cart").getChildren()) {
                    String productName = productId.child("product").child("name").getValue().toString();
                    Double productQuantity = Double.parseDouble(productId.child("quantity").getValue().toString());

                    addPosition(productName, productQuantity);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        return myView;
    }

    public void addPosition(String productName, double qty){
        TextView position = new TextView(this.getActivity());
        TextView qtyText = new TextView(this.getActivity());

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
        TableLayout ll = (TableLayout) myView.findViewById(R.id.table_layout); //getView().findViewById(R.id.table_layout);
        TableRow tr=new TableRow(this.getActivity());
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