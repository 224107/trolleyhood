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

import java.math.BigDecimal;
import java.math.RoundingMode;

public class UserCart extends AppCompatActivity implements View.OnClickListener {

    Cart cart;
    Button order;
    TextView category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_cart);
        cart = (Cart) getApplicationContext();
        order = (Button) findViewById(R.id.orderBtn);
        order.setBackgroundResource(R.drawable.my_button_bg);
        order.setOnClickListener(this::onClick);
        for(CartPosition position : cart.cartPositions){
          addPosition(position.product.name , position.quantity);
        }
        category = (TextView) findViewById(R.id.category);
        category.setBackgroundResource(R.drawable.cart_button);
    }
    public void addPosition(String productName, double qty){
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
                520 , 200);
        params.setMargins(10, 20, 10, 0);
        params.setMarginStart(65);
        TableRow.LayoutParams params2 = new TableRow.LayoutParams(
                200 , 200);
        params2.setMargins(10, 20, 10, 0);
        position.setLayoutParams(params);
        qtyText.setLayoutParams(params2);
        delete.setLayoutParams(params2);
        tr.addView(position);
        tr.addView(qtyText);
        tr.addView(delete);
        ll.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

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
                    Intent i = new Intent(getApplicationContext(), HelpOrAsk.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
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
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Offers");
        Integer i = 0;

        db.child("Cart").removeValue();
        for(CartPosition cp : cart.cartPositions){
            db.child("Cart").child(i.toString()).setValue(cp);
            String qty = String.format("%.2f", cp.quantity);
            db.child("Cart").child(i.toString()).child("quantity").setValue(qty);
            i++;
        }
        db.child("isAccepted").setValue(false);
    }
}