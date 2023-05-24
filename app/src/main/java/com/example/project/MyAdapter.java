package com.example.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>
{
    public MyViewHolder myViewHolder;


    public ArrayList<MSI> MSI_List;

    public MyAdapter(ArrayList<MSI> list)
    {
        MSI_List = list;
    }

    public void set(ArrayList<MSI> msiList)
    {
    this.MSI_List = msiList;
    notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
     View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.msi_item, parent, false);
     myViewHolder = new MyViewHolder(view);
     return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MSI msi = MSI_List.get(position);
        holder.set(msi);

    }

    @Override
    public int getItemCount() {
        return MSI_List.size();

    }

}
