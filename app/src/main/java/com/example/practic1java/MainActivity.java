package com.example.practic1java;

import android.content.Intent;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.Normalizer;

public class MainActivity extends AppCompatActivity {
    private TextView Formula;
    private TextView EndResultat;
    private Button one, two, tree, four, five, six, seven, eight, nine, zero;
    private Button plus, minus, multiply, division, result, cvadrat, koren, procent, delit;
    private  double valueFirst;
    private  double valueSecond;
    private  char Action;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView();
        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button buton = (Button) view;
                Formula.setText(Formula.getText().toString() + buton.getText().toString());
            }
        };
        one.setOnClickListener(numberClickListener);
        two.setOnClickListener(numberClickListener);
        tree.setOnClickListener(numberClickListener);
        four.setOnClickListener(numberClickListener);
        five.setOnClickListener(numberClickListener);
        six.setOnClickListener(numberClickListener);
        seven.setOnClickListener(numberClickListener);
        eight.setOnClickListener(numberClickListener);
        nine.setOnClickListener(numberClickListener);
        zero.setOnClickListener(numberClickListener);

        View.OnClickListener actonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                if(valueFirst == 0.0){
                    valueFirst = Double.parseDouble(Formula.getText().toString());
                    Action = button.getText().charAt(0);
                    Formula.setText(String.valueOf(valueFirst) + Action);
                    return;
                }
                if(valueSecond==0.0){
                    String formula = Formula.getText().toString();
                    int index = formula.indexOf(Action);
                    valueSecond = Double.parseDouble(formula.substring(index+1));
                    colculet();
                    Action = button.getText().charAt(0);
                    Formula.setText(String.valueOf(valueFirst) + Action);
                    EndResultat.setText(Double.toString(valueFirst));
                }
            }
        };
        plus.setOnClickListener(actonClickListener);
        minus.setOnClickListener(actonClickListener);
        multiply.setOnClickListener(actonClickListener);
        division.setOnClickListener(actonClickListener);
        procent.setOnClickListener(actonClickListener);
        koren.setOnClickListener(actonClickListener);
        cvadrat.setOnClickListener(actonClickListener);
        result.setOnClickListener(actonClickListener);

        EndResultat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colculet();
                Action = '=';
                EndResultat.setText(String.valueOf(valueFirst));
                Formula.setText(null);
            }
        });
    }
    public void delitClick(View view){
        valueFirst=0;
        valueSecond=0;
        Formula.setText("");
        EndResultat.setText("");
    }
    private void setupView(){
        one = (Button) findViewById(R.id.one);
        two = (Button) findViewById(R.id.two);
        tree = (Button) findViewById(R.id.three);
        four = (Button) findViewById(R.id.four);
        five = (Button) findViewById(R.id.five);
        six = (Button) findViewById(R.id.six);
        seven = (Button) findViewById(R.id.seven);
        eight = (Button) findViewById(R.id.eight);
        nine = (Button) findViewById(R.id.nine);
        zero = (Button) findViewById(R.id.zero);
        plus = (Button) findViewById(R.id.plus);
        minus = (Button) findViewById(R.id.minus);
        division = (Button) findViewById(R.id.divide);
        multiply = (Button) findViewById(R.id.multiply);
        result = (Button) findViewById(R.id.result);
        Formula = (TextView) findViewById(R.id.Formula);
        EndResultat = (TextView) findViewById(R.id.EndResultat);
        cvadrat = (Button) findViewById(R.id.cvadrat);
        koren = (Button) findViewById(R.id.koren);
        procent = (Button) findViewById(R.id.procent);
        delit = (Button) findViewById(R.id.delit);
    }

    public void colculet(){
        if(!Double.isNaN(valueFirst)){
            String formtext = Formula.getText().toString();
            int indexaction = formtext.indexOf(Action);
            if(indexaction != 1){
                String number = formtext.substring(indexaction + 1);
                valueSecond = Double.parseDouble(number);

                switch (Action){
                    case '+':
                        valueFirst += valueSecond;
                        break;
                    case '-':
                        valueFirst -= valueSecond;
                        break;
                    case '*':
                        valueFirst *= valueSecond;
                        break;
                    case '/':
                        if(valueSecond == 0){
                            valueFirst = 0.0;
                            Formula.setText("Так нельзя в математике идиот");
                        }else {
                            valueFirst /= valueSecond;
                        }
                        break;
                    case '^':
                        valueFirst= Math.pow(valueFirst,valueSecond);
                        break;
                    case '%':
                        valueFirst=(valueFirst*valueSecond)/100;
                        break;
                    case '√':
                        valueFirst = Math.sqrt(valueFirst);
                        break;
                    case 'c':
                        valueFirst = Math.tan(valueFirst)/1;
                    case '=':
                        valueFirst = valueSecond;
                        break;
                }
            }
        }else{
            valueFirst = Double.parseDouble(Formula.getText().toString());
        }
        EndResultat.setText(null);
        Formula.setText(null);
    }
}