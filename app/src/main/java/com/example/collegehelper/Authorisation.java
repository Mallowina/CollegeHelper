package com.example.collegehelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Authorisation extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autoriz);
    }


    public void openreg(View view) {
        // действия, совершаемые после нажатия на кнопку
        // Создаем объект Intent для вызова новой Activity
        Intent intent = new Intent(Authorisation.this, Registration.class);
        // запуск activity
        startActivity(intent);
    }

    public void Authorisation (View view) {
        int openActivity = 0;
        String Login, Password;

        /**ПОЛУЧЕНИЕ ДАННЫХ В ПЕРЕМЕННЫЕ*/
        EditText editLogin = (EditText) findViewById(R.id.editLogin);
        Login = editLogin.getText().toString();
        EditText editPass = (EditText) findViewById(R.id.editPassword2);
        Password = editPass.getText().toString();



        /**ПРОВЕРКА ПУСТЫХ СТРОК*/
        if (Login.isEmpty() || Password.isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    "Поля не должны быть пустыми.",
                    Toast.LENGTH_SHORT).show();
            openActivity++;
        } else {
            openActivity = 0;

                /**ПРОВЕРКА НА НАЛИЧИЕ ПРОБЕЛА В ПОЛЯХ*/
                if (Check.checkSpace(Login)
                        || Check.checkSpace(Password)) {
                    Toast.makeText(getApplicationContext(),
                            "Поля не должны содержать пробелов.",
                            Toast.LENGTH_SHORT).show();
                    openActivity++;
                } else {
                    openActivity = 0;
                }
            }


        if (openActivity == 0) {
            // Создаем объект Intent для вызова новой Activity
            Intent intent = new Intent(this, MainActivity.class);
            // запуск activity
            startActivity(intent);
        }
    }
}
