package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button bt_numZero,bt_numUm, bt_numDois, bt_numTres, bt_numQuatro, bt_numCinco, bt_numSeis, bt_numSete, bt_numOito, bt_numNove,
            bt_numPonto,bt_resultado,bt_soma,bt_subtracao, bt_multi, bt_divisao, bt_limpar, bt_historico;

    private TextView txt_expressao, txt_resultado;

    private ImageView bt_backspace;

    public String txtHistorico = "";
    public boolean resultadoRealizado = false;
    public boolean operadorUsado = false;
    public boolean ultimoOperador = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicarComponentes();

        bt_numZero.setOnClickListener(this);
        bt_numUm.setOnClickListener(this);
        bt_numDois.setOnClickListener(this);
        bt_numTres.setOnClickListener(this);
        bt_numQuatro.setOnClickListener(this);
        bt_numCinco.setOnClickListener(this);
        bt_numSeis.setOnClickListener(this);
        bt_numSete.setOnClickListener(this);
        bt_numOito.setOnClickListener(this);
        bt_numNove.setOnClickListener(this);
        bt_soma.setOnClickListener(this);
        bt_subtracao.setOnClickListener(this);
        bt_multi.setOnClickListener(this);
        bt_divisao.setOnClickListener(this);
        bt_numPonto.setOnClickListener(this);
        bt_historico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getBaseContext(), telaHistoric.class);
                it.putExtra("MSG_INICIAL", txtHistorico);
                startActivity(it);
            }
        });

        bt_backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StringBuilder expressao = new StringBuilder(txt_expressao.getText().toString());

                if (expressao.length() > 0) {
                    expressao.deleteCharAt(expressao.length()-1);
                    txt_expressao.setText(expressao.toString());
                }
            }
        });

        bt_limpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txt_expressao.setText("");
                txt_resultado.setText("");
            }
        });

        bt_resultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Expression expressao = new ExpressionBuilder(txt_expressao.getText().toString()).build();
                    double resultado = expressao.evaluate();
                    long result = (long) resultado;
                    String txtExpr = txt_expressao.getText().toString();
                    String exprTextView;
                    if (resultadoRealizado){
                        exprTextView = txt_resultado.getText().toString();
                        txt_expressao.setText(exprTextView);
                        txt_resultado.setText("");
                        resultadoRealizado = false;
                    }else {
                        if (resultado == (double) result) {
                            txt_resultado.setText((CharSequence) String.valueOf(result));
                            txtHistorico = txtHistorico + txtExpr + '\n' + '=' + result + "\n ----------\n ";
                            resultadoRealizado = true;
                        } else {
                            txt_resultado.setText((CharSequence) String.valueOf(resultado));
                            txtHistorico = txtHistorico + txtExpr + '\n' + '=' + resultado + "\n ----------\n ";
                            resultadoRealizado = true;
                        }

                    }
                }catch (Exception e){

                }
            }
        });

    }


    private void inicarComponentes(){
        bt_numZero = findViewById(R.id.bt_numZero);
        bt_numUm = findViewById(R.id.bt_numUm);
        bt_numDois = findViewById(R.id.bt_numDois);
        bt_numTres = findViewById(R.id.bt_numTres);
        bt_numQuatro = findViewById(R.id.bt_numQuatro);
        bt_numCinco = findViewById(R.id.bt_numCinco);
        bt_numSeis = findViewById(R.id.bt_numSeis);
        bt_numSete = findViewById(R.id.bt_numSete);
        bt_numOito = findViewById(R.id.bt_numOito);
        bt_numNove = findViewById(R.id.bt_numNove);
        bt_numPonto = findViewById(R.id.bt_numPonto);
        bt_resultado = findViewById(R.id.bt_resultado);
        bt_soma = findViewById(R.id.bt_soma);
        bt_subtracao = findViewById(R.id.bt_subtracao);
        bt_multi = findViewById(R.id.bt_multi);
        bt_divisao = findViewById(R.id.bt_divisao);
        bt_limpar = findViewById(R.id.bt_limpar);
        txt_resultado = findViewById(R.id.txt_resultado);
        bt_historico = findViewById(R.id.bt_historico);
        txt_expressao = findViewById(R.id.txt_expressao);
        bt_backspace = findViewById(R.id.bt_backspace);

    }

    public void alimentaTxtExpressao(String string, boolean limparExp){

        if(limparExp){

            txt_resultado.setText(" ");
            txt_expressao.append(string);
            operadorUsado = false;
        }else {
            StringBuilder expressaoOperador = new StringBuilder(txt_expressao.getText().toString());

            if ((string == "/" || string == "*" || string == "-" || string == "+") && operadorUsado ) {

                expressaoOperador.deleteCharAt(expressaoOperador.length() - 1);
                expressaoOperador.append(string);

                txt_expressao.setText(expressaoOperador.toString());
            } else {
                operadorUsado =true;
                txt_expressao.append(txt_resultado.getText());
                txt_expressao.append(string.trim());
                txt_resultado.setText(" ");
            }
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_numZero:
                alimentaTxtExpressao("0",true);
                break;
            case R.id.bt_numUm:
                alimentaTxtExpressao("1",true);
                break;
            case R.id.bt_numDois:
                alimentaTxtExpressao("2",true);
                break;
            case R.id.bt_numTres:
                alimentaTxtExpressao("3",true);
                break;
            case R.id.bt_numQuatro:
                alimentaTxtExpressao("4",true);
                break;
            case R.id.bt_numCinco:
                alimentaTxtExpressao("5",true);
                break;
            case R.id.bt_numSeis:
                alimentaTxtExpressao("6",true);
                break;
            case R.id.bt_numSete:
                alimentaTxtExpressao("7",true);
                break;
            case R.id.bt_numOito:
                alimentaTxtExpressao("8",true);
                break;
            case R.id.bt_numNove:
                alimentaTxtExpressao("9",true);
                break;
            case R.id.bt_soma:
                alimentaTxtExpressao("+",false);
                break;
            case R.id.bt_subtracao:
                alimentaTxtExpressao("-",false);
                break;
            case R.id.bt_multi:
                alimentaTxtExpressao("*",false);
                break;
            case R.id.bt_divisao:
                alimentaTxtExpressao("/",false);
                break;
            case R.id.bt_numPonto:
                alimentaTxtExpressao(".",false);
                break;
        }
    }
}