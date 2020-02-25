package com.example.ainabkoidairy;


import android.nfc.Tag;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SearchEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ainabkoidairy.HolderViews.FarmersView;
import com.example.ainabkoidairy.HolderViews.LocationsView;
import com.example.ainabkoidairy.Models.farmers;
import com.example.ainabkoidairy.Models.locations;
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
public class New_farmer extends Fragment {


    public New_farmer() {
        // Required empty public constructor
    }

    EditText newfarmernametxt,newfarmeridnotxt,newfarmerphonenotxt;
    Button submitnewfarmerbtn;
    SearchView newfarmerlocation;
    ArrayList<locations> locationsArrayList = new ArrayList<>();
    RecyclerView locationlist;
    private DatabaseReference locationlistref;
    private Query locationlistquery;
    AutoCompleteTextView  selectlocationtxt;
    ValueEventListener valueEventListener;
     ArrayList<String> locationsArrayListSearch;
    ArrayAdapter<String> adapter;
    TextView addlocationlink;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_farmer, container, false);
         newfarmernametxt = (EditText) view.findViewById(R.id.new_farmer_name);
         newfarmeridnotxt = (EditText) view.findViewById(R.id.new_farmer_IDNO);
         submitnewfarmerbtn =(Button) view.findViewById(R.id.new_farmer_submit_btn);
         newfarmerphonenotxt = (EditText) view.findViewById(R.id.new_farmer_phoneno);
        addlocationlink = view.findViewById(R.id.add_location_link);

        selectlocationtxt = (AutoCompleteTextView) view.findViewById(R.id.select_location_txt);
        locationlistref=FirebaseDatabase.getInstance().getReference("locations");

        addlocationlink.setVisibility(View.INVISIBLE);
        addlocationlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


     locationsArrayListSearch = new ArrayList<>();

         adapter = new ArrayAdapter<String>(getContext(),
                R.layout.support_simple_spinner_dropdown_item,locationsArrayListSearch);
        selectlocationtxt.setAdapter(adapter);
        retrieve();











//        newfarmerlocation.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String searchedlocation) {
//                search(searchedlocation);
//
//                return true;
//            }
//        });

       
       submitnewfarmerbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               
               CheckDetailsNewFarmer();
               
           }
       });
        
        
        return view;
    }


public void retrieve()
{
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
    @Override
    public void onStart() {
        super.onStart();


    }

    private void CheckDetailsNewFarmer()
    {

        String addnewfarmername = newfarmernametxt.getText().toString();
        String addnewfarmerid = newfarmeridnotxt.getText().toString();
        String addnewfarmerphoneno = newfarmerphonenotxt.getText().toString();
        String selectedlocation = selectlocationtxt.getText().toString();
        if(TextUtils.isEmpty(addnewfarmername)){
            newfarmernametxt.setError("Farmer name required");

            newfarmernametxt.requestFocus();
        }
        else if(TextUtils.isEmpty(addnewfarmerid)){
            newfarmeridnotxt.setError("Farmer ID required");
            newfarmeridnotxt.requestFocus();
        }
        else if(TextUtils.isEmpty(addnewfarmerphoneno))
        {
            newfarmerphonenotxt.setError("Farmer Phone No required");
            newfarmerphonenotxt.requestFocus();
        }

        else if (TextUtils.isEmpty(selectedlocation))

        {
            selectlocationtxt.setError("kindly select location");
            selectlocationtxt.requestFocus();

        }
        else {
         Addnewfarmer(addnewfarmername,addnewfarmerid,addnewfarmerphoneno,selectedlocation);
        }

    }

    private void Addnewfarmer(final String addnewfarmername, final String addnewfarmerid, final String addnewfarmerphoneno,final String selectedlocation)
    {

        locationlistref.child(selectedlocation).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                {


                    final DatabaseReference addnewfarmerref;
                    addnewfarmerref = FirebaseDatabase.getInstance().getReference();

                    addnewfarmerref.addListenerForSingleValueEvent(new ValueEventListener()
                    {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                        {
                            if (!((dataSnapshot.child("Farmers").child(addnewfarmerid).exists())||dataSnapshot.child("Farmers").child(addnewfarmerphoneno).exists()))

                            {
                                HashMap<String, Object> farmerdatamap = new HashMap<>();
                                farmerdatamap.put("farmerid",addnewfarmerid);
                                farmerdatamap.put("farmername",addnewfarmername);
                                farmerdatamap.put("farmerphonenumber",addnewfarmerphoneno);
                                farmerdatamap.put("location",selectedlocation);


                                addnewfarmerref.child("Farmers").child(addnewfarmerid).updateChildren(farmerdatamap)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(getContext(), "Farmer added successfully", Toast.LENGTH_SHORT).show();
                                                    newfarmeridnotxt.setText("");
                                                    selectlocationtxt.setText("");
                                                    newfarmernametxt.setText("");
                                                    newfarmerphonenotxt.setText("");
                                                }
                                                else
                                                {
                                                    Toast.makeText(getContext(), "Farmer has not been added kindly check your network ", Toast.LENGTH_SHORT).show();
                                                }

                                            }
                                        });


                            }
                            else
                            {
                                Toast.makeText(getContext(), "The farmer with this" +addnewfarmerid+ "ID number already exist", Toast.LENGTH_LONG).show();
                                newfarmeridnotxt.setError("Farmer with this " +addnewfarmerid+" ID Number already exist");
                                newfarmeridnotxt.requestFocus();

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError)
                        {

                        }
                    });

                    

                }

                else
                {
                    selectlocationtxt.setError("kindly select the correct location");
                    selectlocationtxt.requestFocus();


                    addlocationlink.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
