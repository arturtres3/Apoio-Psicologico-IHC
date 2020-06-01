package com.example.quicktest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String PREFS_NAME = "MyPrefsFile";
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        super.onCreate(savedInstanceState);

        if (settings.getBoolean("my_first_time", true)) {
            setContentView(R.layout.activity_tela_bem_vindo);

            settings.edit().putBoolean("my_first_time", false).commit();
        }else{
            setContentView(R.layout.activity_main);
        }
    }

    public void boasVindas(android.view.View view){
        Intent bemVindo = new Intent(this, telaBemVindo.class);

        startActivity(bemVindo);
    }
}
