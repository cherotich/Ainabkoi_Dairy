package com.example.ainabkoidairy;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ainabkoidairy.Models.farmers;
import com.example.ainabkoidairy.Models.milk;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class receive_milk extends Fragment {


    public receive_milk() {
        // Required empty public constructor
    }
String saveCurrentDate,saveCurrentTime,RandomKey;
    TextView datetxt;
    AutoCompleteTextView receivemilkIDNO;
    ArrayList<String> idnoArrayListSearch;
    ArrayAdapter<String> adapter;
    ValueEventListener valueEventListener;
    private DatabaseReference idnolistref, milkperlocation;
    String selectedid,milkinlitres;
    TextView receivemilkname,receivemilkphoneno,receivemilklocation;
    ImageView receiveupdate;
    CardView holdercard;
    EditText amountofmilk;
    Button submitmilk;
    Calendar calendar;
    SimpleDateFormat currentDate;
    Double totalmilk = 0.0;


    String day;
    String year;
    String monthno;
    String monthstring;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_receive_milk, container, false);
        holdercard=view.findViewById(R.id.holder_card);

        receivemilklocation = view.findViewById(R.id.receive_milk_location);
        receivemilkname = view.findViewById(R.id.receive_milk_name);
        receivemilkphoneno = view.findViewById(R.id.receive_milk_phoneno);
        receiveupdate = view.findViewById(R.id.receive_update);
        amountofmilk = view.findViewById(R.id.amount_of_milk);
        submitmilk = view.findViewById(R.id.submit_milk);


        idnolistref= FirebaseDatabase.getInstance().getReference("Farmers");
        milkperlocation = FirebaseDatabase.getInstance().getReference("Milk per location");
        idnoArrayListSearch = new ArrayList<>();
        receivemilkIDNO = view.findViewById(R.id.receive_milk_IDNO);
        adapter = new ArrayAdapter<String>(getContext(),
                R.layout.support_simple_spinner_dropdown_item,idnoArrayListSearch);
        receivemilkIDNO.setAdapter(adapter);
        retrieve();
        holdercard.setVisibility(View.INVISIBLE);
        amountofmilk.setVisibility(View.INVISIBLE);
        submitmilk.setVisibility(View.INVISIBLE);


        calendar = Calendar.getInstance();
        currentDate = new SimpleDateFormat("yyyyMMdd ");
        saveCurrentDate = currentDate.format(calendar.getTime());
       // try {
//          date  = currentDate.parse(saveCurrentDate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        String day = (String) DateFormat.format("dd",saveCurrentDate);

        datetxt = view.findViewById(R.id.date_txt);
        datetxt.setText(saveCurrentDate);

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

      RandomKey = saveCurrentDate + saveCurrentTime;
//      String dater= DateFormat.getDateInstance().format(calendar.getTime());
 day = (String) DateFormat.format("dd",calendar.getTime());
 year = (String) DateFormat.format("yyyy",calendar.getTime());
 monthno = (String) DateFormat.format("MM",calendar.getTime());
 monthstring = (String) DateFormat.format("MMM", calendar.getTime());

        submitmilk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                milkinlitres = amountofmilk.getText().toString();
                if (TextUtils.isEmpty(milkinlitres))
                {
                    amountofmilk.requestFocus();
                    amountofmilk.setError("Please enter the amount of milk in litres");
                }

                else
                {
                    selectedid = receivemilkIDNO.getText().toString();


                    AddMilk(selectedid,saveCurrentDate,saveCurrentTime,milkinlitres);


                }
            }


        });

        receiveupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String idno = receivemilkIDNO.getText().toString();
                if (TextUtils.isEmpty(idno)) {
                    receivemilkIDNO.requestFocus();
                    receivemilkIDNO.setError("please enter the ID no");
                    amountofmilk.setVisibility(View.INVISIBLE);
                    holdercard.setVisibility(View.INVISIBLE);
                    submitmilk.setVisibility(View.INVISIBLE);
                }
                else
                {



                    idnolistref.child(idno).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                farmers farmerssnapshot = dataSnapshot.getValue(farmers.class);
                                receivemilkname.setText(farmerssnapshot.getFarmername());
                                receivemilkphoneno.setText(farmerssnapshot.getFarmerphonenumber());
                                receivemilklocation.setText(farmerssnapshot.getLocation());
                                amountofmilk.setVisibility(View.VISIBLE);
                                holdercard.setVisibility(View.VISIBLE);
                                submitmilk.setVisibility(View.VISIBLE);
                                amountofmilk.requestFocus();
                            }
                            else
                                {
                                    amountofmilk.setVisibility(View.INVISIBLE);
                                    holdercard.setVisibility(View.INVISIBLE);
                                    submitmilk.setVisibility(View.INVISIBLE);
                                 receivemilkIDNO.requestFocus();
                                receivemilkIDNO.setError("Kindly ensure that you have entered the correct ID NO");
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {


                        }
                    });

            }
            }
        });

