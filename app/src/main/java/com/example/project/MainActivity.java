package com.example.project;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener{
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private ArrayList<MSI> MSI_List;

    Gson gson;

    private String Json_File = "json-api.json";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gson = new Gson();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MSI_List = new ArrayList<>();
        adapter = new MyAdapter(MSI_List);
        recyclerView.setAdapter(adapter);

        getJsonFromURL();

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
