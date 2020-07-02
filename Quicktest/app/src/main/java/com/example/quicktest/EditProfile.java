package com.example.quicktest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class EditProfile extends AppCompatActivity {

    public void ageFieldDigitsOnly(){
        final EditText age = (EditText) findViewById(R.id.ageText);
        age.setTransformationMethod(null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ageFieldDigitsOnly();
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


    public void onCreateDialog_OK(final View view, String message, final Boolean valid) {
        final AlertDialog.Builder builderAgeError = new AlertDialog.Builder(EditProfile.this);
        builderAgeError.setMessage(message)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if(valid) backToMainPage(view);
                    }
                });

        final Dialog requiredMessage = builderAgeError.create();
        requiredMessage.show();
    }

    /*public void printForDebug(View view, String mytext) {
        Toast toast = Toast.makeText(getApplicationContext(),
                mytext,
                Toast.LENGTH_SHORT);

        toast.show();
    } */

    public void backToMainPage(View view){
        Intent backToMain = new Intent(this, MainActivity.class);

        startActivity(backToMain);
    }

    public void checkProfileInformation(View view){
        final int MINIMUM_AGE = 1;
        final int MAXIMUM_AGE = 120;

        final EditText userAgeText = (EditText) findViewById(R.id.ageText);
        int userAge = Integer.parseInt(userAgeText.getText().toString());

        if (userAge < MINIMUM_AGE || userAge > MAXIMUM_AGE){
            onCreateDialog_OK(view, getString(R.string.age_error_message), false);
        }
        else{
            //chamar método para armazenar os dados do perfil aqui
            onCreateDialog_OK(view, getString(R.string.profile_noproblem), true);
        }
    }

    public void onCreateDialog_Cancel(final View view) {
        AlertDialog.Builder cancelBuilder = new AlertDialog.Builder(EditProfile.this);
        cancelBuilder.setMessage(R.string.are_you_sure)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        backToMainPage(view);
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {}
                });

        final Dialog cancel = cancelBuilder.create();
        cancel.show();
    }
}






