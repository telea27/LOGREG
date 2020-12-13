package com.example.logreg;

import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String ADATBAZISNEV="users";
    public static final String TABLANEV="felhasznalo";
    public static final String id="id";
    public static final String email="email";
    public static final String felhnev="felhnev";
    public static final String jelszo="jelszo";
    public static final String teljesnev="teljesnev";
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE IF NOT EXISTS felhasznalo(id INTEGER PRIMARY KEY,email VARCHAR,felhnev VARCHAR,jelszo VARCHAR,teljesnev VARCHAR);";
     private static final String SQL_DELETE_ENTRIES= "DROP TABLE IF EXISTS "+TABLANEV+";";
    public DatabaseHelper(@Nullable Context context) {
        super(context, ADATBAZISNEV, null, 1);
//       SQLiteDatabase db = this.getWritableDatabase();
//        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void insertItem(String iemail,String iteljesnev,String ijelszo,String ifelhnev) {
//        String query = "INSERT INTO felhasznalo VALUES (email,felhnev,jelszo,teljesnev)";
//        SQLiteDatabase db = getWritableDatabase();
//        db.execSQL(query);
//        db.close();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues ertekek = new ContentValues();

        ertekek.put(email,iemail);
        ertekek.put(felhnev, ifelhnev);
        ertekek.put(jelszo, ijelszo);
        ertekek.put(teljesnev, iteljesnev);
        db.insert(TABLANEV, null, ertekek);
        db.close();

       // return .insert(felhasznalo, null, initialValues);
    }
    public boolean checkUser(String iemail, String ifelhnev){
        SQLiteDatabase db = this.getReadableDatabase();
        String query="select email, felhnev from felhasznalo where email like'"+iemail+"' or felhnev like'"+ifelhnev+"';";
        db.execSQL(query);
        db.close();
        return true;
    }
    public String getTeljesNev(String ifelhnev){
        SQLiteDatabase db = this.getReadableDatabase();
        String query="select teljesnev from felhasznalo where felhnev="+ifelhnev+"";
        Cursor cursor = db.rawQuery(query,null);
        String iteljesnev="";
        if (cursor.moveToFirst())
        {
            do
            {
                iteljesnev = cursor.getString(cursor.getColumnIndex("id"));

            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return iteljesnev;
    }
    public boolean checkLogin(String ifelhnev,String ijelszo){
        boolean l=false;
        SQLiteDatabase db = this.getReadableDatabase();
        String query="select * from felhasznalo where felhnev like '"+ifelhnev+"' and jelszo like '"+ijelszo+"';";
        Cursor cursor = db.rawQuery(query,null);
        String inev="";

        cursor.close();
        db.close();
        int cursorCount = cursor.getCount();
        if(cursorCount > 0) {
            l=true;
        }
        else{
            l=false;
        }
        return l;

    }
}
