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
        super(context, "Novigradd.db",null, 1 );
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        //will create a table that has 2 columns (email and password)




        MyDB.execSQL("create Table user(username TEXT primary key,email TEXT,password TEXT)");
        MyDB.execSQL("create Table employee(username TEXT primary key,email TEXT,password TEXT, nomSuccursale TEXT, horaire TEXT, adresse TEXT, type TEXT)");

        MyDB.execSQL("insert into user values('Admin1234@gmail.com','admin1234')");
        MyDB.execSQL("insert into user values('RachelMp','rachellove@gmail,com','rachel12345')");
        MyDB.execSQL("insert into user values('MayssaTb','Mayssacrazy@gmail.com','mayssa12345')");
        MyDB.execSQL("insert into user values('Amine','Amine44@gmail.com','amine12345')");

        MyDB.execSQL("create Table admin(username TEXT primary key,email TEXT,password TEXT)");
        MyDB.execSQL("insert into admin values('Admin','Admin1234@gmail.com','admin1234')");



        MyDB.execSQL("create Table serviceList(service TEXT primary key, type TEXT)");
        MyDB.execSQL("create Table document(doc TEXT primary key, ch TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop Table if exists user");
        MyDB.execSQL("drop Table if exists serviceList");
        MyDB.execSQL("drop Table if exists employee");
        MyDB.execSQL("drop Table if exists document");
        MyDB.execSQL("drop Table if exists admin");




    }
    //inserer les donnees dans la base de donnee
    public Boolean insertData(String email, String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("email",email);
        contentValues.put("password",password);

        long result = MyDB.insert("user",null, contentValues);

        if(result==-1) return false;
        else
            return true;

    }
    public Boolean deleteClient(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from user where username = ?", new String[] {username});

        if(cursor.getCount()>0) {
            long result = MyDB.delete("user","username=?", new String[] {username});
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
    public  Cursor viewdoc (){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        String query = "Select * from document";

        Cursor cursor = null;
        if(MyDB!=null){
            cursor = MyDB.rawQuery(query,null);
        }
        return cursor;
    }
    public Boolean insertData_Employee(String email,String username,String password, String nom, String horaire, String type,String adresse){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("username",username);
        contentValues.put("email",email);
        contentValues.put("password",password);
        contentValues.put("nomSuccursale",password);
        contentValues.put("horaire",password);
        contentValues.put("adresse",password);
        contentValues.put("type",password);

        long result = MyDB.insert("employee",null, contentValues);

        if(result==-1) return false;
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
    public void deleteData(String service){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("Delete from serviceList  where service = ?",new String[] {service});

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
    public boolean checkAdmin(String email, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from admin where email = ? and password = ?", new String[] {email,password});

        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Cursor getListContent(){
        String query = "Select * from user";
        SQLiteDatabase MyDB = this.getReadableDatabase();


        Cursor cursor = null;
        if(MyDB!=null){
            cursor = MyDB.rawQuery(query,null);
        }
        return cursor;

    }
}