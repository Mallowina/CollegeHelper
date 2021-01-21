package com.example.collegehelper.ui.schedule;

import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;

        import androidx.annotation.NonNull;
        import androidx.fragment.app.Fragment;
        import androidx.fragment.app.FragmentTransaction;

        import com.example.collegehelper.R;

        import static com.example.collegehelper.WorkWithData.UserType;

public class ScheduleFragment extends Fragment {

    private View root;
    private int i = 1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_schedule, container, false);

        Fragment ShowSchedule = new ScheduleActionFragment();
        Fragment AddSchedule = new ScheduleTeacherFragment();
        FragmentTransaction trans = getFragmentManager().beginTransaction();
        trans.replace(R.id.ChangeSchedule, ShowSchedule);
        trans.commit();

        Button showSchedule = root.findViewById(R.id.showSchedule);
        Button addSchedule = root.findViewById(R.id.addSchedule);

        if (!UserType.equals("3")) {
            showSchedule.setVisibility(View.GONE);
            addSchedule.setVisibility(View.GONE);
        }



        showSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSchedule.setVisibility(View.INVISIBLE);
                addSchedule.setVisibility(View.VISIBLE);
                FragmentTransaction trans1 = getFragmentManager().beginTransaction();
                trans1.replace(R.id.ChangeSchedule, ShowSchedule);
                trans1.commit();
            }
        });


        addSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSchedule.setVisibility(View.VISIBLE);
                addSchedule.setVisibility(View.INVISIBLE);
                FragmentTransaction trans2 = getFragmentManager().beginTransaction();
                trans2.replace(R.id.ChangeSchedule, AddSchedule);
                trans2.commit();
            }
        });

        return root;
    }


}
