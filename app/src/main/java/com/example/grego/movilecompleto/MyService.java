package com.example.grego.movilecompleto;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import static com.example.grego.movilecompleto.MyIntentService.RESPONSE;
import static com.example.grego.movilecompleto.MyIntentService.RESPONSE_ACTION;

public class MyService extends Service {
    public static final int ME = 2;
    public static final String TAG = MyService.class.getCanonicalName();

    private Looper mServiceLooper;
    private ServiceHandler mServiceHandler;

    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            Intent intent = (Intent) msg.obj;
            int iteration = intent.getIntExtra(Entrega4.ITERATION, 0);
            String msgs = "(S) Processing Iteration " + iteration;
            Intent response = new Intent(RESPONSE_ACTION);
            response.putExtra(RESPONSE, msgs);
            response.putExtra(Entrega4.WHO, ME);
            Log.d(TAG, msgs);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LocalBroadcastManager.getInstance(MyService.this).sendBroadcast(response);
            stopSelf(msg.arg1);
        }
    }

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        HandlerThread thread = new HandlerThread("ServiceStartArguments", Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();
        mServiceLooper = thread.getLooper();
        mServiceHandler = new ServiceHandler(mServiceLooper);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();
        Message msg = mServiceHandler.obtainMessage();
        msg.arg1 = startId;
        msg.obj = intent;
        //deberiamos crear un looper y un handler para el procesamiento del mensaje, sino estamos creando un thread
        // nuevo por cada mensaje y es muy ineficiente.
        new Thread(() -> mServiceHandler.sendMessage(msg)).start();
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
    }
}
