package com.thirdygraphics.esawar.Pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.thirdygraphics.esawar.Data.Place;
import com.thirdygraphics.esawar.R;
import com.thirdygraphics.esawar.adapter.MyInterface;
import com.thirdygraphics.esawar.adapter.ReccomendedAdapter;
import com.thirdygraphics.esawar.model.RecommendedModel;

import java.util.ArrayList;

public class CategoriesPage extends AppCompatActivity implements MyInterface {
    ArrayList<RecommendedModel> categoriesModels = new ArrayList<>();
    RecyclerView recyclerView;
    TextView type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_page);

        //set categories Type
        type = findViewById(R.id.type);

        Place place = new Place();
        type.setText(place.CATEGORIES);

        //connect
        recyclerView = findViewById(R.id.recyclerView);

        //data
        categoriesSection();


    }

    private void categoriesSection() {
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
            categoriesModels.add(new RecommendedModel("1", image.get(i),name.get(i), places.get(i)));
        }

        ReccomendedAdapter reccomendedAdapter = new ReccomendedAdapter(CategoriesPage.this, categoriesModels, this);
        recyclerView.setAdapter(reccomendedAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(CategoriesPage.this,2));
    }


    @Override
    public void onItemClick(int pos, String categories) {

    }
}