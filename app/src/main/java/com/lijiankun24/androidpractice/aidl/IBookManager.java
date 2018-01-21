package com.lijiankun24.androidpractice.aidl;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

import java.util.List;

/**
 * IBookManager.java
 * <p>
 * Created by lijiankun on 18/1/21.
 */

public interface IBookManager extends IInterface {

    public static final String DESCRIPTOR = "com.lijiankun24.androidpractice.aidl.IBookManager";

    static final int TRANSACTION_getBookList = IBinder.FIRST_CALL_TRANSACTION + 0;

    static final int TRANSACTION_addBook = IBinder.FIRST_CALL_TRANSACTION + 1;

    public List<Book> getBookList() throws RemoteException;

    public void addBook(Book book) throws RemoteException;
}
