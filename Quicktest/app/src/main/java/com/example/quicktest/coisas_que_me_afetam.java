package com.example.quicktest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import android.app.ActionBar;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class coisas_que_me_afetam extends AppCompatActivity {
    int selectedCoisaId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coisas_que_me_afetam);
        String lista_de_coisas_raw = PreferenceManager.getDefaultSharedPreferences(this).getString("COISAS_QUE_ME_AFETAM", "NOT_FOUND");
        lista_de_coisas_raw = cleanCoisasString(lista_de_coisas_raw);

        if(!lista_de_coisas_raw.equals("NOT_FOUND") && !lista_de_coisas_raw.equals("")){
            String[] lista_de_coisas = lista_de_coisas_raw.split("#");
            for(int i=0; i<lista_de_coisas.length; i++){
                putText(lista_de_coisas[i]);
            }
        }
        PreferenceManager.getDefaultSharedPreferences(this).edit().putString("COISAS_QUE_ME_AFETAM", lista_de_coisas_raw).apply();
        //Toast.makeText(this, lista_de_coisas_raw, Toast.LENGTH_SHORT).show();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    // Envia para SharedPreferences
    public void sendText(android.view.View view){
        EditText input = (EditText) findViewById(R.id.input);
        String entrada = input.getText().toString();
        if(!entrada.equals("")) {
            putText(entrada);
            String lista_de_coisas_raw = PreferenceManager.getDefaultSharedPreferences(this).getString("COISAS_QUE_ME_AFETAM", "NOT_FOUND");

            if (!lista_de_coisas_raw.equals("NOT_FOUND") && !lista_de_coisas_raw.equals("")) {
                lista_de_coisas_raw = lista_de_coisas_raw.concat("#").concat(entrada);
            } else {
                lista_de_coisas_raw = entrada;
            }
            PreferenceManager.getDefaultSharedPreferences(this).edit().putString("COISAS_QUE_ME_AFETAM", lista_de_coisas_raw).apply();
            //Toast.makeText(this, lista_de_coisas_raw, Toast.LENGTH_SHORT).show();
            input.setText("");
        }
    }

    // Coloca o texto na tela
    private void putText(String text){
        LinearLayout layout = (LinearLayout) findViewById(R.id.lista);
        TextView novaEntrada = new TextView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(15, 7, 15, 7);
        int id = ViewCompat.generateViewId();
        novaEntrada.setId(id);
        novaEntrada.setText(text);
        novaEntrada.setPadding(20,20,20,20);
        novaEntrada.setBackgroundColor(getResources().getColor(R.color.Coisas));
        novaEntrada.setLayoutParams(params);
        novaEntrada.setOnClickListener(new View.OnClickListener() {
            public void onClick(android.view.View view) {
                Selected(view);
            }
        });
        layout.addView(novaEntrada);
    }

    // Selecão ao clicar no texto
    public void Selected (android.view.View view){
        //TextView coisaSelecionada = (TextView) findViewById(view.getId());
        int ID = view.getId();

        if(selectedCoisaId == ID){
            view.setBackgroundColor(getResources().getColor(R.color.Coisas));
            selectedCoisaId = 0;
        }else{
            view.setBackgroundColor(getResources().getColor(R.color.colorAccent));

            LinearLayout layout = (LinearLayout) findViewById(R.id.lista);
            int childcount = layout.getChildCount();
            for (int i=0; i < childcount; i++){
                TextView coisa = (TextView) layout.getChildAt(i);
                if(coisa.getId() != ID){
                    coisa.setBackgroundColor(getResources().getColor(R.color.Coisas));
                }
            }
            selectedCoisaId = ID;
            //Toast.makeText(this, " "+ID, Toast.LENGTH_SHORT).show();
        }
    }

    // Remove da tela
    public void Delete(android.view.View view){
        TextView coisaSelecionada = (TextView) findViewById(selectedCoisaId);
        String lista_de_coisas_raw = PreferenceManager.getDefaultSharedPreferences(this).getString("COISAS_QUE_ME_AFETAM", "NOT_FOUND");

        LinearLayout layout = (LinearLayout) findViewById(R.id.lista);
        int childcount = layout.getChildCount();

        for (int i=0; i < childcount; i++){
            TextView coisa = (TextView) layout.getChildAt(i);
            if(coisa.getId() == selectedCoisaId){
                deleteText(lista_de_coisas_raw, i);
                layout.removeView(findViewById(selectedCoisaId));
                childcount--;
            }
        }
        //Toast.makeText(this, "" + layout.getChildCount(), Toast.LENGTH_SHORT).show();
    }

    // Remove do SharedPreferences
    private void deleteText(String coisas_raw, int index_removido){
        if(!coisas_raw.equals("NOT_FOUND")) {
            String[] lista_de_coisas = coisas_raw.split("#");
            List<String> list = new ArrayList<String>(Arrays.asList(lista_de_coisas));
            list.remove(index_removido);
            String string_de_coisas = "";
            for(int i = 0; i < list.size(); i++){
                if(i != list.size()-1)
                    string_de_coisas = string_de_coisas.concat(list.get(i)).concat("#");
                else
                    string_de_coisas = string_de_coisas.concat(list.get(i));
            }
            PreferenceManager.getDefaultSharedPreferences(this).edit().putString("COISAS_QUE_ME_AFETAM", string_de_coisas).apply();
            //Toast.makeText(this, string_de_coisas, Toast.LENGTH_SHORT).show();
        }
    }

    // Tira os #'s do inicio e final (que nunca deveriam ter sido colocados, mas ok)
    private String cleanCoisasString(String coisas_raw){
        StringBuilder aux_str = new StringBuilder(coisas_raw);
        if(!coisas_raw.equals("")) {
            if (aux_str.charAt(0) == '#') {
                aux_str.deleteCharAt(0);
            }
            if (aux_str.charAt(aux_str.length() - 1) == '#') {
                aux_str.deleteCharAt(aux_str.length() - 1);
            }
        }

        String resultString = aux_str.toString();
        return resultString;
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
