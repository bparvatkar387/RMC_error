package com.example.rmc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MenuWasteSegregation extends AppCompatActivity implements MenuWasteRecycler.OnMenuWasteListener{
    JSONObject json_waste_all;
    RecyclerView recyclerView;
    MenuWasteRecycler menuWasteRecycler;

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
            recyclerView = findViewById(R.id.menu_waste_segregation);
            menuWasteRecycler = new MenuWasteRecycler(getApplicationContext(), all_menusJsonArray, this);
            recyclerView.setAdapter(menuWasteRecycler);
            recyclerView.setLayoutManager(new LinearLayoutManager(MenuWasteSegregation.this));

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void OnMenuWasteClickListener(int position) {
        String wasteName = menuWasteRecycler.getClickedWasteName(position);
        Log.i("wasteName", wasteName);

    }
}
