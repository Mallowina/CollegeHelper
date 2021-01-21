package com.example.collegehelper.ui;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.collegehelper.Authorisation;
import com.example.collegehelper.R;
import com.example.collegehelper.WorkWithData;
import com.example.collegehelper.ui.schedule.ScheduleActionFragment;
import com.example.collegehelper.ui.schedule.ScheduleTeacherFragment;

import static com.example.collegehelper.WorkWithData.UserType;
import static com.example.collegehelper.WorkWithData.getLastID;
import static com.example.collegehelper.WorkWithData.mDb;

public class RegistrationFragment extends Fragment {

    private String Name, Surname, LastName, Email, Login, Password, Password2;
    private View root;
    private int i = 1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_registration, container, false);

        Button btnReg = root.findViewById(R.id.btnreg);
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int openActivity = 0;

                /**ПОЛУЧЕНИЕ ДАННЫХ В ПЕРЕМЕННЫЕ*/
                EditText editName = (EditText) root.findViewById(R.id.editname);
                Name = editName.getText().toString().trim();
                EditText editSurN = (EditText) root.findViewById(R.id.editsurn);
                Surname = editSurN.getText().toString().trim();
                EditText editLastN = (EditText) root.findViewById(R.id.editlastn);
                LastName = editLastN.getText().toString().trim();
                EditText editMail = (EditText) root.findViewById(R.id.editmail);
                Spinner spinnerMail = (Spinner) root.findViewById(R.id.spinnerMailRegistration);
                Email = editMail.getText().toString().trim();
                EditText editLogin = (EditText) root.findViewById(R.id.editlogin);
                Login = editLogin.getText().toString().trim();
                EditText editPass = (EditText) root.findViewById(R.id.editpassword);
                Password = editPass.getText().toString().trim();
                EditText editPass2 = (EditText) root.findViewById(R.id.editpassword2);
                Password2 = editPass2.getText().toString().trim();

                /**ПРОВЕРКА ПУСТЫХ СТРОК*/
                if (Name.isEmpty()
                        || Surname.isEmpty()
                        || LastName.isEmpty()
                        || Email.isEmpty()
                        || Login.isEmpty()
                        || Password.isEmpty()
                        || Password2.isEmpty()) {
                    Toast.makeText(root.getContext(),
                            "Поля не должны быть пустыми.",
                            Toast.LENGTH_SHORT).show();
                    openActivity++;
                } else {
                    openActivity = 0;

                    /**ПРОВЕРЯЕМ, ЧТОБЫ В ФИО БЫЛИ РУССКИЕ БУКВЫ*/
                    if (!WorkWithData.checkLetter(Name)
                            || !WorkWithData.checkLetter(Surname)
                            || !WorkWithData.checkLetter(LastName)) {
                        Toast.makeText(root.getContext(),
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
                    if (i > 0) Toast.makeText(root.getContext(),
                            "Поле E-mail не должно содержать спец.символов",
                            Toast.LENGTH_SHORT).show();

                    if (openActivity == 0) {
                        /**ПРОВЕРКА НА НАЛИЧИЕ ПРОБЕЛА В ПОЛЯХ*/
                        if (WorkWithData.checkSpace(Name)
                                || WorkWithData.checkSpace(Surname)
                                || WorkWithData.checkSpace(LastName)
                                || WorkWithData.checkSpace(Email)
                                || WorkWithData.checkSpace(Login)
                                || WorkWithData.checkSpace(Password)
                                || WorkWithData.checkSpace(Password2)) {
                            Toast.makeText(root.getContext(),
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
                                    Toast.makeText(root.getContext(),
                                            "Пароли должны совпадать",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }

                    if (openActivity == 0) {
                        try {
                            Password = WorkWithData.byteArrayToHexString(WorkWithData.computeHash(Password));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Email += spinnerMail.getSelectedItem().toString();
                        addUser();
                    }
                }

                editName.setText("");
                editLastN.setText("");
                editSurN.setText("");
                editMail.setText("");
                editLogin.setText("");
                editPass.setText("");
                editPass2.setText("");
            }
        });

        return root;
    }

    /**Запись пользователя в БД*/
    public void addUser() {
        WorkWithData.ConnectToDB(root.getContext());

        mDb.beginTransaction();


        int last_id = getLastID("users_info");

// Создайте новую строку со значениями для вставки.
        ContentValues registrationValues = new ContentValues();
        ContentValues infoPeopleValues = new ContentValues();
// Задайте значения для каждой строки.
        registrationValues.put("id", last_id);
        registrationValues.put("login", Login);
        registrationValues.put("password", Password);
        registrationValues.put("user_type", 2);

        infoPeopleValues.put("id", last_id);
        infoPeopleValues.put("name", Name);
        infoPeopleValues.put("surname", Surname);
        infoPeopleValues.put("second_name", LastName);
        infoPeopleValues.put("email", Email);
// Вставьте строку в вашу базу данных.
        long newRowId = mDb.insert("users_info", null, registrationValues);
        long newRowId2 =mDb.insert("prepods_info", null, infoPeopleValues);

        mDb.setTransactionSuccessful();
        mDb.endTransaction();
        mDb.close();

        if (newRowId == -1 || newRowId2 == -1) {
            // Если ID  -1, значит произошла ошибка
            Toast.makeText(root.getContext(), "Ошибка при добавлении преподавателя", Toast.LENGTH_SHORT).show();
        } else Toast.makeText(root.getContext(), "Преподаватель успешно добавлен", Toast.LENGTH_SHORT).show();
    }
}
