package com.thirdygraphics.esawar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.thirdygraphics.esawar.R;
import com.thirdygraphics.esawar.model.FeedModel;

import java.util.ArrayList;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.MyViewHolder> {

    public final MyInterface myInterfaces;


    Context context;
    ArrayList<FeedModel> feedModels;

    public FeedAdapter(Context context, ArrayList<FeedModel> feedModels, MyInterface myInterfaces){
        this.context = context;
        this.feedModels = feedModels;
        this.myInterfaces = myInterfaces;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType  ) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_feed, parent, false);

        return new MyViewHolder(view, myInterfaces);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(feedModels.get(position).getName());
        holder.date.setText(feedModels.get(position).getDate());
        holder.time.setText(feedModels.get(position).getTime());
        holder.description.setText(feedModels.get(position).getDescription());
        holder.places.setText(feedModels.get(position).getPlaces());


        holder.pic1.setImageResource(feedModels.get(position).getPic1());
        holder.pic2.setImageResource(feedModels.get(position).getPic2());
        holder.pic3.setImageResource(feedModels.get(position).getPic3());
        holder.pic4.setImageResource(feedModels.get(position).getPic4());
        holder.profile_img.setImageResource(feedModels.get(position).getProfile());



        if(feedModels.get(holder.getAdapterPosition()).getLike().equals(true)){
            holder.favorites.setImageResource(R.drawable.baseline_favorite_24);
            holder.like.setText("Liked");
        }else{
            holder.favorites.setImageResource(R.drawable.baseline_favorite_border_24);
            holder.like.setText("Like");
        }

        holder.likesection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(feedModels.get(holder.getAdapterPosition()).getLike().equals(true)){
                    feedModels.get(holder.getAdapterPosition()).setLike(false);
                    holder.favorites.setImageResource(R.drawable.baseline_favorite_border_24);
                    holder.like.setText("Like");
                }else{
                    feedModels.get(holder.getAdapterPosition()).setLike(true);
                    holder.favorites.setImageResource(R.drawable.baseline_favorite_24);
                    holder.like.setText("Liked");
                }
            }
        });


    }


    @Override
    public int getItemCount() {

        return feedModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        LinearLayout likesection;
        TextView seemore,places,description,date,time,name,like;
        ImageView pic1,pic2,pic3,pic4,profile_img,favorites;

        public MyViewHolder(@NonNull View itemView, MyInterface myInterfaces) {
            super(itemView);

            likesection = itemView.findViewById(R.id.like_section);
            seemore = itemView.findViewById(R.id.places);
            description = itemView.findViewById(R.id.description);
            places = itemView.findViewById(R.id.places);

            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            name = itemView.findViewById(R.id.name);
            like = itemView.findViewById(R.id.like);
            pic1 = itemView.findViewById(R.id.pic1);
            pic2 = itemView.findViewById(R.id.pic2);
            pic3 = itemView.findViewById(R.id.pic3);
            pic4 = itemView.findViewById(R.id.pic4);
            profile_img = itemView.findViewById(R.id.profile_img);
            favorites = itemView.findViewById(R.id.favorites);


            itemView.setOnClickListener(view -> {
                if(myInterfaces != null ){
                    int pos = getAdapterPosition();
                    if(pos!= RecyclerView.NO_POSITION){
                        myInterfaces.onItemClick(pos, "feed");
                    }

                }
            });
        }
    }


}
