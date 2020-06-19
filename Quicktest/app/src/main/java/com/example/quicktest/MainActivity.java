package com.example.quicktest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void boasVindas(android.view.View view){
        Intent bemVindo = new Intent(this, telaBemVindo.class);

        startActivity(bemVindo);
    }

    public void startHelpNow(android.view.View view){
        Intent helpNow = new Intent(this, HelpNowFirstPage.class);

        startActivity(helpNow);
    }
}
