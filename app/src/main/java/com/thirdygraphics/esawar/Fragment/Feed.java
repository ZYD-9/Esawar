package com.thirdygraphics.esawar.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.thirdygraphics.esawar.Loading;
import com.thirdygraphics.esawar.MainActivity;
import com.thirdygraphics.esawar.R;
import com.thirdygraphics.esawar.adapter.FeedAdapter;
import com.thirdygraphics.esawar.adapter.MyInterface;
import com.thirdygraphics.esawar.authentication.Welcome;
import com.thirdygraphics.esawar.model.FeedModel;

import java.util.ArrayList;

public class Feed extends Fragment implements MyInterface {

    View view;

    ArrayList<FeedModel> feedModels = new ArrayList<>();

    //Adapter
    FeedAdapter feedAdapter;

    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.activity_feed, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);



        feedSection();


        return  view;
    }


    private void feedSection() {

        feedModels.add(new FeedModel(" Nov 12, 2023", "8:23am", "Novem Lanaban",  "Vigan",
                    "How I spent my Vacation",
                    R.drawable.profile, R.drawable.beach, R.drawable.church, R.drawable.restaurant2, R.drawable.beach, true));

        feedModels.add(new FeedModel(" Oct 12, 2023", "9:22am", "Novem Lanaban",  "Cabuyao",
                "Caption 2",
                R.drawable.profile, R.drawable.restaurant2, R.drawable.beach, R.drawable.restaurant2, R.drawable.church, true));

        feedModels.add(new FeedModel(" Sep 12, 2023", "5:28am", "Novem Lanaban",  "Silang",
                "Caption 3",
                R.drawable.profile, R.drawable.beach, R.drawable.park, R.drawable.restaurant2, R.drawable.restaurant2, true));



        feedAdapter = new FeedAdapter(getActivity(), feedModels, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(feedAdapter);

    }


    @Override
    public void onItemClick(int pos, String categories) {

    }
}