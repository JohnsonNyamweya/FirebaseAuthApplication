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

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText edtLoginEmail, edtLoginPassword;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        edtLoginEmail = findViewById(R.id.edt_login_email);
        edtLoginPassword = findViewById(R.id.edt_login_password);
        btnLogin = findViewById(R.id.login_btn);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

    }

    private void loginUser() {

        String email = edtLoginEmail.getText().toString();
        String password = edtLoginPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            edtLoginEmail.setError("Email cannot be empty");
            edtLoginEmail.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            edtLoginPassword.setError("Password cannot be empty");
            edtLoginPassword.requestFocus();
        }

        else{
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(LoginActivity.this,
                                        "User logged in Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            }
                            else{
                                Toast.makeText(LoginActivity.this,
                                        "Login Error " + task.getException().getMessage()
                                        , Toast.LENGTH_SHORT).show();

                                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));

                            }
                        }
                    });
        }
    }
}