package com.example.project;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>
{
    public MyViewHolder myViewHolder;


    public ArrayList<MSI> MSI_List;

    public Context context;

    public MyAdapter(Context context, ArrayList<MSI> list)
    {
        this.context = context;
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
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        MSI msi = MSI_List.get(position);
        holder.set(msi);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MSI msi = MSI_List.get(position);

                createDetailedViewActivity(msi);
            }
        });

    }
    private void createDetailedViewActivity(MSI selectedItem)
    {
        Intent intent = new Intent(context, DetailedViewActivity.class);

        intent.putExtra("Location", selectedItem.location);

        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return MSI_List.size();

    }

}
