package com.example.collegehelper;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.collegehelper.WorkWithData;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.collegehelper.WorkWithData.getLastID;
import static com.example.collegehelper.WorkWithData.mDb;

public class Registration extends AppCompatActivity {
    private String Name, Surname, LastName, Group, Email, Login, Password, Password2;

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
        Spinner spinnerMail = (Spinner) findViewById(R.id.spinnerMailRegistration);
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
    }

    /**Запись пользователя в БД*/
    public void addUser() {
        WorkWithData.ConnectToDB(this);

        mDb.beginTransaction();

        int last_id = getLastID("users_info");

// Создайте новую строку со значениями для вставки.
        ContentValues registrationValues = new ContentValues();
        ContentValues infoPeopleValues = new ContentValues();
// Задайте значения для каждой строки.
        registrationValues.put("id", last_id);
        registrationValues.put("login", Login);
        registrationValues.put("password", Password);
        registrationValues.put("user_type", 1);

        infoPeopleValues.put("id", last_id);
        infoPeopleValues.put("name", Name);
        infoPeopleValues.put("surname", Surname);
        infoPeopleValues.put("second_name", LastName);
        infoPeopleValues.put("group_name", Group);
        infoPeopleValues.put("email", Email);
// Вставьте строку в вашу базу данных.
        long newRowId = mDb.insert("users_info", null, registrationValues);
        long newRowId2 =mDb.insert("student_info", null, infoPeopleValues);

        mDb.setTransactionSuccessful();
        mDb.endTransaction();
        mDb.close();

        if (newRowId == -1) {
            // Если ID  -1, значит произошла ошибка
            Toast.makeText(this, "Ошибка при заведении гостя", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Гость заведён под номером: " + newRowId, Toast.LENGTH_SHORT).show();
        }

        if (newRowId2 == -1) {
            // Если ID  -1, значит произошла ошибка
            Toast.makeText(this, "Ошибка при заведении гостя", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Гость заведён под номером: " + newRowId, Toast.LENGTH_SHORT).show();
        }



        //   DatabaseHelper.DB_VERSION += 1;
        //   mDb.close();
    }
}
