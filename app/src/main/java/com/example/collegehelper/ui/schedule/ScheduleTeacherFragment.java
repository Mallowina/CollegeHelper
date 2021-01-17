package com.example.collegehelper.ui.schedule;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.collegehelper.R;

public class ScheduleTeacherFragment extends Fragment {
    private View root;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_schedule_teacher, container, false);


        Button main_schedule = root.findViewById(R.id.raspp);
        main_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout linearLayout = (LinearLayout) root.findViewById(R.id.layoutt);
                LinearLayout linearLayout1 = (LinearLayout) root.findViewById(R.id.layoutt1);
                linearLayout.setVisibility(View.VISIBLE);
                linearLayout1.setVisibility(View.INVISIBLE);
            }
        });

        Button change_schedule = root.findViewById(R.id.ismenn);
        change_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout linearLayout = (LinearLayout) root.findViewById(R.id.layoutt);
                LinearLayout linearLayout1 = (LinearLayout) root.findViewById(R.id.layoutt1);
                linearLayout1.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.INVISIBLE);
            }
        });

        return root;
    }
}