//        receivemilkIDNO.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
//        {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
//            {
//                selectedid = parent.getItemAtPosition(position).toString();
//
//                Toast.makeText(getContext(),"selected id is"+ selectedid, Toast.LENGTH_LONG).show();
//
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });






        return view;
    }


    private void AddMilk(final String selectedid, final String saveCurrentDate, final String saveCurrentTime, final String milkinlitres)

    {

        final DatabaseReference addnewmilkref;
        addnewmilkref = FirebaseDatabase.getInstance().getReference().child("milk");
        final HashMap<String, Object> milkMap = new HashMap<>();
        Long x= new Date().getTime();
        milkMap.put("amount",milkinlitres);
        milkMap.put("date",saveCurrentDate);
        milkMap.put("time",saveCurrentTime);
        milkMap.put("milk_id",RandomKey);
        milkMap.put("xvalue",x);
        milkMap.put("year",year);
        milkMap.put("month",monthno);
        milkMap.put("monthstring",monthstring);
        milkMap.put("day",day);


        addnewmilkref.child(selectedid).child("timestamp").child(RandomKey).updateChildren(milkMap).addOnCompleteListener(new OnCompleteListener<Void>()
       {
           @Override
           public void onComplete(@NonNull Task<Void> task) {
               if (task.isSuccessful())
               {


                   addnewmilkref.child(selectedid).addListenerForSingleValueEvent(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                       {
                           if (dataSnapshot.child("totalmilk").exists())
                           {


                               String idno = receivemilkIDNO.getText().toString();

                               final DatabaseReference idnolistreference;
                               idnolistreference = FirebaseDatabase.getInstance().getReference().child("Farmers");

                               idnolistreference.child(idno).addValueEventListener(new ValueEventListener() {
                                   @Override
                                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                       farmers farmerssnapshot = dataSnapshot.getValue(farmers.class);
                                       final String farmerloc = farmerssnapshot.getLocation();

                                       milkperlocation = FirebaseDatabase.getInstance().getReference("Milk per location");


                                       HashMap<String, Object> milklocMap = new HashMap<>();

                                       milklocMap.put("amount", milkinlitres);
                                       milklocMap.put("date", saveCurrentDate);
                                       milklocMap.put("time", saveCurrentTime);
                                       milklocMap.put("milk_id", RandomKey);
                                       milklocMap.put("year", year);
                                       milklocMap.put("month", monthno);
                                       milklocMap.put("monthstring", monthstring);
                                       milklocMap.put("day", day);

                                       milkperlocation.child(farmerloc).child(RandomKey).updateChildren(milklocMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                           @Override
                                           public void onComplete(@NonNull Task<Void> task) {

                                               if (task.isSuccessful()) {
                                                   Toast.makeText(getContext(), "milk location updated", Toast.LENGTH_SHORT).show();
                                               } else {
                                                   Toast.makeText(getContext(), "milk location  unsuccessfully", Toast.LENGTH_SHORT).show();
                                               }
                                           }
                                       });


                                   }

                                   @Override
                                   public void onCancelled(@NonNull DatabaseError databaseError) {

                                   }
                               });




                               milk milkdata = dataSnapshot.getValue(milk.class);
                           //String milk =    milkdata.getTotalmilk().toString();

                           Double total = Double.parseDouble(milkdata.getTotalmilk())+Double.parseDouble(milkinlitres);

                               addnewmilkref.child(selectedid).child("totalmilk").setValue(String.valueOf(total)).addOnCompleteListener(new OnCompleteListener<Void>()
                               {
                                   @Override
                                   public void onComplete(@NonNull Task<Void> task) {
                                       if (task.isSuccessful())
                                       {


                                           Toast.makeText(getContext(), "successfull", Toast.LENGTH_SHORT).show();

                                           receivemilkIDNO.setText("");
                                           amountofmilk.setText("");
                                           amountofmilk.setVisibility(View.INVISIBLE);
                                           holdercard.setVisibility(View.INVISIBLE);
                                           submitmilk.setVisibility(View.INVISIBLE);

                                       }
                                       else {
                                           Toast.makeText(getContext(), "unsuccessfull", Toast.LENGTH_SHORT).show();
                                       }
                                   }
                               });
                               //Toast.makeText(getContext(), "everything is successfull", Toast.LENGTH_SHORT).show();


                           }
                           else
                           {
//
//                               String idno = receivemilkIDNO.getText().toString();
//
//                               idnolistref.child(idno).addValueEventListener(new ValueEventListener()
//                               {
//                                   @Override
//                                   public void onDataChange(@NonNull DataSnapshot dataSnapshot)
//                                   {
//
//                                       farmers farmerssnapshot = dataSnapshot.getValue(farmers.class);
//                                       String farmerloc = farmerssnapshot.getLocation();
//                                       milkperlocation = FirebaseDatabase.getInstance().getReference("Milk per location");
//
//
//
//                                       final HashMap<String, Object> milkMap = new HashMap<>();
//
//                                       milkMap.put("amount",milkinlitres);
//                                       milkMap.put("date",saveCurrentDate);
//                                       milkMap.put("time",saveCurrentTime);
//                                       milkMap.put("milk_id",RandomKey);
//                                       milkMap.put("year",year);
//                                       milkMap.put("month",monthno);
//                                       milkMap.put("monthstring",monthstring);
//                                       milkMap.put("day",day);
//
//                                       milkperlocation.child(farmerloc).child(RandomKey).updateChildren(milkMap).addOnCompleteListener(new OnCompleteListener<Void>()
//                                       {
//                                           @Override
//                                           public void onComplete(@NonNull Task<Void> task)
//                                           {
//
//                                               if (task.isSuccessful())
//
//                                               {
//                                                   Toast.makeText(getContext(), "milk location updated", Toast.LENGTH_SHORT).show();
//                                               }
//                                               else
//                                               {
//                                                   Toast.makeText(getContext(), "milk location  unsuccessfully", Toast.LENGTH_SHORT).show();
//                                               }
//                                           }
//                                       });
//
//                                   }
//
//                                   @Override
//                                   public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                                   }
//                               });
//



                               addnewmilkref.child(selectedid).child("totalmilk").setValue(milkinlitres);
                               receivemilkIDNO.setText("");
                               amountofmilk.setText("");
                               amountofmilk.setVisibility(View.INVISIBLE);
                               holdercard.setVisibility(View.INVISIBLE);
                               submitmilk.setVisibility(View.INVISIBLE);
                               //Toast.makeText(getContext(), "please ensure that you have entered the correct ID NO", Toast.LENGTH_SHORT).show();


                           }


                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError databaseError) {

                       }
                   });


               }
               else
               {
                   Toast.makeText(getContext(), "Milk entry unsuccessful, kindly retry", Toast.LENGTH_LONG).show();
               }
           }
       });

    }

    private void retrieve()
    {
        valueEventListener= idnolistref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot locate:dataSnapshot.getChildren())
                {
                    idnoArrayListSearch.add(locate.getKey());


                }
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
