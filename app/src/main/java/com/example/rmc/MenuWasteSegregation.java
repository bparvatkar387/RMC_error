package com.example.rmc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MenuWasteSegregation extends AppCompatActivity {
    JSONObject json_waste_all;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_waste_segregation);

        try {
            json_waste_all = new JSONObject(OpenJSON.readJSONFromAsset(getApplicationContext(), "waste_resources.json"));
            filWasteMenu("menuWaste");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void filWasteMenu(String menuWaste) {
        try {
            JSONArray all_menusJsonArray = json_waste_all.getJSONArray(menuWaste);
            RecyclerView recyclerView = findViewById(R.id.menu_waste_segregation);
            MenuWasteRecycler menuWasteRecycler = new MenuWasteRecycler(getApplicationContext(), all_menusJsonArray);
            recyclerView.setAdapter(menuWasteRecycler);
            recyclerView.setLayoutManager(new LinearLayoutManager(MenuWasteSegregation.this));

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
