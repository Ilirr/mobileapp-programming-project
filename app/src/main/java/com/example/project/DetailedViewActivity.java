package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;

public class DetailedViewActivity extends AppCompatActivity {

    TextView locationText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_view);

        locationText = findViewById(R.id.detailedTextView);

        Intent intent = this.getIntent();
        if (intent != null)
        {
            String location = intent.getStringExtra("Location");

            locationText.setText(location);
        }


    }
}