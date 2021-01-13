package com.example.collegehelper.ui.event;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.collegehelper.R;

public class AddEventFragment extends Fragment {



    private Button button1;
    private View root;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_add_event, container, false);

        /*Действия по выбору Курс/Группы */
        RadioButton btnCourse = root.findViewById(R.id.btnCourse);
        RadioButton btnGroup = root.findViewById(R.id.btnGroup);
        btnCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner spinnerCourse = root.findViewById(R.id.spinnerCourse);
                Spinner spinnerGroup = root.findViewById(R.id.spinnerGroupAddEvent);
                spinnerCourse.setVisibility(View.VISIBLE);
                spinnerGroup.setVisibility(View.INVISIBLE);
            }
        });
        btnGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner spinnerCourse = root.findViewById(R.id.spinnerCourse);
                Spinner spinnerGroup = root.findViewById(R.id.spinnerGroupAddEvent);
                spinnerCourse.setVisibility(View.INVISIBLE);
                spinnerGroup.setVisibility(View.VISIBLE);
            }
        });

        return root;
    }

}
