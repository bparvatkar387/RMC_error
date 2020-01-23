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

public class MenuWasteRecycler extends RecyclerView.Adapter<MenuWasteRecycler.WasteViewHolder> {

    JSONArray wasteList;
    Context context;
    OnMenuWasteListener onMenuWasteListener;


    public MenuWasteRecycler(Context context, JSONArray wasteList, OnMenuWasteListener onMenuWasteListener) {
        this.wasteList = wasteList;
        this.context = context;
        this.onMenuWasteListener = onMenuWasteListener;
    }

    public String getClickedWasteName(int position){
        try {
            return wasteList.getJSONObject(position).getString("src");
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    @NonNull
    @Override
    public WasteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_waste_segregation, parent, false);
        WasteViewHolder viewHolder = new WasteViewHolder(view, onMenuWasteListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuWasteRecycler.WasteViewHolder holder, int position) {
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

    public class WasteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView wasteImg;
        TextView wasteTitle;
        LinearLayout linearLayout;
        OnMenuWasteListener onMenuWasteListener;

        public WasteViewHolder(@NonNull View itemView, OnMenuWasteListener onMenuWasteListener) {
            super(itemView);

            wasteImg = itemView.findViewById(R.id.wasteImg);
            wasteTitle = itemView.findViewById(R.id.wasteTitle);
            linearLayout = itemView.findViewById(R.id.parent_menu_waste_segregation);

            this.onMenuWasteListener = onMenuWasteListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            onMenuWasteListener.OnMenuWasteClickListener(getAdapterPosition());
        }
    }

    public interface OnMenuWasteListener{
        void OnMenuWasteClickListener(int position);
    }

}
