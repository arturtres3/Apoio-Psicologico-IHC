package com.example.quicktest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.quicktest.ui.login.LoginActivity;

public class telaBemVindo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_bem_vindo);
    }

    public void fazerLogin(android.view.View view){
        Intent userLogin = new Intent(telaBemVindo.this, LoginActivity.class);

        startActivity(userLogin);
    }
}
