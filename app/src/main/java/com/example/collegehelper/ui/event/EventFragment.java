package com.example.collegehelper.ui.event;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.collegehelper.R;

public class EventFragment extends Fragment {

    private View root;
    private int i = 1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_event, container, false);

        Fragment addEvent = new AddEventFragment();
        Fragment Event = new EventActionFragment();
        FragmentTransaction trans = getFragmentManager().beginTransaction();
        trans.replace(R.id.event, Event);
        trans.commit();

        Button btnAddEvent = (Button) root.findViewById(R.id.btnAddEvent);
        btnAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == 1) {
                    FragmentTransaction trans1 = getFragmentManager().beginTransaction();
                    trans1.replace(R.id.event, addEvent);
                    trans1.commit();

                    btnAddEvent.setText("Назад");
                    i=2;
                } else if (i == 2) {
                    btnAddEvent.setText("Добавить событие");

                    FragmentTransaction trans2 = getFragmentManager().beginTransaction();
                    trans2.replace(R.id.event, Event);
                    trans2.commit();

                    i=1;
                }
            }
        });



        return root;
    }



}