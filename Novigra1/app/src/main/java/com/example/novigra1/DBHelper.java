package com.example.novigra1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    //database name



    public DBHelper(Context context) {
        // create the database
        super(context, "Login.db",null, 1 );
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        //will create a table that has 2 columns (email and password)
        MyDB.execSQL("create Table user(email TEXT primary key, password TEXT)");
        MyDB.execSQL("insert into user values('Admin1234@gmail.com','admin1234')");
        String createTable = "Create Table userServices(service TEXT primary key ,  documents TEXT)";
        MyDB.execSQL(createTable);


    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop Table if exists user");
        MyDB.execSQL("drop table if exists userServices");
        onCreate(MyDB);
    }
    //inserer les donnees dans la base de donnee
    public Boolean insertData(String email, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",email);
        contentValues.put("password",password);

        long result = MyDB.insert("user",null, contentValues);

        if(result==-1) return false;
        else
            return true;


    }
    public boolean insertData_Services(String service, String documents){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("service",service);
        contentValues.put("documents", documents);
        long result = db.insert("userServices",null,contentValues);
        if(result == -1){
            return  false;
        }else{
            return  true;
        }




    }
    public  Cursor viewData (){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM userServices";
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }

    public boolean checkEmail(String email){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from user where email = ?", new String[] {email});

        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public boolean checkEmilPassword(String email, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from user where email = ? and password = ?", new String[] {email,password});

        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}