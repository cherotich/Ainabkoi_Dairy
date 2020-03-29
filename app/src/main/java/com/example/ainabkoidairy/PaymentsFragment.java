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
public class PaymentsFragment extends Fragment {


    public PaymentsFragment() {
        // Required empty public constructor
    }

ImageView payfarmer;
    ImageView farmerpaymenthistory;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_payments, container, false);
        payfarmer = view.findViewById(R.id.pay_farmer);
        farmerpaymenthistory = view.findViewById(R.id.farmer_payment_history);

        payfarmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.mainframe,new pay_farmer());
                fr.commit();
            }
        });

        farmerpaymenthistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.mainframe, new generalpaymenthistory());
                fr.commit();
            }
        });


        return view;
    }

}
