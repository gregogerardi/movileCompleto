package com.example.grego.movilecompleto;

import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Entrega2 extends AppCompatActivity {
    private static final int GET_CONTACT = 1;
    private static final int GET_MULTIPLICATION = 2;
    public static final String VALOR_MULTIPLICADO = "VALOR_MULTIPLICADO";
    public static final String CONTACT_STRING = "CONTACT_STRING";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrega2);

        Button boton = findViewById(R.id.btn_pick_contact);
        boton.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_PICK);
            i.addCategory(Intent.CATEGORY_DEFAULT);
            i.setType(ContactsContract.Contacts.CONTENT_TYPE);
            startActivityForResult(i,GET_CONTACT);
        });

        Button boton_multiplicar = findViewById(R.id.btn_multiplicar);
        boton_multiplicar.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Multiplication.class);
            startActivityForResult(intent,GET_MULTIPLICATION);
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(VALOR_MULTIPLICADO, (String.valueOf(((TextView)findViewById(R.id.textViewMult)).getText())));
        outState.putString(CONTACT_STRING, (String.valueOf(((TextView)findViewById(R.id.textViewContact)).getText())));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        ((TextView) findViewById(R.id.textViewContact)).setText(savedInstanceState.getString(CONTACT_STRING));
        ((TextView) findViewById(R.id.textViewMult)).setText(savedInstanceState.getString(VALOR_MULTIPLICADO));
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if(requestCode==GET_CONTACT){
            TextView tv = (TextView) findViewById(R.id.textViewContact);
            if(resultCode==RESULT_OK){
                String[] projection = {ContactsContract.Contacts.DISPLAY_NAME};
                Cursor c = getContentResolver().query(data.getData(), projection, null,null,null);
                c.moveToNext();
                String dat = data.getData().toString()+"\n";
                dat = dat +" + "+ c.getString(0);
                tv.setText(dat);
            } else{
                tv.setText(getString(R.string.Cancelado));
            }
        }
        if (requestCode == GET_MULTIPLICATION){
            TextView tv_multiplicacion = findViewById(R.id.textViewMult);
            if (resultCode==RESULT_OK){
                tv_multiplicacion.setText(Integer.toString(data.getIntExtra(Multiplication.RESULTADO,0)));
            } else {
                tv_multiplicacion.setText(R.string.Cancelado);
            }
        }
    }
}
