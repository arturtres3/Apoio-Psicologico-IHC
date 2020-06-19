package com.example.quicktest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HelpNowResults extends AppCompatActivity {
    int contaPaginas;
    List mostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_now_results);

        final Button button = findViewById(R.id.button8);
        Bundle extras = getIntent().getExtras();
        int[] resultados;
        resultados = extras.getIntArray("values");

        mostrar = criaMostrar(resultados);

        displayInfo((Integer) mostrar.get(0));

        contaPaginas = 1;

        if(contaPaginas == mostrar.size()){
            button.setText(getResources().getString(R.string.back));
        }
    }

    protected void displayInfo (int code) {
        TextView titulo = (TextView) findViewById(R.id.tituloResultado);
        TextView texto = (TextView) findViewById(R.id.textoResultado);
        ImageView imagem = (ImageView) findViewById(R.id.imagemResultado);

        switch(code)
        {
            case 0:
                titulo.setText(getResources().getString(R.string.pensamentos_intrusivos_titulo));
                texto.setText(getResources().getString(R.string.pensamentos_intrusivos_texto));
                //imagem.setImageDrawable(); //sem imagem por enquanto
                break;
            case 1:
                titulo.setText(getResources().getString(R.string.medo_titulo));
                texto.setText(getResources().getString(R.string.medo_texto));
                imagem.setImageDrawable(getResources().getDrawable(R.drawable.sunrise_fear));
                break;
            case 2:
                titulo.setText(getResources().getString(R.string.queima_titulo));
                texto.setText(getResources().getString(R.string.queima_texto));
                imagem.setImageDrawable(getResources().getDrawable(R.drawable.folha_chest));
                break;
            case 3:
                titulo.setText(getResources().getString(R.string.insonia_titulo));
                texto.setText(getResources().getString(R.string.insonia_texto));
                imagem.setImageDrawable(getResources().getDrawable(R.drawable.paradise_insomnia));
                break;
            case 4:
                titulo.setText(getResources().getString(R.string.tristeza_titulo));
                texto.setText(getResources().getString(R.string.tristeza_texto));
                imagem.setImageDrawable(getResources().getDrawable(R.drawable.seal_sadness));
                break;
            case 5:
                titulo.setText(getResources().getString(R.string.estresse_titulo));
                texto.setText(getResources().getString(R.string.estresse_texto));
                imagem.setImageDrawable(getResources().getDrawable(R.drawable.miau_stress));
                break;
            default:
                Toast.makeText(this, "Ocorreu um Erro", Toast.LENGTH_SHORT).show();
        }

    }

    public int getIndexOfLargest( int[] array ) {
        if ( array == null || array.length == 0 ) return -1; // null or empty

        int largest = 0;
        for ( int i = 1; i < array.length; i++ )
        {
            if ( array[i] > array[largest] ) largest = i;
        }
        return largest; // position of the first largest found
    }

    //cria lista de assuntos para mostrar para o usuário
    public List criaMostrar(int[] resultados){
        int i;
        List mostrar = new ArrayList();
        for(i=0; i < resultados.length; i++){
            if(resultados[i] >= 3){
                mostrar.add(i);
            }
        }

        if(mostrar.size() == 0){
            mostrar.add(getIndexOfLargest(resultados));
        }

        return mostrar;
    }

    public void proxResultado(android.view.View view){
        final Button button = findViewById(R.id.button8);
        if(contaPaginas < mostrar.size()){
            displayInfo((Integer) mostrar.get(contaPaginas));
            contaPaginas++;
            if(contaPaginas == mostrar.size()){
                button.setText("Voltar");
            }
        }else{
            Intent goBack = new Intent(this, MainActivity.class);
            startActivity(goBack);
        }
    }

}
