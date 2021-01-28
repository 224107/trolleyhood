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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

    public class Settings extends AppCompatActivity implements View.OnClickListener{
    Button buttonChangeName, buttonChangeAddress, buttonChangePhone, buttonLogOut;
    TextView textName, textStreet, textNumber;
    EditText editTextName, editTextNumber;
    boolean isButtonChangeNameOn = false, isButtonChangePhoneOn = false;
    private FirebaseAuth mAuth;
    private  FirebaseDatabase db;
    Cart cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        cart = (Cart) getApplicationContext();
        textName = (TextView) findViewById(R.id.textViewName);
        textNumber = (TextView) findViewById(R.id.textViewNumber);
        textStreet = (TextView) findViewById(R.id.textViewStreet);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextNumber = (EditText) findViewById(R.id.editTextPhone);

        buttonChangeName = (Button) findViewById(R.id.buttonChangeName);
        buttonChangeAddress = (Button) findViewById(R.id.buttonChangeAddress);
        buttonChangePhone = (Button) findViewById(R.id.buttonChangePhone);
        buttonLogOut = (Button) findViewById(R.id.buttonLogOut);

        buttonChangeName.setOnClickListener(this);
        buttonChangeAddress.setOnClickListener(this);
        buttonChangePhone.setOnClickListener(this);
        buttonLogOut.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        db.getReference("Users").child(mAuth.getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String name = snapshot.child("name").getValue().toString();
                        String phone = snapshot.child("phone").getValue().toString();
                        textName.setText(name);
                        textNumber.setText(phone);
                    }
                    @Override
                    public void onCancelled (@NonNull DatabaseError error){
                    }
                });
        db.getReference("Users").child(mAuth.getCurrentUser().getUid()).child("Location")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String street = snapshot.child("street").getValue().toString();
                        String buildingNumber = snapshot.child("buildingNo").getValue().toString();
                        String apartmentNumber = snapshot.child("apartmentNo").getValue().toString();
                        String city = snapshot.child("city").getValue().toString();
                        textStreet.setText(street + ' ' + buildingNumber + '/' + apartmentNumber + ", " + city);
                    }
                    @Override
                    public void onCancelled (@NonNull DatabaseError error){
                    }
                });

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
        mAuth.signOut();
        cart.deleteAll();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    private void changeAddress() {
        startActivity(new Intent(getApplicationContext(), SetLocation.class));
    }
    
    private void changePhone() {
        String phone;

        if( !isButtonChangePhoneOn){
            textNumber.setVisibility(View.GONE);
            editTextNumber.setVisibility(View.VISIBLE);
            buttonChangePhone.setText("save phone");
            isButtonChangePhoneOn = true;
        } else if ( isButtonChangePhoneOn){
            phone = editTextNumber.getText().toString().trim();
            if (phone.isEmpty()){
                editTextNumber.setError("Phone is required");
                editTextNumber.requestFocus();
                return;
            }
            textNumber.setText(phone);
            changePhoneInDb(phone);
            textNumber.setVisibility(View.VISIBLE);
            editTextNumber.setVisibility(View.GONE);
            buttonChangePhone.setText("change phone");
            isButtonChangePhoneOn = false;
        }
    }

        private void changePhoneInDb(String phone) {
            db.getReference("Users")
                    .child(mAuth.getCurrentUser().getUid()).child("phone")
                    .setValue(phone).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()) {
                        Toast.makeText(Settings.this, "Phone number saved", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(Settings.this, "Failed to save Phone number", Toast.LENGTH_LONG).show();
                    }
                }
            });
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
        db.getReference("Users")
                .child(mAuth.getCurrentUser().getUid()).child("name")
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
