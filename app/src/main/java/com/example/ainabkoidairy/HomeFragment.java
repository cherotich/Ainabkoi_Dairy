package com.example.ainabkoidairy;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        final ImageView farmerdata = (ImageView) view.findViewById(R.id.farmer_data);
        ImageView farmerpayment = (ImageView) view.findViewById(R.id.farmer_payment);
        ImageView farmerdatavisualization = (ImageView) view.findViewById(R.id.visualize_data);
        ImageView receivemilk  = view.findViewById(R.id.receive_milk);
        farmerdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.mainframe,new farmer_data());
                fr.commit();


            }
        });

        farmerpayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.mainframe,new PaymentsFragment());
                fr.commit();

            }
        });


        receivemilk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.mainframe,new receive_milk());
                fr.commit();
            }
        });


        return view;
    }



}
