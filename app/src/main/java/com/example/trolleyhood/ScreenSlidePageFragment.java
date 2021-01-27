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

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class ScreenSlidePageFragment extends Fragment {

    Cart cart;
    View myView;
    TextView text;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.fragment_screen_slide_page, container, false);
        cart = (Cart) getContext().getApplicationContext();
        text = (TextView) myView.findViewById(R.id.status);
        for(CartPosition position : cart.cartPositions){
            addPosition(position.product.name , position.quantity);
        }

        //for now
        if(true){
            text.setText("Joao \n 111222333");
        }
        return myView;
    }

    public void addPosition(String productName,double qty){
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