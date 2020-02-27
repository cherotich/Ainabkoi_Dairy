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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ainabkoidairy.Models.farmers;
import com.example.ainabkoidairy.Models.milk;
import com.example.ainabkoidairy.Models.payment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class pay_farmer extends Fragment {


    public pay_farmer() {
        // Required empty public constructor
    }
    CardView filter_data,holderdetailscard;
    AutoCompleteTextView paymilkIDNO;
    ArrayList<String> idnoArrayListSearch;
    private ArrayAdapter<String> adapter;
    ValueEventListener valueEventListener;
    ImageView payfarmerupdate;
    Calendar calendar;
    SimpleDateFormat currentDate;
    private DatabaseReference idnolistref;
    String saveCurrentDate,saveCurrentTime,RandomKey;
    TextView datetxt,chosenmonthid,Amountpaid,milksupplied,Amountpayable,paymenthistorylink;
    EditText currentyear,Amountpaidnow;
    String selectedid;
    Spinner chosenmonth,timespanfilter;
    Button viewpossiblepay,submitpayment;
    String timespan,month;
    double payabled;
int total=0;
    int t;

    int paidalready=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pay_farmer, container, false);
        payfarmerupdate = view.findViewById(R.id.pay_farmer_update);
        holderdetailscard = view.findViewById(R.id.holder_details_card);
        filter_data = view.findViewById(R.id.filters);
        datetxt = view.findViewById(R.id.pay_farmer_date_txt);
        currentyear = view.findViewById(R.id.current_year);
        paymilkIDNO = view.findViewById(R.id.pay_farmer_IDNO);
        chosenmonth = view.findViewById(R.id.chosen_month);
        timespanfilter = view.findViewById(R.id.time_span_filter);
        idnolistref= FirebaseDatabase.getInstance().getReference("Farmers");
        milksupplied = view.findViewById(R.id.milk_supplied);
        viewpossiblepay = view.findViewById(R.id.view_possiblepay);
        chosenmonthid = view.findViewById(R.id.chosenmonthid);
        submitpayment = view.findViewById(R.id.submit_payment);
        Amountpaidnow = view.findViewById(R.id.Amount_paid_now);
        Amountpaid  = view.findViewById(R.id.Amount_paid);
        Amountpayable = view.findViewById(R.id.Amount_payable);

        chosenmonthid.setVisibility(View.INVISIBLE);
        selectedid = paymilkIDNO.getText().toString();
        paymenthistorylink = view.findViewById(R.id.payment_history_link);



        idnoArrayListSearch = new ArrayList<>();
        adapter = new ArrayAdapter<String>(getContext(),
                R.layout.support_simple_spinner_dropdown_item,idnoArrayListSearch);
        paymilkIDNO.setAdapter(adapter);
        retrieve();

        ArrayAdapter<CharSequence> chosen_adapter = ArrayAdapter.createFromResource(getActivity(), R.array.months,android.R.layout.simple_spinner_item);
        chosen_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chosenmonth.setAdapter(chosen_adapter);


        chosenmonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if (id==0)
                {
                    calendar = Calendar.getInstance();
                    month = (String) DateFormat.format("MMM", calendar.getTime());
                    chosenmonthid.setText(month);
                    chosenmonthid.setVisibility(View.VISIBLE);
                }
                if (id==1)
                {
                    month = parent.getItemAtPosition(position).toString();
                    chosenmonthid.setVisibility(View.INVISIBLE);
                }
                if (id==2)
                {
                    month = parent.getItemAtPosition(position).toString();
                    chosenmonthid.setVisibility(View.INVISIBLE);
                }
                if (id==3)
                {
                    month = parent.getItemAtPosition(position).toString();
                    chosenmonthid.setVisibility(View.INVISIBLE);
                }
                if (id==4)
                {
                    month = parent.getItemAtPosition(position).toString();
                    chosenmonthid.setVisibility(View.INVISIBLE);
                }
                if (id==5)
                {
                    month = parent.getItemAtPosition(position).toString();
                    chosenmonthid.setVisibility(View.INVISIBLE);
                }
                if (id==6)
                {
                    month = parent.getItemAtPosition(position).toString();
                    chosenmonthid.setVisibility(View.INVISIBLE);
                }
                if (id==7)
                {
                    month = parent.getItemAtPosition(position).toString();
                    chosenmonthid.setVisibility(View.INVISIBLE);
                }
                if (id==8)
                {
                    month = parent.getItemAtPosition(position).toString();
                    chosenmonthid.setVisibility(View.INVISIBLE);
                }
                if (id==9)
                {
                    month = parent.getItemAtPosition(position).toString();
                    chosenmonthid.setVisibility(View.INVISIBLE);
                }
                if (id==10)
                {
                    month = parent.getItemAtPosition(position).toString();
                    chosenmonthid.setVisibility(View.INVISIBLE);
                }
                if (id==11)
                {
                    month = parent.getItemAtPosition(position).toString();
                    chosenmonthid.setVisibility(View.INVISIBLE);
                }
                if (id==12)
                {
                    month = parent.getItemAtPosition(position).toString();
                    chosenmonthid.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        ArrayAdapter<CharSequence> limits_adapter = ArrayAdapter.createFromResource(getActivity(), R.array.filter_pay_farmer_margin,android.R.layout.simple_spinner_item);
        limits_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timespanfilter.setAdapter(limits_adapter);
        timespanfilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if (id==0) {

                    timespan = parent.getItemAtPosition(position).toString();
                }
                if (id==1)
                {
                    timespan = parent.getItemAtPosition(position).toString();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)

            {

            }
        });

        filter_data.setVisibility(View.INVISIBLE);
        holderdetailscard.setVisibility(View.INVISIBLE);

        calendar = Calendar.getInstance();
        currentDate = new SimpleDateFormat("yyyymmdd");
        saveCurrentDate = currentDate.format(calendar.getTime());
       // datetxt = view.findViewById(R.id.date_txt);
        datetxt.setText(saveCurrentDate);

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());
        RandomKey = saveCurrentDate + saveCurrentTime;
        int year = calendar.get(Calendar.YEAR);
