package com.example.grego.movilecompleto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button1 = findViewById(R.id.button1);
        final Button button2 = findViewById(R.id.button2);
        final Button button3 = findViewById(R.id.button3);
        final Button button4 = findViewById(R.id.button4);
        final Button button5 = findViewById(R.id.button5);

        button1.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Entrega1.class)));
        button2.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Entrega2.class)));
        button3.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Entrega3.class)));
        button4.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Entrega4.class)));
        button5.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Entrega5.class)));

    }
}
