package com.example.collegehelper.ui.event;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
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
    ArrayList<State> states = new ArrayList<State>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        eventViewModel =
                new ViewModelProvider(this).get(EventViewModel.class);
        View root = inflater.inflate(R.layout.fragment_event, container, false);

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
        Fragment addEvent = new AddEventFragment();
        FragmentTransaction trans=getFragmentManager().beginTransaction();
        trans.replace(R.id.event, addEvent);
        trans.addToBackStack(null);
        trans.commit();
        button1.setVisibility(View.INVISIBLE);
    }
}