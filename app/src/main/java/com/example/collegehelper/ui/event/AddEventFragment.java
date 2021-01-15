package com.example.collegehelper.ui.event;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.collegehelper.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddEventFragment extends Fragment {

    private View root;
    private int choose=0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_add_event, container, false);

        Spinner spinnerCourse = root.findViewById(R.id.spinnerCourse);
        Spinner spinnerGroup = root.findViewById(R.id.spinnerGroupAddEvent);

        /*Действия по выбору Курс/Группы */
        RadioButton btnCourse = root.findViewById(R.id.btnCourse);
        btnCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choose=1;
                spinnerCourse.setVisibility(View.VISIBLE);
                spinnerGroup.setVisibility(View.INVISIBLE);
            }
        });

        RadioButton btnGroup = root.findViewById(R.id.btnGroup);
        btnGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choose=2;
                spinnerCourse.setVisibility(View.INVISIBLE);
                spinnerGroup.setVisibility(View.VISIBLE);
            }
        });

        Spinner spinnerEvents = root.findViewById(R.id.spinnerEventAddEvent);
        CalendarView dateEvent = (CalendarView) root.findViewById(R.id.dateEvent);
        TextView descEvent = root.findViewById(R.id.descriptionEvent);



        Button btnCreateEvent = root.findViewById(R.id.btnCreateEvent);
        btnCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String course_name = "", group_name="", event_name, event_date, event_desc;
                if (choose == 1) course_name=spinnerCourse.getSelectedItem().toString();
                else if (choose == 2) group_name=spinnerGroup.getSelectedItem().toString();
                else Toast.makeText(getContext(), "Курс или группа не были выбраны", Toast.LENGTH_SHORT).show();


                event_name = spinnerEvents.getSelectedItem().toString();
                event_date = DateFormat.format("dd.MM.yyyy", dateEvent.getDate()).toString();
                event_desc = descEvent.getText().toString();

                Toast.makeText(getContext(), "Получаемые данные:\n" + course_name+"\n"+group_name+"\n"+event_name+"\n"+event_date+"\n"+event_desc,
                        Toast.LENGTH_LONG).show();


                /*ОЧИСТКА ПОЛЕЙ*/
                RadioGroup radioGroup = root.findViewById(R.id.ForWhoEvent);
                radioGroup.clearCheck();
                spinnerCourse.setSelection(0);
                spinnerGroup.setSelection(0);
                spinnerEvents.setSelection(0);
                Date currentDate = new Date();
                // Форматирование времени как "день.месяц.год"
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
                String dateText = dateFormat.format(currentDate);
                Date date = new Date();
                try {
                    date = dateFormat.parse(dateText);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                dateEvent.setDate(date.getTime());
                descEvent.setText(null);

                spinnerCourse.setVisibility(View.INVISIBLE);
                spinnerGroup.setVisibility(View.INVISIBLE);

                choose = 0;
            }
        });

        return root;
    }

}
