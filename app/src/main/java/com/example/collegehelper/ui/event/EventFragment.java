package com.example.collegehelper.ui.event;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegehelper.MainActivity;
import com.example.collegehelper.R;
import com.example.collegehelper.State;
import com.example.collegehelper.StateAdapter;

import java.util.ArrayList;

public class EventFragment extends Fragment implements View.OnClickListener {

    private EventViewModel eventViewModel;
    private Button button1;
    private View root;
    private int i = 1;

    ArrayList<State> states = new ArrayList<State>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        eventViewModel =
//                new ViewModelProvider(this).get(EventViewModel.class);
        root = inflater.inflate(R.layout.fragment_event, container, false);

        setInitialData();
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.list);
        // создаем адаптер
        StateAdapter adapter = new StateAdapter(this.getContext(), states);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);







        button1 = (Button) root.findViewById(R.id.btnAddEvent);
        button1.setOnClickListener(this);


        return root;
    }

    private void setInitialData(){

        states.add(new State ("Бразилия", "Бразилиа"));
        states.add(new State ("Аргентина", "Буэнос-Айрес"));
        states.add(new State ("Колумбия", "Богота"));
        states.add(new State ("Уругвай", "Монтевидео"));
        states.add(new State ("Чили", "Сантьяго"));
    }

    @Override
    public void onClick(View view) {
        if (i == 1) {
            Fragment addEvent = new AddEventFragment();
            FragmentTransaction trans = getFragmentManager().beginTransaction();
            trans.replace(R.id.event, addEvent);
            trans.addToBackStack(null);
            trans.commit();

            button1.setText("Назад");

            i=2;
            //   button1.setVisibility(View.INVISIBLE);
        } else if (i == 2) {
            Fragment Event = new EventFragment();
            FragmentTransaction trans2 = getFragmentManager().beginTransaction();
            trans2.replace(R.id.addEvent, Event);
            trans2.addToBackStack(null);
            trans2.commit();
          //  button1.setVisibility(View.INVISIBLE);

            button1.setText("Добавить событие");

            i=1;

            ConstraintLayout constraintLayout = root.findViewById(R.id.addEvent);
            constraintLayout.setVisibility(View.INVISIBLE);
        }
    }
}