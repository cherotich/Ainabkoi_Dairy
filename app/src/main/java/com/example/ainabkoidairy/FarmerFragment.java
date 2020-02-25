package com.example.ainabkoidairy;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class FarmerFragment extends Fragment {
  //  public class FarmerFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    public FarmerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_farmer, container, false);

        ImageView addfarmer =  (ImageView) view.findViewById(R.id.add_farmer);
        ImageView viewfarmer =  (ImageView) view.findViewById(R.id.view_farmer);
        ImageView editfarmer =  (ImageView) view.findViewById(R.id.edit_farmer);
        ImageView viewfarmerperlocation =  (ImageView) view.findViewById(R.id.view_farmer_per_location);
        ImageView addlocation = (ImageView) view.findViewById(R.id.add_location);


        addfarmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.mainframe,new New_farmer());
                fr.commit();
            }
        });

        editfarmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.mainframe,new Edit_farmer());
                fr.commit();
            }
        });

        viewfarmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.mainframe,new View_farmer());
                fr.commit();
            }
        });

        addlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.mainframe,new Add_Location());
                fr.commit();
            }
        });
        return view;
}}
