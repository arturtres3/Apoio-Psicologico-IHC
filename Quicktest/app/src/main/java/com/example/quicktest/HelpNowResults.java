package com.example.quicktest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class HelpNowResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_now_results);
        //TextView results = (TextView) findViewById(R.id.results);

        Bundle extras = getIntent().getExtras();
        int[] resultados = extras.getIntArray("values");
        Toast.makeText(this,   "" + resultados[0] + resultados[1] + resultados[2]+ resultados[3]+ resultados[4] + resultados[5],
                Toast.LENGTH_LONG).show();

    }
}
