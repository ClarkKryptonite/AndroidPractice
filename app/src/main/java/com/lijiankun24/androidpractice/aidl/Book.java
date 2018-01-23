package com.lijiankun24.androidpractice.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Book.java
 * <p>
 * Created by lijiankun on 18/1/21.
 */

public class Book implements Parcelable {

    private int id;

    private String name;

    public Book(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private Book(Parcel parcel) {
        id = parcel.readInt();
        name = parcel.readString();
    }

    public String getName() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
    }

    public static final Parcelable.Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
