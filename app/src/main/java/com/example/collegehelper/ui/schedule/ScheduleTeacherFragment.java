package com.example.collegehelper.ui.schedule;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.collegehelper.R;
import com.example.collegehelper.WorkWithData;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.collegehelper.WorkWithData.firstUpperCase;
import static com.example.collegehelper.WorkWithData.getGroupName;
import static com.example.collegehelper.WorkWithData.getLastID;
import static com.example.collegehelper.WorkWithData.mDb;

public class ScheduleTeacherFragment extends Fragment {
    private View root;
    private int what_create = 1;

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
                what_create = 1;
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
                what_create=2;
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
                if (what_create==1) addMainSchedule();           //Функция получения данных и добавление их в бд
                if (what_create==2) addChangeSchedule();
            }
        });

        return root;
    }

    private void addMainSchedule() {
        /*Строковые данные для добавления в бд*/
        String Mon1, Mon2, Mon3, Mon4, Mon5, Mon6;
        String Tue1, Tue2, Tue3, Tue4, Tue5, Tue6;
        String Wen1, Wen2, Wen3, Wen4, Wen5, Wen6;
        String Thu1, Thu2, Thu3, Thu4, Thu5, Thu6;
        String Fri1, Fri2, Fri3, Fri4, Fri5, Fri6;
        String group_name="";
        Spinner group = root.findViewById(R.id.spinnerGroupSchedule);
        group_name = group.getSelectedItem().toString();
        //Добавление в строки данных
        EditText mon1 = root.findViewById(R.id.as1);
        EditText mon2 = root.findViewById(R.id.as2);
        EditText mon3 = root.findViewById(R.id.as3);
        EditText mon4 = root.findViewById(R.id.as4);
        EditText mon5 = root.findViewById(R.id.as5);
        EditText mon6 = root.findViewById(R.id.as6);

        EditText tue1 = root.findViewById(R.id.bn1);
        EditText tue2 = root.findViewById(R.id.bn2);
        EditText tue3 = root.findViewById(R.id.bn3);
        EditText tue4 = root.findViewById(R.id.bn4);
        EditText tue5 = root.findViewById(R.id.bn5);
        EditText tue6 = root.findViewById(R.id.bn6);

        EditText wen1 = root.findViewById(R.id.cv1);
        EditText wen2 = root.findViewById(R.id.cv2);
        EditText wen3 = root.findViewById(R.id.cv3);
        EditText wen4 = root.findViewById(R.id.cv4);
        EditText wen5 = root.findViewById(R.id.cv5);
        EditText wen6 = root.findViewById(R.id.cv6);

        EditText thu1 = root.findViewById(R.id.df1);
        EditText thu2 = root.findViewById(R.id.df2);
        EditText thu3 = root.findViewById(R.id.df3);
        EditText thu4 = root.findViewById(R.id.df4);
        EditText thu5 = root.findViewById(R.id.df5);
        EditText thu6 = root.findViewById(R.id.df6);

        EditText fri1 = root.findViewById(R.id.er1);
        EditText fri2 = root.findViewById(R.id.er2);
        EditText fri3 = root.findViewById(R.id.er3);
        EditText fri4 = root.findViewById(R.id.er4);
        EditText fri5 = root.findViewById(R.id.er5);
        EditText fri6 = root.findViewById(R.id.er6);

        Mon1 = mon1.getText().toString();
        Mon2 = mon2.getText().toString();
        Mon3 = mon3.getText().toString();
        Mon4 = mon4.getText().toString();
        Mon5 = mon5.getText().toString();
        Mon6 = mon6.getText().toString();

        Tue1 = tue1.getText().toString();
        Tue2 = tue2.getText().toString();
        Tue3 = tue3.getText().toString();
        Tue4 = tue4.getText().toString();
        Tue5 = tue5.getText().toString();
        Tue6 = tue6.getText().toString();

        Wen1 = wen1.getText().toString();
        Wen2 = wen2.getText().toString();
        Wen3 = wen3.getText().toString();
        Wen4 = wen4.getText().toString();
        Wen5 = wen5.getText().toString();
        Wen6 = wen6.getText().toString();

        Thu1 = thu1.getText().toString();
        Thu2 = thu2.getText().toString();
        Thu3 = thu3.getText().toString();
        Thu4 = thu4.getText().toString();
        Thu5 = thu5.getText().toString();
        Thu6 = thu6.getText().toString();

        Fri1 = fri1.getText().toString();
        Fri2 = fri2.getText().toString();
        Fri3 = fri3.getText().toString();
        Fri4 = fri4.getText().toString();
        Fri5 = fri5.getText().toString();
        Fri6 = fri6.getText().toString();


        WorkWithData.ConnectToDB(root.getContext());

        mDb.beginTransaction();

        int last_id = getLastID("mainschendule");

// Создайте новую строку со значениями для вставки.
        ContentValues mon1Values = new ContentValues();
        ContentValues mon2Values = new ContentValues();
        ContentValues mon3Values = new ContentValues();
        ContentValues mon4Values = new ContentValues();
        ContentValues mon5Values = new ContentValues();
        ContentValues mon6Values = new ContentValues();

        ContentValues tue1Values = new ContentValues();
        ContentValues tue2Values = new ContentValues();
        ContentValues tue3Values = new ContentValues();
        ContentValues tue4Values = new ContentValues();
        ContentValues tue5Values = new ContentValues();
        ContentValues tue6Values = new ContentValues();

        ContentValues wen1Values = new ContentValues();
        ContentValues wen2Values = new ContentValues();
        ContentValues wen3Values = new ContentValues();
        ContentValues wen4Values = new ContentValues();
        ContentValues wen5Values = new ContentValues();
        ContentValues wen6Values = new ContentValues();

        ContentValues thu1Values = new ContentValues();
        ContentValues thu2Values = new ContentValues();
        ContentValues thu3Values = new ContentValues();
        ContentValues thu4Values = new ContentValues();
        ContentValues thu5Values = new ContentValues();
        ContentValues thu6Values = new ContentValues();

        ContentValues fri1Values = new ContentValues();
        ContentValues fri2Values = new ContentValues();
        ContentValues fri3Values = new ContentValues();
        ContentValues fri4Values = new ContentValues();
        ContentValues fri5Values = new ContentValues();
        ContentValues fri6Values = new ContentValues();

// Задайте значения для каждой строки.
        mon1Values.put("id", last_id);    //Понедельник
        mon1Values.put("group_name", group_name);
        mon1Values.put("day_of_week", "Понедельник");
        mon1Values.put("number", "1");
        mon1Values.put("lesson", Mon1);
        mon2Values.put("id", last_id+1);
        mon2Values.put("group_name", group_name);
        mon2Values.put("day_of_week", "Понедельник");
        mon2Values.put("number", "2");
        mon2Values.put("lesson", Mon2);
        mon3Values.put("id", last_id+2);
        mon3Values.put("group_name", group_name);
        mon3Values.put("day_of_week", "Понедельник");
        mon3Values.put("number", "3");
        mon3Values.put("lesson", Mon3);
        mon4Values.put("id", last_id+3);
        mon4Values.put("group_name", group_name);
        mon4Values.put("day_of_week", "Понедельник");
        mon4Values.put("number", "4");
        mon4Values.put("lesson", Mon4);
        mon5Values.put("id", last_id+4);
        mon5Values.put("group_name", group_name);
        mon5Values.put("day_of_week", "Понедельник");
        mon5Values.put("number", "5");
        mon5Values.put("lesson", Mon5);
        mon6Values.put("id", last_id+5);
        mon6Values.put("group_name", group_name);
        mon6Values.put("day_of_week", "Понедельник");
        mon6Values.put("number", "6");
        mon6Values.put("lesson", Mon6);

        tue1Values.put("id", last_id+6);    //Вторник
        tue1Values.put("group_name", group_name);
        tue1Values.put("day_of_week", "Вторник");
        tue1Values.put("number", "1");
        tue1Values.put("lesson", Tue1);
        tue2Values.put("id", last_id+7);
        tue2Values.put("group_name", group_name);
        tue2Values.put("day_of_week", "Вторник");
        tue2Values.put("number", "2");
        tue2Values.put("lesson", Tue2);
        tue3Values.put("id", last_id+8);
        tue3Values.put("group_name", group_name);
        tue3Values.put("day_of_week", "Вторник");
        tue3Values.put("number", "3");
        tue3Values.put("lesson", Tue3);
        tue4Values.put("id", last_id+9);
        tue4Values.put("group_name", group_name);
        tue4Values.put("day_of_week", "Вторник");
        tue4Values.put("number", "4");
        tue4Values.put("lesson", Tue4);
        tue5Values.put("id", last_id+10);
        tue5Values.put("group_name", group_name);
        tue5Values.put("day_of_week", "Вторник");
        tue5Values.put("number", "5");
        tue5Values.put("lesson", Tue5);
        tue6Values.put("id", last_id+11);
        tue6Values.put("group_name", group_name);
        tue6Values.put("day_of_week", "Вторник");
        tue6Values.put("number", "6");
        tue6Values.put("lesson", Tue6);

        wen1Values.put("id", last_id+12);    //Среда
        wen1Values.put("group_name", group_name);
        wen1Values.put("day_of_week", "Среда");
        wen1Values.put("number", "1");
        wen1Values.put("lesson", Wen1);
        wen2Values.put("id", last_id+13);
        wen2Values.put("group_name", group_name);
        wen2Values.put("day_of_week", "Среда");
        wen2Values.put("number", "2");
        wen2Values.put("lesson", Wen2);
        wen3Values.put("id", last_id+16);
        wen3Values.put("group_name", group_name);
        wen3Values.put("day_of_week", "Среда");
        wen3Values.put("number", "3");
        wen3Values.put("lesson", Wen3);
        wen4Values.put("id", last_id+17);
        wen4Values.put("group_name", group_name);
        wen4Values.put("day_of_week", "Среда");
        wen4Values.put("number", "4");
        wen4Values.put("lesson", Wen4);
        wen5Values.put("id", last_id+18);
        wen5Values.put("group_name", group_name);
        wen5Values.put("day_of_week", "Среда");
        wen5Values.put("number", "5");
        wen5Values.put("lesson", Wen5);
        wen6Values.put("id", last_id+19);
        wen6Values.put("group_name", group_name);
        wen6Values.put("day_of_week", "Среда");
        wen6Values.put("number", "6");
        wen6Values.put("lesson", Wen6);

        thu1Values.put("id", last_id+20);           //Четверг
        thu1Values.put("group_name", group_name);
        thu1Values.put("day_of_week", "Четверг");
        thu1Values.put("number", "1");
        thu1Values.put("lesson", Thu1);
        thu2Values.put("id", last_id+21);
        thu2Values.put("group_name", group_name);
        thu2Values.put("day_of_week", "Четверг");
        thu2Values.put("number", "2");
        thu2Values.put("lesson", Thu2);
        thu3Values.put("id", last_id+22);
        thu3Values.put("group_name", group_name);
        thu3Values.put("day_of_week", "Четверг");
        thu3Values.put("number", "3");
        thu3Values.put("lesson", Thu3);
        thu4Values.put("id", last_id+23);
        thu4Values.put("group_name", group_name);
        thu4Values.put("day_of_week", "Четверг");
        thu4Values.put("number", "4");
        thu4Values.put("lesson", Thu4);
        thu5Values.put("id", last_id+24);
        thu5Values.put("group_name", group_name);
        thu5Values.put("day_of_week", "Четверг");
        thu5Values.put("number", "5");
        thu5Values.put("lesson", Thu5);
        thu6Values.put("id", last_id+25);
        thu6Values.put("group_name", group_name);
        thu6Values.put("day_of_week", "Четверг");
        thu6Values.put("number", "6");
        thu6Values.put("lesson", Thu6);

        fri1Values.put("id", last_id+26);            //Пятница
        fri1Values.put("group_name", group_name);
        fri1Values.put("day_of_week", "Пятница");
        fri1Values.put("number", "1");
        fri1Values.put("lesson", Fri1);
        fri2Values.put("id", last_id+27);
        fri2Values.put("group_name", group_name);
        fri2Values.put("day_of_week", "Пятница");
        fri2Values.put("number", "2");
        fri2Values.put("lesson", Fri2);
        fri3Values.put("id", last_id+28);
        fri3Values.put("group_name", group_name);
        fri3Values.put("day_of_week", "Пятница");
        fri3Values.put("number", "3");
        fri3Values.put("lesson", Fri3);
        fri4Values.put("id", last_id+29);
        fri4Values.put("group_name", group_name);
        fri4Values.put("day_of_week", "Пятница");
        fri4Values.put("number", "4");
        fri4Values.put("lesson", Fri4);
        fri5Values.put("id", last_id+30);
        fri5Values.put("group_name", group_name);
        fri5Values.put("day_of_week", "Пятница");
        fri5Values.put("number", "5");
        fri5Values.put("lesson", Fri5);
        fri6Values.put("id", last_id+31);
        fri6Values.put("group_name", group_name);
        fri6Values.put("day_of_week", "Пятница");
        fri6Values.put("number", "6");
        fri6Values.put("lesson", Fri6);

// Вставьте строку в вашу базу данных.
        mDb.insert("mainschendule", null, mon1Values);
        mDb.insert("mainschendule", null, mon2Values);
        mDb.insert("mainschendule", null, mon3Values);
        mDb.insert("mainschendule", null, mon4Values);
        mDb.insert("mainschendule", null, mon5Values);
        mDb.insert("mainschendule", null, mon6Values);

        mDb.insert("mainschendule", null, tue1Values);
        mDb.insert("mainschendule", null, tue2Values);
        mDb.insert("mainschendule", null, tue3Values);
        mDb.insert("mainschendule", null, tue4Values);
        mDb.insert("mainschendule", null, tue5Values);
        mDb.insert("mainschendule", null, tue6Values);

        mDb.insert("mainschendule", null, wen1Values);
        mDb.insert("mainschendule", null, wen2Values);
        mDb.insert("mainschendule", null, wen3Values);
        mDb.insert("mainschendule", null, wen4Values);
        mDb.insert("mainschendule", null, wen5Values);
        mDb.insert("mainschendule", null, wen6Values);

        mDb.insert("mainschendule", null, thu1Values);
        mDb.insert("mainschendule", null, thu2Values);
        mDb.insert("mainschendule", null, thu3Values);
        mDb.insert("mainschendule", null, thu4Values);
        mDb.insert("mainschendule", null, thu5Values);
        mDb.insert("mainschendule", null, thu6Values);

        mDb.insert("mainschendule", null, fri1Values);
        mDb.insert("mainschendule", null, fri2Values);
        mDb.insert("mainschendule", null, fri3Values);
        mDb.insert("mainschendule", null, fri4Values);
        mDb.insert("mainschendule", null, fri5Values);
        mDb.insert("mainschendule", null, fri6Values);

        mDb.setTransactionSuccessful();
        mDb.endTransaction();
        mDb.close();

        Toast.makeText(root.getContext(), "Расписание создано для группы: " + group_name, Toast.LENGTH_SHORT).show();
    }

    public void addChangeSchedule() {
        /*Строковые данные для добавления в бд*/
        String Tod1, Tod2, Tod3, Tod4, Tod5, Tod6;
        String Tom1, Tom2, Tom3, Tom4, Tom5, Tom6;
        String group_name="";
        Spinner group = root.findViewById(R.id.spinnerGroupSchedule);
        group_name = group.getSelectedItem().toString();
        //Добавление в строки данных
        EditText tod1 = root.findViewById(R.id.addTod1);
        EditText tod2 = root.findViewById(R.id.addTod2);
        EditText tod3 = root.findViewById(R.id.addTod3);
        EditText tod4 = root.findViewById(R.id.addTod4);
        EditText tod5 = root.findViewById(R.id.addTod5);
        EditText tod6 = root.findViewById(R.id.addTod6);

        EditText tom1 = root.findViewById(R.id.addTom1);
        EditText tom2 = root.findViewById(R.id.addTom2);
        EditText tom3 = root.findViewById(R.id.addTom3);
        EditText tom4 = root.findViewById(R.id.addTom4);
        EditText tom5 = root.findViewById(R.id.addTom5);
        EditText tom6 = root.findViewById(R.id.addTom6);

        Tod1 = tod1.getText().toString();
        Tod2 = tod2.getText().toString();
        Tod3 = tod3.getText().toString();
        Tod4 = tod4.getText().toString();
        Tod5 = tod5.getText().toString();
        Tod6 = tod6.getText().toString();

        Tom1 = tom1.getText().toString();
        Tom2 = tom2.getText().toString();
        Tom3 = tom3.getText().toString();
        Tom4 = tom4.getText().toString();
        Tom5 = tom5.getText().toString();
        Tom6 = tom6.getText().toString();

        WorkWithData.ConnectToDB(root.getContext());

        mDb.beginTransaction();

        int last_id = getLastID("changeschendule");

// Создайте новую строку со значениями для вставки.
        ContentValues tod1Values = new ContentValues();
        ContentValues tod2Values = new ContentValues();
        ContentValues tod3Values = new ContentValues();
        ContentValues tod4Values = new ContentValues();
        ContentValues tod5Values = new ContentValues();
        ContentValues tod6Values = new ContentValues();

        ContentValues tom1Values = new ContentValues();
        ContentValues tom2Values = new ContentValues();
        ContentValues tom3Values = new ContentValues();
        ContentValues tom4Values = new ContentValues();
        ContentValues tom5Values = new ContentValues();
        ContentValues tom6Values = new ContentValues();

// Задайте значения для каждой строки.
        tod1Values.put("id", last_id);    //Понедельник
        tod1Values.put("group_name", group_name);
        tod1Values.put("day_of_week", "Понедельник");
        tod1Values.put("number", "1");
        tod1Values.put("lesson", Tod1);
        tod2Values.put("id", last_id+1);
        tod2Values.put("group_name", group_name);
        tod2Values.put("day_of_week", "Понедельник");
        tod2Values.put("number", "2");
        tod2Values.put("lesson", Tod2);
        tod3Values.put("id", last_id+2);
        tod3Values.put("group_name", group_name);
        tod3Values.put("day_of_week", "Понедельник");
        tod3Values.put("number", "3");
        tod3Values.put("lesson", Tod3);
        tod4Values.put("id", last_id+3);
        tod4Values.put("group_name", group_name);
        tod4Values.put("day_of_week", "Понедельник");
        tod4Values.put("number", "4");
        tod4Values.put("lesson", Tod4);
        tod5Values.put("id", last_id+4);
        tod5Values.put("group_name", group_name);
        tod5Values.put("day_of_week", "Понедельник");
        tod5Values.put("number", "5");
        tod5Values.put("lesson", Tod5);
        tod6Values.put("id", last_id+5);
        tod6Values.put("group_name", group_name);
        tod6Values.put("day_of_week", "Понедельник");
        tod6Values.put("number", "6");
        tod6Values.put("lesson", Tod6);

        tom1Values.put("id", last_id+6);    //Вторник
        tom1Values.put("group_name", group_name);
        tom1Values.put("day_of_week", "Вторник");
        tom1Values.put("number", "1");
        tom1Values.put("lesson", Tom1);
        tom2Values.put("id", last_id+7);
        tom2Values.put("group_name", group_name);
        tom2Values.put("day_of_week", "Вторник");
        tom2Values.put("number", "2");
        tom2Values.put("lesson", Tom2);
        tom3Values.put("id", last_id+8);
        tom3Values.put("group_name", group_name);
        tom3Values.put("day_of_week", "Вторник");
        tom3Values.put("number", "3");
        tom3Values.put("lesson", Tom3);
        tom4Values.put("id", last_id+9);
        tom4Values.put("group_name", group_name);
        tom4Values.put("day_of_week", "Вторник");
        tom4Values.put("number", "4");
        tom4Values.put("lesson", Tom4);
        tom5Values.put("id", last_id+10);
        tom5Values.put("group_name", group_name);
        tom5Values.put("day_of_week", "Вторник");
        tom5Values.put("number", "5");
        tom5Values.put("lesson", Tom5);
        tom6Values.put("id", last_id+11);
        tom6Values.put("group_name", group_name);
        tom6Values.put("day_of_week", "Вторник");
        tom6Values.put("number", "6");
        tom6Values.put("lesson", Tom6);

// Вставьте строку в вашу базу данных.
        mDb.insert("changeschendule", null, tod1Values);
        mDb.insert("changeschendule", null, tod2Values);
        mDb.insert("changeschendule", null, tod3Values);
        mDb.insert("changeschendule", null, tod4Values);
        mDb.insert("changeschendule", null, tod5Values);
        mDb.insert("changeschendule", null, tod6Values);

        mDb.insert("changeschendule", null, tom1Values);
        mDb.insert("changeschendule", null, tom2Values);
        mDb.insert("changeschendule", null, tom3Values);
        mDb.insert("changeschendule", null, tom4Values);
        mDb.insert("changeschendule", null, tom5Values);
        mDb.insert("changeschendule", null, tom6Values);


        mDb.setTransactionSuccessful();
        mDb.endTransaction();
        mDb.close();

        Toast.makeText(root.getContext(), "Изменения в расписании созданы для группы: " + group_name, Toast.LENGTH_SHORT).show();
    }

}