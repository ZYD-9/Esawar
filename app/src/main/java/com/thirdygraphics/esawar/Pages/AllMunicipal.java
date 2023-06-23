package com.thirdygraphics.esawar.Pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.thirdygraphics.esawar.R;
import com.thirdygraphics.esawar.adapter.AllMunicpalAdapter;
import com.thirdygraphics.esawar.adapter.MyInterface;
import com.thirdygraphics.esawar.model.AllMunicipalModel;

import java.util.ArrayList;

public class AllMunicipal extends AppCompatActivity implements MyInterface {

    public ArrayList<AllMunicipalModel>  allMunicipalModels = new ArrayList<>();
    private RecyclerView recyclerView;
    private AllMunicpalAdapter allMunicpalAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_municipal);

        //connection
        recyclerView = findViewById(R.id.recyclerView);

        //initiate
        allMunicpalAdapter = new AllMunicpalAdapter(getApplicationContext(), allMunicipalModels, this);

        //set Data
        ArrayList<String> name = new ArrayList<>();
        ArrayList<Integer> icon = new ArrayList<>();

        name.add("Alilem");
        name.add("Banayoyo");
        name.add("Bantay");
        name.add("Burgos");
        name.add("Cabugao");
        name.add("Candon");
        name.add("Caoayan");
        name.add("Cervantes");
        name.add("Galimuyod");
        name.add("Gregorio del Pilar");

        icon.add(R.drawable.municipal_alilem);
        icon.add(R.drawable.municipal_banayoyo);
        icon.add(R.drawable.municipal_bantay);
        icon.add(R.drawable.municipal_burgos);
        icon.add(R.drawable.municipal_cabugao);
        icon.add(R.drawable.municipal_candon);
        icon.add(R.drawable.municipal_caoayan);
        icon.add(R.drawable.municipal_cervantes);
        icon.add(R.drawable.municipal_galimuyod);
        icon.add(R.drawable.municipal_gregorio);

        for(int i  = 0; i<name.size(); i++){
                allMunicipalModels.add(new AllMunicipalModel(icon.get(i), name.get(i)));
        }
        recyclerView.setAdapter(allMunicpalAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(AllMunicipal.this));

    }

    @Override
    public void onItemClick(int pos, String categories) {

    }
}