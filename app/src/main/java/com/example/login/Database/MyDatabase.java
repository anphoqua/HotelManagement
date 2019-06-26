package com.example.login.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabase extends SQLiteOpenHelper {
    public MyDatabase (Context context){
        super(context, "QLKhachSan", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String strSql = "CREATE TABLE QLTK(Id integer primary key autoincrement,"
                + "TenNv text, Ma text, ChucVu text, TenTk text, SoCmnd integer, DiaChi text, Sdt int, Email text)";
        db.execSQL(strSql);
        strSql = "INSERT INTO QLTK(TenNv, Ma, ChucVu, TenTk, SoCmnd, DiaChi, Sdt, Email) VALUES ('Trần Văn Tài', 'PT8213', 'Lễ tân','mnt12', '1231321', 'Tanbinh', '09823832', 'td@gamil.com')";
        db.execSQL(strSql);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE QLTK");
        onCreate(db);
    }
}
