package com.example.collegehelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Authorisation extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autoriz);

    }

    // Метод обработки нажатия на кнопку
    public void openmainactivity(View view) {
        // действия, совершаемые после нажатия на кнопку
        // Создаем объект Intent для вызова новой Activity
        Intent intent = new Intent(Authorisation.this, MainActivity.class);
        // запуск activity
        startActivity(intent);
    }

    public void openreg(View view) {
        // действия, совершаемые после нажатия на кнопку
        // Создаем объект Intent для вызова новой Activity
        Intent intent = new Intent(Authorisation.this, Registration.class);
        // запуск activity
        startActivity(intent);
    }
}
