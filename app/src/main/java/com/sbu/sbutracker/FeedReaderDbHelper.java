package com.sbu.sbutracker;

/**
 * Created by sumukha on 11/4/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class FeedReaderDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "GPS.db";

    //
    public static final String TABLE_NAME = "GPS_DATA";
    public static final String COLUMN_NAME_LONGITUDE = "longitude";
    public static final String COLUMN_NAME_LATTITUDE = "lattitude";
    public static final String COLUMN_NAME_TIMESTAMP = "timestamp";
    //

    public FeedReaderDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    //Creating
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE="CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( " + COLUMN_NAME_LONGITUDE +
                " text not null, " +  COLUMN_NAME_LATTITUDE + " text not null, " + COLUMN_NAME_TIMESTAMP + " text not null);";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insert(DataTable record){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_NAME_LONGITUDE,record.getLongitude());
        values.put(COLUMN_NAME_LATTITUDE,record.getLattitude());
        values.put(COLUMN_NAME_TIMESTAMP,record.getTimestamp());

        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public List<DataTable> getAllrecords(){
        List<DataTable> dl=new ArrayList<DataTable>();
        String SELECT_QUERY="SELECT * FROM "+ TABLE_NAME;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor = db.rawQuery(SELECT_QUERY,null);

        if(cursor.moveToFirst()){
            do{
                DataTable temp=new DataTable();
                temp.setLongitude(cursor.getDouble(0));
                temp.setLattitude(cursor.getDouble(1));
                dl.add(temp);
            }while (cursor.moveToNext());
        }
        db.close();
        return dl;
    }

    public DataTable getLatestRecord(){
        SQLiteDatabase db=this.getReadableDatabase();
        String SELECT_QUERY="SELECT * FROM "+ TABLE_NAME + " order by " + COLUMN_NAME_TIMESTAMP + " desc";
        Cursor cursor = db.rawQuery(SELECT_QUERY,null);
        DataTable data = null;
        if(cursor.moveToFirst()) {
            data = new DataTable();
            data.setLongitude(cursor.getDouble(0));
            data.setLattitude(cursor.getDouble(1));
            data.setTimestamp(cursor.getLong(2));
        }
        db.close();
        return data;
    }

    /*public DataTable getrecord(int id){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor =db.query(TABLE_NAME, new String[] {COLUMN_NAME_LONGITUDE,COLUMN_NAME_LATTITUDE},)
    }*
    Its a composite key
    */
}
