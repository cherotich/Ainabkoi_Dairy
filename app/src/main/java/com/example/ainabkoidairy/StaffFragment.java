package com.example.ainabkoidairy;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class StaffFragment extends Fragment {


    public StaffFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_staff, container, false);
        ImageView new_staff_image = (ImageView) view.findViewById(R.id.new_staff);
        ImageView edit_staff_image = (ImageView) view.findViewById(R.id.edit_staff);
        ImageView dept_staff_image = (ImageView) view.findViewById(R.id.staff_dept);
        ImageView view_staff_image = (ImageView) view.findViewById(R.id.view_staff);


        new_staff_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.mainframe,new New_Staff());
                fr.commit();
            }
        });

        edit_staff_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.mainframe,new Edit_staff());
                fr.commit();

            }
        });

        return view;
    }

}
