package com.example.collegehelper.ui.schedule;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import static com.example.collegehelper.WorkWithData.GROUP_NAME;
import static com.example.collegehelper.WorkWithData.ID;
import static com.example.collegehelper.WorkWithData.UserType;
import static com.example.collegehelper.WorkWithData.mDb;

import com.example.collegehelper.MainActivity;
import com.example.collegehelper.R;
import com.example.collegehelper.WorkWithData;

public class ScheduleFragment extends Fragment {

    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_schedule, container, false);

        WorkWithData.ConnectToDB(this.getContext());

        setMainSchedule();

        Button main_schedule = root.findViewById(R.id.rasp);
        main_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout linearLayout = (LinearLayout) root.findViewById(R.id.layoutt);
                LinearLayout linearLayout1 = (LinearLayout) root.findViewById(R.id.layoutt1);
                linearLayout.setVisibility(View.VISIBLE);
                linearLayout1.setVisibility(View.INVISIBLE);



            }
        });

        Button change_schedule = root.findViewById(R.id.ismen);
        change_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout linearLayout = (LinearLayout) root.findViewById(R.id.layoutt);
                LinearLayout linearLayout1 = (LinearLayout) root.findViewById(R.id.layoutt1);
                linearLayout1.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.INVISIBLE);
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

        Cursor cursor = mDb.rawQuery("SELECT * FROM mainschendule", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if (GROUP_NAME.equals(cursor.getString(0))) {
                switch (cursor.getString(1)) {
                    case "Понедельник": {
                        switch (cursor.getString(2)) {
                            case "1":{txtMon1.setText(cursor.getString(3));break;}
                            case "2":{txtMon2.setText(cursor.getString(3));break;}
                            case "3":{txtMon3.setText(cursor.getString(3));break;}
                            case "4":{txtMon4.setText(cursor.getString(3));break;}
                            case "5":{txtMon5.setText(cursor.getString(3));break;}
                            case "6":{txtMon6.setText(cursor.getString(3));break;}
                        }
                        break;
                    }
                    case "Вторник": {
                        switch (cursor.getString(2)) {
                            case "1":{txtTue1.setText(cursor.getString(3));break;}
                            case "2":{txtTue2.setText(cursor.getString(3));break;}
                            case "3":{txtTue3.setText(cursor.getString(3));break;}
                            case "4":{txtTue4.setText(cursor.getString(3));break;}
                            case "5":{txtTue5.setText(cursor.getString(3));break;}
                            case "6":{txtTue6.setText(cursor.getString(3));break;}
                        }
                        break;
                    }
                    case "Среда": {
                        switch (cursor.getString(2)) {
                            case "1":{txtWen1.setText(cursor.getString(3));break;}
                            case "2":{txtWen2.setText(cursor.getString(3));break;}
                            case "3":{txtWen3.setText(cursor.getString(3));break;}
                            case "4":{txtWen4.setText(cursor.getString(3));break;}
                            case "5":{txtWen5.setText(cursor.getString(3));break;}
                            case "6":{txtWen6.setText(cursor.getString(3));break;}
                        }
                        break;
                    }
                    case "Четверг": {
                        switch (cursor.getString(2)) {
                            case "1":{txtThu1.setText(cursor.getString(3));break;}
                            case "2":{txtThu2.setText(cursor.getString(3));break;}
                            case "3":{txtThu3.setText(cursor.getString(3));break;}
                            case "4":{txtThu4.setText(cursor.getString(3));break;}
                            case "5":{txtThu5.setText(cursor.getString(3));break;}
                            case "6":{txtThu6.setText(cursor.getString(3));break;}
                        }
                        break;
                    }
                    case "Пятница": {
                        switch (cursor.getString(2)) {
                            case "1":{txtFri1.setText(cursor.getString(3));break;}
                            case "2":{txtFri2.setText(cursor.getString(3));break;}
                            case "3":{txtFri3.setText(cursor.getString(3));break;}
                            case "4":{txtFri4.setText(cursor.getString(3));break;}
                            case "5":{txtFri5.setText(cursor.getString(3));break;}
                            case "6":{txtFri6.setText(cursor.getString(3));break;}
                        }
                        break;
                    }
                }
            }

            cursor.moveToNext();
        }
        cursor.close();
    }
}
