package com.example.ainabkoidairy;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ainabkoidairy.Models.farmers;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class Edit_farmer extends Fragment {


    public Edit_farmer() {
        // Required empty public constructor
    }
//SearchView
AutoCompleteTextView selectidtxt,selecteditfarmerlocation;
    ValueEventListener valueEventListener,valueEventListener1;
    ArrayList<String> locationsArrayListSearch,locationarrayListsearch1;
    ArrayAdapter<String> adapter,adapter1;
    private DatabaseReference locationlistref,locationlistref1;
    Button viewfarmersubmitbtn,editfarmersubmitbtn;
  TextView editfarmername,editfarmerIDNO,addfarmerlink;
    EditText editfarmerphono;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_farmer, container, false);

        editfarmername = view.findViewById(R.id.edit_farmer_name);
        editfarmerIDNO = view.findViewById(R.id.edit_farmer_IDNO);
        editfarmerphono = view.findViewById(R.id.edit_farmer_phono);
        addfarmerlink = view.findViewById(R.id.add_farmer_link);
        selectidtxt = (AutoCompleteTextView) view.findViewById(R.id.select_id_txt);
        selecteditfarmerlocation = (AutoCompleteTextView) view.findViewById(R.id.select_edit_farmer_location);
        editfarmersubmitbtn = view.findViewById(R.id.edit_farmer_submit_btn);

        locationlistref= FirebaseDatabase.getInstance().getReference("Farmers");

        locationlistref1= FirebaseDatabase.getInstance().getReference("locations");



        viewfarmersubmitbtn = view.findViewById(R.id.view_farmer_submit_btn);

        locationsArrayListSearch = new ArrayList<>();
        locationarrayListsearch1 = new ArrayList<>();

        adapter1 = new ArrayAdapter<String>(getContext(),
                R.layout.support_simple_spinner_dropdown_item,locationarrayListsearch1 );
        selecteditfarmerlocation.setAdapter(adapter1);
        retrieve1();

        adapter = new ArrayAdapter<String>(getContext(),
                R.layout.support_simple_spinner_dropdown_item,locationsArrayListSearch);
        selectidtxt.setAdapter(adapter);
        retrieve();




        viewfarmersubmitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String selectedidno = selectidtxt.getText().toString();
                if (TextUtils.isEmpty(selectedidno)){
                    selectidtxt.requestFocus();
                    selectidtxt.setError("please select a ID no");
                }
                else{
               locationlistref.child(selectedidno).addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       if (dataSnapshot.exists()) {
                           farmers farmerssnapshot = dataSnapshot.getValue(farmers.class);
                           editfarmername.setText(farmerssnapshot.getFarmername());
                           editfarmerIDNO.setText(farmerssnapshot.getFarmerid());
                           editfarmerphono.setText(farmerssnapshot.getFarmerphonenumber());
                           selecteditfarmerlocation.setText(farmerssnapshot.getLocation());
                           editfarmerphono.requestFocus();

                       }
                       else
                       {
                           String idno=selectidtxt.getText().toString();
                           Toast.makeText(getContext(), "This ID no "+idno+" does not exist ", Toast.LENGTH_SHORT).show();
                           selectidtxt.setError("This ID NO does not exist");
                           addfarmerlink.setVisibility(View.VISIBLE);
                           addfarmerlink.setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View v) {
                                   FragmentTransaction fr=getFragmentManager().beginTransaction();
                                   fr.replace(R.id.mainframe,new New_farmer());
                                   fr.commit();
                               }
                           });
                       }

                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError databaseError) {

                   }
               });



                }

            }
        });


        editfarmersubmitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

               String fname= editfarmername.getText().toString();
             final String fIDNO=   editfarmerIDNO.getText().toString();
               final String fphoneno= editfarmerphono.getText().toString();
              final String flocation=  selecteditfarmerlocation.getText().toString();

//                locationlistref.child(fIDNO).child("location").setValue(flocation).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful())
//                        {
//                            locationlistref.child(fIDNO).child("farmerphonenumber").setValue(fphoneno);
//                            Toast.makeText(getContext(), "update successfull", Toast.LENGTH_SHORT).show();
//                        }
//                        else
//                        {
//                            Toast.makeText(getContext(), "update unsuccessfull", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
              locationlistref.child(fIDNO).addListenerForSingleValueEvent(new ValueEventListener() {
                  @Override
                  public void onDataChange(@NonNull DataSnapshot dataSnapshot)

                  {



                      HashMap<String,Object> farmerdatamap = new HashMap<>();
                      farmerdatamap.put("location",flocation);
                      farmerdatamap.put("farmerphonenumber",fphoneno);
                      locationlistref.child(fIDNO).updateChildren(farmerdatamap)
                              .addOnCompleteListener(new OnCompleteListener<Void>() {
                                  @Override
                                  public void onComplete(@NonNull Task<Void> task) {
                                      if (task.isSuccessful()) {
                                          Toast.makeText(getContext(), " added successfully", Toast.LENGTH_SHORT).show();

                                      }
                                      else
                                      {
                                          Toast.makeText(getContext(), " has not been added kindly check your network ", Toast.LENGTH_SHORT).show();
                                      }

                                  }
                              });
                  }

                  @Override
                  public void onCancelled(@NonNull DatabaseError databaseError) {

                  }
              });

            }
        });

        return view;
    }

    private void retrieve1()
    {
        valueEventListener1= locationlistref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot locate1:dataSnapshot.getChildren())
                {
                    locationarrayListsearch1.add(locate1.getKey());


                }
                adapter1.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();

        addfarmerlink.setVisibility(View.INVISIBLE);


    }

    private void retrieve() {
        valueEventListener= locationlistref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot locate:dataSnapshot.getChildren())
                {
                    locationsArrayListSearch.add(locate.getKey());


                }
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

}
