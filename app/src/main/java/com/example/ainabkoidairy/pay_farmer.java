package com.example.ainabkoidairy;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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
    EditText currentyear, Amountpaidnow, daytxt;
    String selectedid;
    Spinner chosenmonth,timespanfilter;
    Button viewpossiblepay,submitpayment;
    String timespan,month;
    String payabled;
    String monthn, truncmonth;

int total=0;
    int t;
    String existingpay;
//        int existingpayee = Integer.parseInt(existingpay);

    String payable;
    String supply;
    //        int payablee =Integer.parseInt(payable);
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
        daytxt = view.findViewById(R.id.day);

        chosenmonthid.setVisibility(View.INVISIBLE);
        selectedid = paymilkIDNO.getText().toString();
        paymenthistorylink = view.findViewById(R.id.payment_history_link);

        payable = Amountpayable.getText().toString();
        existingpay = Amountpaid.getText().toString();
        supply = milksupplied.getText().toString();



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
                    truncmonth = month.substring(0, 3);
                    chosenmonthid.setText(month);
                    chosenmonthid.setVisibility(View.VISIBLE);

                    //String inmonthno = (String) DateFormat.format("MM", calendar.set);
                    monthn = String.valueOf(id);
                    // monthn=(String) DateFormat.format("MM",calendar.getTime());
                    // Toast.makeText(getContext(), "id"+Long.parseLong(month), Toast.LENGTH_SHORT).show();

                }
                if (id==1)
                {
                    month = parent.getItemAtPosition(position).toString();
                    truncmonth = month.substring(0, 3);
                    //Toast.makeText(getContext(), "trun"+truncmonth, Toast.LENGTH_SHORT).show();
                    chosenmonthid.setVisibility(View.INVISIBLE);
                    monthn = "01";
                }
                if (id==2)
                {
                    month = parent.getItemAtPosition(position).toString();
                    chosenmonthid.setVisibility(View.INVISIBLE);
                    truncmonth = month.substring(0, 3);
                    //  Toast.makeText(getContext(), "trun"+truncmonth, Toast.LENGTH_SHORT).show();
                    monthn = "02";
                }
                if (id==3)
                {
                    month = parent.getItemAtPosition(position).toString();
                    truncmonth = month.substring(0, 3);
                    chosenmonthid.setVisibility(View.INVISIBLE);
                    monthn = "03";
                }
                if (id==4)
                {
                    month = parent.getItemAtPosition(position).toString();
                    truncmonth = month.substring(0, 3);
                    chosenmonthid.setVisibility(View.INVISIBLE);


                    //Toast.makeText(getContext(), "trun"+truncmonth, Toast.LENGTH_SHORT).show();
                    monthn = "04";
                }
                if (id==5)
                {
                    month = parent.getItemAtPosition(position).toString();
                    truncmonth = month.substring(0, 3);
                    chosenmonthid.setVisibility(View.INVISIBLE);
                    monthn = "05";
                }
                if (id==6)
                {
                    month = parent.getItemAtPosition(position).toString();
                    truncmonth = month.substring(0, 3);
                    chosenmonthid.setVisibility(View.INVISIBLE);
                    monthn = "06";
                }
                if (id==7)
                {
                    month = parent.getItemAtPosition(position).toString();
                    truncmonth = month.substring(0, 3);
                    chosenmonthid.setVisibility(View.INVISIBLE);
                    monthn = "07";
                }
                if (id==8)
                {
                    month = parent.getItemAtPosition(position).toString();
                    truncmonth = month.substring(0, 3);
                    chosenmonthid.setVisibility(View.INVISIBLE);
                    monthn = "08";
                }
                if (id==9)
                {
                    month = parent.getItemAtPosition(position).toString();
                    truncmonth = month.substring(0, 3);
                    chosenmonthid.setVisibility(View.INVISIBLE);
                    monthn = "09";
                }
                if (id==10)
                {
                    month = parent.getItemAtPosition(position).toString();
                    truncmonth = month.substring(0, 3);
                    chosenmonthid.setVisibility(View.INVISIBLE);
                    monthn = "10";
                }
                if (id==11)
                {
                    month = parent.getItemAtPosition(position).toString();
                    truncmonth = month.substring(0, 3);
                    chosenmonthid.setVisibility(View.INVISIBLE);
                    monthn = "11";
                }
                if (id==12)
                {
                    month = parent.getItemAtPosition(position).toString();
                    truncmonth = month.substring(0, 3);
                    chosenmonthid.setVisibility(View.INVISIBLE);
                    monthn = "12";
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


        String inday = (String) DateFormat.format("dd", calendar.getTime());
        daytxt.setText(inday);



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

                if (TextUtils.isEmpty(supply)) {


                    holderdetailscard.setVisibility(View.VISIBLE);
                    final String cYear = currentyear.getText().toString();
                    final String cmonth = month;
                    String frquency = timespan;

                    selectedid = paymilkIDNO.getText().toString();
                    DatabaseReference idnolistrefer = FirebaseDatabase.getInstance().getReference("milk");
                    ValueEventListener valueEventListener;
                    idnolistrefer.child(selectedid).child("timestamp").orderByChild(truncmonth).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            if (dataSnapshot.exists()) {
                                int tot = 0;
                                Iterable<DataSnapshot> childrensnapshot = dataSnapshot.getChildren();
//                      ArrayList<milk> milks = new ArrayList<>();
                                for (DataSnapshot dataSnapshoti : childrensnapshot) {
                                    milk m = dataSnapshoti.getValue(milk.class);

                                    if ((cYear).equals(m.getYear()) && truncmonth.equalsIgnoreCase(m.getMonthstring())) {
                                        tot = tot + Integer.parseInt(m.getAmount());

//     payabled = total*35;

                                        //total=Integer.parseInt(m.getAmount());
                                        milksupplied.setText(String.valueOf(tot));

                                        int payab = tot * 35;

                                        payabled = String.valueOf(payab);


                                    } else {
                                        milksupplied.setText("0");
                                    }


                                }
                            } else
{


    milksupplied.setText("0");
}
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                    final DatabaseReference paylistrefermilk = FirebaseDatabase.getInstance().getReference("payment");
                    paylistrefermilk.child(selectedid).child("timestamp").orderByChild(truncmonth).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                int totp = 0;
                                Iterable<DataSnapshot> childrensnapshot = dataSnapshot.getChildren();
                                for (DataSnapshot dataSnapshoti : childrensnapshot) {


                                    payment payinstance = dataSnapshoti.getValue(payment.class);

                                    if ((cYear).equals(payinstance.getForyear()) && truncmonth.equalsIgnoreCase(payinstance.getFormonth())) {
                                        totp = totp + Integer.parseInt(payinstance.getPaidnow());
                                        Amountpaid.setText(String.valueOf(totp));

                                        DatabaseReference idnolistrefer = FirebaseDatabase.getInstance().getReference("milk");
                                        ValueEventListener valueEventListener;
                                        final int finalTotp = totp;
                                        idnolistrefer.child(selectedid).child("timestamp").orderByChild(truncmonth).addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                if (dataSnapshot.exists()) {
                                                    int tot = 0;
                                                    Iterable<DataSnapshot> childrensnapshot = dataSnapshot.getChildren();
                                                    for (DataSnapshot dataSnapshoti : childrensnapshot) {
                                                        milk m = dataSnapshoti.getValue(milk.class);

                                                        if ((cYear).equals(m.getYear()) && truncmonth.equalsIgnoreCase(m.getMonthstring())) {
                                                            tot = tot + Integer.parseInt(m.getAmount());


                                                            milksupplied.setText(String.valueOf(tot));

                                                            int payab = tot * 35;

                                                            int tobepaid = payab - finalTotp;

                                                            payabled = String.valueOf(tobepaid);
                                                            Amountpayable.setText(payabled);


                                                        }


                                                    }


                                                }


                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });


                                    } else {
                                        Amountpaid.setText("0");


                                        DatabaseReference idnolistrefer = FirebaseDatabase.getInstance().getReference("milk");
                                        ValueEventListener valueEventListener;
                                        final int finalTotp = totp;
                                        idnolistrefer.child(selectedid).child("timestamp").orderByChild(truncmonth).addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                if (dataSnapshot.exists()) {
                                                    int tot = 0;
                                                    Iterable<DataSnapshot> childrensnapshot = dataSnapshot.getChildren();
//                      ArrayList<milk> milks = new ArrayList<>();
                                                    for (DataSnapshot dataSnapshoti : childrensnapshot) {
                                                        milk m = dataSnapshoti.getValue(milk.class);

                                                        if ((cYear).equals(m.getYear()) && truncmonth.equalsIgnoreCase(m.getMonthstring())) {
                                                            tot = tot + Integer.parseInt(m.getAmount());

//     payabled = total*35;

                                                            //total=Integer.parseInt(m.getAmount());
                                                            milksupplied.setText(String.valueOf(tot));

                                                            int payab = tot * 35;

//                                                                            int tobepaid= payab- finalTotp;

                                                            payabled = String.valueOf(payab);
                                                            Amountpayable.setText(payabled);


                                                        } else {
                                                            Amountpayable.setText("0");
                                                        }


                                                    }


                                                }


                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });


                                    }

                                }


                            } else {


                                DatabaseReference idnolistrefer = FirebaseDatabase.getInstance().getReference("milk");
                                ValueEventListener valueEventListener;

                                idnolistrefer.child(selectedid).child("timestamp").orderByChild(truncmonth).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                        if (dataSnapshot.exists()) {
                                            int tot = 0;
                                            Iterable<DataSnapshot> childrensnapshot = dataSnapshot.getChildren();
                                            for (DataSnapshot dataSnapshoti : childrensnapshot) {
                                                milk m = dataSnapshoti.getValue(milk.class);

                                                if ((cYear).equals(m.getYear()) && truncmonth.equalsIgnoreCase(m.getMonthstring())) {
                                                    tot = tot + Integer.parseInt(m.getAmount());

                                                    int payab = tot * 35;

                                                    int tobepaid = payab;

                                                    payabled = String.valueOf(tobepaid);
                                                    Amountpayable.setText(payabled);


                                                }


                                            }


                                        }


                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });


                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                } else {
                    Toast.makeText(getContext(), "already computed", Toast.LENGTH_SHORT).show();
                }







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


                FragmentTransaction fr = getFragmentManager().beginTransaction();
                payment_history_per_farmer fraghistory = new payment_history_per_farmer();
                Bundle bundle = new Bundle();
                bundle.putString("fid", paymilkIDNO.getText().toString());
                fraghistory.setArguments(bundle);

                fr.replace(R.id.mainframe, fraghistory);
                fr.commit();
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
        final String inmonthno = (String) DateFormat.format("MM", calendar.getTime());
        final String inmonthstring = (String) DateFormat.format("MMM", calendar.getTime());
        RandomKey=inday+inmonthno+inmonthstring+saveCurrentTime;

        final String exactdate = daytxt.getText().toString();
        final String amountpaid=Amountpaidnow.getText().toString();


        String payable = Amountpayable.getText().toString();
        selectedid = paymilkIDNO.getText().toString();
        if (TextUtils.isEmpty(amountpaid))
        {
            Amountpaidnow.requestFocus();
            Amountpaidnow.setError("Please enter the amount to be paid");
        } else if (Integer.parseInt(amountpaid) > Integer.parseInt(payable))
        {


            Amountpaidnow.requestFocus();
            Amountpaidnow.setError("Amount paid cannot be greater than the amount payable");
        }
        else
            {


        final DatabaseReference addnewpaymentref;
        addnewpaymentref = FirebaseDatabase.getInstance().getReference().child("payment");
        final HashMap<String, Object> paymentMap = new HashMap<>();
                //final int totalamount= Integer.parseInt(amountpaid) +Integer.parseInt(existingpay);

        paymentMap.put("farmeridno",selectedid);
        paymentMap.put("time",saveCurrentTime);
        paymentMap.put("monthstring",inmonthstring);

        paymentMap.put("monthno",String.valueOf(inmonthno));
        paymentMap.put("foryear",String.valueOf(cYear));
        paymentMap.put("formonth",formonth);
        paymentMap.put("paidnow",amountpaid);
                paymentMap.put("forday", exactdate);

                paymentMap.put("totalmilk",milksupplied.getText().toString());







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


                            final DatabaseReference addnewpaymenthistoryref;
                            addnewpaymenthistoryref = FirebaseDatabase.getInstance().getReference().child("history");
                            final HashMap<String, Object> paymentMap = new HashMap<>();

                            paymentMap.put("farmeridno", selectedid);
                            paymentMap.put("time", saveCurrentTime);
                            paymentMap.put("monthstring", inmonthstring);

                            paymentMap.put("monthno", String.valueOf(inmonthno));
                            paymentMap.put("foryear", String.valueOf(cYear));
                            paymentMap.put("formonth", formonth);
                            paymentMap.put("paidnow", amountpaid);
                            paymentMap.put("forday", exactdate);


                            addnewpaymenthistoryref.child(RandomKey).updateChildren(paymentMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getContext(), "history updated", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getContext(), "history not updated", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError)
                        {

                        }
                    });

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
