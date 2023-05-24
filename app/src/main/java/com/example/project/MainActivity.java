package com.example.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

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
        if (filterText.isEmpty()) {
            filtered_MSI_List.addAll(MSI_List);
        } else {
            for (MSI msiObject : MSI_List) {
                if (msiObject.Name.toLowerCase().contains(filterText.toLowerCase())) {
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
            if (json != null) {
                try {
                    JSONArray jsonArray = new JSONArray(json);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String ID = jsonObject.getString("ID");
                        String name = jsonObject.getString("name");
                        String company = jsonObject.getString("company");
                        String location = jsonObject.getString("location");
                        String category = jsonObject.getString("category");
                        int cost = jsonObject.getInt("cost");
                        String score = jsonObject.getString("auxdata");

                        MSI msi = new MSI(ID, name, company, location, category, score,cost);
                        MSI_List.add(msi);
                    }
                    adapter.set(MSI_List);
                    filterData(savedFilter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        }


    };


    @Override
    public void onPostExecute(String json) {

    }
}
