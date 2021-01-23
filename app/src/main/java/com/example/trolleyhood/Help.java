package com.example.trolleyhood;

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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.List;

public class Help extends AppCompatActivity implements View.OnClickListener {

    Cart cart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
        cart = (Cart) getApplicationContext();
       for(Order order : cart.ordersRepo.allOrders){
          addPosition(order.user);
     }
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
