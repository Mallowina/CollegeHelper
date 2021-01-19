package com.example.collegehelper.ui.schedule;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.collegehelper.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.collegehelper.WorkWithData.firstUpperCase;

public class ScheduleTeacherFragment extends Fragment {
    private View root;

    /*Строковые данные для добавления в бд*/

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_schedule_teacher, container, false);

        String today, next_day;

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        today = firstUpperCase(sdf.format(d));

        switch (today) {
            case "Понедельник": next_day="Вторник"; break;
            case "Вторник":next_day="Среда"; break;
            case "Среда":next_day="Четверг"; break;
            case "Четверг":next_day="Пятница"; break;
            case "Пятница":next_day="Понедельник"; break;
            default: {
                today = "Понедельник";
                next_day="Вторник";
                break;
            }
        }

        TextView addTod = root.findViewById(R.id.addTod);
        addTod.setText(today);
        TextView addTom = root.findViewById(R.id.addTom);
        addTom.setText(next_day);

        Button main_schedule = root.findViewById(R.id.raspp);
        main_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout linearLayout = (LinearLayout) root.findViewById(R.id.layoutt);
                LinearLayout linearLayout1 = (LinearLayout) root.findViewById(R.id.layoutt1);
                linearLayout.setVisibility(View.VISIBLE);
                linearLayout1.setVisibility(View.GONE);
            }
        });

        Button change_schedule = root.findViewById(R.id.ismenn);
        change_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout linearLayout = (LinearLayout) root.findViewById(R.id.layoutt);
                LinearLayout linearLayout1 = (LinearLayout) root.findViewById(R.id.layoutt1);
                linearLayout1.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.GONE);
            }
        });

        Button createMainSchedule = root.findViewById(R.id.btnCreateMainSchedule);
        createMainSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSchedule();  //Функция получения данных
            }
        });

        return root;
    }

    private void getSchedule() {
        //Добавление в строки данных
    }
}