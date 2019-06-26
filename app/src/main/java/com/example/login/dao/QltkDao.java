package com.example.login.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.login.Database.MyDatabase;
import com.example.login.model.Qltk;

import java.util.ArrayList;

public class QltkDao {
    MyDatabase mydb;
    public QltkDao(Context context){
        mydb = new MyDatabase(context);
    }

    public void insertQltk(Qltk qltk){
        SQLiteDatabase db = mydb.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("tenNv", qltk.getTenNv());
        cv.put("ma", qltk.getMa());
        cv.put("chucVu", qltk.getChucVu());

        db.insert("QLTK", null, cv);

        db.close();
    }

    public ArrayList<Qltk> getAllQltk(){
        ArrayList<Qltk> list = new ArrayList<>();
        SQLiteDatabase db = mydb.getReadableDatabase();
        String strSql = "SELECT * FROM QLTK";
        Cursor c = db.rawQuery(strSql, null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            int id = c.getInt(0);
            String tenNv = c.getString(1);
            String ma = c.getString(2);
            String chucVu = c.getString(3);
            list.add(new Qltk(id, tenNv, ma, chucVu));
            c.moveToNext();
        }
        return list;
    }

    public int updateQltk(Qltk qltk){
        SQLiteDatabase db = mydb.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("tenNv", qltk.getTenNv());
        cv.put("ma", qltk.getMa());
        cv.put("chucVu", qltk.getChucVu());

        return db.update("QLTK", cv,  "Id"+ " = ?", new String[]{String.valueOf(qltk.getId())});
    }
    public void deleteQltk(Qltk qltk){
        SQLiteDatabase db = mydb.getWritableDatabase();
        db.delete("QLTK", "Id" + "=? ", new String[]{String.valueOf(qltk.getId())});
        db.close();
    }
}
