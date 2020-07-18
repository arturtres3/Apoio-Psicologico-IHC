package com.example.quicktest;

import android.annotation.SuppressLint;
import android.icu.text.TimeZoneFormat;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class EditProfile extends AppCompatActivity {

    final int HOURS_ANSWER_ID = 100;

    public void ageFieldDigitsOnly(){
        final EditText age = (EditText) findViewById(R.id.ageText);
        age.setTransformationMethod(null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        //testing();


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
        howManyHoursText.setTextColor(getResources().getColor(R.color.colorPrimary));

        final EditText howManyHoursAnswer = new EditText(EditProfile.this);
        howManyHoursAnswer.setId(HOURS_ANSWER_ID);
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
                        if(valid){
                            saveInformation(view);
                            backToMainPage(view);
                        }
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
        if(checkAgeInformation(view) == "EMPTY_AGE")
            onCreateDialog_OK(view, getString(R.string.age_empty_message), false);

        else if(checkAgeInformation(view) == "OUT_OF_RANGE_AGE")
            onCreateDialog_OK(view, getString(R.string.age_error_message), false);

        else if(!existsConditionInformation(view))
            onCreateDialog_OK(view, getString(R.string.condition_empty_message), false);

        else if(checkNameInformation(view) == "EMPTY_NAME")
            onCreateDialog_OK(view, getString(R.string.empty_name_message), false);

        else if(checkNameInformation(view) == "INVALID_NAME")
            onCreateDialog_OK(view, getString(R.string.invalid_name_message), false);

        else{
            onCreateDialog_OK(view, getString(R.string.profile_noproblem), true);
        }
    }

    public String checkAgeInformation(View view){
        final int MINIMUM_AGE = 1;
        final int MAXIMUM_AGE = 120;

        final EditText userAgeText = (EditText) findViewById(R.id.ageText);

        if (userAgeText.getText().toString().equals("")){
            return "EMPTY_AGE";
        }
        else{
            int userAge = Integer.parseInt(userAgeText.getText().toString());

            if (userAge < MINIMUM_AGE || userAge > MAXIMUM_AGE){
                return "OUT_OF_RANGE_AGE";
            }

            return "OK";

        }
    }

    public boolean existsConditionInformation(View view){
        final int NOTHING_CHECKED = -1;

        RadioGroup conditionGroup = (RadioGroup) findViewById(R.id.conditionRadioGroup);
        if(conditionGroup.getCheckedRadioButtonId() == NOTHING_CHECKED){
            onCreateDialog_OK(view, getString(R.string.condition_empty_message), false);
            return false;
        }
        return true;
    }

    public String checkNameInformation(View view){
        final String REGEX_FINDDIGITS = ".*\\d.*";

        EditText userNameText = (EditText) findViewById(R.id.nameText);
        String userName = userNameText.getText().toString();
        if(userName.equals(""))
            //Toast.makeText(getApplicationContext(), , Toast.LENGTH_SHORT).show();
            return "EMPTY_NAME";
        else if(userName.matches(REGEX_FINDDIGITS))
            return "INVALID_NAME";
        else
            return "OK";
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


    public void saveInformation(android.view.View view){
        saveAge(view);
        saveHoursWork(view);
        saveHealthCondition(view);
        saveName(view);

    }

    public void saveAge(View view){
        EditText input = (EditText) findViewById(R.id.ageText);
        String entrada = input.getText().toString();
        PreferenceManager.getDefaultSharedPreferences(this).edit().putString("IDADE", entrada).apply();
        input.setText("");
        //    linhas comentadas abaixo feitas para teste
        //String idade = PreferenceManager.getDefaultSharedPreferences(this).getString("IDADE", "NOT_FOUND");
        //Toast.makeText(this, idade, Toast.LENGTH_SHORT).show();

    }

    public void saveHoursWork(View view){
        if(findViewById(HOURS_ANSWER_ID) != null){
            EditText hours_answer = (EditText) findViewById(HOURS_ANSWER_ID);
            String hours = hours_answer.getText().toString();
            PreferenceManager.getDefaultSharedPreferences(this).edit().putString("HORAS_TRABALHO", hours).apply();
            hours_answer.setText("");
            //    linhas comentadas abaixo feitas para teste
            //String horas = PreferenceManager.getDefaultSharedPreferences(this).getString("HORAS_TRABALHO", "NOT_FOUND");
            //Toast.makeText(this, horas, Toast.LENGTH_SHORT).show();
        }

    }

    public void saveHealthCondition(View view){
        RadioGroup conditionGroup = (RadioGroup) findViewById(R.id.conditionRadioGroup);
        int selectedConditionId = conditionGroup.getCheckedRadioButtonId();
        RadioButton selectedCondition = (RadioButton)findViewById(selectedConditionId);
        String selectedCondition_Text = selectedCondition.getText().toString();
        PreferenceManager.getDefaultSharedPreferences(this).edit().putString("HEALTH_CONDITION", selectedCondition_Text).apply();
        //Toast.makeText(getApplicationContext(), selectedCondition.getText().toString()+" is selected", Toast.LENGTH_SHORT).show();
    }

    public void saveName(View view){
        EditText input = (EditText) findViewById(R.id.nameText);
        String entrada = input.getText().toString();
        PreferenceManager.getDefaultSharedPreferences(this).edit().putString("NOME", entrada).apply();
        input.setText("");
        //    linhas comentadas abaixo feitas para teste
        //String nome = PreferenceManager.getDefaultSharedPreferences(this).getString("NOME", "NOT_FOUND");
        //Toast.makeText(this, nome, Toast.LENGTH_SHORT).show();
    }

}






