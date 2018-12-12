package com.example.grego.movilecompleto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Entrega1 extends AppCompatActivity {
private static final String TAG = "tag";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrega1);
        final Button button = (Button)findViewById(R.id.Entrega1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,getString(R.string.bottonPress));
                Toast.makeText(getApplicationContext(), R.string.esto_es_un_toast,Toast.LENGTH_SHORT ).show();
            }
        });
    }
}
