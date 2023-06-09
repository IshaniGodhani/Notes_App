package com.example.notes_app;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context,"Notes",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query="create table Notes (id integer primary key autoincrement, title text, note text,currentdate date)";
        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void insertData(String title, String note,String date)
    {
        String query="insert into Notes (title,note,date) values('"+title+"','"+note+"','"+date+"')";
        SQLiteDatabase database;
        database=getWritableDatabase();
        database.execSQL(query);
    }
    public Cursor viewData()
    {
        String query="select * from Notes";
        SQLiteDatabase sqLiteDatabase;
        sqLiteDatabase=getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(query,null);
        return cursor;
    }
    public void deleteData(int id)
    {

        String query="delete from Notes where id='"+id+"'";
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        sqLiteDatabase.execSQL(query);

    }
    public void updateData(int id, String title, String note,String date)
    {
        String query="Update Notes set title='"+title+"',note='"+note+"',date='"+date+"' where id='"+id+"'";
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        sqLiteDatabase.execSQL(query);
    }

}
