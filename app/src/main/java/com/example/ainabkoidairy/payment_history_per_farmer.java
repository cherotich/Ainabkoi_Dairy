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
import android.widget.Toast;

import com.example.ainabkoidairy.Models.farmers;
import com.example.ainabkoidairy.Models.payment;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.example.ainabkoidairy.HolderViews.paymentview;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class payment_history_per_farmer extends Fragment {


    public payment_history_per_farmer() {
        // Required empty public constructor
    }

    private RecyclerView paymentperfarmerlist;
    private Query paymentperfarmerlistquery;
    private DatabaseReference paymentperfarmerlistref;
    //    private ArrayList<payment> paymentArrayList;
    String farmeridno;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payment_history_per_farmer, container, false);

        Bundle bundle = getArguments();
        farmeridno = bundle.getString("fid");

        paymentperfarmerlist = view.findViewById(R.id.payment_history_per_farmer_list);
        paymentperfarmerlistref = FirebaseDatabase.getInstance().getReference("payment").child(farmeridno).child("timestamp");
        paymentperfarmerlistquery = paymentperfarmerlistref.child(farmeridno).child("timestamp");


        LinearLayoutManager paymentlistlayoutmanager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        paymentperfarmerlist.setLayoutManager(paymentlistlayoutmanager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(paymentperfarmerlist.getContext(),
                paymentlistlayoutmanager.getOrientation());
        paymentperfarmerlist.addItemDecoration(dividerItemDecoration);


        return view;
    }


    @Override
    public void onStart() {
        super.onStart();


        FirebaseRecyclerOptions<payment> options =
                new FirebaseRecyclerOptions.Builder<payment>()
                        .setQuery(paymentperfarmerlistref, payment.class)
                        .build();
        FirebaseRecyclerAdapter<payment, paymentview> adapter = new FirebaseRecyclerAdapter<payment, paymentview>(options) {
            @Override
            protected void onBindViewHolder(@NonNull paymentview paymentviewmodel, int i, @NonNull payment pament) {
                paymentviewmodel.paymentperfarmerdate.setText(pament.getForday());
                paymentviewmodel.paymentperfarmeramount.setText(pament.getPaidnow());
                paymentviewmodel.paymentperfarmertime.setText(pament.getTime());
            }

            @NonNull
            @Override
            public paymentview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_history_per_farmer_content_list, parent, false);
                paymentview holder = new paymentview(view);
                return holder;
            }

        };
        paymentperfarmerlist.setAdapter(adapter);
        adapter.startListening();
//        if (paymentperfarmerlistquery!=null)
//        {
//            paymentperfarmerlistquery.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot)
//                {
//
//
//                    if (dataSnapshot.exists())
//
//                    {
//
//
//                        paymentArrayList= new ArrayList<>();
//
//                        for (DataSnapshot ds: dataSnapshot.getChildren())
//                        {
//                            paymentArrayList.add(ds.getValue(payment.class));
//
//                            Toast.makeText(getContext(), "hjh"+ds.getValue(), Toast.LENGTH_SHORT).show();
//
//
//
//
//                        }
//                        paymentview pv = new paymentview(paymentArrayList);
//                        paymentperfarmerlist.setAdapter(pv);
//
//
//
//                    }
//
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                }
//            });
//
//
//        }
//
//
//else{
//            Toast.makeText(getContext(), "not paid yet", Toast.LENGTH_SHORT).show();
//        }


    }
}
