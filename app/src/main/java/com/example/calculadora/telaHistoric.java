package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class telaHistoric extends AppCompatActivity {
    TextView txtHistorico;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_historic);
        txtHistorico = findViewById(R.id.txtHistorico);
        String msg = getIntent().getStringExtra("MSG_INICIAL");
        txtHistorico.setText(msg);
    }
    public void btVoltarClick(View v){
        finish();
    }
}