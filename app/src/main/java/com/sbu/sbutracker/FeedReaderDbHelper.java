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
import java.util.Calendar;
import java.util.List;

public class FeedReaderDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 5;
    public static final String DATABASE_NAME = "GPS.db";

    //
    public static final String TABLE_NAME = "GPS_DATA";
    public static final String COLUMN_NAME_LONGITUDE = "longitude";
    public static final String COLUMN_NAME_LATITUDE = "latitude";
    public static final String COLUMN_NAME_TIMESTAMP = "timestamp";
    //

    //
    public static final String NOTIFICATION_TABLE_NAME = "NOTIFICATION_DATA";
    public static final String COLUMN_NAME_HEADING = "heading";
    public static final String COLUMN_NAME_TEXT= "text";
    public static final String COLUMN_NAME_TIME = "time";
    public static final String COLUMN_NAME_SEEN = "seen";
    //

    public FeedReaderDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    //Creating
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE="CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( " + COLUMN_NAME_LONGITUDE +
                " text not null, " +  COLUMN_NAME_LATITUDE + " text not null, " + COLUMN_NAME_TIMESTAMP + " text not null);";
        db.execSQL(CREATE_TABLE);
        Log.d("test", "onCreate: ");
        CREATE_TABLE="CREATE TABLE IF NOT EXISTS " + NOTIFICATION_TABLE_NAME + " ( " + COLUMN_NAME_HEADING +
                " text not null, " +  COLUMN_NAME_TEXT + " text not null, " +  COLUMN_NAME_SEEN + " text not null, " + COLUMN_NAME_TIME + " text not null);";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + NOTIFICATION_TABLE_NAME);
        onCreate(db);
    }

    public void insertDummyValues(){
        DataTable record = new DataTable();
        record.setLattitude(40.907606);
        record.setLongitude(-73.107988);
        record.setTimestamp(1512572400);
        insert(record);
        record = new DataTable();
        record.setLattitude(40.907910);
        record.setLongitude(-73.111389);
        record.setTimestamp(1512590460);
        insert(record);
        record.add(40.908595, -73.111845,1512590480);
        insert(record);
        record.add(40.910784, -73.113046, 1512590490);
        insert(record);
        record.add(40.913460, -73.113239, 1512590495);
        insert(record);
        record.add(40.914465, -73.116973, 1512590500);
        insert(record);
        record.add(40.915260, -73.118775, 1512590505);
        insert(record);
        record.add(40.911352, -73.118862, 1512590515);
        insert(record);
        record.add( 40.909731, -73.119720, 1512590520);
        insert(record);
        record.add(40.909909, -73.122939, 1512590525);
        insert(record);
        record.add(40.910704, -73.125192, 1512590530);
        insert(record);
        record.add(40.911928, -73.127852, 1512590535);
        insert(record);
        record.add(40.913193, -73.129226, 1512590540);
        insert(record);
        record.add(40.914296, -73.124762, 1512590545);
        insert(record);
        record.add(40.914296, -73.124762, 1512590575);
        insert(record);
    }

    public void insert(DataTable record){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_NAME_LONGITUDE,record.getLongitude());
        values.put(COLUMN_NAME_LATITUDE,record.getLattitude());
        values.put(COLUMN_NAME_TIMESTAMP,record.getTimestamp());

        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public void insertNotification(NotificationClass record){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_NAME_HEADING,record.getNotificationHeading());
        values.put(COLUMN_NAME_TEXT,record.getNotificationText());
        values.put(COLUMN_NAME_TIME,record.getNotificationTime());
        values.put(COLUMN_NAME_SEEN,record.getNotificationSeen());

        db.insert(NOTIFICATION_TABLE_NAME,null,values);
        db.close();
    }

    public void setNotificationAsSeen(NotificationClass record){
        String SELECT_QUERY="Update "+ NOTIFICATION_TABLE_NAME + " set " + COLUMN_NAME_SEEN + " = true where " + COLUMN_NAME_TIME + " = " + record.getNotificationTime();
        SQLiteDatabase db=this.getWritableDatabase();
        db.rawQuery(SELECT_QUERY,null);
        db.close();
    }

    public List<NotificationClass>getAllUnseenNotification(){
        List<NotificationClass> dl=new ArrayList<>();
        String SELECT_QUERY="SELECT * FROM "+ NOTIFICATION_TABLE_NAME + " where seen = '0' order by " + COLUMN_NAME_TIME + " desc";
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor = db.rawQuery(SELECT_QUERY,null);
        NotificationClass temp = new NotificationClass();
        temp.setNotificationHeading("Missed Activity");
        temp.setNotificationText("Javtis Center at 12:57");
        temp.setNotificationSeen(false);
        temp.setNotificationTime(1512737820000L);
        dl.add(temp);
        if(cursor.moveToFirst()){
            do{
                temp = new NotificationClass();
                temp.setNotificationHeading(cursor.getString(0));
                temp.setNotificationText(cursor.getString(1));
                temp.setNotificationSeen(cursor.getInt(2)>0);
                temp.setNotificationTime(cursor.getLong(3));
                dl.add(temp);
            }while (cursor.moveToNext());
        }
        db.close();
        temp = new NotificationClass();
        temp.setNotificationHeading("Missed Activity");
        temp.setNotificationText("NCS Building at 10:03");
        temp.setNotificationSeen(false);
        temp.setNotificationTime(1512640810000L);
        dl.add(temp);
        temp = new NotificationClass();
        temp.setNotificationHeading("Missed Activity");
        temp.setNotificationText("Aldi's at 11:23");
        temp.setNotificationSeen(false);
        temp.setNotificationTime(1512640810000L);
        dl.add(temp);
        temp = new NotificationClass();
        temp.setNotificationHeading("Inactive alert!!");
        temp.setNotificationText("Time for quick walk? You have not moved for a while");
        temp.setNotificationSeen(false);
        temp.setNotificationTime(1512640810000L);
        dl.add(temp);
        temp = new NotificationClass();
        temp.setNotificationHeading("Missed Activity");
        temp.setNotificationText("Aldi's at 11:23");
        temp.setNotificationSeen(false);
        temp.setNotificationTime(1512640810000L);
        dl.add(temp);
        temp = new NotificationClass();
        temp.setNotificationHeading("Missed Activity");
        temp.setNotificationText("Chapin Commons at 17:04");
        temp.setNotificationSeen(false);
        temp.setNotificationTime(1512640810000L);
        temp = new NotificationClass();
        temp.setNotificationHeading("Inactive alert!!");
        temp.setNotificationText("Time for quick walk? You have not moved for a while");
        temp.setNotificationSeen(false);
        temp.setNotificationTime(1512640810000L);
        dl.add(temp);
        dl.add(temp);
        return dl;
    }

    public List<NotificationClass>getAllNotification(){
        List<NotificationClass> dl=new ArrayList<>();
        String SELECT_QUERY="SELECT * FROM "+ NOTIFICATION_TABLE_NAME + " where " + COLUMN_NAME_TIME + " desc";
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor = db.rawQuery(SELECT_QUERY,null);

        if(cursor.moveToFirst()){
            do{
                NotificationClass temp=new NotificationClass();
                temp.setNotificationHeading(cursor.getString(0));
                temp.setNotificationText(cursor.getString(1));
                temp.setNotificationSeen(cursor.getInt(2)>0);
                temp.setNotificationTime(cursor.getLong(3));
                dl.add(temp);
            }while (cursor.moveToNext());
        }
        db.close();
        return dl;
    }

    public Boolean isUnseenNotificationExists(){
        return getAllUnseenNotification().size()>0;
    }

    public List<DataTable> getAllrecords(){
        List<DataTable> dl=new ArrayList<DataTable>();
        String SELECT_QUERY="SELECT * FROM "+ TABLE_NAME + " order by " + COLUMN_NAME_TIMESTAMP + " desc";
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor = db.rawQuery(SELECT_QUERY,null);

        if(cursor.moveToFirst()){
            do{
                DataTable temp=new DataTable();
                temp.setLongitude(cursor.getDouble(0));
                temp.setLattitude(cursor.getDouble(1));
                temp.setTimestamp(cursor.getLong(2));
                dl.add(temp);
            }while (cursor.moveToNext());
        }
        db.close();
        return dl;
    }

    public List<DataTable> getSevenDaysRecords(){
        List<DataTable> dl=new ArrayList<DataTable>();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -7);
        int year  = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int date  = cal.get(Calendar.DATE);
        cal.clear();
        cal.set(year, month, date-7);
        long t1 = cal.getTimeInMillis();
        cal = Calendar.getInstance();
        year  = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        date  = cal.get(Calendar.DATE);
        cal.clear();
        cal.set(year, month, date);
        long midnight = cal.getTimeInMillis();
        String SELECT_QUERY="SELECT * FROM "+ TABLE_NAME + " where timestamp > "+ t1 +" and timestamp < "+ midnight + " order by " + COLUMN_NAME_TIMESTAMP + " desc";
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor = db.rawQuery(SELECT_QUERY,null);

        if(cursor.moveToFirst()){
            do{
                DataTable temp=new DataTable();
                temp.setLongitude(cursor.getDouble(0));
                temp.setLattitude(cursor.getDouble(1));
                temp.setTimestamp(cursor.getLong(2));
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

    public List<DataTable> getTodayRecord() {
        List<DataTable> dataList=new ArrayList<DataTable>();
        SQLiteDatabase db=this.getReadableDatabase();
        Calendar cal = Calendar.getInstance();
        int year  = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int date  = cal.get(Calendar.DATE);
        cal.clear();
        cal.set(year, month, date);
        long midnight = cal.getTimeInMillis();
        String SELECT_QUERY="SELECT * FROM "+ TABLE_NAME + " where timestamp > "+ midnight +" order by " + COLUMN_NAME_TIMESTAMP + " desc";
        Cursor cursor = db.rawQuery(SELECT_QUERY,null);
        if(cursor.moveToFirst()) {
            do {
                DataTable data = new DataTable();
                data.setLongitude(cursor.getDouble(0));
                data.setLattitude(cursor.getDouble(1));
                data.setTimestamp(cursor.getLong(2));
                dataList.add(data);
            }while (cursor.moveToNext());
        }
        db.close();
        return dataList;
    }

    /*public DataTable getrecord(int id){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor =db.query(TABLE_NAME, new String[] {COLUMN_NAME_LONGITUDE,COLUMN_NAME_LATTITUDE},)
    }*
    Its a composite key
    */
}
