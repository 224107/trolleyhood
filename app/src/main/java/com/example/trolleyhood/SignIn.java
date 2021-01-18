package com.example.trolleyhood;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn  extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    Button buttonSignIn;
    private EditText editTextEmail, editTextPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        mAuth = FirebaseAuth.getInstance();

        buttonSignIn = (Button) findViewById(R.id.btnSignIn);
        buttonSignIn.setOnClickListener(this);

        editTextEmail = (EditText) findViewById(R.id.emailText);
        editTextPassword = (EditText) findViewById(R.id.passwordText);
     }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnSignIn:
                userLogin();
                break;
        }
    }

    private void userLogin() {
        String email =  editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()){
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please provide valid email");
            editTextEmail.requestFocus();
            return;
        }
        if (password.isEmpty()){
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(getApplicationContext(), SetLocation.class));
                } else {
                    Toast.makeText(SignIn.this, "Failed to log in, try again", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}