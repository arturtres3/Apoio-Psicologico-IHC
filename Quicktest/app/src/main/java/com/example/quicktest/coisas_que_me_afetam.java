package com.example.quicktest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class coisas_que_me_afetam extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coisas_que_me_afetam);
        String lista_de_coisas_raw = PreferenceManager.getDefaultSharedPreferences(this).getString("COISAS_QUE_ME_AFETAM", "NOT_FOUND");

        if(!lista_de_coisas_raw.equals("NOT_FOUND")){
            String[] lista_de_coisas = lista_de_coisas_raw.split("#");
            for(int i=0; i<lista_de_coisas.length; i++){
                putText(lista_de_coisas[i]);
            }
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void sendText(android.view.View view){
        EditText input = (EditText) findViewById(R.id.input);
        String entrada = input.getText().toString();
        putText(entrada);
        String lista_de_coisas_raw = PreferenceManager.getDefaultSharedPreferences(this).getString("COISAS_QUE_ME_AFETAM", "NOT_FOUND");

        if(!lista_de_coisas_raw.equals("NOT_FOUND")){
            lista_de_coisas_raw = lista_de_coisas_raw.concat("#").concat(entrada);
        }else{
            lista_de_coisas_raw = entrada;
        }
        PreferenceManager.getDefaultSharedPreferences(this).edit().putString("COISAS_QUE_ME_AFETAM", lista_de_coisas_raw).apply();

        input.setText("");
    }

    public void putText(String text){
        LinearLayout layout = (LinearLayout) findViewById(R.id.lista);
        TextView novaEntrada = new TextView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(15, 7, 15, 7);

        novaEntrada.setText(text);
        novaEntrada.setPadding(20,20,20,20);
        novaEntrada.setBackgroundColor(getResources().getColor(R.color.Coisas));
        novaEntrada.setLayoutParams(params);
        layout.addView(novaEntrada);
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
