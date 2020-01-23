package com.example.rmc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DiseaseMain extends AppCompatActivity {
    JSONObject allDiseases;
    RecyclerView recyclerView;
    DiseaseRecycler diseaseRecycler;
    EditText searchDisease;
    List<AllDiseasesAdapter> diseasesAdapterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_main);

        searchDisease.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());

            }
        });

        try {
            allDiseases = new JSONObject(OpenJSON.readJSONFromAsset(getApplicationContext(), "diseasedata.json"));

            JSONArray diseases = allDiseases.getJSONArray("diseases");
            int len = diseases.length();
            for(int i = 0; i < len; i++){
                JSONObject object = diseases.getJSONObject(i);
                String productName = object.getString("productName");
                String causesName = object.getString("causesName");
                String sympName = object.getString("sympName");

                diseasesAdapterList.add(new AllDiseasesAdapter(productName, causesName, sympName));

            }

            recyclerView = findViewById(R.id.list_disease);
            diseaseRecycler = new DiseaseRecycler(getApplicationContext(), diseasesAdapterList);
            recyclerView.setAdapter(diseaseRecycler);
            recyclerView.setLayoutManager(new LinearLayoutManager(DiseaseMain.this));



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void filter(String toString) {
        List<AllDiseasesAdapter> temp = new ArrayList();
        for(AllDiseasesAdapter d: diseasesAdapterList){
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if(d.getSympName().contains(toString)){
                temp.add(d);
            }
        }

        //update recyclerview
        diseaseRecycler.updateList(temp);
    }

}
