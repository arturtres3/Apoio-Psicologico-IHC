package com.example.quicktest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EditProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    public void howManyHoursWork(View view){
        final float HOWMANYHOURS_TEXTSIZE = 20;
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear_layout);
        TextView howManyHoursText = new TextView(EditProfile.this);
        howManyHoursText.setText("Tudo bem, mas quantas horas por dia de trabalho?");
        howManyHoursText.setTextSize(HOWMANYHOURS_TEXTSIZE);

        final EditText howManyHoursAnswer = new EditText(EditProfile.this);
        howManyHoursAnswer.setHint("Insira apenas um número de horas(ex: 8)");
        howManyHoursAnswer.setTextSize(14);

        linearLayout.addView(howManyHoursText);
        linearLayout.addView(howManyHoursAnswer);
    }

    public void doNotWork(View view){
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear_layout);
        linearLayout.removeAllViews();
    }


}






