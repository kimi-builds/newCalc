package com.lorod.newcalc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.view.View;
import android.widget.TextView;
import android.os.Bundle;
import java.util.regex.*;

public class MainActivity extends AppCompatActivity {
    private TextView resultadoTextView;
    private String numAtual = "";
    private String opAtual = "";
    private double resultado = 0;
    private boolean novoNum = true;
    private String expr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultadoTextView = findViewById(R.id.txtResultado);
        AppCompatButton botaoC = findViewById(R.id.limpar);
        AppCompatButton botao_soma = findViewById(R.id.adicao);
        AppCompatButton botao_limpartudo = findViewById(R.id.limparTudo);
        AppCompatButton botao_igual = findViewById(R.id.igual);
        AppCompatButton botao_porcentagem = findViewById(R.id.porcentagem);
        AppCompatButton botao_div = findViewById(R.id.divisao);
        AppCompatButton botao_um = findViewById(R.id.um);
        AppCompatButton botao_dois = findViewById(R.id.dois);
        AppCompatButton botao_tres = findViewById(R.id.tres);
        AppCompatButton botao_quatro = findViewById(R.id.quatro);
        AppCompatButton botao_cinco = findViewById(R.id.cinco);
        AppCompatButton botao_seis = findViewById(R.id.seis);
        AppCompatButton botao_sete = findViewById(R.id.sete);
        AppCompatButton botao_oito = findViewById(R.id.oito);
        AppCompatButton botao_nove = findViewById(R.id.nove);
        AppCompatButton botao_mul = findViewById(R.id.multiplicacao);
        AppCompatButton botao_sub = findViewById(R.id.subtracao);
        AppCompatButton botao_zero = findViewById(R.id.zero);
        AppCompatButton botao_virgula = findViewById(R.id.virgula);
        AppCompatButton botao_maisoumenos = findViewById(R.id.mais_ou_menos);
        botao_zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarNum("0");
                expr += "0";
            }
        });
        botao_um.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarNum("1");
                expr += "1";
            }
        });
        botao_dois.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarNum("2");
                expr += "2";
            }
        });
        botao_tres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarNum("3");
                expr += "3";
            }
        });
        botao_quatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarNum("4");
                expr += "4";
            }
        });
        botao_cinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarNum("5");
                expr += "5";
            }
        });
        botao_seis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarNum("6");
                expr += "6";
            }
        });
        botao_sete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarNum("7");
                expr += "7";
            }
        });
        botao_oito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarNum("8");
                expr += "8";
            }
        });
        botao_nove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarNum("9");
                expr += "9";
            }
        });
        botaoC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numAtual = "0";
                opAtual = "";
                atualVisor();
                novoNum = true;
                expr = "";
            }
        });
        botao_limpartudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numAtual = "";
                resultado = 0;
                opAtual = "";
                atualVisor();
                novoNum = true;
                expr = "";
            }
        });
        botao_virgula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!numAtual.contains(".")) {
                    numAtual += ".";
                }
                atualVisor();
            }
        });
        botao_maisoumenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!numAtual.isEmpty()) {
                    resultado *= -1;
                    numAtual = String.valueOf(resultado);
                    atualVisor();
                }
            }
        });
        botao_soma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opAtual = "+";
                expr += "+";
                calcular();
                novoNum = true;
            }
        });
        botao_igual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcular();
                novoNum = true;
            }
        });
        botao_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opAtual = "-";
                expr += "-";
                calcular();
                novoNum = true;
            }
        });
        botao_mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opAtual = "*";
                expr += "*";
                calcular();
                novoNum = true;
            }
        });
        botao_div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opAtual = "/";
                expr += "/";
                calcular();
                novoNum = true;
            }
        });
        botao_porcentagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opAtual = "%";
                expr += "%";
                calcular();
                novoNum = true;
            }
        });
    }

    private void adicionarNum(String dig) {
        if (novoNum) {
            numAtual = dig;
            novoNum = false;
        } else {
            numAtual += dig;
        }
        if(!expr.contains("+") && !expr.contains("-") && !expr.contains("*") && !expr.contains("/") && !expr.contains("%") && !expr.equals(String.valueOf(resultado))) {
            resultado = Double.parseDouble(numAtual);
        }
        atualVisor();
    }

    private void atualVisor() {
        resultadoTextView.setText(numAtual);
    }

    private void calcular() {
        final Pattern opera = Pattern.compile(".*[0-9]", Pattern.CASE_INSENSITIVE);
        final Matcher operaMatcher = opera.matcher(expr);
        if (operaMatcher.matches()) {
            switch (opAtual) {
                case "-" -> {
                    resultado -= Double.parseDouble(numAtual);
                }
                case "*" -> {
                    resultado *= Double.parseDouble(numAtual);
                }
                case "/" -> {
                    resultado /= Double.parseDouble(numAtual);
                }
                case "%" -> {
                    double numDigito = Double.parseDouble(numAtual);
                    resultado = numDigito*(resultado/100);
                }
                case "+" -> {
                    resultado += Double.parseDouble(numAtual);
                }
            }
            numAtual = String.valueOf(resultado);
            atualVisor();
            expr=numAtual;
        }
    }
}