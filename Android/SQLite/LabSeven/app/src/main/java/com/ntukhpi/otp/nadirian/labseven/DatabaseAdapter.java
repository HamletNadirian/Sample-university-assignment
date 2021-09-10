package com.ntukhpi.otp.nadirian.labseven;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAdapter {
    int age ;

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public DatabaseAdapter(Context context){
        databaseHelper=new DatabaseHelper(context.getApplicationContext());

    }

    public  DatabaseAdapter open(){
        database=databaseHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        databaseHelper.close();
    }

    private Cursor getAllEntries(){
        String[] columns=new String[]{DatabaseHelper.KEY_ID,DatabaseHelper.KEY_NAME,DatabaseHelper.KEY_SURNAME,DatabaseHelper.KEY_PATRONYM,DatabaseHelper.KEY_AGE,DatabaseHelper.KEY_CITY};
        return database.query(DatabaseHelper.TABLE,columns,null,null,null,null,"age");
    }

    public List<People> getPeople(){
        ArrayList<People> people = new ArrayList<>();
        Cursor cursor = getAllEntries();
        if(cursor.moveToFirst()){
            do{
            int id=cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_ID));
            String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_NAME));
            String surname = cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_SURNAME));
            String patronym = cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_PATRONYM));
            int age = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_AGE));
            String city = cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_CITY));
            people.add(new People(id,name,surname,patronym,age,city));
            }
            while (cursor.moveToNext());
        }
    cursor.close();
    return people;
    }
    public People getPeople(long id){
        People people = null;
        String query = String.format("SELECT * FROM %s where %s=?",DatabaseHelper.TABLE,DatabaseHelper.KEY_ID);
        Cursor cursor = database.rawQuery(query,new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()){
            String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_NAME));
            String surname = cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_SURNAME));
            String patronym = cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_PATRONYM));
            int age = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_AGE));
            String city = cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_CITY));
            people=new People(id,name,surname,patronym,age,city);
        }
        cursor.close();
        return people;
    }
    public long insert(People people){
        ContentValues cv = new ContentValues();

        cv.put(DatabaseHelper.KEY_NAME,people.getName());
        cv.put(DatabaseHelper.KEY_SURNAME,people.getSurname());
        cv.put(DatabaseHelper.KEY_PATRONYM,people.getPatronym());
        cv.put(DatabaseHelper.KEY_AGE,people.getAge());
        cv.put(DatabaseHelper.KEY_CITY,people.getCity());
        return database.insert(DatabaseHelper.TABLE,null,cv);
    }
    public long delete (long userId){

        String whereClause = "_id = ?";
        String[] whereArgs = new String[]{String.valueOf(userId)};
        return database.delete(DatabaseHelper.TABLE,whereClause,whereArgs);
    }

    public long update (People people){
        String whereClause = DatabaseHelper.KEY_ID+"="+String.valueOf(people.getId());
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.KEY_NAME,people.getName());
        cv.put(DatabaseHelper.KEY_SURNAME,people.getSurname());
        cv.put(DatabaseHelper.KEY_PATRONYM,people.getPatronym());
        cv.put(DatabaseHelper.KEY_AGE,people.getAge());
        cv.put(DatabaseHelper.KEY_CITY,people.getCity());
        return database.update(DatabaseHelper.TABLE,cv,whereClause,null);
    }

}
