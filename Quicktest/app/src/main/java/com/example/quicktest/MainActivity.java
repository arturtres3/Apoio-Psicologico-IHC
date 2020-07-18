package com.example.quicktest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void boasVindas(android.view.View view){
        Intent bemVindo = new Intent(this, telaBemVindo.class);

        startActivity(bemVindo);
    }

    public void startHelpNow(android.view.View view){
        Intent helpNow = new Intent(this, HelpNowFirstPage.class);

        startActivity(helpNow);
    }

    public void startEditProfile(android.view.View view){
        Intent editProfile = new Intent(this, EditProfile.class);

        startActivity(editProfile);
    }

    public void startCoisasQueMeAfetam(android.view.View view){
        Intent coisas = new Intent(this, coisas_que_me_afetam.class);

        startActivity(coisas);
    }

    public void startMelhoreSeuDia(android.view.View view){

        if(getUserName() == "NOT_FOUND"){
            Redireciona(view, "Preencha o seu perfil primeiro");
        }else{
            Intent improveYourDay = new Intent(this, ImproveYourDay.class);
            startActivity(improveYourDay);
        }

    }
    public void Redireciona(final View view, String message) {
        final AlertDialog.Builder builderAgeError = new AlertDialog.Builder(this);
        builderAgeError.setMessage(message)
                .setPositiveButton("Ok, ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        editProfile(view);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        final Dialog requiredMessage = builderAgeError.create();
        requiredMessage.show();
    }

    public void editProfile(View view){
        Intent profile = new Intent(this, EditProfile.class);
        startActivity(profile);
    }

    public String getUserName(){
        return PreferenceManager.getDefaultSharedPreferences(this).getString("NOME", "NOT_FOUND");
    }
}
