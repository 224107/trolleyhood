    package com.example.trolleyhood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

    public class Settings extends AppCompatActivity implements View.OnClickListener{
    Button buttonChangeName, buttonChangeAddress, buttonChangePhone, buttonLogOut;
    TextView textName, textStreet, textNumber;
    EditText editTextName, editTextNumber;
    boolean isButtonChangeNameOn = false, isButtonChangePhoneOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        textName = (TextView) findViewById(R.id.textViewName);
        textNumber = (TextView) findViewById(R.id.textViewNumber);
        textStreet = (TextView) findViewById(R.id.textViewStreet);

        editTextName = (EditText) findViewById(R.id.editTextName);

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
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    private void changeAddress() {
        startActivity(new Intent(getApplicationContext(), SetLocation.class));
    }
    
    private void changePhone() {
        throw new UnsupportedOperationException();
    }



    private void changeName() {
        String name;

        if( !isButtonChangeNameOn){
            textName.setVisibility(View.GONE);
            editTextName.setVisibility(View.VISIBLE);
            buttonChangeName.setText("save name");
            isButtonChangeNameOn = true;
        } else if ( isButtonChangeNameOn){
            name = editTextName.getText().toString().trim();
            if (name.isEmpty()){
                editTextName.setError("Name is required");
                editTextName.requestFocus();
                return;
            }
            textName.setText(name);
            changeNameInDb(name);
            textName.setVisibility(View.VISIBLE);
            editTextName.setVisibility(View.GONE);
            buttonChangeName.setText("change name");
            isButtonChangeNameOn = false;
        }
    }

    private void changeNameInDb( String name) {
        FirebaseDatabase.getInstance().getReference("Users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("name")
                .setValue(name).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(Settings.this, "Name saved", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(Settings.this, "Failed to save Name", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
