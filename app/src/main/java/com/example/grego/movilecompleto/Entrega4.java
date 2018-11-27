package com.example.grego.movilecompleto;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class Entrega4 extends AppCompatActivity {

    public static final String ITERATION = "iterarion";
    private TextView textview1;
    private TextView textview2;
    private LocalReciever receiver = new LocalReciever();
    public static final String WHO = "Who";

    public class LocalReciever extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String msg = intent.getStringExtra(MyIntentService.RESPONSE);
            int who = intent.getIntExtra(WHO, -1);
            switch (who) {
                case (MyIntentService.ME):
                    textview1.setText(msg);
                    break;
                case (MyService.ME):
                    textview2.setText((msg));
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrega4);
        Button is = findViewById(R.id.entrega4button1);
        textview1 = findViewById(R.id.entrega4textview1);
        is.setOnClickListener(v -> {
            for (int i = 0; i < 4; i++) {
                Intent mServiceIntent = new Intent(Entrega4.this, MyIntentService.class);
                mServiceIntent.putExtra(ITERATION, i);
                startService(mServiceIntent);
            }
        });

        Button is2 = findViewById(R.id.entrega4button2);
        textview2 = findViewById(R.id.entrega4textview2);
        is2.setOnClickListener(v -> {
            for (int i = 0; i < 4; i++) {
                Intent mServiceIntent = new Intent(Entrega4.this, MyService.class);
                mServiceIntent.putExtra(ITERATION, i);
                startService(mServiceIntent);
            }
        });
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter(MyIntentService.RESPONSE_ACTION));
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }
}
