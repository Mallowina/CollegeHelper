package com.example.collegehelper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.collegehelper.WorkWithData.COURSE_NAME;
import static com.example.collegehelper.WorkWithData.EMAIL;
import static com.example.collegehelper.WorkWithData.GROUP_NAME;
import static com.example.collegehelper.WorkWithData.NAME;
import static com.example.collegehelper.WorkWithData.SECOND_NAME;
import static com.example.collegehelper.WorkWithData.SURNAME;
import static com.example.collegehelper.WorkWithData.UserType;
import static com.example.collegehelper.WorkWithData.mDb;
import static com.example.collegehelper.WorkWithData.ID;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class Authorisation extends AppCompatActivity {
    private String Login, Password;
    private String CurrentLogin=""; // название
    private String CurrentPassword="";

    private EditText editLogin;
    private EditText editPass;

    SharedPreferences sPref;
    public static final String keyLogin = "login";
    public static final String keyPassword = "password";
    public static final String myPrefs = "myprefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autoriz);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        WorkWithData.ConnectToDB(this);

        editLogin = (EditText) findViewById(R.id.editLogin);
        editPass = (EditText) findViewById(R.id.editPassword2);

        // при старте проверяем есть ли в файлах настроек
        // данные по ключу nameKey
        sPref = getSharedPreferences(myPrefs, Context.MODE_PRIVATE);
        if (sPref.contains(keyLogin) && sPref.contains(keyPassword)) {
            // если есть, то ставим значение этого ключа в EditText
            Login = sPref.getString(keyLogin, "");
            Password = sPref.getString(keyPassword, "");
            try {
                Password = WorkWithData.byteArrayToHexString(WorkWithData.computeHash(Password));
            } catch (Exception e) {
                e.printStackTrace();
            }
            boolean trLogin = isEqualsLogin(), trPassword = isEqualsPassword();
            if (trLogin && trPassword) {
                WorkWithData.getUser();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        }
    }

    void saveText() {
        Password = editPass.getText().toString();

        // получаем доступ к файлу
        SharedPreferences.Editor editor = sPref.edit();
        // сохраняем по текст из EditText по ключу nameKey
        editor.putString(keyLogin, Login);
        editor.putString(keyPassword, Password);
        editor.apply();
    }

//    void getText() {
//        sPref = getSharedPreferences(myPrefs, Context.MODE_PRIVATE);
//        if (sPref.contains(keyLogin) && sPref.contains(keyPassword)) {
//            Login = sPref.getString(keyLogin, "");
//            Password = sPref.getString(keyPassword, "");
//        }
//    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        loadText();
//    }

    public void openreg(View view) {
        // действия, совершаемые после нажатия на кнопку
        // Создаем объект Intent для вызова новой Activity
        Intent intent = new Intent(Authorisation.this, Registration.class);
        // запуск activity
        startActivity(intent);
    }

    public void Authorisation (View view) throws Exception {
        int openActivity = 0;

        /**ПОЛУЧЕНИЕ ДАННЫХ В ПЕРЕМЕННЫЕ*/

        Login = editLogin.getText().toString().trim();
        Password = editPass.getText().toString().trim();



        /**ПРОВЕРКА ПУСТЫХ СТРОК*/
        if (Login.isEmpty() || Password.isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    "Поля не должны быть пустыми.",
                    Toast.LENGTH_SHORT).show();
            openActivity++;
        } else {
            openActivity = 0;

            /**ПРОВЕРКА НА НАЛИЧИЕ ПРОБЕЛА В ПОЛЯХ*/
            if (WorkWithData.checkSpace(Login)
                    || WorkWithData.checkSpace(Password)) {
                Toast.makeText(getApplicationContext(),
                        "Поля не должны содержать пробелов.",
                        Toast.LENGTH_SHORT).show();
                openActivity++;
            } else {
                openActivity = 0;

                Password = WorkWithData.byteArrayToHexString(WorkWithData.computeHash(Password));

                boolean trLogin = isEqualsLogin(), trPassword = isEqualsPassword();

                if (trLogin) {
                    if (trPassword) openActivity=0;
                    else {
                        Toast.makeText(getApplicationContext(),
                                "Неверный пароль",
                                Toast.LENGTH_SHORT).show();
                        openActivity++;
                    }

                } else {
                    Toast.makeText(getApplicationContext(),
                            "Неверный логин",
                            Toast.LENGTH_SHORT).show();
                    openActivity++;
                }

            }
        }


        if (openActivity == 0) {
            WorkWithData.getUser();

            saveText();

            editLogin.setText("");
            editPass.setText("");
            
            // Создаем объект Intent для вызова новой Activity
            Intent intent = new Intent(this, MainActivity.class);
            // запуск activity
            startActivity(intent);
        }

    }


    /*ФУНКЦИЯ ПРОВЕРКИ ЛОГИНА И ПАРОЛЯ*/
    private boolean isEqualsLogin() {
        Cursor cursor = mDb.rawQuery("SELECT * FROM users_info", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ID = cursor.getString(0);
            CurrentLogin = cursor.getString(1);
            CurrentPassword = cursor.getString(2);
            UserType = cursor.getString(3);

            if (CurrentLogin.equals(Login)) return true;
            else {
                cursor.moveToNext();
            }
        }
        cursor.close();
        return false;
    }
    private boolean isEqualsPassword() {
        Cursor cursor = mDb.rawQuery("SELECT * FROM users_info", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ID = cursor.getString(0);
            CurrentLogin = cursor.getString(1);
            CurrentPassword = cursor.getString(2);
            UserType = cursor.getString(3);

            if (CurrentLogin.equals(Login)) {
               if (CurrentPassword.equals(Password)) return true;
               else {
                   cursor.moveToNext();
               }
            } else cursor.moveToNext();
        }
        cursor.close();
        return false;
    }
}