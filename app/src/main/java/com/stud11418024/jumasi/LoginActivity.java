package com.stud11418024.jumasi;


import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.stud11418024.jumasi.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        binding.btnLogin.setOnClickListener(v -> {
            if (checkIsNotEmpty()) {
                Toast.makeText(this, "Email dan Password Tidak Boleh Kosong!", Toast.LENGTH_SHORT).show();
                return;
            }
            login(binding.email.getText().toString(), binding.password.getText().toString());
        });

        binding.daftar.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
        });
    }

    private void login(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) reload();
                    else Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                });
    }

    private void reload() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    private boolean checkIsNotEmpty() {
        return binding.email.equals("") || binding.password.equals("");
    }
}