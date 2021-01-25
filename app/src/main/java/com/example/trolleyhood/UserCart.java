package com.example.trolleyhood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserCart extends AppCompatActivity implements View.OnClickListener {

    Cart cart;
    Button order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_cart);
        cart = (Cart) getApplicationContext();
        order = (Button) findViewById(R.id.orderBtn);
        order.setOnClickListener(this::onClick);
        for(CartPosition position : cart.cartPositions){
          addPosition(position.product.name , position.quantity);
        }
    }
    public void addPosition(String productName,double qty){
        TextView position = new TextView(this);
        TextView qtyText = new TextView(this);
        ImageView delete = new ImageView(this);
        delete.setImageResource(R.drawable.delete);
        delete.setTag(productName);

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
        delete.setOnClickListener(this::onClick);
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
        qtyText.setLayoutParams(params2);
        delete.setLayoutParams(params2);
        tr.addView(position);
        tr.addView(qtyText);
        tr.addView(delete);
        ll.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

      /*  TableLayout tl = (TableLayout) findViewById(R.id.table_layout);
        TableRow tr = new TableRow(this);
        tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        Button b = new Button(this);
        b.setText("Dynamic Button");
        b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        tr.addView(b);

        tl.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));*/
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.orderBtn :
                if(!cart.cartPositions.isEmpty()) {
                    order.setEnabled(true);
                    order.setText("Order made");
                    addOrderToDatabase();
                    Toast.makeText(UserCart.this, "Order submitted", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                String product = v.getTag().toString();
                cart.deletePosition(product);
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);        }
    }

    private void addOrderToDatabase() {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Orders")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("Cart");

        for(CartPosition cp : cart.cartPositions){
            db.push().setValue(cp);
        }
    }
}