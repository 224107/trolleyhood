package com.example.trolleyhood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSingIn = (Button) findViewById(R.id.btnSignIn);
        btnSingIn.setOnClickListener(this);
        Button btnCreateAccount = (Button) findViewById(R.id.btnCreateAccount);
        btnCreateAccount.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnSignIn:
                startActivity(new Intent(getApplicationContext(), SignIn.class));
                break;
            case R.id.btnCreateAccount:
                startActivity(new Intent(getApplicationContext(), CreateAccount.class));
                break;
        }
    }
}