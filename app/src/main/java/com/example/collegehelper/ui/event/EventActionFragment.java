package com.example.collegehelper.ui.event;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegehelper.R;
import com.example.collegehelper.State;
import com.example.collegehelper.StateAdapter;

import java.util.ArrayList;

public class EventActionFragment extends Fragment {

    private View root;
    ArrayList<State> states = new ArrayList<State>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_action_event, container, false);

        /*Динамический вывод событий*/
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.list);
        // создаем адаптер
        StateAdapter adapter = new StateAdapter(this.getContext(), states);

        Spinner spinnerEvents = (Spinner) root.findViewById(R.id.spinnerEvents);
        spinnerEvents.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(),
                        "Ваш выбор: " + spinnerEvents.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                switch (spinnerEvents.getSelectedItem().toString()) {
                    case "Самостоятельные работы": {
                        if (!states.isEmpty()) states.clear();
                        states.add(new State("Apple", "Juice"));
                        states.add(new State ("Banana", "Vanil"));
                        break;
                    }
                    case "Контрольные работы": {
                        if (!states.isEmpty()) states.clear();
                        states.add(new State ("tortic", "vkusna"));
                        states.add(new State ("vip`em", "happy"));
                        break;
                    }
                    case "Мероприятия от колледжа": {
                        if (!states.isEmpty()) states.clear();
                        states.add(new State ("oh~", "ah~"));
                        states.add(new State ("ou", "shit"));
                        break;
                    }
                    case "Другое": {
                        if (!states.isEmpty()) states.clear();
                        states.add(new State ("bloooooo", "pipetc"));
                        states.add(new State ("sleep", "please"));
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

        states.add(new State ("Бразилия", "Бразилиа"));
        states.add(new State ("Аргентина", "Буэнос-Айрес"));
        states.add(new State ("Колумбия", "Богота"));
        states.add(new State ("Уругвай", "Монтевидео"));
        states.add(new State ("Чили", "Сантьяго"));
    }
}
