package com.example.ainabkoidairy;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ainabkoidairy.HolderViews.FarmersView;
import com.example.ainabkoidairy.HolderViews.LocationsView;
import com.example.ainabkoidairy.Models.farmers;
import com.example.ainabkoidairy.Models.locations;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Locations extends Fragment {


    public Locations() {
        // Required empty public constructor
    }

    private Query locationslistquery;
    private DatabaseReference locationslistref;
    private ArrayList<locations> locationsArrayList;

RecyclerView locationsrecyclerlist;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_locations, container, false);

        locationsrecyclerlist = (RecyclerView) view.findViewById(R.id.locations_recyclerlist);
        locationslistref = FirebaseDatabase.getInstance().getReference();
        locationslistquery= locationslistref.child("locations");

        LinearLayoutManager locationslistlayoutmanager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);

        locationsrecyclerlist.setLayoutManager(locationslistlayoutmanager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(locationsrecyclerlist.getContext(),
                locationslistlayoutmanager.getOrientation());
        locationsrecyclerlist.addItemDecoration(dividerItemDecoration);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();


        if (locationslistquery!=null){
            locationslistquery.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                {
                    if (dataSnapshot.exists())
                    {


                        locationsArrayList =new ArrayList<>();
                        for (DataSnapshot ds:dataSnapshot.getChildren())
                        {

                            locationsArrayList.add(ds.getValue(locations.class));



                        }
                        LocationsView locationsView  = new LocationsView(locationsArrayList);
                        locationsrecyclerlist.setAdapter(locationsView);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }

    }
}
