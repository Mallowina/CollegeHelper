package com.example.collegehelper.ui.event;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
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

import com.example.collegehelper.DatabaseHelper;
import com.example.collegehelper.R;

import java.io.IOException;
import java.util.ArrayList;

public class EventActionFragment extends Fragment {

    private View root;
//    ArrayList<State> states = new ArrayList<State>();
    ArrayList<EventConstructor> events = new ArrayList<EventConstructor>();

    //Переменная для работы с БД
    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_action_event, container, false);

        mDBHelper = new DatabaseHelper(root.getContext());

        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        try {
            mDb = mDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }

        /*Динамический вывод событий*/
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.list);
        // создаем адаптер
//        StateAdapter adapter = new StateAdapter(this.getContext(), states);
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

    private void get(String event) {
        String event_name=""; // название
        String event_desc="";
        String event_date="";
        String group_name="";
        String course_name="";

        Cursor cursor = mDb.rawQuery("SELECT * FROM events", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if (cursor.getString(1).equals(event)) {
                event_name += cursor.getString(1);
                event_desc += cursor.getString(2);
                event_date += cursor.getString(3);
                group_name += cursor.getString(4);
                course_name += cursor.getString(5);

                events.add(new EventConstructor(event_name, event_desc, event_date, group_name, course_name));
            }
            cursor.moveToNext();
        }
        cursor.close();
    }
}
