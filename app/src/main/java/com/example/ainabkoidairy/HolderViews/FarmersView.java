package com.example.ainabkoidairy.HolderViews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ainabkoidairy.ItemClickListener;
import com.example.ainabkoidairy.Models.farmers;
import com.example.ainabkoidairy.R;

import java.util.ArrayList;

public class FarmersView extends RecyclerView.Adapter<FarmersView.FarmersViewholder>{

    ArrayList<farmers> farmersArrayList;


    public FarmersView( ArrayList<farmers> farmersArrayList)
    {
        this.farmersArrayList = farmersArrayList;
    }

    @NonNull
    @Override
    public FarmersViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.farmers_list_content,parent,false);

        return new FarmersViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FarmersViewholder holder, int position)
    {
        holder.farmerlistidno.setText(farmersArrayList.get(position).getFarmerid());
        holder.farmerlistname.setText(farmersArrayList.get(position).getFarmername());
        holder.farmerlistphoneno.setText(farmersArrayList.get(position).getFarmerphonenumber());

    }

    @Override
    public int getItemCount() {
        return farmersArrayList.size();
    }

    class FarmersViewholder extends RecyclerView.ViewHolder{
            public TextView farmerlistidno, farmerlistname, farmerlistphoneno;
        public FarmersViewholder(@NonNull View itemView) {
            super(itemView);
        farmerlistidno = (TextView) itemView.findViewById(R.id.farmer_list_idno);
        farmerlistname = (TextView) itemView.findViewById(R.id.farmer_list_name);
        farmerlistphoneno = (TextView) itemView.findViewById(R.id.farmer_list_phoneno);

        }


    }

}





//
//
//public class FarmersView extends RecyclerView.ViewHolder implements View.OnClickListener {
//
//    public ItemClickListener farmersviewlisten;
//    public TextView farmerlistidno, farmerlistname, farmerlistphoneno;
//
//    public FarmersView(@NonNull View itemView) {
//        super(itemView);
//
//        farmerlistidno = (TextView) itemView.findViewById(R.id.farmer_list_idno);
//        farmerlistname = (TextView) itemView.findViewById(R.id.farmer_list_name);
//        farmerlistphoneno = (TextView) itemView.findViewById(R.id.farmer_list_phoneno);
//
//
//    }
//
//    public void setItemClickListener(ItemClickListener farmersviewlisten) {
//        this.farmersviewlisten = farmersviewlisten;
//    }
//
//    @Override
//    public void onClick(View v) {
//        farmersviewlisten.onClick(v, getAdapterPosition(), false);
//
//    }
//}

