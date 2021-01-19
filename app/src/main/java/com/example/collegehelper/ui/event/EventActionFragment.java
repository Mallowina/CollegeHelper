package com.example.collegehelper.ui.event;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegehelper.R;
import com.example.collegehelper.WorkWithData;

import static com.example.collegehelper.WorkWithData.getCourseName;
import static com.example.collegehelper.WorkWithData.getGroupName;
import static com.example.collegehelper.WorkWithData.mDb;

import java.util.ArrayList;



public class EventActionFragment extends Fragment {

    private View root;
    ArrayList<EventConstructor> events = new ArrayList<EventConstructor>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_action_event, container, false);

        WorkWithData.ConnectToDB(this.getContext());



        /*Динамический вывод событий*/
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.list);
        // создаем адаптер
        EventConstructorAdapter adapter = new EventConstructorAdapter(this.getContext(), events);

        Spinner spinnerEvents = (Spinner) root.findViewById(R.id.spinnerEvents);
        spinnerEvents.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(),
                        "Ваш выбор: " + spinnerEvents.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                switch (spinnerEvents.getSelectedItem().toString()) {
                    case "Самостоятельные работы": {
                        if (!events.isEmpty()) events.clear();
                        get("Самостоятельные работы");
                        break;
                    }
                    case "Контрольные работы": {
                        if (!events.isEmpty()) events.clear();
                        get("Контрольные работы");
                        break;
                    }
                    case "Мероприятия от колледжа": {
                        if (!events.isEmpty()) events.clear();
                        get("Мероприятия от колледжа");
                        break;
                    }
                    case "Другое": {
                        if (!events.isEmpty()) events.clear();
                        get("Другое");
                        break;
                    }
                }
                // устанавливаем для списка адаптер
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                setInitialData();
                // устанавливаем для списка адаптер
                recyclerView.setAdapter(adapter);
            }
        });

        return root;
    }

    private void setInitialData(){
//        states.add(new State ("Бразилия", "Бразилиа"));
    }

    private String now_group_name=getGroupName().trim();
    private void get(String event) {
        String event_name=""; // название
        String event_desc="";
        String event_date="";
        String group_name="";

        String course_name="";
        String now_course_name=getCourseName().trim();

        Cursor cursor = mDb.rawQuery("SELECT * FROM events", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if (cursor.getString(1).equals(event)) {
                event_name = cursor.getString(1);
                event_desc = cursor.getString(2);
                event_date = cursor.getString(3);
                group_name = cursor.getString(4);
                course_name = cursor.getString(5);

//                Toast.makeText(getContext(),
//                        now_course_name + course_name + "\n"
//                                +now_group_name+group_name,
//                        Toast.LENGTH_SHORT).show();

                if (now_course_name.equals(course_name)) {
                    events.add(new EventConstructor(event_name, event_desc, event_date, group_name, course_name));
                }
                if (now_group_name.equalsIgnoreCase(group_name)) {
                    events.add(new EventConstructor(event_name, event_desc, event_date, group_name, course_name));
                } /*else  {
                    Toast.makeText(getContext(),
                            "совпадений не найдено",
                            Toast.LENGTH_SHORT).show();
                }*/
            }
            cursor.moveToNext();
        }
        cursor.close();
    }
}
