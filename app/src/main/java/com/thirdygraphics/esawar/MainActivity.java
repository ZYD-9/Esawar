package com.thirdygraphics.esawar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.thirdygraphics.esawar.Fragment.Feed;
import com.thirdygraphics.esawar.Fragment.Home;
import com.thirdygraphics.esawar.Fragment.Profile;
import com.thirdygraphics.esawar.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(navListener);

        Fragment selectedFragment = new Home();


        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                selectedFragment).commit();


    }


    private NavigationBarView.OnItemSelectedListener navListener =
            item -> {
                Fragment selectedFragment = null;
                switch (item.getItemId()){

                    case R.id.nav_home:
                        selectedFragment = new Home();
                        break;
                    case R.id.nav_feed:
                        selectedFragment = new Feed();
                        break;
                    case R.id.nav_profile:
                        selectedFragment = new Profile();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                return true;
            };
}