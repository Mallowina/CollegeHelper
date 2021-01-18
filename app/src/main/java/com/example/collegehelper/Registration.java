package com.example.collegehelper;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import static com.example.collegehelper.WorkWithData.mDBHelper;

public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();     //Убирает заголовок приложения (некрасивая cтрочка сверху)
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
    private String Name, Surname, LastName, Group, Email, Login, Password, Password2;
    public void Registration (View view) throws Exception {
        int openActivity = 0;


        /**ПОЛУЧЕНИЕ ДАННЫХ В ПЕРЕМЕННЫЕ*/
        EditText editName = (EditText) findViewById(R.id.editName);
        Name = editName.getText().toString();
        EditText editSurN = (EditText) findViewById(R.id.editSurN);
        Surname = editSurN.getText().toString();
        EditText editLastN = (EditText) findViewById(R.id.editLastN);
        LastName = editLastN.getText().toString();
        Spinner spinnerGroup = (Spinner) findViewById(R.id.spinnerGroupRegistration);
        Group = spinnerGroup.getSelectedItem().toString();
        EditText editMail = (EditText) findViewById(R.id.editEmail);
        Spinner spinnerMail=(Spinner) findViewById(R.id.spinnerMailRegistration);
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
            if (!WorkWithData.checkLetter(Name)
                    || !WorkWithData.checkLetter(Surname)
                    || !WorkWithData.checkLetter(LastName)) {
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
                if (WorkWithData.checkSpace(Name)
                        || WorkWithData.checkSpace(Surname)
                        || WorkWithData.checkSpace(LastName)
                        || WorkWithData.checkSpace(Group)
                        || WorkWithData.checkSpace(Email)
                        || WorkWithData.checkSpace(Login)
                        || WorkWithData.checkSpace(Password)
                        || WorkWithData.checkSpace(Password2)) {
                    Toast.makeText(getApplicationContext(),
                            "Поля не должны содержать пробелов.",
                            Toast.LENGTH_SHORT).show();
                    openActivity++;
                } else {
                    openActivity = 0;

                    if (WorkWithData.isExist(Login)) openActivity++;
                    else {
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
        }

        if (openActivity == 0) {
            Password = WorkWithData.byteArrayToHexString(WorkWithData.computeHash(Password));
            Email += spinnerMail.getSelectedItem().toString();
            addUser();
            // Создаем объект Intent для вызова новой Activity
            Intent intent = new Intent(this, Authorisation.class);
            // запуск activity
            startActivity(intent);
        }
    }

    /**Запись пользователя в БД*/
    public void addUser() {
        WorkWithData.ConnectToDB(this);

        boolean isInserted =   mDBHelper.insertUsersInfo(Login, Password);
        boolean isInserted2 =   mDBHelper.insertStudentInfo(Name, Surname, LastName, Group, Email);


        if((isInserted = true) && (isInserted2 = true))
            Toast.makeText(this,"Data successfully inserted.",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this,"Data not successfully inserted.",Toast.LENGTH_LONG).show();

    }
}
