package com.example.rmc.menuListOut;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rmc.OpenJSON;
import com.example.rmc.R;
import com.example.rmc.customAdapters.FoodTestModel;
import com.example.rmc.customAdapters.FoodTestRecycler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MenuFoodFragment extends Fragment {
    JSONObject json_menu_all;
    JSONArray menuList;

    RecyclerView recyclerView;
    List<FoodTestModel> modelList;
    FoodTestRecycler foodTestRecycler;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_list_food, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        modelList = new ArrayList<>();

        try {
            json_menu_all = new JSONObject(OpenJSON.readJSONFromAsset(getContext(), "milk_products.json"));

            Log.i("json_menu_all_list", json_menu_all.toString());

            menuList = json_menu_all.getJSONArray("food");
            Log.i("json_menu_all_list", menuList.toString());

            for(int i = 0; i < menuList.length(); i++){
                JSONObject object = menuList.getJSONObject(i);
                Log.i("json_menu_all_list", object.getString("title"));
                modelList.add(new FoodTestModel(object.getString("title"), object.getInt("id")));
            }

            Log.i("modelList", String.valueOf(modelList.size()));

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

            recyclerView = getView().findViewById(R.id.fragment_menu_food_recycler);

            foodTestRecycler = new FoodTestRecycler(getContext(), modelList);

            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(foodTestRecycler);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }
}
