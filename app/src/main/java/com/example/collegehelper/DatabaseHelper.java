package com.example.collegehelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "collegeHelper.db";
    private static String DB_PATH = "";
    private static final int DB_VERSION = 16;

    private SQLiteDatabase mDataBase;
    private final Context mContext;
    private boolean mNeedUpdate = false;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        if (android.os.Build.VERSION.SDK_INT >= 17)
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        else
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        this.mContext = context;

        copyDataBase();

        this.getReadableDatabase();
    }

    public void updateDataBase() throws IOException {
        if (mNeedUpdate) {
            File dbFile = new File(DB_PATH + DB_NAME);
            if (dbFile.exists())
                dbFile.delete();

            copyDataBase();

            mNeedUpdate = false;
        }
    }

    private boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DB_NAME);
        return dbFile.exists();
    }

    private void copyDataBase() {
        if (!checkDataBase()) {
            this.getReadableDatabase();
            this.close();
            try {
                copyDBFile();
            } catch (IOException mIOException) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    private void copyDBFile() throws IOException {
        InputStream mInput = mContext.getAssets().open(DB_NAME);
        //InputStream mInput = mContext.getResources().openRawResource(R.raw.info);
        OutputStream mOutput = new FileOutputStream(DB_PATH + DB_NAME);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0)
            mOutput.write(mBuffer, 0, mLength);
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    public boolean openDataBase() throws SQLException {
        mDataBase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        return mDataBase != null;
    }

    @Override
    public synchronized void close() {
        if (mDataBase != null)
            mDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
            mNeedUpdate = true;
    }




    public boolean insertUsersInfo(String Login, String Password)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "select * from users_info";
        Cursor cursor = db.rawQuery(query, null);
        int last_id = cursor.getCount();
// Создайте новую строку со значениями для вставки.
        ContentValues registrationValues = new ContentValues();

// Задайте значения для каждой строки.
        registrationValues.put("id", last_id);
        registrationValues.put("login", Login);
        registrationValues.put("password", Password);
        registrationValues.put("user_type", "1");


        //SEE WHETHER THE DATA INSERT INTO DB OR NOT
        //IF RETURN -1, DATA NOT SUCCESSFUL INSERTED
        long result = db.insert("users_info", null, registrationValues);
        if (result == -1)
            return false;
        else
            return true;
    }



    public boolean insertStudentInfo(String Name, String Surname, String LastName, String Group, String Email) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "select * from users_info";
        Cursor cursor = db.rawQuery(query, null);
        int last_id = cursor.getCount();

// Создайте новую строку со значениями для вставки.
        ContentValues infoPeopleValues = new ContentValues();

// Задайте значения для каждой строки.
        infoPeopleValues.put("id", last_id);
        infoPeopleValues.put("name", Name);
        infoPeopleValues.put("surname", Surname);
        infoPeopleValues.put("second_name", LastName);
        infoPeopleValues.put("group_name", Group);
        infoPeopleValues.put("email", Email);


        //SEE WHETHER THE DATA INSERT INTO DB OR NOT
        //IF RETURN -1, DATA NOT SUCCESSFUL INSERTED
        long result = db.insert("student_info", null, infoPeopleValues);
        if (result == -1)
            return false;
        else
            return true;
    }
}
