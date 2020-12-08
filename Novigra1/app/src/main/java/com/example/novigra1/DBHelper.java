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
        super(context, "newProvince.db",null, 1 );
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        //will create a table that has 2 columns (email and password)
        MyDB.execSQL("create Table CustumerAccount(username TEXT primary key,email TEXT,password TEXT)");
        MyDB.execSQL("create Table EmployeeAccount(username TEXT primary key,email TEXT,password TEXT, nomSuccursale TEXT, horaire TEXT, adresse TEXT, type TEXT)");




        MyDB.execSQL("create Table adminAccount(username TEXT primary key,email TEXT,password TEXT)");
        MyDB.execSQL("insert into adminAccount values('Admin','Admin1234@gmail.com','admin1234')");



        MyDB.execSQL("create Table serviceList(service TEXT primary key, type TEXT)");
        MyDB.execSQL("create Table document(doc TEXT primary key, ch TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop Table if exists CustumerAccount");
        MyDB.execSQL("drop Table if exists EmployeeAccount");
        MyDB.execSQL("drop Table if exists adminAccount");
        MyDB.execSQL("drop Table if exists serviceList");
        MyDB.execSQL("drop Table if exists document");





    }
    //inserer les donnees dans la base de donnee
    public Boolean insertData(String email, String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("email",email);


        contentValues.put("password",password);

        long result = MyDB.insert("CustumerAccount",null, contentValues);

        if(result==-1) return false;
        else
            return true;

    }

    public Boolean insertData_Employee(String username,String email,String password, String nom, String horaire, String adresse, String type){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("email",email);
        contentValues.put("password",password);

        contentValues.put("nomSuccursale",nom);
        contentValues.put("horaire",horaire);
        contentValues.put("adresse",adresse);
        contentValues.put("type",type);

        long result = MyDB.insert("EmployeeAccount",null, contentValues);

        if(result==-1) return false;
        else
            return true;

    }
    public Boolean insertdoc(String doc, String ch){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("doc",doc);
        contentValues.put("ch",ch);
        long result = MyDB.insert("document",null, contentValues);

        if(result==-1)
            return false;
        else
            return true;

    }
    public boolean insertData_Services(String service, String type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("service",service);
        contentValues.put("type", type);
        long result = db.insert("serviceList",null,contentValues);
        if(result == -1){
            return  false;
        }else{
            return  true;
        }




    }
    public  Cursor viewData (){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        String query = "Select * from serviceList";

        Cursor cursor = null;
        if(MyDB!=null){
            cursor = MyDB.rawQuery(query,null);
        }
        return cursor;
    }
    public  Cursor viewdoc (){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        String query = "Select * from document";

        Cursor cursor = null;
        if(MyDB!=null){
            cursor = MyDB.rawQuery(query,null);
        }
        return cursor;
    }
    public Boolean deleteClient(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from CustumerAccount where username = ?", new String[] {username});

        if(cursor.getCount()>0) {
            long result = MyDB.delete("CustumerAccount","username=?", new String[] {username});
            if (result == -1) {
                return false;
            }
            else {
                return true;
            }
        }else{
            return false;
        }


    }
    public Boolean deleteEmployee(String employee){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from EmployeeAccount where username = ?", new String[] {employee});

        if(cursor.getCount()>0) {
            long result = MyDB.delete("EmployeeAccount","username=?", new String[] {employee});
            if (result == -1) {
                return false;
            }
            else {
                return true;
            }
        }else{
            return false;
        }


    }



    public void deleteData(String service){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("Delete from serviceList  where service = ?",new String[] {service});

    }




    public boolean checkEmail(String email){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from CustumerAccount where email = ?", new String[] {email});

        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public boolean checkEmailEmployee(String email){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from EmployeeAccount where email = ?", new String[] {email});

        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public boolean checkEmilPassword(String email, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from CustumerAccount where email = ? and password = ?", new String[] {email,password});

        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public Boolean checkEmailPasswordEmployee(String email, String pass) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from EmployeeAccount where email = ? and password = ?", new String[] {email,pass});

        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public boolean checkAdmin(String email, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from adminAccount where email = ? and password = ?", new String[] {email,password});

        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Cursor getListContent(){
        String query = "Select * from CustumerAccount";
        SQLiteDatabase MyDB = this.getReadableDatabase();


        Cursor cursor = null;
        if(MyDB!=null){
            cursor = MyDB.rawQuery(query,null);
        }
        return cursor;

    }

    public Cursor getListContentEmployee(){
        String query = "Select * from EmployeeAccount";
        SQLiteDatabase MyDB = this.getReadableDatabase();


        Cursor cursor = null;
        if(MyDB!=null){
            cursor = MyDB.rawQuery(query,null);
        }
        return cursor;

    }


}