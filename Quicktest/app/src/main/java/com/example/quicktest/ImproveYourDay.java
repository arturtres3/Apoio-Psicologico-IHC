package com.example.quicktest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
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
        //novaEntrada.setGravity(Gravity.CENTER);
        //novaEntrada.setBackgroundColor(getResources().getColor(R.color.Coisas));
        novaEntrada.setLayoutParams(params);
        if (isTitle) {
            novaEntrada.setTextSize(GREETINGS_TEXTSIZE);
            novaEntrada.setTextColor(getResources().getColor(R.color.colorPrimary));
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


    //monta o texto
    private void customText(){
        int age = Integer.parseInt(getUserAge());
        String healthCond = getUserHealthCondition();
        int work;

        if(getUserHoursOfWork().equals("NOT_FOUND")){
            work = 0;
        }else{
            work = Integer.parseInt(getUserHoursOfWork());
        }

        /*texto idade
        if(age > 0 && age < IDADE_CRIANÇA){
            putText("Idade menor que 13 TEXTO", false);
        }else if(age > IDADE_CRIANÇA && age < IDADE_ADULTO){
            putText("Idade entre 14-18 TEXTO", false);
        }else if(age > IDADE_ADULTO && age< IDADE_IDOSO){
            putText("Idade entre 19-59 TEXTO", false);
        }else if(age > IDADE_IDOSO){
            putText("Idade maior que 60 TEXTO", false);
        } */

        /*texto trabalho
        if(work == 0){
            putText("Não trabalha TEXTO", false);
        }else if(work > 0 && work < TRAB_MIN){
            putText("Trabalha entre 0-6hrs TEXTO", false);
        }else if(work > TRAB_MIN && work < TRAB_MAX){
            putText("Trabalha entre 6-9hrs TEXTO", false);
        }else if(work > TRAB_MAX){
            putText("Trabalha mais que 9hrs TEXTO", false);
        } */

        customTextWeekDay();
        customTextHealthCondition();
        customTextInfoCombinations();


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
        Intent backToMain = new Intent(this, MainActivity.class);

        startActivity(backToMain);
    }

}
