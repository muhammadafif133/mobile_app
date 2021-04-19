package com.example.fitnessandworkout.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper {
    public Context context;
    public static final String DATABASE_NAME = "FitnessWorkout.db";
    public static final int DATABASE_VERSION = 1;

    // Creating table Image
    public static final String IMAGE_TABLE = "image";
    public static final String IMG_ID = "id";
    public static final String IMG_URL = "ImageComplex";

    // Creating table Login
    public static final String USER_TABLE = "users";
    public static final String USER_EMAIL = "email";
    public static final String USER_PW = "password";

    // Creating table Activity
    public static final String ACT_TABLE = "activity";
    public static final String ACT_ID = "id";
    public static final String ACT_DATE = "date";
    public static final String ACT_LEVEL = "level";
    public static final String ACT_ACTIVITY = "activity";

    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    // Login query creation
    public static final String CREATE_TABLE_USER = "CREATE TABLE " + USER_TABLE + "(" + USER_EMAIL +
            " PRIMARY KEY," + USER_PW + " TEXT" + ")";
    public static final String DROP_TABLE_USER = "DROP TABLE IF EXISTS " + USER_TABLE + "";

    // Image query creation
    public static final String CREATE_TABLE_IMG = "CREATE TABLE " + IMAGE_TABLE + "(" + IMG_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT," + IMG_URL + " TEXT" + ")";
    public static final String DROP_TABLE_IMAGE = "DROP TABLE IF EXISTS " + IMAGE_TABLE + "";

    // Activity query creation
    public static final String CREATE_TABLE_ACT = "CREATE TABLE " + ACT_TABLE + "(" + ACT_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT," + ACT_DATE + " TEXT" + ACT_LEVEL + "TEXT" + ACT_ACTIVITY + "TEXT" + ")";
    public static final String DROP_TABLE_ACT = "DROP TABLE IF EXISTS " + ACT_TABLE + "";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_IMG);
        db.execSQL(CREATE_TABLE_ACT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_USER);
        db.execSQL(DROP_TABLE_IMAGE);
        db.execSQL(DROP_TABLE_ACT);
        onCreate(db);
    }

    public void deleteEntry(long row) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(IMAGE_TABLE, IMG_ID + "=" + row, null);
        sqLiteDatabase.delete(USER_TABLE, USER_EMAIL + "=" + row, null);
        sqLiteDatabase.delete(ACT_TABLE, ACT_ID + "=" + row, null);
    }

    /**
     * Insert user credentials for sign up
     * @param email
     * @param password
     * @return
     */
    public Boolean insertData (String email, String password ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_EMAIL, email);
        contentValues.put(USER_PW, password);
        long result = db.insert(USER_TABLE, null, contentValues);
        if(result == -1) return false;
        else
            return true;
    }

    /**
     * Verify email is exist in the database to log in
     * @param email
     * @return
     */
    public Boolean checkEmail(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where email = ?", new String[] {email});
        //Cursor cursor = db.rawQuery(CHECK_EMAIL, new String[] {email});
        if(cursor.getCount() > 0) //user is exist
            return true;
        else
            return false;
    }

    /**
     * Verify email and password are matched to log in
     * @param email
     * @param password
     * @return
     */
    public Boolean checkEmailPassword(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where email = ? and password = ?", new String[] {email, password});
        //Cursor cursor = db.rawQuery(CHECK_EMAIL_PW, new String[] {email, password});

        if(cursor.getCount() > 0) //email and password are exist
            return true;
        else
            return false;
    }

    public void addActivity(ActivityModel activityModel){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHandler.ACT_DATE, activityModel.getDate());
        contentValues.put(DataBaseHandler.ACT_LEVEL, activityModel.getLevel());
        contentValues.put(DataBaseHandler.ACT_ACTIVITY, activityModel.getActivity());
        contentValues.put(DataBaseHandler.ACT_DATE, activityModel.getDate());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(DataBaseHandler.ACT_TABLE, null,contentValues);
    }

    public List<ActivityModel> getActivityList(){
        String sql = "select * from " + ACT_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        List<ActivityModel> storeActivity = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String date = cursor.getString(1);
                String level = cursor.getString(2);
                String activity = cursor.getString(3);
                storeActivity.add(new ActivityModel(id,date,level,activity));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeActivity;
    }
}
