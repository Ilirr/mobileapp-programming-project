package com.example.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>
{


    private ArrayList<MSI> MSI_List;

    public MyAdapter(ArrayList<MSI> list)
    {
        MSI_List = list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
     View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.msi_item, parent, false);
     return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MSI msi = MSI_List.get(position);
        holder.set(msi);

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
