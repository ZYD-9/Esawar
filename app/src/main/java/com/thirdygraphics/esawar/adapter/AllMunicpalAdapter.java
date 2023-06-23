package com.thirdygraphics.esawar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thirdygraphics.esawar.R;
import com.thirdygraphics.esawar.model.AllMunicipalModel;

import java.util.ArrayList;

public class AllMunicpalAdapter extends RecyclerView.Adapter<AllMunicpalAdapter.MyViewHolder> {

    public final MyInterface myInterfaces;


    Context context;
    ArrayList<AllMunicipalModel> models;

    public AllMunicpalAdapter(Context context, ArrayList<AllMunicipalModel> models, MyInterface myInterfaces){
        this.context = context;
        this.models = models;
        this.myInterfaces = myInterfaces;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType  ) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_municipal, parent, false);

        return new MyViewHolder(view, myInterfaces);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.municipalName.setText(models.get(position).getName());
        holder.municipalLogo.setImageResource(models.get(position).getLogo());
    }


    @Override
    public int getItemCount() {
        return models.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView municipalName;
        ImageView municipalLogo;

        public MyViewHolder(@NonNull View itemView, MyInterface myInterfaces) {
            super(itemView);
            municipalName = itemView.findViewById(R.id.municipalName);
            municipalLogo = itemView.findViewById(R.id.municipalLogo);


            itemView.setOnClickListener(view -> {
                if(myInterfaces != null ){
                    int pos = getAdapterPosition();
                    if(pos!= RecyclerView.NO_POSITION){
                        myInterfaces.onItemClick(pos, "municipal");
                    }

                }
            });
        }
    }


}
