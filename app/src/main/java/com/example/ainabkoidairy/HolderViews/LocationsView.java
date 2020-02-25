package com.example.ainabkoidairy.HolderViews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ainabkoidairy.Models.locations;
import com.example.ainabkoidairy.R;

import java.util.ArrayList;

public class LocationsView extends RecyclerView.Adapter< LocationsView.LocationsViewholder>{

        ArrayList<locations> locationsArrayList;


public LocationsView( ArrayList<locations> locationsArrayList)
        {
        this.locationsArrayList = locationsArrayList;
        }

    @NonNull
    @Override
    public LocationsViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.locations_contents,parent,false);

        return new LocationsViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationsViewholder holder, int position) {
        holder.locationscontentstxt.setText(locationsArrayList.get(position).getLocation());


    }

    @Override
    public int getItemCount() {
        return locationsArrayList.size();
    }


    class LocationsViewholder extends RecyclerView.ViewHolder{
        public TextView locationscontentstxt;
        public LocationsViewholder(@NonNull View itemView) {
            super(itemView);
            locationscontentstxt = (TextView) itemView.findViewById(R.id.locations_content_txt);


        }


    }
}
