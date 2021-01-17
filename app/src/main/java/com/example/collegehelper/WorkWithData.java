package com.example.collegehelper;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.io.IOException;

public class WorkWithData {
    /**/
    public static String UserType;
    public static String ID;
    public static String NAME;           //Имя вошедшего
    public static String SURNAME;        //Фамилия вошедшего
    public static String SECOND_NAME;        //Отчество вошедшего
    public static String GROUP_NAME;
    public static String COURSE_NAME;
    public static String EMAIL;


    public static boolean checkLetter (String word) {
        if (word.matches("^[a-zA-Z0-9]*$")) {
            return false;
        } else return true;
    }

    public static boolean checkSpace (String word) {
        if (word.contains(" ")) return true;
        else return false;
    }

    public static byte[] computeHash(String x)
            throws Exception {
        java.security.MessageDigest d = null;
        d = java.security.MessageDigest.getInstance("SHA-1");
        d.reset();
        d.update(x.getBytes());
        return d.digest();
    }

    public static String byteArrayToHexString(byte[] b) {
        StringBuffer sb = new StringBuffer(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            int v = b[i] & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase();
    }

    public static SQLiteDatabase mDb;

    public static void ConnectToDB(Context context) {
        //Переменная для работы с БД
        DatabaseHelper mDBHelper;

        mDBHelper = new DatabaseHelper(context);

        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        try {
            mDb = mDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }
    }

    public static void getUser() {
        switch (UserType) {
            case "1": {
                Cursor cursor = mDb.rawQuery("SELECT * FROM student_info", null);
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    if (ID.equals(cursor.getString(0))) {
                        NAME = cursor.getString(1);
                        SURNAME = cursor.getString(2);
                        SECOND_NAME = cursor.getString(3);
                        GROUP_NAME = cursor.getString(4);
                        EMAIL = cursor.getString(5);
                        COURSE_NAME = GROUP_NAME.substring(0,1);
                        return;
                    } else cursor.moveToNext();
                }
                cursor.close();
                break;
            }
            case "2": {
                Cursor cursor = mDb.rawQuery("SELECT * FROM prepods_info", null);
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    if (ID.equals(cursor.getString(0))) {
                        NAME = cursor.getString(1);
                        SURNAME = cursor.getString(2);
                        SECOND_NAME = cursor.getString(3);
                        EMAIL = cursor.getString(4);
                        return;
                    } else cursor.moveToNext();
                }
                cursor.close();
                break;
            }
        }
    }

    public static int getLastID() {

        Cursor cursor = mDb.rawQuery("SELECT * FROM users_info", null);
        cursor.moveToLast();
        int last_id=Integer.parseInt(cursor.getString(0))+1;
        cursor.close();
        return last_id;
    }

    public static String getCourseName() {
        return COURSE_NAME;
    }
    public static String getGroupName() {
        return GROUP_NAME;
    }

    public static String firstUpperCase(String word){
        if(word == null || word.isEmpty()) return "";//или return word;
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}
