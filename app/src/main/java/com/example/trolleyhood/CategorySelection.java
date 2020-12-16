package com.example.trolleyhood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class CategorySelection extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_selection);
        Button cartBtn = (Button) findViewById(R.id.cartBtn);
        cartBtn.setOnClickListener(this);
        ImageView meatBtn = (ImageView) findViewById(R.id.meatBtn);
        meatBtn.setOnClickListener(this);
        ImageView sweetBtn = (ImageView) findViewById(R.id.sweetBtn);
        sweetBtn.setOnClickListener(this);
        ImageView breadBtn = (ImageView) findViewById(R.id.breadBtn);
        breadBtn.setOnClickListener(this);
        ImageView fishBtn = (ImageView) findViewById(R.id.fishBtn);
        fishBtn.setOnClickListener(this);
        ImageView drinksBtn = (ImageView) findViewById(R.id.drinksBtn);
        drinksBtn.setOnClickListener(this);
        ImageView cosmeticsBtn = (ImageView) findViewById(R.id.cosmeticsBtn);
        cosmeticsBtn.setOnClickListener(this);
        ImageView dryBtn = (ImageView) findViewById(R.id.dryBtn);
        dryBtn.setOnClickListener(this);
        ImageView fruitsBtn = (ImageView) findViewById(R.id.fruitsBtn);
        fruitsBtn.setOnClickListener(this);
        ImageView dairyBtn = (ImageView) findViewById(R.id.dairyBtn);
        dairyBtn.setOnClickListener(this);
        ImageView vegetablesBtn = (ImageView) findViewById(R.id.vegetablesBtn);
        vegetablesBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), ProductsSelection.class);

        switch(v.getId()){
            case R.id.cartBtn:
                startActivity(new Intent(getApplicationContext(), UserCart.class));
                break;
            case R.id.meatBtn:
                intent.putExtra("category", "meat");
                startActivity(intent);
                break;
            case R.id.sweetBtn:
                intent.putExtra("category", "sweet");
                startActivity(intent);
                break;
            case R.id.breadBtn:
                intent.putExtra("category", "bread");
                startActivity(intent);
                break;
            case R.id.fishBtn:
                intent.putExtra("category", "fish");
                startActivity(intent);
                break;
            case R.id.drinksBtn:
                intent.putExtra("category", "drinks");
                startActivity(intent);
                break;
            case R.id.cosmeticsBtn:
                intent.putExtra("category", "cosmetics");
                startActivity(intent);
                break;
            case R.id.dryBtn:
                intent.putExtra("category", "dry");
                startActivity(intent);
                break;
            case R.id.fruitsBtn:
                intent.putExtra("category", "fruits");
                startActivity(intent);
                break;
            case R.id.dairyBtn:
                intent.putExtra("category", "dairy");
                startActivity(intent);
                break;
            case R.id.vegetablesBtn:
                intent.putExtra("category", "vegetables");
                startActivity(intent);
                break;
        }
    }
}
