package com.example.collegehelper;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();     //Убирает заголовок приложения (некрасивая чтрочка сверху)
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
    }

    public void OpenAuthorization (View view) {
        // действия, совершаемые после нажатия на кнопку
        // Создаем объект Intent для вызова новой Activity
        Intent intent = new Intent(this, Authorisation.class);
        // запуск activity
        startActivity(intent);
    }

    public void Registration (View view) {
        int openActivity = 0;
        String Name, Surname, LastName, Group, Email, Login, Password, Password2;

        /**ПОЛУЧЕНИЕ ДАННЫХ В ПЕРЕМЕННЫЕ*/
        EditText editName = (EditText) findViewById(R.id.editName);
        Name = editName.getText().toString();
        EditText editSurN = (EditText) findViewById(R.id.editSurN);
        Surname = editSurN.getText().toString();
        EditText editLastN = (EditText) findViewById(R.id.editLastN);
        LastName = editLastN.getText().toString();
        Spinner spinnerGroup = (Spinner) findViewById(R.id.spinnerGroup);
        Group = spinnerGroup.getSelectedItem().toString();
        EditText editMail = (EditText) findViewById(R.id.editEmail);
        Email = editMail.getText().toString();
        EditText editLogin = (EditText) findViewById(R.id.editLogin);
        Login = editLogin.getText().toString();
        EditText editPass = (EditText) findViewById(R.id.editPassword);
        Password = editPass.getText().toString();
        EditText editPass2 = (EditText) findViewById(R.id.editPassword2);
        Password2 = editPass2.getText().toString();

        /**ПРОВЕРКА ПУСТЫХ СТРОК*/
        if (Name.isEmpty()
        || Surname.isEmpty()
        || LastName.isEmpty()
        || Group.isEmpty()
        || Email.isEmpty()
        || Login.isEmpty()
        || Password.isEmpty()
        || Password2.isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    "Поля не должны быть пустыми.",
                    Toast.LENGTH_SHORT).show();
            openActivity++;
        } else {
            openActivity = 0;

            /**ПРОВЕРЯЕМ, ЧТОБЫ В ФИО БЫЛИ РУССКИЕ БУКВЫ*/
            if (!Check.checkLetter(Name)
                    || !Check.checkLetter(Surname)
                    || !Check.checkLetter(LastName)) {
                Toast.makeText(getApplicationContext(),
                        "Поля ФИО должны содержать русские буквы.",
                        Toast.LENGTH_SHORT).show();
                openActivity++;
            } else openActivity = 0;

            /**ПРОВЕРКА ПОЛЯ ПОЧТЫ НА ПРАВИЛЬНО ВВВЕДННОЕ ЗНАЧЕНИЕ*/
            int i = 0;
            if (i == 0) {
                for (char letter : Email.toCharArray()) {
                    if (!Character.isLetter(letter)) {
                        openActivity++;
                        i++;
                    }
                }
            }
            if (i > 0) Toast.makeText(getApplicationContext(),
                    "Поле E-mail не должно содержать спец.символов",
                    Toast.LENGTH_SHORT).show();

            if (openActivity == 0) {
                /**ПРОВЕРКА НА НАЛИЧИЕ ПРОБЕЛА В ПОЛЯХ*/
                if (Check.checkSpace(Name)
                        || Check.checkSpace(Surname)
                        || Check.checkSpace(LastName)
                        || Check.checkSpace(Group)
                        || Check.checkSpace(Email)
                        || Check.checkSpace(Login)
                        || Check.checkSpace(Password)
                        || Check.checkSpace(Password2)) {
                    Toast.makeText(getApplicationContext(),
                            "Поля не должны содержать пробелов.",
                            Toast.LENGTH_SHORT).show();
                    openActivity++;
                } else {
                    openActivity = 0;

                    /**ПРОВЕРКА НА СОВПАДЕНИЕ ПАРОЛЕЙ*/
                    if (Password.equals(Password2)) openActivity = 0;
                    else {
                        openActivity++;
                        Toast.makeText(getApplicationContext(),
                                "Пароли должны совпадать",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }

        if (openActivity == 0) {
            // Создаем объект Intent для вызова новой Activity
            Intent intent = new Intent(this, Authorisation.class);
            // запуск activity
            startActivity(intent);
        }
    }
}
