package com.example.collegehelper.ui.home;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.collegehelper.R;

public class Head extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_header_main);

        /**Имя и фамилия в голове*/
        String Name = "sda";
        TextView textView = findViewById(R.id.viewFIO);
        // задаём текст
        textView.setText(Name);

}}
