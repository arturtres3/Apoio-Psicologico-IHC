package com.example.quicktest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

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
                Toast.makeText(HelpNowFirstPage.this, "Seek bar progress is :" + progressChangedValue[0],
                        Toast.LENGTH_SHORT).show();

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
                Toast.makeText(HelpNowFirstPage.this, "Seek bar progress is :" + progressChangedValue[1],
                        Toast.LENGTH_SHORT).show();

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
                Toast.makeText(HelpNowFirstPage.this, "Seek bar progress is :" + progressChangedValue[2],
                        Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void pickValueSweatSeekBar() {
        sweatSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue[3] = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(HelpNowFirstPage.this, "Seek bar progress is :" + progressChangedValue[3],
                        Toast.LENGTH_SHORT).show();

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
                Toast.makeText(HelpNowFirstPage.this, "Seek bar progress is :" + progressChangedValue[4],
                        Toast.LENGTH_SHORT).show();

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
                Toast.makeText(HelpNowFirstPage.this, "Seek bar progress is :" + progressChangedValue[5],
                        Toast.LENGTH_SHORT).show();

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
        sweatSeekBar = (SeekBar) findViewById(R.id.seekBarSweat);
        sadnessSeekBar = (SeekBar) findViewById(R.id.seekBarSadness);
        stressSeekBar = (SeekBar) findViewById(R.id.seekBarStress);


        // perform seek bar change listener event used for getting the progress value

        pickValueIntrThoughtsSeekBar();
        pickValueFearSeekBar();
        pickValueChestSeekBar();
        pickValueSweatSeekBar();
        pickValueSadnessSeekBar();
        pickValueStressSeekBar();
    }

}

