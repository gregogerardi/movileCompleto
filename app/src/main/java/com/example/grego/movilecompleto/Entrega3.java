package com.example.grego.movilecompleto;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Entrega3 extends AppCompatActivity {

    private boolean active = false;
    private int counter = 0;
    TextView text_time;
    private String sharedPrefFile = "entrega3";

    @Override
    protected void onStop() {
        Button mBtnAutomaticClick;
        mBtnAutomaticClick = findViewById(R.id.btn_stop2);
        mBtnAutomaticClick.performClick();

        SharedPreferences mPreferences = getSharedPreferences(sharedPrefFile,MODE_PRIVATE);
        SharedPreferences.Editor spEditor = mPreferences.edit();
        spEditor.putString("count",String.valueOf(counter));
        spEditor.apply();
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrega3);
        final Button btn_start = (Button) findViewById(R.id.btn_start2);
        final Button btn_stop = (Button) findViewById(R.id.btn_stop2);
        final Button btn_reset = (Button) findViewById(R.id.btn_reset);
        text_time = (TextView) findViewById(R.id.text_time);
        SharedPreferences mPreferences = getSharedPreferences(sharedPrefFile,MODE_PRIVATE);
        text_time.setText(mPreferences.getString("count","0"));
        counter = Integer.valueOf(mPreferences.getString("count","0"));
        btn_stop.setEnabled(false);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_stop.setEnabled(true);
                btn_start.setEnabled(false);
                active=true;

                final AsyncTask<?,?,?> asyncTask = new AsyncTask<Object, Integer, Object>() {
                    @Override
                    protected Object doInBackground(Object... objects) {
                        while (active) {
                            try {
                                synchronized ((Entrega3.this)) {
                                    Entrega3.this.wait(1000);
                                }
                                if (!active)
                                    break;
                            }
                            catch (InterruptedException e){

                            }
                            counter++;
                            Log.d("hola","Van:" + counter +"seg.");
                            publishProgress(counter);
                        }
                        return null;
                    }

                    @Override
                    protected void onProgressUpdate(Integer... values) {
                        super.onProgressUpdate(values);
                        text_time.setText(String.valueOf(counter));
                    }

                };

                asyncTask.execute();
            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_stop.setEnabled(false);
                btn_start.setEnabled(true);
                active=false;
                synchronized (Entrega3.this){
                    Entrega3.this.notifyAll();
                }
            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_stop.setEnabled(false);
                btn_start.setEnabled(true);
                text_time.setText("0");
                active=false;
                counter=0;
                synchronized (Entrega3.this){
                    Entrega3.this.notifyAll();
                }
            }
        });
    }
}
