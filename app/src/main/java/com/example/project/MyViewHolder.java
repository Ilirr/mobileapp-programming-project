package com.example.project;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder
{
    public TextView ID;
    public TextView name;
    public TextView location;
    public TextView category;
    public TextView company;
    public TextView cost;
    public TextView score;

    public MyViewHolder(View itemView) {
        super(itemView);
        ID = itemView.findViewById(R.id.ID);
        name = itemView.findViewById(R.id.name);
        location = itemView.findViewById(R.id.location);
        category = itemView.findViewById(R.id.category);
        company = itemView.findViewById(R.id.company);
        cost = itemView.findViewById(R.id.cost);
        score = itemView.findViewById(R.id.score);
    }

    public void set(MSI msi)
    {
        ID.setText(msi.ID);
        name.setText(msi.Name);
        company.setText(msi.Company);
        location.setText(msi.Location);
        category.setText(msi.Category);
        cost.setText(String.valueOf(msi.Cost));
        score.setText(msi.Score);
       // sizeTextView.setText(String.valueOf(msi.getSize()));

    }

}
