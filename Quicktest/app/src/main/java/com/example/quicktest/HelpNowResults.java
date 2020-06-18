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
            button.setText("Voltar");
        }
    }

    protected void displayInfo (int code) {
        TextView titulo = (TextView) findViewById(R.id.tituloResultado);
        TextView texto = (TextView) findViewById(R.id.textoResultado);
        ImageView imagem = (ImageView) findViewById(R.id.imagemResultado);

        //Drawable d = getResources().getDrawable(android.R.drawable.sunrise_fear);

        switch(code)
        {
            case 0:
                String mystring = getResources().getString(R.string.pensamentos_intrusivos_texto);
                titulo.setText("Pensamentos Intrusivos");
                texto.setText(mystring);
                //imagem.setImageDrawable();
                break;
            case 1:
                Drawable medo = this.getResources().getDrawable(R.drawable.sunrise_fear);
                String mystring1 = getResources().getString(R.string.medo_texto);
                titulo.setText("Medo");
                texto.setText(mystring1);
                imagem.setImageDrawable(medo);
                break;
            case 2:
                Drawable folha = this.getResources().getDrawable(R.drawable.folha_chest);
                String mystring2 = getResources().getString(R.string.queima_texto);
                titulo.setText("Queimação no Peito");
                texto.setText(mystring2);
                imagem.setImageDrawable(folha);
                break;
            case 3:
                Drawable paradise = this.getResources().getDrawable(R.drawable.paradise_insomnia);
                String mystring3 = getResources().getString(R.string.insonia_texto);
                titulo.setText("Insônia");
                texto.setText(mystring3);
                imagem.setImageDrawable(paradise);
                break;
            case 4:
                Drawable seal = this.getResources().getDrawable(R.drawable.seal_sadness);
                String mystring4 = getResources().getString(R.string.tristeza_texto);
                titulo.setText("Tristeza");
                texto.setText(mystring4);
                imagem.setImageDrawable(seal);
                break;
            case 5:
                Drawable cat = this.getResources().getDrawable(R.drawable.miau_stress);
                String mystring5 = getResources().getString(R.string.estresse_texto);
                titulo.setText("Estresse");
                texto.setText(mystring5);
                imagem.setImageDrawable(cat);
                break;


            default:

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
