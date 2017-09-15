package com.lijiankun24.androidpractice.util.storage;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.support.v4.content.ContextCompat;

import com.lijiankun24.androidpractice.util.L;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.STORAGE_SERVICE;

/**
 * CustomStorageManager.java
 * <p>
 * Created by lijiankun on 17/9/14.
 */

public class CustomStorageManager {

    private static CustomStorageManager INSTANCE = null;

    private Context mContext = null;

    private CustomStorageManager() {
    }

    public static CustomStorageManager getInstance() {
        if (INSTANCE == null) {
            synchronized (CustomStorageManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CustomStorageManager();
                }
            }
        }
        return INSTANCE;
    }

    public void init(Context context) {
        mContext = context.getApplicationContext();
    }

    public List<MyStorageVolume> getStorage() {
        List<MyStorageVolume> volumeList = new ArrayList<>(3);
        StorageManager storageManager = (StorageManager) mContext.getSystemService(Context.STORAGE_SERVICE);
        try {
            Class<?>[] paramClasses = {};
            Method method = StorageManager.class.getMethod("getVolumeList", paramClasses);
            Object[] params = {};
            Object[] invokes = (Object[]) method.invoke(storageManager, params);
            if (invokes != null) {
                for (Object object : invokes) {
                    volumeList.add(new MyStorageVolume(object));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return volumeList;
    }

    /**
     * 获取Volume挂载状态, 例如Environment.MEDIA_MOUNTED
     *
     * @param context 上下文
     * @param path    目录路径
     * @return 挂载状态
     */
    public static String getVolumeState(Context context, String path) {
        //mountPoint是挂载点名Storage'paths[1]:/mnt/extSdCard不是/mnt/extSdCard/
        //不同手机外接存储卡名字不一样。/mnt/sdcard
        StorageManager mStorageManager = (StorageManager) context
                .getSystemService(STORAGE_SERVICE);
        String status = null;
        try {
            Method mMethodGetPathsState = mStorageManager.getClass().
                    getMethod("getVolumeState", String.class);
            status = (String) mMethodGetPathsState.invoke(mStorageManager, path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    /**
     * 获取目录可用空间大小
     *
     * @param path 获取目录
     * @return 存储目录可用空间大小
     */
    public static long getAvailableSize(String path) {
        try {
            StatFs sf = new StatFs(path);
            long blockSize = sf.getBlockSize();
            long availableCount = sf.getAvailableBlocks();
            return availableCount * blockSize;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取目录总存储空间
     *
     * @param path 存储目录
     * @return 总存储空间大小
     */
    public static long getTotalSize(String path) {
        try {
            StatFs sf = new StatFs(path);
            long blockSize = sf.getBlockSize();
            long totalCount = sf.getBlockCount();
            return totalCount * blockSize;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getSizeStr(long fileLength) {
        String strSize;
        try {
            if (fileLength >= 1024 * 1024 * 1024) {
                strSize = (float) Math.round(10 * fileLength / (1024 * 1024 * 1024)) / 10 + " GB";
            } else if (fileLength >= 1024 * 1024) {
                strSize = (float) Math.round(10 * fileLength / (1024 * 1024 * 1.0)) / 10 + " MB";
            } else if (fileLength >= 1024) {
                strSize = (float) Math.round(10 * fileLength / (1024)) / 10 + " KB";
            } else if (fileLength >= 0) {
                strSize = fileLength + " B";
            } else {
                strSize = "0 B";
            }
        } catch (Exception e) {
            e.printStackTrace();
            strSize = "0 B";
        }
        return strSize;
    }

    @TargetApi(24)
    public void storagePath() {
        String path = mContext.getFilesDir().getAbsolutePath();
        String path1 = mContext.getCacheDir().getAbsolutePath();
        String path2 = ContextCompat.getDataDir(mContext).getAbsolutePath();
        L.i("path is " + path);
        L.i("path1 is " + path1);
        L.i("path2 is " + path2);

        String extalPath = Environment.getExternalStorageState();
        String extalPath1 = mContext.getExternalCacheDir().getAbsolutePath();
        String extalPath2 = Environment.getExternalStorageDirectory().getAbsolutePath();
        String extalPath3 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getAbsolutePath();
        L.i("extal state is " + extalPath);
        L.i("extalPath1 is " + extalPath1);
        L.i("extalPath2 is " + extalPath2);
        L.i("extalPath3 is " + extalPath3);

        String dataPath = Environment.getDataDirectory().getAbsolutePath();
        String dataPath1 = Environment.getDownloadCacheDirectory().getAbsolutePath();
        String dataPath2 = Environment.getRootDirectory().getAbsolutePath();
        L.i("dataPath is " + dataPath);
        L.i("dataPath1 is " + dataPath1);
        L.i("dataPath2 is " + dataPath2);
    }
}
