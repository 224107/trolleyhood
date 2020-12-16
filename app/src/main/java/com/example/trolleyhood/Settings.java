package com.example.trolleyhood;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Settings extends AppCompatActivity implements View.OnClickListener{
    Button buttonChangeName, buttonChangeAddress, buttonChangePhone, buttonLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        buttonChangeName = (Button) findViewById(R.id.buttonChangeName);
        buttonChangeAddress = (Button) findViewById(R.id.buttonChangeAddress);
        buttonChangePhone = (Button) findViewById(R.id.buttonChangePhone);
        buttonLogOut = (Button) findViewById(R.id.buttonLogOut);
        buttonChangeName.setOnClickListener(this);
        buttonChangeAddress.setOnClickListener(this);
        buttonChangePhone.setOnClickListener(this);
        buttonLogOut.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.buttonChangeName:
                changeName();
                break;
            case R.id.buttonChangeAddress:
                changeAddress();
                break;
            case R.id.buttonChangePhone:
                changePhone();
                break;
            case R.id.buttonLogOut:
                logOut();
                break;
        }
    }

    private void logOut() {
        throw new UnsupportedOperationException();
    }

    private void changePhone() {
        throw new UnsupportedOperationException();
    }

    private void changeAddress() {
        throw new UnsupportedOperationException();
    }

    private void changeName() {
        throw new UnsupportedOperationException();
    }
}
