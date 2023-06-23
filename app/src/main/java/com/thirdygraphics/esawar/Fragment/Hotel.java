package com.thirdygraphics.esawar.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thirdygraphics.esawar.R;
import com.thirdygraphics.esawar.adapter.MyInterface;
import com.thirdygraphics.esawar.adapter.ReccomendedAdapter;
import com.thirdygraphics.esawar.model.RecommendedModel;

import java.util.ArrayList;

public class Hotel extends Fragment implements MyInterface {
    ArrayList<RecommendedModel> hotelsModels = new ArrayList<>();
    View view;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.activity_hotel, container, false);

        //connect
        recyclerView = view.findViewById(R.id.recyclerView);

        //data
       hotelSection();


        return  view;
    }

    private void hotelSection() {
        //TODO palitan na sa firebase or Hotel Data
        ArrayList<Integer> image = new ArrayList<>();
        ArrayList<String> name = new ArrayList<>();
        ArrayList<String> places = new ArrayList<>();

        name.add("Hotel Veneto");
        name.add("Home Hotel");
        name.add("Paradores ");
        name.add("Traversa Hotel");
        name.add("Ciudad Fernandina");

        places.add("Vigan");
        places.add("Sigay");
        places.add("Bantay");
        places.add("Suyo");
        places.add("Taal");
        places.add("Bantay");


        image.add(R.drawable.hotel1);
        image.add(R.drawable.hotel2);
        image.add(R.drawable.hotel3);
        image.add(R.drawable.hotel4);
        image.add(R.drawable.hotel5);
        image.add(R.drawable.hotel6);

        for(int i=0; i<name.size();i++){
            hotelsModels.add(new RecommendedModel("1", image.get(i),name.get(i), places.get(i)));
        }

        ReccomendedAdapter reccomendedAdapter = new ReccomendedAdapter(getActivity(), hotelsModels, this);
        recyclerView.setAdapter(reccomendedAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
    }

    @Override
    public void onItemClick(int pos, String categories) {

    }
}