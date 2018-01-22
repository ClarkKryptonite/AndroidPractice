package com.lijiankun24.androidpractice.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Debug;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lijiankun24.androidpractice.R;
import com.lijiankun24.androidpractice.aidl.IBookManager;
import com.lijiankun24.androidpractice.multiprocess.RemoteService;
import com.lijiankun24.androidpractice.multiprocess.UserManager;
import com.lijiankun24.androidpractice.util.L;
import com.lijiankun24.androidpractice.util.storage.CustomStorageManager;
import com.lijiankun24.androidpractice.util.storage.MyStorageVolume;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public IBinder.DeathRecipient mRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {

        }
    };

    private Messenger mMessenger = null;

    private TextView mTextView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        L.i("UserManager user's amount is " + UserManager.sUserAmount);
        UserManager.sUserAmount = 100;
        L.i("UserManager user's amount is " + UserManager.sUserAmount);
        mTextView = findViewById(R.id.tv_start_service);
        mTextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        findViewById(R.id.tv_start_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                traceViewTest();
            }
        });

        ViewGroup viewGroup;
        LinearLayout layout;
    }

    private void traceViewTest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                L.i("UserManager user's amount is ");
                Debug.startMethodTracing("shixintrace");
                try {
                    Thread.sleep(10 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Debug.stopMethodTracing();
                L.i("UserManager user's amount is ");
            }
        }).start();
    }

    private void remoteServiceTest() {
        Intent intent = new Intent(MainActivity.this, RemoteService.class);
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mMessenger = new Messenger(service);
                try {
                    mMessenger.send(Message.obtain());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, BIND_AUTO_CREATE);
    }

    private void storageTest() {
        CustomStorageManager.getInstance().storagePath();
        List<MyStorageVolume> volumeList = CustomStorageManager.getInstance().getStorage();
        for (MyStorageVolume volume : volumeList) {
            L.i(volume.toString());
        }
    }

    private void aidlTest() {
        IBookManager iBookManager = null;
        IBinder iBinder = null;
        try {
            iBinder.linkToDeath(mRecipient, 0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
