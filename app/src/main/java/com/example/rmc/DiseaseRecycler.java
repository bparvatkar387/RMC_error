package com.example.rmc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class DiseaseRecycler extends RecyclerView.Adapter<DiseaseRecycler.DiseaseViewHolder> {
    JSONArray diseaseList;
    Context context;

    List<AllDiseasesAdapter> allDiseasesAdapterList;

    public DiseaseRecycler(Context context, List<AllDiseasesAdapter> allDiseasesAdapters) {
        this.allDiseasesAdapterList = allDiseasesAdapters;
        this.context = context;
    }

    public void updateList(List<AllDiseasesAdapter> list){
        allDiseasesAdapterList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DiseaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_disease, parent, false);
        DiseaseViewHolder viewHolder = new DiseaseViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DiseaseViewHolder holder, int position) {
        AllDiseasesAdapter adapter = allDiseasesAdapterList.get(position);
        holder.productName.setText(adapter.getProductTitle());
        holder.causesName.setText(adapter.getCausesName());
        holder.sympName.setText(adapter.getProductTitle());
    }


    @Override
    public int getItemCount() {
        return allDiseasesAdapterList.size();
    }

    public class DiseaseViewHolder extends RecyclerView.ViewHolder{
        TextView productName, causesName, sympName;
        LinearLayout linearLayout;

        public DiseaseViewHolder(@NonNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.productName);
            causesName = itemView.findViewById(R.id.causesName);
            sympName = itemView.findViewById(R.id.sympName);
            linearLayout = itemView.findViewById(R.id.parent_one_disease);



        }
    }
}
