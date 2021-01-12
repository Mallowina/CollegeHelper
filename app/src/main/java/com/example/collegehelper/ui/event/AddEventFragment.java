package com.example.collegehelper.ui.event;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegehelper.R;
import com.example.collegehelper.State;
import com.example.collegehelper.StateAdapter;

import java.util.ArrayList;

public class AddEventFragment extends EventFragment {

    private EventViewModel eventViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        eventViewModel =
                new ViewModelProvider(this).get(EventViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add_event, container, false);

        return root;
    }
}
