package com.lijiankun24.androidpractice.activity;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;

import com.lijiankun24.androidpractice.R;
import com.lijiankun24.androidpractice.aidl.IBookManager;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        storageTest();
        aidlTest();
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
