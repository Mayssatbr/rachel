package com.example.novigra1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DatabaseHelper_Services extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "mylist.db";
    private static final String TABLE_NAME = "User_Table";
    private static final String COL2 = "NAME";
    private static final String COL1 = "ID";


    public DatabaseHelper_Services(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "(" + COL1 +"ID INTEGER primary key AUTOINCREMENT, " +
                COL2+ " TEXT)";

        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public boolean insertData(String name, String id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("id",id);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result == -1){
            return  false;
        }else{
            return  true;
        }




    }
    public  Cursor viewData (){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }
    public boolean deleteData(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from User_Table where name = ?",new String[]{name});
        if (cursor.getCount() > 0) {
            long result = db.delete("userServices", "name = ?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }
}

