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
import android.widget.LinearLayout;

import com.thirdygraphics.esawar.Data.Place;
import com.thirdygraphics.esawar.Pages.AllMunicipal;
import com.thirdygraphics.esawar.Pages.CategoriesPage;
import com.thirdygraphics.esawar.Pages.Places.PlacesHomePage;
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
    private LinearLayout btnSeeAll;
    View view;
    RecyclerView categories_recycler,places_recycler,accelerate_recycler;
    Place place;


    private LinearLayout btnAlilem;
    private LinearLayout btnBanayoyo;
    private LinearLayout btnBantay;
    private LinearLayout btnBurgos;
    private LinearLayout btnCabugao;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.activity_home, container, false);

        place = new Place();

        initXml();
        categoriesSection();
        //placesSection();
        reccomendedSection();
        minicipalSection();
        return  view;
    }

    private void minicipalSection() {



        btnAlilem = view.findViewById(R.id.btnAlilem);
        btnBanayoyo = view.findViewById(R.id.btnBanayoyo);
        btnBantay = view.findViewById(R.id.btnBantay);
        btnBurgos = view.findViewById(R.id.btnBurgos);
        btnCabugao = view.findViewById(R.id.btnCabugao);

        setButtonClickListener(btnAlilem, PlacesHomePage.class);
        setButtonClickListener(btnBanayoyo, PlacesHomePage.class);
        setButtonClickListener(btnBantay, PlacesHomePage.class);
        setButtonClickListener(btnBurgos, PlacesHomePage.class);
        setButtonClickListener(btnCabugao, PlacesHomePage.class);

        btnSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AllMunicipal.class);
                startActivity(intent);
            }
        });
    }

    private void setButtonClickListener(LinearLayout button, final Class<?> destinationActivity) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), destinationActivity);
                startActivity(intent);
            }
        });
    }


    private void categoriesSection() {
        ArrayList<Integer> image = new ArrayList<>();
        ArrayList<String> categoryName = new ArrayList<>();

        image.add(R.drawable.beach);
        image.add(R.drawable.museum);
        image.add(R.drawable.church);
        image.add(R.drawable.park);
        image.add(R.drawable.restaurant2);
        image.add(R.drawable.restaurant2);
        image.add(R.drawable.restaurant2);

        categoryName.add("Beach");
        categoryName.add("Museum");
        categoryName.add("Church");
        categoryName.add("Park");
        categoryName.add("Restaurant");
        categoryName.add("Restaurant");
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

        name.add("Crisologo Museum");
        name.add("Aw-asen Falls");
        name.add("Bantay Bell Tower");
        name.add("Sangbay ni Ragsak");
        name.add("Dawara Falls");
        name.add("Baluarte Resort");

        places.add("Vigan");
        places.add("Sigay");
        places.add("Bantay");
        places.add("Suyo");
        places.add("Taal");
        places.add("Suyo");


        image.add(R.drawable.places1);
        image.add(R.drawable.places2);
        image.add(R.drawable.places3);
        image.add(R.drawable.places4);
        image.add(R.drawable.places5);
        image.add(R.drawable.places6);


        for(int i=0; i<name.size();i++){
            recommendedModels.add(new RecommendedModel("1", image.get(i),name.get(i), places.get(i)));
        }

        ReccomendedAdapter reccomendedAdapter = new ReccomendedAdapter(getActivity(), recommendedModels, this);
        accelerate_recycler.setAdapter(reccomendedAdapter);
        accelerate_recycler.setLayoutManager(new GridLayoutManager(getActivity(),2));
    }




    private void initXml() {
        categories_recycler = view.findViewById(R.id.rec_categories);
        places_recycler = view.findViewById(R.id.rec_places);
        accelerate_recycler = view.findViewById(R.id.accelerate);
        btnSeeAll  = view.findViewById(R.id.btnSeeAll);
    }

    @Override
    public void onItemClick(int pos, String homepage) {
        Intent intent;
        switch (homepage){
            case "categories":
                place.CATEGORIES = homepageModels.get(pos).getCategoryName();
                intent = new Intent(getActivity(), CategoriesPage.class);
                startActivity(intent);
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