//       String theyear = currentTime.format(calendar.());
        currentyear.setText(String.valueOf(year));









        payfarmerupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedid = paymilkIDNO.getText().toString();
                if (TextUtils.isEmpty(selectedid))
                {
                    paymilkIDNO.requestFocus();
                    paymilkIDNO.setError("Farmer ID NO required");

                }
                else
                {
                    idnolistref.child(selectedid).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot)

                        {
                            if (dataSnapshot.exists())
                            {
                                farmers farmerssnapshot = dataSnapshot.getValue(farmers.class);
                                filter_data.setVisibility(View.VISIBLE);



                            }
                            else
                            {
                                paymilkIDNO.requestFocus();
                                paymilkIDNO.setError("Farmer ID NO does not exist");


                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }


            }
        });



        viewpossiblepay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                holderdetailscard.setVisibility(View.VISIBLE);
                final String cYear = currentyear.getText().toString();
                final String cmonth = month;
                String frquency = timespan;

                selectedid = paymilkIDNO.getText().toString();
                DatabaseReference  idnolistrefer= FirebaseDatabase.getInstance().getReference("milk");
                ValueEventListener valueEventListener;
                idnolistrefer.child(selectedid).child("timestamp").addValueEventListener(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {

                      Iterable<DataSnapshot> childrensnapshot = dataSnapshot.getChildren();
//                      ArrayList<milk> milks = new ArrayList<>();
                      for (DataSnapshot dataSnapshoti:childrensnapshot)

                      {
                          milk  m = dataSnapshoti.getValue(milk.class);
                          total=total+Integer.parseInt(m.getAmount());
if ((cYear) .equals(m.getYear())&&cmonth.equalsIgnoreCase(m.getMonthstring()))
{

//     payabled = total*35;

    //total=Integer.parseInt(m.getAmount());
    milksupplied.setText(String.valueOf(total));
}

else
{
    milksupplied.setText("0");
}
//                          milks.add(m);


                      }

//
//                        if (dataSnapshot.exists())
//
//                        {
//                            farmers farmerssnapshot = dataSnapshot.getValue(farmers.class);
//                            receivemilkname.setText(farmerssnapshot.getFarmername());
//                            receivemilkphoneno.setText(farmerssnapshot.getFarmerphonenumber());
//                            receivemilklocation.setText(farmerssnapshot.getLocation());
//                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError)
                    {

                    }
                });




                final DatabaseReference  idnolistrefermilk= FirebaseDatabase.getInstance().getReference("payment");
                idnolistrefermilk.child(selectedid).child("totalpaid").addValueEventListener(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {
                        if (dataSnapshot.exists())
                        {

                            final String totalpaid = dataSnapshot.getValue().toString();
                            Amountpaid.setText(totalpaid);


                            idnolistrefermilk.child(selectedid).child("timestamp").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    Iterable<DataSnapshot> childrensnapshot = dataSnapshot.getChildren();
                                    for (DataSnapshot dataSnapshoti : childrensnapshot) {

                                        payment m = dataSnapshoti.getValue(payment.class);
                                        String totm = m.getTotalmilk();

                                        int totmi = Integer.parseInt(totm);

                                        int totpa = Integer.parseInt(totalpaid);

                                        int totreq = totmi * 35;

                                        int payabl = totreq - totpa;


                                        Amountpayable.setText(String.valueOf(payabl));


                                    }


                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });



//
//                            Iterable<DataSnapshot> paymentnapshot = dataSnapshot.getChildren();
////                      ArrayList<milk> milks = new ArrayList<>();
//                            for (DataSnapshot psnapshot:paymentnapshot)
//
//                            {
//
//
//                                payment  p = psnapshot.getValue(payment.class);
//                                Amountpaid.setText(p.getTotalpaid());
//
////                                        String x= p.getTotalpaid();
////
//////                            paidalready= Integer.parseInt(x);
////
////
////                                        //String w = p.getPaidalready();
////
////                                        int paidb = Integer.parseInt(x)/35;
////                                        String q = p.getTotalmilk();
////
////                                        int tot = Integer.parseInt(q);
////
////                                        int sub = tot-paidb;
////                                        int totalsub = sub*35;
////
////
////                                        Amountpayable.setText(String.valueOf(totalsub));
//
//
//
//
//
//                            }



                        }
                        else
                        {


                            Amountpaid.setText("0");

                            Amountpayable.setText("0");
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError)
                    {

                    }
                });


            }
        });




        submitpayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitpayment();
            }
        });



        paymenthistorylink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });


        return view;
    }

    private void submitpayment()

    {
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());




        final String cYear = currentyear.getText().toString();
        final String formonth = month;

        String inday = (String) DateFormat.format("dd",calendar.getTime());
        String inmonthno = (String) DateFormat.format("MM",calendar.getTime());
        String inmonthstring = (String) DateFormat.format("MMM", calendar.getTime());
        RandomKey=inday+inmonthno+inmonthstring+saveCurrentTime;


        final String amountpaid=Amountpaidnow.getText().toString();

