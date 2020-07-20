package com.example.quicktest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class BreathExercise extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breath_exercise);
        BreathExercise();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void BreathExercise() {

        @SuppressLint("ClickableViewAccessibility")
        final Button mybutton = (Button) findViewById(R.id.button);
        final TextView mytext = (TextView) findViewById(R.id.textView);
        final ProgressBar myprogbar = (ProgressBar) findViewById(R.id.progressBar);


        mybutton.setOnTouchListener(new View.OnTouchListener() {

            private Handler mHandler;
            int counter = 0;
            boolean alreadyPressed = false;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (alreadyPressed) {
                            mHandler.removeCallbacks(mAction2);
                        }
                        mHandler = new Handler();
                        mHandler.postDelayed(mAction, 500);
                        break;
                    case MotionEvent.ACTION_UP:
                        mHandler.removeCallbacks(mAction);
                        mHandler = new Handler();
                        mHandler.postDelayed(mAction2, 500);
                        alreadyPressed = true;
                        break;
                }
                return false;
            }

            Runnable mAction = new Runnable() {
                @Override
                public void run() {
                    mytext.setText("Inspire");
                    myprogbar.incrementProgressBy(10);

                    mHandler.postDelayed(this, 500);
                }
            };

            Runnable mAction2 = new Runnable() {
                @Override
                public void run() {
                    mytext.setText("Expire");
                    myprogbar.incrementProgressBy(-10);


                    mHandler.postDelayed(this, 500);
                }
            };

        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

}
