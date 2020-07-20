package com.example.quicktest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ImproveYourDay extends AppCompatActivity {

    final String CURRENT_HOUR = "H";
    final String DAY_OF_THE_WEEK = "EEE";
    final long THREE_HOURS_IN_MILISSECONDS = 10800000;
    final int FIRST_HOUR_MORNING = 6;
    final int FIRST_HOUR_AFTERNOON = 12;
    final int FIRST_HOUR_NIGHT = 18;
    final float GREETINGS_TEXTSIZE = 30;
    final int IDADE_CRIANÇA = 13;//limites para as 4 categorias de idade
    final int IDADE_ADULTO = 18;
    final int IDADE_IDOSO = 59;
    final int TRAB_MIN = 6;//limites para as 3 categorias de tempo de trabalho
    final int TRAB_MAX = 8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_improve_your_day);
        putText(greetings(),false);
        customText();
        customExercise();
        //createButtonToBreathExercise();
        //createButtonToRelaxExercise();
    }

    public String greetings(){
        //TextView greetingsTextView = (TextView) findViewById(R.id.greetingsText);
        //greetingsTextView.setTextSize(GREETINGS_TEXTSIZE);
        int numeric_hour = Integer.valueOf(getDateFormat(CURRENT_HOUR));
        if(numeric_hour >= FIRST_HOUR_MORNING && numeric_hour < FIRST_HOUR_AFTERNOON){
            putText("Bom dia, " + getUserName(), true);
            return getResources().getString(R.string.morning_improveText);
        }
        else if(numeric_hour >= FIRST_HOUR_AFTERNOON && numeric_hour < FIRST_HOUR_NIGHT){
            return "Boa tarde, " + getUserName();
        }
        else{
            putText("Boa noite, " + getUserName(), true);
            return getResources().getString(R.string.night_improveText);
        }
    }

    // Coloca o texto na tela
    private void putText(String text, boolean isTitle){
        LinearLayout layout = (LinearLayout) findViewById(R.id.layoutImprove);
        TextView novaEntrada = new TextView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layout.setGravity(Gravity.CENTER);
        params.setMargins(15, 7, 15, 7);
        int id = ViewCompat.generateViewId();
        novaEntrada.setId(id);
        novaEntrada.setText(text);
        novaEntrada.setPadding(20,20,20,20);
        novaEntrada.setTextSize(17);
        novaEntrada.setLayoutParams(params);
        if (isTitle) {
            novaEntrada.setTextSize(GREETINGS_TEXTSIZE);
            novaEntrada.setTextColor(getResources().getColor(R.color.colorPrimary));
        }
        else{
            novaEntrada.setTextColor(getResources().getColor(R.color.improveYourDay));
            novaEntrada.setTypeface(null, Typeface.BOLD);
        }
        layout.addView(novaEntrada);
    }

    private void customTextHealthCondition(){
        String userHealthCondition = getUserHealthCondition();

        if(userHealthCondition.equals("Medo")){
            putText(getResources().getString(R.string.fearCondition_improveText), false);
        }else if(userHealthCondition.equals("Tristeza")){
            putText(getResources().getString(R.string.sadnessCondition_improveText), false);
        }else if(userHealthCondition.equals("Estresse")){
            putText(getResources().getString(R.string.stressCondition_improveText), false);
        }else if(userHealthCondition.equals("Queimação no peito")){
            putText(getResources().getString(R.string.chestCondition_improveText), false);
        }else if(userHealthCondition.equals("Pensamentos intrusivos")){
            putText(getResources().getString(R.string.thoughtsCondition_improveText), false);
        }else if(userHealthCondition.equals("Insônia")){
            putText(getResources().getString(R.string.insomniaCondition_improveText), false);
        }

    }

    private void customTextWeekDay(){
        String dia = getDateFormat(DAY_OF_THE_WEEK);
        if(dia.equals("Sun")){
            putText(getResources().getString(R.string.sunday_improveText), false);
        }else if(dia.equals("Mon")){
            putText(getResources().getString(R.string.monday_improveText), false);
        }else if(dia.equals("Tue")){
            putText(getResources().getString(R.string.tuesday_improveText), false);
        }else if(dia.equals("Wed")){
            putText(getResources().getString(R.string.wednesday_improveText), false);
        }else if(dia.equals("Thu")){
            putText(getResources().getString(R.string.thursday_improveText), false);
        }else if(dia.equals("Fri")){
            putText(getResources().getString(R.string.friday_improveText), false);
        }else if(dia.equals("Sat")){
            putText(getResources().getString(R.string.saturday_improveText), false);
        }
    }

    private void customTextInfoCombinations(){
        int userAge = Integer.parseInt(getUserAge());
        String userHealthCondition = getUserHealthCondition();
        int userHoursWork = Integer.parseInt(getUserHoursOfWork());

        if(isStressedWithExcessWork(userHealthCondition, userHoursWork))
            putText(getResources().getString(R.string.stressPlusMuchWork_improveText), false);
        else if(isSadAndOld(userHealthCondition, userAge))
            putText(getResources().getString(R.string.oldPlusSad_improveText), false);
        else if(isYoungAdultAndAnsious(userHealthCondition, userAge))
            putText(getResources().getString(R.string.youngPlusAnxiety_improveText), false);
        else if(isChildAndScared(userHealthCondition, userAge))
            putText(getResources().getString(R.string.childAndFear_improveText), false);
    }


    private boolean isStressedWithExcessWork(String health, int hours){
        return hours > TRAB_MAX && health.equals("Estresse");
    }

    private boolean isSadAndOld(String health, int age){
        return age > IDADE_IDOSO && health.equals("Tristeza");
    }

    private boolean isYoungAdultAndAnsious(String health, int age){
        return age >= IDADE_ADULTO && age <= IDADE_IDOSO && health.equals("Queimação no peito");
    }

    private boolean isChildAndScared(String health, int age){
        return age <= IDADE_CRIANÇA && health.equals("Medo");
    }


    //monta o texto
    private void customText(){

        customTextWeekDay();
        customTextHealthCondition();
        customTextInfoCombinations();

    }

    private void customExercise(){
        breathExercise();
        relaxExercise();
    }

    private void breathExercise(){
        String userHealthCondition = getUserHealthCondition();
        int userAge = Integer.parseInt(getUserAge());
        if(userHealthCondition.equals("Estresse") || isYoungAdultAndAnsious(userHealthCondition, userAge))
            createButtonToBreathExercise();
    }

    private void relaxExercise(){
        String userHealthCondition = getUserHealthCondition();
        int userAge = Integer.parseInt(getUserAge());
        if(userHealthCondition.equals("Insônia") || isChildAndScared(userHealthCondition, userAge))
            createButtonToRelaxExercise();
    }


    public void createButtonToBreathExercise(){
        LinearLayout layout = (LinearLayout) findViewById(R.id.layoutImprove);

        Button interactionButton = new Button(this);
        interactionButton.setText("COMEÇAR!");
        interactionButton.setBackgroundColor(getResources().getColor(R.color.improveYourDayStartBreathExercise));
        interactionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToBreathExercise();
            }
        });
        layout.addView(interactionButton);
    }

    public void createButtonToRelaxExercise(){
        LinearLayout layout = (LinearLayout) findViewById(R.id.layoutImprove);

        Button interactionButton = new Button(this);
        interactionButton.setText("COMEÇAR!");
        interactionButton.setBackgroundColor(getResources().getColor(R.color.improveYourDayStartRelaxExercise));
        interactionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRelaxSongExercise();
            }
        });

        layout.addView(interactionButton);

    }

    public void goToBreathExercise(){
        Intent breathExercise = new Intent(this, BreathExercise.class);

        startActivity(breathExercise);
    }

    public void goToRelaxSongExercise(){
        Intent relaxSong = new Intent(this, RelaxExercise.class);

        startActivity(relaxSong);
    }

    public String getDateFormat(String format){
        SimpleDateFormat day_of_the_week = new SimpleDateFormat(format);
        Date date = new Date(System.currentTimeMillis() - THREE_HOURS_IN_MILISSECONDS);
        return day_of_the_week.format(date);
    }

    public String getUserName(){
        return PreferenceManager.getDefaultSharedPreferences(this).getString("NOME", "NOT_FOUND");
    }

    public String getUserAge(){
        return PreferenceManager.getDefaultSharedPreferences(this).getString("IDADE", "NOT_FOUND");
    }

    public String getUserHealthCondition(){
        return PreferenceManager.getDefaultSharedPreferences(this).getString("HEALTH_CONDITION", "NOT_FOUND");
    }

    public String getUserHoursOfWork(){
        return PreferenceManager.getDefaultSharedPreferences(this).getString("HORAS_TRABALHO", "NOT_FOUND");
    }

    public void backToMainPage(View view){
        LinearLayout layout = (LinearLayout) findViewById(R.id.layoutImprove);
        layout.removeAllViews();
        Intent backToMain = new Intent(this, MainActivity.class);

        startActivity(backToMain);
    }

}
