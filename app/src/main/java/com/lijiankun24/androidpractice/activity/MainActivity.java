package com.lijiankun24.androidpractice.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lijiankun24.androidpractice.R;
import com.lijiankun24.androidpractice.util.L;
import com.lijiankun24.androidpractice.util.storage.CustomStorageManager;
import com.lijiankun24.androidpractice.util.storage.MyStorageVolume;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        storageTest();
    }

    private void storageTest() {
        CustomStorageManager.getInstance().storagePath();
        List<MyStorageVolume> volumeList = CustomStorageManager.getInstance().getStorage();
        for (MyStorageVolume volume : volumeList) {
            L.i(volume.toString());
        }
    }
}
