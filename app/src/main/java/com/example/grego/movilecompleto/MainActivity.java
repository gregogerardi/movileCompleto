package com.example.grego.movilecompleto;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    public static final String WHO = "Who";
    private TextView numRadom;
    private LocalReceiver receiver = new LocalReceiver();
    public static final String RESPONSE_ACTION = "Respuesta de la iteracion";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button1 = findViewById(R.id.button1);
        final Button button2 = findViewById(R.id.button2);
        final Button button3 = findViewById(R.id.button3);
        final Button button4 = findViewById(R.id.button4);
        numRadom = findViewById(R.id.entrega5);
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver,new IntentFilter(RESPONSE_ACTION));
        button1.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Entrega1.class)));
        button2.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Entrega2.class)));
        button3.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Entrega3.class)));
        button4.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Entrega4.class)));
    }

    private  class LocalReceiver extends BroadcastReceiver {
        public static final String RESPONSE = "Response";
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(getApplicationContext(),"Se recibio un numero nuevo, sera actualizado",Toast.LENGTH_SHORT).show();
            String msg = intent.getStringExtra(RESPONSE);
            int who =0;
            who = intent.getIntExtra(WHO,-1);
            if (who == MessengerService.ME){
                numRadom.setText(msg);
            }
        }
        }
}
