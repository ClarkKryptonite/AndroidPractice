package com.lijiankun24.androidpractice.util.storage;

import android.content.Context;
import android.os.Environment;

import java.lang.reflect.Method;

/**
 * MyStorageVolume.java
 * <p>
 * Created by lijiankun on 17/9/14.
 */

public class MyStorageVolume {

    private int mStorageId;
    private String mPath;
    private boolean mPrimary;
    private boolean mRemovable;
    private boolean mEmulated;
    private long mMtpReserveSpace;
    private boolean mAllowMassStorage;
    private long mMaxFileSize;
    private String mState;

    public MyStorageVolume(Object reflectItem) {
        try {
            Method fmStorageId = reflectItem.getClass().getDeclaredMethod("getStorageId");
            fmStorageId.setAccessible(true);
            mStorageId = (Integer) fmStorageId.invoke(reflectItem);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Method fmPath = reflectItem.getClass().getDeclaredMethod("getPath");
            fmPath.setAccessible(true);
            mPath = (String) fmPath.invoke(reflectItem);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Method fmPrimary = reflectItem.getClass().getDeclaredMethod("isPrimary");
            fmPrimary.setAccessible(true);
            mPrimary = (Boolean) fmPrimary.invoke(reflectItem);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Method fisRemovable = reflectItem.getClass().getDeclaredMethod("isRemovable");
            fisRemovable.setAccessible(true);
            mRemovable = (Boolean) fisRemovable.invoke(reflectItem);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Method fisEmulated = reflectItem.getClass().getDeclaredMethod("isEmulated");
            fisEmulated.setAccessible(true);
            mEmulated = (Boolean) fisEmulated.invoke(reflectItem);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Method fmMtpReserveSpace = reflectItem.getClass().getDeclaredMethod("getMtpReserveSpace");
            fmMtpReserveSpace.setAccessible(true);
            mMtpReserveSpace = (Long) fmMtpReserveSpace.invoke(reflectItem);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Method fAllowMassStorage = reflectItem.getClass().getDeclaredMethod("allowMassStorage");
            fAllowMassStorage.setAccessible(true);
            mAllowMassStorage = (Boolean) fAllowMassStorage.invoke(reflectItem);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Method fMaxFileSize = reflectItem.getClass().getDeclaredMethod("getMaxFileSize");
            fMaxFileSize.setAccessible(true);
            mMaxFileSize = (Long) fMaxFileSize.invoke(reflectItem);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Method fState = reflectItem.getClass().getDeclaredMethod("getState");
            fState.setAccessible(true);
            mState = (String) fState.invoke(reflectItem);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取 Volume 挂载状态, 例如 Environment.MEDIA_MOUNTED
     *
     * @param context 上下文
     * @return 获取 Volume 挂载状态
     */
    public String getVolumeState(Context context) {
        return CustomStorageManager.getVolumeState(context, mPath);
    }

    /**
     * 获取当前存储设备是否是处于挂起状态
     *
     * @param context 上下文
     * @return true 表示处于挂起，即可用；false 表示处于非挂起，即不可用
     */
    public boolean isMounted(Context context) {
        return getVolumeState(context).equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取存储设备的唯一标识
     *
     * @return 存储设备的唯一表示 Id
     */
    public String getUniqueFlag() {
        return "" + mStorageId;
    }

    /**
     * 获取目录可用空间大小
     *
     * @return 获取当前空间可用大小
     */
    public long getAvailableSize() {
        return CustomStorageManager.getAvailableSize(mPath);
    }

    /**
     * 获取目录总存储空间
     *
     * @return 获取空间总可用大小
     */
    public long getTotalSize() {
        return CustomStorageManager.getTotalSize(mPath);
    }

    @Override
    public String toString() {
        return "MyStorageVolume{" +
                "\nmStorageId=" + mStorageId +
                "\n, mPath='" + mPath + '\'' +
                "\n, mPrimary=" + mPrimary +
                "\n, mRemovable=" + mRemovable +
                "\n, mEmulated=" + mEmulated +
                "\n, mMtpReserveSpace=" + mMtpReserveSpace +
                "\n, mAllowMassStorage=" + mAllowMassStorage +
                "\n, mMaxFileSize=" + mMaxFileSize +
                "\n, mState='" + mState + '\'' +
                "\n, getTotalSize='" + CustomStorageManager.getSizeStr(getTotalSize()) + '\'' +
                "\n, getAvailableSize='" + CustomStorageManager.getSizeStr(getAvailableSize()) + '\'' +
                '}' + "\n";
    }
}
