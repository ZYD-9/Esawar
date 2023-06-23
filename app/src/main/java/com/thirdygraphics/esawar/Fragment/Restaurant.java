package com.thirdygraphics.esawar.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thirdygraphics.esawar.R;
import com.thirdygraphics.esawar.adapter.MyInterface;
import com.thirdygraphics.esawar.adapter.ReccomendedAdapter;
import com.thirdygraphics.esawar.model.RecommendedModel;

import java.util.ArrayList;

public class Restaurant extends Fragment implements MyInterface {

    ArrayList<RecommendedModel> restaurantModels = new ArrayList<>();
    View view;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.activity_restaurant, container, false);


        //connect
        recyclerView = view.findViewById(R.id.recyclerView);

        //data
        restaurantSection();


        return  view;
    }

    private void restaurantSection() {
        //TODO palitan na sa firebase or Hotel Data
        ArrayList<Integer> image = new ArrayList<>();
        ArrayList<String> name = new ArrayList<>();
        ArrayList<String> places = new ArrayList<>();

        name.add("Bantay");
        name.add("Vigan");
        name.add("Golden Haven");
        name.add("Sky Ranch");
        name.add("Mount Mayon");

        places.add("Vigan");
        places.add("Sigay");
        places.add("Bantay");
        places.add("Suyo");
        places.add("Taal");


        image.add(R.drawable.tangkelagoon);
        image.add(R.drawable.masasabeach);
        image.add(R.drawable.goldenhaven);
        image.add(R.drawable.skyranch);
        image.add(R.drawable.mountmayon);

        for(int i=0; i<name.size();i++){
            restaurantModels.add(new RecommendedModel("1", image.get(i),name.get(i), places.get(i)));
        }

        ReccomendedAdapter reccomendedAdapter = new ReccomendedAdapter(getActivity(), restaurantModels, this);
        recyclerView.setAdapter(reccomendedAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
    }


    @Override
    public void onItemClick(int pos, String categories) {

    }
}