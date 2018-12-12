package com.example.grego.movilecompleto;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import java.util.Random;

public class MessengerService extends Service {

    public MessengerService() {
    }
    private static final String TAG = MessengerService.class.getCanonicalName();
    private final Random nGenerator = new Random();
    public static final int ME=3;
    static final int NEXT_RANDOM = 1;

    class IncomingHangler extends Handler{
        public static final String RESPONSE = "Response";
        public static final String RESPONSE_ACTION = "Respuesta de la iteracion";

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case NEXT_RANDOM:
                    String text = "AppOriginal: Numero Generado "+ nGenerator.nextInt(100);
                    Log.d(TAG,text);
                    Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
                    Intent response = new Intent(RESPONSE_ACTION);
                    response.putExtra(RESPONSE,text);
                    response.putExtra(MainActivity.WHO,ME);
                    LocalBroadcastManager.getInstance(MessengerService.this).sendBroadcast(response);
                    stopSelf(msg.arg1);
                    break;
                default:
                    Toast.makeText(getApplicationContext(),"default",Toast.LENGTH_SHORT).show();
                    super.handleMessage(msg);
            }
        }
    }

    final Messenger mMessenger = new Messenger(new IncomingHangler());


    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(getApplicationContext(),getString(R.string.toastConectada),Toast.LENGTH_SHORT).show();
        return mMessenger.getBinder();
    }

}
