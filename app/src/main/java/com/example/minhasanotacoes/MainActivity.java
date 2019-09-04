package com.example.minhasanotacoes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private ImageView btnSalvar;
    private EditText texto;

    private static final String NOME_ARQUIVO = "anotacao.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSalvar = findViewById(R.id.btn_salvar_id);
        texto = findViewById(R.id.txt_id);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String textoDigitado = texto.getText().toString();
                gravarNoArquivo(textoDigitado);
                Toast.makeText(MainActivity.this, "Mensagem salva com sucesso.", Toast.LENGTH_SHORT).show();

            }
        });

        //recuperar oq foi gravado

        if(lerArquivo() != null){

            texto.setText(lerArquivo());
        }

    }

    private void gravarNoArquivo(String texto){

        try{

            // instancia para gravar arquivos para o android
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(NOME_ARQUIVO, Context.MODE_PRIVATE));
            outputStreamWriter.write(texto);
            outputStreamWriter.close();


        }catch (IOException e){
            Log.v("MainActivity", e.toString());
        }
    }

    private String lerArquivo(){

        String resultado = "";

        try{

            //abrir o arquivo
            InputStream arquivo = openFileInput(NOME_ARQUIVO);

            if(arquivo != null){

                //ler  arquivo
                InputStreamReader inputStreamReader =  new InputStreamReader(arquivo);

                //Gerar buffer do arquivo lido
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                //recuperar texto do carquivo
                String linhaArquivo = "";
                while ((linhaArquivo = bufferedReader.readLine()) != null){

                    resultado += linhaArquivo;

                }

                arquivo.close();
            }


        }catch (IOException e){
            Log.v("MainActivity", e.toString());
        }

        return  resultado;
    }
}
