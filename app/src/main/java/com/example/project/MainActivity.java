package com.example.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener{
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private ArrayList<MSI> MSI_List;
    private ArrayList<MSI> filtered_MSI_List;
    private EditText filterText;
    private SharedPreferences sharedPreferences;

    private static final String JSON_FILE = "json/json-api.json";
    private static final String FILTER_PREFS = "filter_pref";
    private static final String FILTER_KEY = "filter_key";
    Gson gson;

    Button aboutButton;

    String savedFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gson = new Gson();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        filterText = findViewById(R.id.filterText);


        MSI_List = new ArrayList<>();
        filtered_MSI_List = new ArrayList<>();

        aboutButton = findViewById(R.id.button);

        getJsonFromURL();



        adapter = new MyAdapter(MainActivity.this,filtered_MSI_List);
        recyclerView.setAdapter(adapter);



        sharedPreferences = getSharedPreferences(FILTER_PREFS, MODE_PRIVATE);
        savedFilter = sharedPreferences.getString(FILTER_KEY, "");
        filterText.setText(savedFilter);


        filterText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s != null)
                {
                    String userInput = s.toString().trim();
                    filterData(userInput);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }

        });

        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);

            }
        });
    }


    public void filterData(String filterText)
    {
        filtered_MSI_List.clear();
        if (filterText.isEmpty())
        {
            filtered_MSI_List.addAll(MSI_List);
        }
        else
        {
            for (MSI msiObject : MSI_List) {
                if (msiObject.name.toLowerCase().contains(filterText.toLowerCase()))
                {
                    filtered_MSI_List.add(msiObject);
                }
            }
        }

        adapter.set(filtered_MSI_List);
        adapter.notifyDataSetChanged();


        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(FILTER_KEY, filterText);
        editor.apply();

    }


    private void getJsonFromURL()
    {
        new JsonTask(jsonTaskListener).execute("https://mobprog.webug.se/json-api?login=a22iliru");
    }
    private JsonTask.JsonTaskListener jsonTaskListener = new JsonTask.JsonTaskListener()
    {
        @Override
        public void onPostExecute(String json)
        {
            if (json != null)
            {
                List<MSI> msiList = gson.fromJson(json, new TypeToken<List<MSI>>(){}.getType());

                MSI_List.addAll(msiList);
                adapter.set(MSI_List);
                filterData(savedFilter);


            }


        }


    };


    @Override
    public void onPostExecute(String json) {

    }
}
