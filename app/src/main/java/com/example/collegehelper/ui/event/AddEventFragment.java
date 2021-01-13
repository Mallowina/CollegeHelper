package com.example.collegehelper.ui.event;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegehelper.R;
import com.example.collegehelper.State;
import com.example.collegehelper.StateAdapter;

import java.util.ArrayList;

public class AddEventFragment extends EventFragment {

    private EventViewModel eventViewModel;

    private Button button1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        eventViewModel =
                new ViewModelProvider(this).get(EventViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add_event, container, false);

        button1 = (Button) root.findViewById(R.id.btnEvent);
        button1.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View view) {
        Fragment Event = new EventFragment();
        FragmentTransaction trans2 = getFragmentManager().beginTransaction();
        trans2.replace(R.id.addEvent, Event);
        trans2.addToBackStack(null);
        trans2.commit();
        button1.setVisibility(View.INVISIBLE);
    }
}
