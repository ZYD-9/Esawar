package com.thirdygraphics.esawar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.thirdygraphics.esawar.R;
import com.thirdygraphics.esawar.model.HomepagePlacesModel;

import java.util.ArrayList;

public class HomepagePlacesAdapter extends RecyclerView.Adapter<HomepagePlacesAdapter.MyViewHolder> {

    public final MyInterface myInterfaces;


    Context context;
    ArrayList<HomepagePlacesModel> homepagePlacesModels;

    public HomepagePlacesAdapter(Context context, ArrayList<HomepagePlacesModel> homepagePlacesModels, MyInterface myInterfaces){
        this.context = context;
        this.homepagePlacesModels = homepagePlacesModels;
        this.myInterfaces = myInterfaces;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType  ) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_places, parent, false);

        return new MyViewHolder(view, myInterfaces);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.places.setText(homepagePlacesModels.get(position).getPlaces());
        Picasso.get().load(homepagePlacesModels.get(position).getImage()).placeholder(R.drawable.placesholder).error(R.drawable.placesholder).resize(1080,720).centerCrop().into(holder.image);

    }


    @Override
    public int getItemCount() {
        return homepagePlacesModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView places;
        ImageView image;


        public MyViewHolder(@NonNull View itemView, MyInterface myInterfaces) {
            super(itemView);
            places = itemView.findViewById(R.id.txt_name);
            image =  itemView.findViewById(R.id.image);


            itemView.setOnClickListener(view -> {
                if(myInterfaces != null ){
                    int pos = getAdapterPosition();
                    if(pos!= RecyclerView.NO_POSITION){
                        myInterfaces.onItemClick(pos, "places");
                    }

                }
            });
        }
    }


}
