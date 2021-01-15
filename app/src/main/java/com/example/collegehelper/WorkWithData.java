package com.example.collegehelper;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.io.IOException;

public class WorkWithData {
    /**/
    public static String UserType="";


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
}
