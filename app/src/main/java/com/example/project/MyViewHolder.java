package com.example.project;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder implements Comparable<MyViewHolder>
{
    public TextView ID;
    public TextView name;
    public TextView category;
    public TextView company;
    public TextView cost;
    public TextView score;

    ImageView myImageView;

    public MyViewHolder(View itemView) {
        super(itemView);
        ID = itemView.findViewById(R.id.ID);
        name = itemView.findViewById(R.id.name);
        category = itemView.findViewById(R.id.category);
        company = itemView.findViewById(R.id.company);
        cost = itemView.findViewById(R.id.cost);
        score = itemView.findViewById(R.id.score);
        myImageView = itemView.findViewById(R.id.imageView2);
    }


    public void set(MSI msi)
    {
        ID.setText("Tournament name: " + msi.ID);
        name.setText("Teams: " + msi.Name);
        company.setText("Company: " + msi.Company);
        category.setText("Category: " + msi.Category);
        cost.setText("Prize Pool: " + String.valueOf(msi.Cost) + "USD");
        score.setText("Final score: " + msi.Score);
        if(msi.ID.equals("MSI 2015"))
        {
            myImageView.setImageResource(R.drawable.screenshot1);


        }
        if(msi.ID.equals("MSI 2016"))
        {
            myImageView.setImageResource(R.drawable.screenshot2);


        }
        if(msi.ID.equals("MSI 2017"))
        {
            myImageView.setImageResource(R.drawable.screenshot3);


        }
        if(msi.ID.equals("MSI 2018"))
        {
            myImageView.setImageResource(R.drawable.screenshot4);


        }
        if(msi.ID.equals("MSI 2019"))
        {
            myImageView.setImageResource(R.drawable.screenshot5);


        }
        if(msi.ID.equals("MSI 2021"))
        {
            myImageView.setImageResource(R.drawable.screenshot6);


        }
        if(msi.ID.equals("MSI 2022")) {
            myImageView.setImageResource(R.drawable.screenshot7);


        }
    }

    @Override
    public int compareTo(MyViewHolder myViewHolder) {
        return name.getText().toString().compareTo(myViewHolder.name.getText().toString());
    }
}
