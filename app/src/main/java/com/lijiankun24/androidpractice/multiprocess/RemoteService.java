package com.lijiankun24.androidpractice.multiprocess;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;

import com.lijiankun24.androidpractice.util.L;

public class RemoteService extends Service {

    private Messenger mMessenger = new Messenger(new MessagerHandler());

    @Override
    public void onCreate() {
        super.onCreate();
        L.i("RemoteService onCreate ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        L.i("UserManager user's amount is " + UserManager.sUserAmount);
        L.i("RemoteService onStartCommand ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }

    private static class MessagerHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }
}
