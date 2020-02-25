package com.example.ainabkoidairy;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
public class New_Staff extends Fragment implements AdapterView.OnItemSelectedListener {


    public New_Staff() {
        // Required empty public constructor
    }

    private EditText newstaffnametxt,newstaffidnotxt,newstaffphonenotxt;
    private Button addnewstaffbtn;
    String newstaffrole,newstaffsection;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_new__staff, container, false);

newstaffnametxt = (EditText) view.findViewById(R.id.new_staff_name);
newstaffidnotxt = (EditText) view.findViewById(R.id.new_staff_IDNO);
newstaffphonenotxt = (EditText) view.findViewById(R.id.new_staff_phoneno);
addnewstaffbtn = (Button) view.findViewById(R.id.new_staff_submit_btn);




        //section spinner
        Spinner staff_spinner = (Spinner) view.findViewById(R.id.new_staff_Section_spinner);
        ArrayAdapter<CharSequence> section_adapter = ArrayAdapter.createFromResource(getActivity(), R.array.Sections,android.R.layout.simple_spinner_item);
        section_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        staff_spinner.setAdapter(section_adapter);
        staff_spinner.setOnItemSelectedListener(this);

        //role spinner
        Spinner new_staff_role_spinner = (Spinner) view.findViewById(R.id.new_staff_role_spinner);
        ArrayAdapter<CharSequence> role_adapter = ArrayAdapter.createFromResource(getActivity(), R.array.Roles,android.R.layout.simple_spinner_item);
        role_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        new_staff_role_spinner.setAdapter(role_adapter);
        new_staff_role_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                 newstaffrole = parent.getItemAtPosition(position).toString();
                Toast.makeText(getActivity(), newstaffrole, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        addnewstaffbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                checkStaffDetails();
            }
        });

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
         newstaffsection = parent.getItemAtPosition(position).toString();
        Toast.makeText(getActivity(), newstaffsection, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {


    }

    private void checkStaffDetails()
    {
        String addnewstaffname = newstaffnametxt.getText().toString();
        String addnewstaffid = newstaffidnotxt.getText().toString();
        String addnewstaffphoneno = newstaffphonenotxt.getText().toString();
        if(TextUtils.isEmpty(addnewstaffname)){
            newstaffnametxt.requestFocus();
        }
        else if(TextUtils.isEmpty(addnewstaffid)){
            newstaffidnotxt.requestFocus();
        }
        else if(TextUtils.isEmpty(addnewstaffphoneno))
        {
            newstaffphonenotxt.requestFocus();
        }
        else {
            Addnewstaff(addnewstaffname,addnewstaffid,addnewstaffphoneno);
        }

    }

    private void Addnewstaff(final String addnewstaffname, final String addnewstaffid, final String addnewstaffphoneno)
    {

        final DatabaseReference addnewstaffref;
        addnewstaffref = FirebaseDatabase.getInstance().getReference();

        addnewstaffref.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (!(dataSnapshot.child("Staff").child(addnewstaffid).exists()))

                {
                    HashMap<String, Object> staffdatamap = new HashMap<>();
                    staffdatamap.put("staffid",addnewstaffid);
                    staffdatamap.put("staffname",addnewstaffname);
                    staffdatamap.put("staffphonenumber",addnewstaffphoneno);
                    staffdatamap.put("staffrole",newstaffrole);
                    staffdatamap.put("staffsection",newstaffsection);



                    addnewstaffref.child("Staff").child(addnewstaffid).updateChildren(staffdatamap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getContext(), "Staff added successfully", Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        Toast.makeText(getContext(), "Staff has not been added kindly check your network ", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });


                }
                else
                {
                    Toast.makeText(getContext(), "The staff with this" +addnewstaffid+ "ID number already exist", Toast.LENGTH_LONG).show();


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });



    }


}
