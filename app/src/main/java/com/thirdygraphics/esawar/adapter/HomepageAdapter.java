package com.thirdygraphics.esawar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thirdygraphics.esawar.R;
import com.thirdygraphics.esawar.model.HomepageModel;


import java.util.ArrayList;

public class HomepageAdapter extends RecyclerView.Adapter<HomepageAdapter.MyViewHolder> {

    public final MyInterface myInterfaces;


    Context context;
    ArrayList<HomepageModel> homepageModels;

    public HomepageAdapter(Context context, ArrayList<HomepageModel> homepageModels, MyInterface myInterfaces){
        this.context = context;
        this.homepageModels = homepageModels;
        this.myInterfaces = myInterfaces;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType  ) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_categories, parent, false);

        return new MyViewHolder(view, myInterfaces);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.category.setText(homepageModels.get(position).getCategoryName());
        holder.image.setImageResource(homepageModels.get(position).getImage());
    }


    @Override
    public int getItemCount() {

        return homepageModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView category;
        ImageView image;


        public MyViewHolder(@NonNull View itemView, MyInterface myInterfaces) {
            super(itemView);
            category = itemView.findViewById(R.id.txt_categories);
            image =  itemView.findViewById(R.id.image);


            itemView.setOnClickListener(view -> {
                if(myInterfaces != null ){
                    int pos = getAdapterPosition();
                    if(pos!= RecyclerView.NO_POSITION){
                        myInterfaces.onItemClick(pos, "categories");
                    }

                }
            });
        }
    }


}
