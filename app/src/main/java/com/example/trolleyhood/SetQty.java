package com.example.trolleyhood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SetQty extends AppCompatActivity implements View.OnClickListener {

    private EditText editQty;
    ProductList list = new ProductList();
    String passedProduct;
    double qty = 0.0;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_qty);
        passedProduct = getIntent().getStringExtra("product");
        TextView category = (TextView) findViewById(R.id.product);
        Button setBtn = (Button) findViewById(R.id.setBtn);
        image = (ImageView) findViewById(R.id.imageView);
        setBtn.setOnClickListener(this);
        category.setText(passedProduct.toUpperCase());
        editQty = (EditText) findViewById(R.id.emailText);
        InputType(passedProduct);
    }

    public void InputType(String passedProduct){
        if(list.getCountableByName(passedProduct) == true) {
            editQty.setHint("type in pieces");
            image.setImageResource(R.drawable.pieceicon);
            editQty.setInputType(InputType.TYPE_CLASS_NUMBER);
        }
        else {
            editQty.setHint("type in kg");
            image.setImageResource(R.drawable.scaleicon);
            editQty.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }
    }
    @Override
    public void onClick(View v) {
    getQty();
    }

    public void getQty(){
        String qtyString = editQty.getText().toString().trim();

        if (qtyString.isEmpty() || Double.parseDouble(qtyString) == 0 ){
            editQty.setError("set correct quantity");
            editQty.requestFocus();
            return;
        }
        qty = Double.parseDouble(qtyString) + 0.0;
        Cart cart = (Cart) getApplicationContext();
        cart.addPosition(new CartPosition(list.getByName(passedProduct),qty));
        Toast.makeText(SetQty.this, passedProduct + " added to cart!", Toast.LENGTH_LONG).show();
        return;
    }
}