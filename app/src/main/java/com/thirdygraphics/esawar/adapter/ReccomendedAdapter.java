package com.thirdygraphics.esawar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thirdygraphics.esawar.R;
import com.thirdygraphics.esawar.model.RecommendedModel;


import java.util.ArrayList;

public class ReccomendedAdapter extends RecyclerView.Adapter<ReccomendedAdapter.MyViewHolder> {

    public final MyInterface myInterfaces;


    Context context;
    ArrayList<RecommendedModel> recommendedmodels;

    public ReccomendedAdapter(Context context, ArrayList<RecommendedModel> recommendedmodels, MyInterface myInterfaces){
        this.context = context;
        this.recommendedmodels = recommendedmodels;
        this.myInterfaces = myInterfaces;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType  ) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_reccommended, parent, false);

        return new MyViewHolder(view, myInterfaces);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(recommendedmodels.get(position).getName());
        holder.image.setBackgroundResource(recommendedmodels.get(position).getPic());
    }


    @Override
    public int getItemCount() {
        return recommendedmodels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView rate,name,places;
        RelativeLayout image;


        public MyViewHolder(@NonNull View itemView, MyInterface myInterfaces) {
            super(itemView);
            name = itemView.findViewById(R.id.name);

            image =  itemView.findViewById(R.id.image);


            itemView.setOnClickListener(view -> {
                if(myInterfaces != null ){
                    int pos = getAdapterPosition();
                    if(pos!= RecyclerView.NO_POSITION){
                        myInterfaces.onItemClick(pos, "recommended");
                    }

                }
            });
        }
    }


}
