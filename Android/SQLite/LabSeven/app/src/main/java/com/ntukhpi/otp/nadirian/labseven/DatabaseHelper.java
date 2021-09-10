package com.ntukhpi.otp.nadirian.labseven;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static  int DATABASE_VERSION=13;
    public static final String DATABASE_NAME="userstore.db";
    public  static  String TABLE = "users"; // название таблицы в бд

    public static  String KEY_ID ="_id";
    public static  String KEY_NAME ="name";
    public static  String KEY_SURNAME ="surname";
    public static  String KEY_PATRONYM ="patronym";
    public static  String KEY_AGE ="age";
    public static  String KEY_CITY ="city";

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

             /*   db.execSQL("CREATE TABLE " + TABLE + " ("+KEY_ID
                + " integer primary key, " +KEY_NAME + " text, "+KEY_SURNAME
                + " text, " + KEY_PATRONYM + " text, " + KEY_AGE + " text, " + KEY_CITY +" text" +")" );
*/

        db.execSQL("CREATE TABLE " + TABLE + " (" + KEY_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME
                + " TEXT, "   +KEY_SURNAME + " TEXT, " +KEY_PATRONYM +" TEXT, "+KEY_AGE+" INTEGER, " + KEY_CITY + " TEXT)");


              db.execSQL("INSERT INTO "+TABLE + " (" +KEY_NAME + ", "+KEY_SURNAME +", "+KEY_PATRONYM
                        + ", "+KEY_AGE +", "+KEY_CITY
                        +") VALUES ('Tom','Smith','Jonhs', '38','Kiev');");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE);
        onCreate(db);
    }
}
