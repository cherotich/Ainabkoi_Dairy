package com.example.ainabkoidairy;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
//import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.widget.SearchView;

import com.example.ainabkoidairy.HolderViews.FarmersView;
import com.example.ainabkoidairy.Models.farmers;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
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
public class View_farmer extends Fragment  {


    public View_farmer() {
        // Required empty public constructor
    }
    private SearchView searchfarmerview;
    private RecyclerView farmerslist;
    private String viewfarmerspinnertxt,farmersviewlimit;
   // private  RecyclerView.LayoutManager farmerslistlayoutmanager;
    private Query  farmerslistquery,farmerslistqueryrange;
    private DatabaseReference farmerslistref,farmerslistrefrange;
    private ArrayList<farmers> farmersArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_view_farmer, container, false);

        farmerslistref = FirebaseDatabase.getInstance().getReference();
        farmerslistquery= farmerslistref.child("Farmers");

        farmerslist = (RecyclerView) view.findViewById(R.id.farmers_list);
        searchfarmerview = (SearchView) view.findViewById(R.id.search_farmer_view);

        LinearLayoutManager  farmerslistlayoutmanager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);

        farmerslist.setLayoutManager(farmerslistlayoutmanager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(farmerslist.getContext(),
                farmerslistlayoutmanager.getOrientation());
        farmerslist.addItemDecoration(dividerItemDecoration);

        Spinner farmer_view_filter_spinner = (Spinner) view.findViewById(R.id.chose_filter_view_farmer_spinner);
        ArrayAdapter<CharSequence> farmer_view_filter__adapter = ArrayAdapter.createFromResource(getActivity(), R.array.filter_farmer,android.R.layout.simple_spinner_item);
        farmer_view_filter__adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        farmer_view_filter_spinner.setAdapter(farmer_view_filter__adapter);
        farmer_view_filter_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

              if(id==0)
              {
                  
              }
                if (id==1)

                {
                    viewfarmerspinnertxt = parent.getItemAtPosition(position).toString();
                    Toast.makeText(getActivity(), viewfarmerspinnertxt, Toast.LENGTH_SHORT).show();

                    farmerslistrefrange = FirebaseDatabase.getInstance().getReference();
                    farmerslistqueryrange= farmerslistrefrange.child("Farmers").orderByValue();

                    if (farmerslistqueryrange!=null){
                        farmerslistqueryrange.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                            {
                                if (dataSnapshot.exists())
                                {
                                    farmersArrayList =new ArrayList<>();
                                    for (DataSnapshot ds:dataSnapshot.getChildren())
                                    {
                                        farmersArrayList.add(ds.getValue(farmers.class));

                                    }
                                    FarmersView farmersViewsortby = new FarmersView(farmersArrayList);
                                    farmerslist.setAdapter(farmersViewsortby);
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

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Spinner farmer_view_filter_range = (Spinner) view.findViewById(R.id.limitTo);
        ArrayAdapter<CharSequence> farmer_view_filter_range__adapter = ArrayAdapter.createFromResource(getActivity(), R.array.limits,android.R.layout.simple_spinner_item);
        farmer_view_filter__adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        farmer_view_filter_range.setAdapter(farmer_view_filter_range__adapter);


        farmer_view_filter_range.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                farmersviewlimit = parent.getItemAtPosition(position).toString();
                Toast.makeText(getActivity(), farmersviewlimit, Toast.LENGTH_SHORT).show();
                if (id==0)
                {
                    farmerslistrefrange = FirebaseDatabase.getInstance().getReference().child("Farmers");
                    farmerslistqueryrange= farmerslistrefrange.limitToFirst(50);

                    if (farmerslistqueryrange!=null){
                        farmerslistqueryrange.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                            {
                                if (dataSnapshot.exists())
                                {
                                    farmersArrayList =new ArrayList<>();
                                    for (DataSnapshot ds:dataSnapshot.getChildren())
                                    {
                                        farmersArrayList.add(ds.getValue(farmers.class));

                                    }
                                    FarmersView farmersViewsort = new FarmersView(farmersArrayList);
                                    farmerslist.setAdapter(farmersViewsort);
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });
                    }


                }

                if (id==1)
                {
                    farmerslistrefrange = FirebaseDatabase.getInstance().getReference().child("Farmers");
                    farmerslistqueryrange= farmerslistrefrange.limitToFirst(100);

                    if (farmerslistqueryrange!=null){
                        farmerslistqueryrange.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                            {
                                if (dataSnapshot.exists())
                                {
                                    farmersArrayList =new ArrayList<>();
                                    for (DataSnapshot ds:dataSnapshot.getChildren())
                                    {
                                        farmersArrayList.add(ds.getValue(farmers.class));

                                    }
                                    FarmersView farmersViewsort = new FarmersView(farmersArrayList);
                                    farmerslist.setAdapter(farmersViewsort);
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });
                    }


                }

                if (id==2)
                {
                    farmerslistrefrange = FirebaseDatabase.getInstance().getReference().child("Farmers");
                    farmerslistqueryrange= farmerslistrefrange.limitToFirst(150);

                    if (farmerslistqueryrange!=null){
                        farmerslistqueryrange.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                            {
                                if (dataSnapshot.exists())
                                {
                                    farmersArrayList =new ArrayList<>();
                                    for (DataSnapshot ds:dataSnapshot.getChildren())
                                    {
                                        farmersArrayList.add(ds.getValue(farmers.class));

                                    }
                                    FarmersView farmersViewsort = new FarmersView(farmersArrayList);
                                    farmerslist.setAdapter(farmersViewsort);
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });
                    }


                }
                if (id==3)
                {
                    farmerslistrefrange = FirebaseDatabase.getInstance().getReference().child("Farmers");
                    farmerslistqueryrange= farmerslistrefrange.limitToFirst(150);

                    if (farmerslistqueryrange!=null){
                        farmerslistqueryrange.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                            {
                                if (dataSnapshot.exists())
                                {
                                    farmersArrayList =new ArrayList<>();
                                    for (DataSnapshot ds:dataSnapshot.getChildren())
                                    {
                                        farmersArrayList.add(ds.getValue(farmers.class));

                                    }
                                    FarmersView farmersViewsort = new FarmersView(farmersArrayList);
                                    farmerslist.setAdapter(farmersViewsort);
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

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });










        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (farmerslistquery!=null){
            farmerslistquery.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                {
                    if (dataSnapshot.exists())
                    {


                        //

//                        farmer_view_filter_range.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                            @Override
//                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                                if (farmersviewlimit=="100")
//                                {
//                                    farmerslistrefrange = FirebaseDatabase.getInstance().getReference().child("Farmers");
//                                    farmerslistqueryrange= farmerslistrefrange.limitToFirst(2);
//
//
//                                }
//
//                            }
//
//                            @Override
//                            public void onNothingSelected(AdapterView<?> parent) {
//
//                            }
//                        });

                        //


                        farmersArrayList =new ArrayList<>();
                        for (DataSnapshot ds:dataSnapshot.getChildren())
                        {

                            farmersArrayList.add(ds.getValue(farmers.class));



                        }
                        FarmersView farmersView = new FarmersView(farmersArrayList);
                        farmerslist.setAdapter(farmersView);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }



        if (searchfarmerview!=null)
        {
            searchfarmerview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    search(s);
                    return true;
                }
            });
        }
//        final DatabaseReference farmerslistref = FirebaseDatabase.getInstance().getReference();
//         farmerslistquery=farmerslistref.child("Farmers");
//        FirebaseRecyclerOptions<farmers> FROfarmerlistoptions = new FirebaseRecyclerOptions.Builder<farmers>()
//                .setQuery(farmerslistquery,farmers.class)
//                .build();
//
//        FirebaseRecyclerAdapter<farmers, FarmersView> farmerslistadapter
//                = new FirebaseRecyclerAdapter<farmers, FarmersView>(FROfarmerlistoptions) {
//            @Override
//            protected void onBindViewHolder(@NonNull FarmersView farmersView, int i, @NonNull farmers farmers)
//            {
//
//                farmersView.farmerlistidno.setText(farmers.getFarmerid());
//                farmersView.farmerlistname.setText(farmers.getFarmername());
//                farmersView.farmerlistphoneno.setText(farmers.getFarmerphonenumber());
//
//
//
//
//            }
//
//
//
//            @NonNull
//            @Override
//            public FarmersView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.farmers_list_content, parent, false);
//                return new FarmersView(view);
//
//            }
//        };
//        farmerslist.setAdapter(farmerslistadapter);
//
//        farmerslistadapter.startListening();
    }

    private void search(String str)
    {
        ArrayList<farmers> farmersArrayListSearch = new ArrayList<>();
 for (farmers object : farmersArrayList)
 {
     if (object.getFarmername().contains(str.toLowerCase()))
     {
         farmersArrayListSearch.add(object);

     }
 }

 FarmersView farmersView = new FarmersView(farmersArrayListSearch);
 farmerslist.setAdapter(farmersView);
    }



}
