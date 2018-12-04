package com.example.grego.movilecompleto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Multiplication extends AppCompatActivity {

    public static final String RESULTADO = "Resultado";
    public static final String EMPTY = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplication);
        Button boton_multiplicar = findViewById(R.id.buttonMultiplicar);
        final EditText num1 = (EditText) findViewById(R.id.editTextOperando1);
        final EditText num2 = (EditText) findViewById(R.id.editTextOperando2);
        num1.setText(EMPTY);
        num2.setText(EMPTY);
        boton_multiplicar.setOnClickListener(v -> {
            Intent replyIntent = new Intent();
            int resultado = Integer.parseInt(num1.getText().toString()) * Integer.parseInt(num2.getText().toString());
            replyIntent.putExtra(RESULTADO,resultado);
            setResult(RESULT_OK,replyIntent);
            finish();
        });
    }
}
