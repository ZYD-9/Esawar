package com.thirdygraphics.esawar.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thirdygraphics.esawar.R;
import com.thirdygraphics.esawar.adapter.HomepageAdapter;
import com.thirdygraphics.esawar.adapter.HomepagePlacesAdapter;
import com.thirdygraphics.esawar.adapter.MyInterface;
import com.thirdygraphics.esawar.adapter.ReccomendedAdapter;
import com.thirdygraphics.esawar.model.HomepageModel;
import com.thirdygraphics.esawar.model.HomepagePlacesModel;
import com.thirdygraphics.esawar.model.RecommendedModel;

import java.util.ArrayList;

public class Home extends Fragment implements MyInterface {
    ArrayList<HomepageModel> homepageModels = new ArrayList<>(); //for categorees
    ArrayList<HomepagePlacesModel> homepagePlacesModels = new ArrayList<>();
    ArrayList<RecommendedModel> recommendedModels = new ArrayList<>();

    View view;
    RecyclerView categories_recycler,places_recycler,accelerate_recycler;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.activity_home, container, false);

        initXml();
        categoriesSection();
        placesSection();
        reccomendedSection();
        return  view;
    }
    private void categoriesSection() {
        ArrayList<Integer> image = new ArrayList<>();
        ArrayList<String> categoryName = new ArrayList<>();

        image.add(R.drawable.beach);
        image.add(R.drawable.museum);
        image.add(R.drawable.church);
        image.add(R.drawable.park);
        image.add(R.drawable.restaurant2);

        categoryName.add("Beach");
        categoryName.add("Museum");
        categoryName.add("Church");
        categoryName.add("Park");
        categoryName.add("Restaurant");



        for(int i = 0; i<image.size(); i++){
            homepageModels.add(new HomepageModel(image.get(i), categoryName.get(i)));
        }

        HomepageAdapter HomepageAdapter = new HomepageAdapter(getActivity(), homepageModels, this);
        categories_recycler.setAdapter(HomepageAdapter);
        categories_recycler.setLayoutManager(new GridLayoutManager(getActivity(), 1 , GridLayoutManager.HORIZONTAL, false));

    }


    private void placesSection() {

        //goto places

        ArrayList<String> images = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();

        names.add("manila");
        names.add("cebu");
        names.add("el nido");
        names.add("bacolod");
        names.add("iloilo");
        names.add("boracay");

        images.add("https://firebasestorage.googleapis.com/v0/b/feelinpinas.appspot.com/o/places%2Fimage%203.png?alt=media&token=d8c6b8c4-6bd8-4ea4-b2e1-40dc7156aa27");
        images.add("https://firebasestorage.googleapis.com/v0/b/feelinpinas.appspot.com/o/places%2Fcebu.png?alt=media&token=b3af6fe1-b034-4fac-b0a6-29966f7f5692");
        images.add("https://firebasestorage.googleapis.com/v0/b/feelinpinas.appspot.com/o/places%2Felnido.png?alt=media&token=0dcc0466-c0a5-4017-9dd3-4aa8c34bef25");
        images.add("https://firebasestorage.googleapis.com/v0/b/feelinpinas.appspot.com/o/places%2Fbacolod.png?alt=media&token=c230ea6a-9f8f-4389-9b05-a66d9b54f86b");
        images.add("https://firebasestorage.googleapis.com/v0/b/feelinpinas.appspot.com/o/places%2Fimage%203.png?alt=media&token=d8c6b8c4-6bd8-4ea4-b2e1-40dc7156aa27");
        images.add("https://firebasestorage.googleapis.com/v0/b/feelinpinas.appspot.com/o/places%2Felnido.png?alt=media&token=0dcc0466-c0a5-4017-9dd3-4aa8c34bef25");

        for(int i=0; i<names.size();i++){
            homepagePlacesModels.add(new HomepagePlacesModel(names.get(i),images.get(i)));
        }

        HomepagePlacesAdapter homepagePlacesAdapter = new HomepagePlacesAdapter(getActivity(), homepagePlacesModels, this);
        places_recycler.setAdapter(homepagePlacesAdapter);
        places_recycler.setLayoutManager(new GridLayoutManager(getActivity(),2, GridLayoutManager.VERTICAL, false));


    }

    private void reccomendedSection() {
        ArrayList<Integer> image = new ArrayList<>();
        ArrayList<String> name = new ArrayList<>();
        ArrayList<String> places = new ArrayList<>();
        ArrayList<Double> rate = new ArrayList<>();

        name.add("Bantay");
        name.add("Vigan");
        name.add("Golden Haven Memorial Park");
        name.add("Sky Ranch");
        name.add("Mount Mayon");


        image.add(R.drawable.tangkelagoon);
        image.add(R.drawable.masasabeach);
        image.add(R.drawable.goldenhaven);
        image.add(R.drawable.skyranch);
        image.add(R.drawable.mountmayon);

        for(int i=0; i<name.size();i++){
            recommendedModels.add(new RecommendedModel(image.get(i),name.get(i)));
        }

        ReccomendedAdapter reccomendedAdapter = new ReccomendedAdapter(getActivity(), recommendedModels, this);
        accelerate_recycler.setAdapter(reccomendedAdapter);
        accelerate_recycler.setLayoutManager(new GridLayoutManager(getActivity(),2));
    }




    private void initXml() {
        categories_recycler = view.findViewById(R.id.rec_categories);
        places_recycler = view.findViewById(R.id.rec_places);
        accelerate_recycler = view.findViewById(R.id.accelerate);

    }

    @Override
    public void onItemClick(int pos, String homepage) {
        Intent intent;
        switch (homepage){
            case "categories":
                Log.d("TAG", "CHOOSING CATEGORIES");
                //intent = new Intent(getActivity(), CategoryFeed.class);
                //startActivity(intent);
                break;
            case "places":
                Log.d("TAG", "CHOOSING places");
                //intent = new Intent(getActivity(), CityPage.class);
                //startActivity(intent);
                break;
            case "recommended":
                Log.d("TAG", "CHOOSING recommended places");
                //intent = new Intent(getActivity(), PlacePage.class);
                //startActivity(intent);
                break;

        }
    }

}