package com.example.rmc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MenuWasteRecycler extends RecyclerView.Adapter<MenuWasteRecycler.ViewHolder> {

    JSONArray wasteList;
    Context context;


    public MenuWasteRecycler(Context context, JSONArray wasteList) {
        this.wasteList = wasteList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_waste_segregation, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuWasteRecycler.ViewHolder holder, int position) {
        try {
            JSONObject one_waste = wasteList.getJSONObject(position);
            String s_name = one_waste.getString("img");
            int redID = context.getResources().getIdentifier(s_name,
                    "drawable", context.getPackageName());;

            holder.wasteImg.setImageResource(redID);
            holder.wasteTitle.setText(one_waste.getString("title"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return wasteList.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView wasteImg;
        TextView wasteTitle;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            wasteImg = itemView.findViewById(R.id.wasteImg);
            wasteTitle = itemView.findViewById(R.id.wasteTitle);
            linearLayout = itemView.findViewById(R.id.parent_menu_waste_segregation);

        }
    }

}
