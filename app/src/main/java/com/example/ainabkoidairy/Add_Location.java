package com.example.ainabkoidairy;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class Add_Location extends Fragment {


    public Add_Location() {
        // Required empty public constructor
    }

    EditText addlocationfragmenttxt;
    TextView viewlocation;
    String addlocation;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add__location, container, false);

         addlocationfragmenttxt = (EditText) view.findViewById(R.id.add_location_fragment_txt);
         viewlocation = (TextView) view.findViewById(R.id.view_location);
        Button btnsavelocation = (Button) view.findViewById(R.id.btn_save_location);




        viewlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.mainframe,new Locations());
                fr.commit();
            }
        });

        btnsavelocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savelocationverification();
            }
        });


        return view;
    }

    private void savelocationverification()
    {

         addlocation = addlocationfragmenttxt.getText().toString();
        if (TextUtils.isEmpty(addlocation))
        {
            addlocationfragmenttxt.setError("Location required");
            addlocationfragmenttxt.requestFocus();
        }
        else
        {
            savelocation(addlocation);
        }

    }

    private void savelocation(final String addlocation)
    {

        final DatabaseReference saveloactionref;
        saveloactionref = FirebaseDatabase.getInstance().getReference("locations");


//        saveloactionref.push().setValue(addlocation).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if (task.isSuccessful())
//                {
//                    addlocationfragmenttxt.setText("");
//                    Toast.makeText(getContext(), "Location successfully added", Toast.LENGTH_SHORT).show();
//                }
//                else
//                {
//                    Toast.makeText(getContext(), "Location has not been added kindly check your network ", Toast.LENGTH_SHORT).show();
//                }
//
//
//            }
//        });

        saveloactionref.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (!(dataSnapshot.child(addlocation).exists()))

                {
                    HashMap<String,Object> farmerdatamap = new HashMap<>();
                    farmerdatamap.put("location",addlocation);



                    saveloactionref.child(addlocation).updateChildren(farmerdatamap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getContext(), "Location added successfully", Toast.LENGTH_SHORT).show();
                                        addlocationfragmenttxt.setText("");
                                    }
                                    else
                                    {
                                        Toast.makeText(getContext(), "Location has not been added kindly check your network ", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });


                }
                else
                {
                    Toast.makeText(getContext(), "This location  " +addlocation+ " already exist", Toast.LENGTH_LONG).show();


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });


    }

}
