package com.example.ainabkoidairy;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Edit_staff extends Fragment implements AdapterView.OnItemSelectedListener {


    public Edit_staff() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_edit_staff, container, false);
         //edit role staff spinner
        Spinner edit_role_staff_spinner = (Spinner) view.findViewById(R.id.edit_staff_role_spinner);
        ArrayAdapter<CharSequence> edit_staff_role__adapter = ArrayAdapter.createFromResource(getActivity(), R.array.Roles,android.R.layout.simple_spinner_item);
        edit_staff_role__adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edit_role_staff_spinner.setAdapter(edit_staff_role__adapter);
        edit_role_staff_spinner.setOnItemSelectedListener(this);


        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text=parent.getItemAtPosition(position).toString();
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
