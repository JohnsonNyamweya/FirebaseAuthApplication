package com.johnsonnyamweya.firebaseauthapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {


    private Button btnRegister;
    private EditText edtRegisterEmail, edtRegisterPassword;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        edtRegisterEmail = findViewById(R.id.edt_register_email);
        edtRegisterPassword = findViewById(R.id.edt_register_password);
        btnRegister = findViewById(R.id.register_btn);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

    }

    private void registerUser() {

        String email = edtRegisterEmail.getText().toString();
        String password = edtRegisterPassword.getText().toString();

        if (TextUtils.isEmpty(email)){
            edtRegisterEmail.setError("Email cannot be empty");
            edtRegisterEmail.requestFocus();
        }
        else if (TextUtils.isEmpty(password)){
            edtRegisterPassword.setError("Password cannot be empty");
            edtRegisterPassword.requestFocus();
        }
        else{
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this,
                                        "User Registered Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                            }

                            else{
                                Toast.makeText(RegisterActivity.this,
                                        "Registration Error " + task.getException().getMessage()
                                        , Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}