package com.example.grego.movilecompleto;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

public class MyIntentService extends IntentService {
    public static final String RESPONSE_ACTION = "Respuesta a la iteracion";
    public static final String RESPONSE = "Response";
    public static final String TAG = MyIntentService.class.getCanonicalName();
    public static final int ME = 1;

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            int iteration = intent.getIntExtra(Entrega4.ITERATION, 0);
            String msg = "(IS) Processing Iteration " + iteration;
            Intent response = new Intent(RESPONSE_ACTION);
            response.putExtra(RESPONSE, msg);
            response.putExtra(Entrega4.WHO, ME);
            Log.d(TAG, msg);

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            LocalBroadcastManager.getInstance(this).sendBroadcast(response);
        }
    }
}
