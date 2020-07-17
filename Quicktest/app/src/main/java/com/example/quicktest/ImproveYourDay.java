package com.example.quicktest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ImproveYourDay extends AppCompatActivity {

    final String CURRENT_HOUR = "H";
    final long THREE_HOURS_IN_MILISSECONDS = 10800000;
    final int FIRST_HOUR_MORNING = 6;
    final int FIRST_HOUR_AFTERNOON = 12;
    final int FIRST_HOUR_NIGHT = 18;
    final float GREETINGS_TEXTSIZE = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_improve_your_day);
        greetings();
    }

    public void greetings(){
        SimpleDateFormat current_hour = new SimpleDateFormat(CURRENT_HOUR);
        Date date = new Date(System.currentTimeMillis() - THREE_HOURS_IN_MILISSECONDS);
        String formatted_hour = current_hour.format(date);

        TextView greetingsTextView = (TextView) findViewById(R.id.greetingsText);
        greetingsTextView.setTextSize(GREETINGS_TEXTSIZE);
        int numeric_hour = Integer.valueOf(formatted_hour);
        if(numeric_hour >= FIRST_HOUR_MORNING && numeric_hour < FIRST_HOUR_AFTERNOON){
            greetingsTextView.setText("Bom dia, " + getUserName());
        }
        else if(numeric_hour >= FIRST_HOUR_AFTERNOON && numeric_hour < FIRST_HOUR_NIGHT){
            greetingsTextView.setText("Boa tarde, " + getUserName());
        }
        else{
            greetingsTextView.setText("Boa noite, " + getUserName());
        }
    }


    public String getUserName(){
        return PreferenceManager.getDefaultSharedPreferences(this).getString("NOME", "NOT_FOUND");
    }

}
