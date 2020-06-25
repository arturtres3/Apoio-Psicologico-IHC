package com.example.quicktest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SeekBar;

public class HelpNowFirstPage extends AppCompatActivity {

    private final int SEEKBARQUANTITY = 6;
    Button submitButton;
    SeekBar intrusiveThoughtsSeekBar;
    SeekBar fearSeekBar;
    SeekBar chestSeekBar;
    SeekBar sweatSeekBar;
    SeekBar sadnessSeekBar;
    SeekBar stressSeekBar;

    final int[] progressChangedValue = new int[SEEKBARQUANTITY];

    private void pickValueIntrThoughtsSeekBar() {
        intrusiveThoughtsSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue[0] = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void pickValueFearSeekBar() {
        fearSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue[1] = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    private void pickValueChestSeekBar() {
        chestSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue[2] = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void pickValueInsomniaSeekBar() {
        sweatSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue[3] = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    private void pickValueSadnessSeekBar() {
        sadnessSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue[4] = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void pickValueStressSeekBar() {
        stressSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue[5] = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_now_first_page);
        // initiate  views
        intrusiveThoughtsSeekBar = (SeekBar) findViewById(R.id.seekBarIntrThoughts);
        fearSeekBar = (SeekBar) findViewById(R.id.seekBarFear);
        chestSeekBar = (SeekBar) findViewById(R.id.seekBarChest);
        sweatSeekBar = (SeekBar) findViewById(R.id.seekBarInsomnia);
        sadnessSeekBar = (SeekBar) findViewById(R.id.seekBarSadness);
        stressSeekBar = (SeekBar) findViewById(R.id.seekBarStress);


        // perform seek bar change listener event used for getting the progress value

        pickValueIntrThoughtsSeekBar();
        pickValueFearSeekBar();
        pickValueChestSeekBar();
        pickValueInsomniaSeekBar();
        pickValueSadnessSeekBar();
        pickValueStressSeekBar();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void next (android.view.View view){
        Intent display = new Intent(this, HelpNowResults.class);

        display.putExtra("values", progressChangedValue);

        startActivity(display);

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

