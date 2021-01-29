package com.example.collegehelper.ui.schedule;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.collegehelper.R;
import com.example.collegehelper.WorkWithData;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.collegehelper.WorkWithData.GROUP_NAME;
import static com.example.collegehelper.WorkWithData.UserType;
import static com.example.collegehelper.WorkWithData.firstUpperCase;
import static com.example.collegehelper.WorkWithData.mDb;

public class ScheduleActionFragment extends Fragment {
    private View root;
    private String GroupName = GROUP_NAME;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_action_schedule, container, false);

        WorkWithData.ConnectToDB(this.getContext());



        Button main_schedule = root.findViewById(R.id.rasp);
        Button change_schedule = root.findViewById(R.id.ismen);
        Spinner showListGroup = root.findViewById(R.id.spinnerGroupShowSchedule);

        if (UserType.equals("1")) {
            showListGroup.setVisibility(View.GONE);
            setMainSchedule();
            setChangeSchedule();
        }

        showListGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                GroupName = showListGroup.getSelectedItem().toString();
                setMainSchedule();
                setChangeSchedule();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        main_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main_schedule.setVisibility(View.INVISIBLE);
                change_schedule.setVisibility(View.VISIBLE);
                LinearLayout linearLayout = (LinearLayout) root.findViewById(R.id.layout);
                LinearLayout linearLayout1 = (LinearLayout) root.findViewById(R.id.layout1);
                linearLayout.setVisibility(View.VISIBLE);
                linearLayout1.setVisibility(View.GONE);
            }
        });

        change_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main_schedule.setVisibility(View.VISIBLE);
                change_schedule.setVisibility(View.INVISIBLE);
                LinearLayout linearLayout = (LinearLayout) root.findViewById(R.id.layout);
                LinearLayout linearLayout1 = (LinearLayout) root.findViewById(R.id.layout1);
                linearLayout1.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.GONE);
            }
        });

        return root;
    }

    private void setMainSchedule() {
        // TextView для основного расписания
        TextView txtMon1 = root.findViewById(R.id.a1);
        TextView txtMon2 = root.findViewById(R.id.a2);
        TextView txtMon3 = root.findViewById(R.id.a3);
        TextView txtMon4 = root.findViewById(R.id.a4);
        TextView txtMon5 = root.findViewById(R.id.a5);
        TextView txtMon6 = root.findViewById(R.id.a6);

        TextView txtTue1 = root.findViewById(R.id.b1);
        TextView txtTue2 = root.findViewById(R.id.b2);
        TextView txtTue3 = root.findViewById(R.id.b3);
        TextView txtTue4 = root.findViewById(R.id.b4);
        TextView txtTue5 = root.findViewById(R.id.b5);
        TextView txtTue6 = root.findViewById(R.id.b6);

        TextView txtWen1 = root.findViewById(R.id.c1);
        TextView txtWen2 = root.findViewById(R.id.c2);
        TextView txtWen3 = root.findViewById(R.id.c3);
        TextView txtWen4 = root.findViewById(R.id.c4);
        TextView txtWen5 = root.findViewById(R.id.c5);
        TextView txtWen6 = root.findViewById(R.id.c6);

        TextView txtThu1 = root.findViewById(R.id.d1);
        TextView txtThu2 = root.findViewById(R.id.d2);
        TextView txtThu3 = root.findViewById(R.id.d3);
        TextView txtThu4 = root.findViewById(R.id.d4);
        TextView txtThu5 = root.findViewById(R.id.d5);
        TextView txtThu6 = root.findViewById(R.id.d6);

        TextView txtFri1 = root.findViewById(R.id.e1);
        TextView txtFri2 = root.findViewById(R.id.e2);
        TextView txtFri3 = root.findViewById(R.id.e3);
        TextView txtFri4 = root.findViewById(R.id.e4);
        TextView txtFri5 = root.findViewById(R.id.e5);
        TextView txtFri6 = root.findViewById(R.id.e6);

        txtMon1.setText("______");
        txtMon2.setText("______");
        txtMon3.setText("______");
        txtMon4.setText("______");
        txtMon5.setText("______");
        txtMon6.setText("______");

        txtTue1.setText("______");
        txtTue2.setText("______");
        txtTue3.setText("______");
        txtTue4.setText("______");
        txtTue5.setText("______");
        txtTue6.setText("______");

        txtWen1.setText("______");
        txtWen2.setText("______");
        txtWen3.setText("______");
        txtWen4.setText("______");
        txtWen5.setText("______");
        txtWen6.setText("______");

        txtThu1.setText("______");
        txtThu2.setText("______");
        txtThu3.setText("______");
        txtThu4.setText("______");
        txtThu5.setText("______");
        txtThu6.setText("______");

        txtFri1.setText("______");
        txtFri2.setText("______");
        txtFri3.setText("______");
        txtFri4.setText("______");
        txtFri5.setText("______");
        txtFri6.setText("______");

        Cursor cursor = mDb.rawQuery("SELECT * FROM mainschendule", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if (GroupName.equals(cursor.getString(1))) {
                switch (cursor.getString(2)) {
                    case "Понедельник": {
                        switch (cursor.getString(3)) {
                            case "1":{txtMon1.setText(cursor.getString(4));break;}
                            case "2":{txtMon2.setText(cursor.getString(4));break;}
                            case "3":{txtMon3.setText(cursor.getString(4));break;}
                            case "4":{txtMon4.setText(cursor.getString(4));break;}
                            case "5":{txtMon5.setText(cursor.getString(4));break;}
                            case "6":{txtMon6.setText(cursor.getString(4));break;}
                        }
                        break;
                    }
                    case "Вторник": {
                        switch (cursor.getString(3)) {
                            case "1":{txtTue1.setText(cursor.getString(4));break;}
                            case "2":{txtTue2.setText(cursor.getString(4));break;}
                            case "3":{txtTue3.setText(cursor.getString(4));break;}
                            case "4":{txtTue4.setText(cursor.getString(4));break;}
                            case "5":{txtTue5.setText(cursor.getString(4));break;}
                            case "6":{txtTue6.setText(cursor.getString(4));break;}
                        }
                        break;
                    }
                    case "Среда": {
                        switch (cursor.getString(3)) {
                            case "1":{txtWen1.setText(cursor.getString(4));break;}
                            case "2":{txtWen2.setText(cursor.getString(4));break;}
                            case "3":{txtWen3.setText(cursor.getString(4));break;}
                            case "4":{txtWen4.setText(cursor.getString(4));break;}
                            case "5":{txtWen5.setText(cursor.getString(4));break;}
                            case "6":{txtWen6.setText(cursor.getString(4));break;}
                        }
                        break;
                    }
                    case "Четверг": {
                        switch (cursor.getString(3)) {
                            case "1":{txtThu1.setText(cursor.getString(4));break;}
                            case "2":{txtThu2.setText(cursor.getString(4));break;}
                            case "3":{txtThu3.setText(cursor.getString(4));break;}
                            case "4":{txtThu4.setText(cursor.getString(4));break;}
                            case "5":{txtThu5.setText(cursor.getString(4));break;}
                            case "6":{txtThu6.setText(cursor.getString(4));break;}
                        }
                        break;
                    }
                    case "Пятница": {
                        switch (cursor.getString(3)) {
                            case "1":{txtFri1.setText(cursor.getString(4));break;}
                            case "2":{txtFri2.setText(cursor.getString(4));break;}
                            case "3":{txtFri3.setText(cursor.getString(4));break;}
                            case "4":{txtFri4.setText(cursor.getString(4));break;}
                            case "5":{txtFri5.setText(cursor.getString(4));break;}
                            case "6":{txtFri6.setText(cursor.getString(4));break;}
                        }
                        break;
                    }
                }
            }

            cursor.moveToNext();
        }
        cursor.close();
    }


    private void setChangeSchedule() {
        // TextView для основного расписания
        TextView txtTod1 = root.findViewById(R.id.txtTod1);
        TextView txtTod2 = root.findViewById(R.id.txtTod2);
        TextView txtTod3 = root.findViewById(R.id.txtTod3);
        TextView txtTod4 = root.findViewById(R.id.txtTod4);
        TextView txtTod5 = root.findViewById(R.id.txtTod5);
        TextView txtTod6 = root.findViewById(R.id.txtTod6);

        TextView txtTom1 = root.findViewById(R.id.txtTom1);
        TextView txtTom2 = root.findViewById(R.id.txtTom2);
        TextView txtTom3 = root.findViewById(R.id.txtTom3);
        TextView txtTom4 = root.findViewById(R.id.txtTom4);
        TextView txtTom5 = root.findViewById(R.id.txtTom5);
        TextView txtTom6 = root.findViewById(R.id.txtTom6);

        TextView txtTod = root.findViewById(R.id.txtTod);
        TextView txtTom = root.findViewById(R.id.txtNextDay);

        txtTod1.setText("______");
        txtTod2.setText("______");
        txtTod3.setText("______");
        txtTod4.setText("______");
        txtTod5.setText("______");
        txtTod6.setText("______");

        txtTom1.setText("______");
        txtTom2.setText("______");
        txtTom3.setText("______");
        txtTom4.setText("______");
        txtTom5.setText("______");
        txtTom6.setText("______");

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
        txtTod.setText(today);
        txtTom.setText(next_day);

        Cursor cursor = mDb.rawQuery("SELECT * FROM changeschendule", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if (GroupName.equals(cursor.getString(1))) {
                if (cursor.getString(2).equals(today)) {
//                    txtTod.setText(today);
                    switch (cursor.getString(3)) {
                        case "1": { txtTod1.setText(cursor.getString(4));break; }
                        case "2": { txtTod2.setText(cursor.getString(4));break; }
                        case "3": { txtTod3.setText(cursor.getString(4));break; }
                        case "4": { txtTod4.setText(cursor.getString(4));break; }
                        case "5": { txtTod5.setText(cursor.getString(4));break; }
                        case "6": { txtTod6.setText(cursor.getString(4));break; }
                    }
                }
            }
            if (cursor.getString(2).equals(next_day)) {
//                txtTom.setText(next_day);
                switch (cursor.getString(3)) {
                    case "1": { txtTom1.setText(cursor.getString(4));break; }
                    case "2": { txtTom2.setText(cursor.getString(4));break; }
                    case "3": { txtTom3.setText(cursor.getString(4));break; }
                    case "4": { txtTom4.setText(cursor.getString(4));break; }
                    case "5": { txtTom5.setText(cursor.getString(4));break; }
                    case "6": { txtTom6.setText(cursor.getString(4));break; }
                }
            }
            cursor.moveToNext();
        }
        cursor.close();
    }
}
