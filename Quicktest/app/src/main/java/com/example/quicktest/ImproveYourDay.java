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
    final int IDADE_1 = 13;//limites para as 4 categorias de idade
    final int IDADE_2 = 18;
    final int IDADE_3 = 59;
    final int TRAB1 = 6;//limites para as 3 categorias de tempo de trabalho
    final int TRAB2 = 9;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_improve_your_day);
        putText(greetings(),true);
        customText();
    }

    public String greetings(){
        SimpleDateFormat current_hour = new SimpleDateFormat(CURRENT_HOUR);
        Date date = new Date(System.currentTimeMillis() - THREE_HOURS_IN_MILISSECONDS);
        String formatted_hour = current_hour.format(date);

        //TextView greetingsTextView = (TextView) findViewById(R.id.greetingsText);
        //greetingsTextView.setTextSize(GREETINGS_TEXTSIZE);
        int numeric_hour = Integer.valueOf(formatted_hour);
        if(numeric_hour >= FIRST_HOUR_MORNING && numeric_hour < FIRST_HOUR_AFTERNOON){
            return "Bom dia, " + getUserName();
        }
        else if(numeric_hour >= FIRST_HOUR_AFTERNOON && numeric_hour < FIRST_HOUR_NIGHT){
            return "Boa tarde, " + getUserName();
        }
        else{
            return "Boa noite, " + getUserName();
        }
    }

    // Coloca o texto na tela
    private void putText(String text, boolean titulo){
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
        if (titulo) {
            novaEntrada.setTextSize(GREETINGS_TEXTSIZE);
            novaEntrada.setTextColor(getResources().getColor(R.color.colorPrimary));
        }
        layout.addView(novaEntrada);
    }

    //monta o texto
    private void customText(){
        int age = Integer.parseInt(getUserAge());
        String healthCond = getUserHealthCondition();
        int work;

        if(getUserHoursOfWork() == "NOT_FOUND"){
            work = 0;
        }else{
            work = Integer.parseInt(getUserHoursOfWork());
        }

        //texto idade
        if(age > 0 && age < IDADE_1){
            putText("Idade menor que 13 TEXTO", false);
        }else if(age > IDADE_1 && age < IDADE_2){
            putText("Idade entre 14-18 TEXTO", false);
        }else if(age > IDADE_2 && age< IDADE_3){
            putText("Idade entre 19-59 TEXTO", false);
        }else if(age > IDADE_3){
            putText("Idade maior que 60 TEXTO", false);
        }

        //texto trabalho
        if(work == 0){
            putText("Não trabalha TEXTO", false);
        }else if(work > 0 && work < TRAB1){
            putText("Trabalha entre 0-6hrs TEXTO", false);
        }else if(work > TRAB1 && work < TRAB2){
            putText("Trabalha entre 6-9hrs TEXTO", false);
        }else if(work > TRAB2){
            putText("Trabalha mais que 9hrs TEXTO", false);
        }

        //texto condição
        if(healthCond.equals("Medo")){
            putText("Medo TEXTO", false);
        }else if(healthCond.equals("Tristeza")){
            putText("Tristeza TEXTO", false);
        }else if(healthCond.equals("Estresse")){
            putText("Estresse TEXTO", false);
        }else if(healthCond.equals("Queimação no peito")){
            putText("Queimação TEXTO", false);
        }else if(healthCond.equals("Pensamentos intrusivos")){
            putText("Pensamentos TEXTO", false);
        }else if(healthCond.equals("Insônia")){
            putText("Insônia TEXTO", false);
        }

        //texto dia da semana
        SimpleDateFormat day_of_the_week = new SimpleDateFormat(DAY_OF_THE_WEEK);
        Date date = new Date(System.currentTimeMillis() - THREE_HOURS_IN_MILISSECONDS);
        String dia = day_of_the_week.format(date);
        if(dia.equals("Sun")){
            putText("Domingo TEXTO", false);
        }else if(dia.equals("Mon")){
            putText("Segunda TEXTO", false);
        }else if(dia.equals("Tue")){
            putText("Terça TEXTO", false);
        }else if(dia.equals("Wed")){
            putText("Quarta TEXTO", false);
        }else if(dia.equals("Thu")){
            putText("Quinta TEXTO", false);
        }else if(dia.equals("Fri")){
            putText("Sexta TEXTO", false);
        }else if(dia.equals("Sat")){
            putText("Sábado TEXTO", false);
        }


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