//        final int amountpaide= Integer.parseInt(amountpaid);

        String existingpay=Amountpaid.getText().toString();
//        int existingpayee = Integer.parseInt(existingpay);

        String payable = Amountpayable.getText().toString();
//        int payablee =Integer.parseInt(payable);
        selectedid = paymilkIDNO.getText().toString();
        if (TextUtils.isEmpty(amountpaid))
        {
            Amountpaidnow.requestFocus();
            Amountpaidnow.setError("Please enter the amount to be paid");
        }
        else if (Integer.parseInt(amountpaid)>Integer.parseInt(amountpaid))
        {


            Amountpaidnow.requestFocus();
            Amountpaidnow.setError("Amount paid cannot be greater than the amount payable");
        }
        else
            {


        final DatabaseReference addnewpaymentref;
        addnewpaymentref = FirebaseDatabase.getInstance().getReference().child("payment");
        final HashMap<String, Object> paymentMap = new HashMap<>();
               final int totalamount= Integer.parseInt(amountpaid) +Integer.parseInt(existingpay);

        paymentMap.put("farmeridno",selectedid);
        paymentMap.put("time",saveCurrentTime);
        paymentMap.put("monthstring",inmonthstring);

        paymentMap.put("monthno",String.valueOf(inmonthno));
        paymentMap.put("foryear",String.valueOf(cYear));
        paymentMap.put("formonth",formonth);
        paymentMap.put("paidnow",amountpaid);
              //paymentMap.put("amountpayable",);
//                paymentMap.put("paidalready",String.valueOf(totalamount));

//                int milkunpaid= totalamount- Integer.parseInt(milksupplied.getText().toString())*35;
//                paymentMap.put("milkunpaid",String.valueOf(milkunpaid));
                paymentMap.put("totalmilk",milksupplied.getText().toString());
//                int paidmilk = Integer.parseInt(milksupplied.getText().toString())-totalamount/35;
//                paymentMap.put("paidmilk",String.valueOf(paidmilk));






        addnewpaymentref.child(selectedid).child("timestamp").child(RandomKey).updateChildren(paymentMap).addOnCompleteListener(new OnCompleteListener<Void>()
        {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                if (task.isSuccessful())
                {
                    addnewpaymentref.child(selectedid).child("totalpaid").addListenerForSingleValueEvent(new ValueEventListener()
                    {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                        {
                            if (dataSnapshot.exists())
                            {
//                               payment  payments = dataSnapshot.getValue(payment.class);

                             int paid=  Integer.parseInt(dataSnapshot.getValue().toString()) + Integer.parseInt(amountpaid);

                                addnewpaymentref.child(selectedid).child("totalpaid").setValue(String.valueOf(paid)).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task)
                                    {
                                        if (task.isSuccessful())
                                        {
                                            Amountpaidnow.setText("");
                                            Toast.makeText(getContext(), "Successful", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });

                            }

                            else
                            {

                                addnewpaymentref.child(selectedid).child("totalpaid").setValue(amountpaid);
                                Amountpaidnow.setText("");
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError)
                        {

                        }
                    });

//                    addnewpaymentref.child(selectedid).child("timestamp").child(RandomKey).child("amountpayable").addValueEventListener(new ValueEventListener()
//                    {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot dataSnapshot)
//                        {
//                            if (dataSnapshot.exists())
//                            {
//                                DatabaseReference  idnolistrefermilk= FirebaseDatabase.getInstance().getReference("payment");
//                                idnolistrefermilk.child(selectedid).child("timestamp").addValueEventListener(new ValueEventListener()
//                                {
//                                    @Override
//                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
//                                    {
//
//                                        if (dataSnapshot.exists())
//                                        {
//
//                                            Iterable<DataSnapshot> paymentnapshot = dataSnapshot.getChildren();
//                                            for (DataSnapshot psnapshot:paymentnapshot)
//
//                                            {
//
//
//                                                payment  p = psnapshot.getValue(payment.class);
//                                              int payab= Integer.parseInt(p.getAmountpayable())-Integer.parseInt(amountpaid) ;
//
//                                                int paidmilk = Integer.parseInt(p.getMilkunpaid())*35;
//
//
//
//
//                                                addnewpaymentref.child(selectedid).child("timestamp").child(RandomKey).child("amountpayable").setValue(String.valueOf(paidmilk)).addOnCompleteListener(new OnCompleteListener<Void>()
//                                                {
//                                                    @Override
//                                                    public void onComplete(@NonNull Task<Void> task)
//                                                    {
//                                                        if (task.isSuccessful())
//                                                        {
//                                                            Toast.makeText(getContext(), "Wow it is a success", Toast.LENGTH_SHORT).show();
//                                                        }
//                                                        else {
//                                                            Toast.makeText(getContext(), "oops fail", Toast.LENGTH_SHORT).show();
//                                                        }
//                                                    }
//                                                });
//
//
//
//                                            }
//                                        }
//
//
//
//
//
//
//                                    }
//
//                                    @Override
//                                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                                    }
//                                });
//
//
//
//                            }
//                            else
//                            {
//                                addnewpaymentref.child(selectedid).child("timestamp").child(RandomKey).child("amountpayable").setValue(String.valueOf(t));
//                            }
//
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                        }
//                    });
                    Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
                    Amountpaidnow.setText("");
                }
                else
                {
                    Toast.makeText(getContext(), "unsuccessful", Toast.LENGTH_SHORT).show();
                }

            }
        });


        }

    }

    private void retrieve() {

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
