package com.thirdygraphics.esawar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.thirdygraphics.esawar.Fragment.Feed;
import com.thirdygraphics.esawar.Fragment.Home;
import com.thirdygraphics.esawar.Fragment.Hotel;
import com.thirdygraphics.esawar.Fragment.Restaurant;
import com.thirdygraphics.esawar.Pages.Post.CreatePost;

public class MainActivity extends AppCompatActivity {

    private MenuItem lastSelectedItem;
    private  BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(navListener);

        Fragment selectedFragment = new Home();

        // Kunin ang huling piniling item mula sa navigation bar
        lastSelectedItem = bottomNav.getMenu().findItem(bottomNav.getSelectedItemId());

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                selectedFragment).commit();




    }


    private NavigationBarView.OnItemSelectedListener navListener =
            item -> {
                Fragment selectedFragment = null;
                switch (item.getItemId()){

                    case R.id.nav_home:
                        lastSelectedItem = item;
                        selectedFragment = new Home();
                        break;
                    case R.id.nav_feed:
                        lastSelectedItem = item;
                        selectedFragment = new Feed();
                        break;
                    case R.id.nav_restaurant:
                        lastSelectedItem = item;
                        selectedFragment = new Restaurant();
                        break;
                    case R.id.nav_hotel:
                        // Tandaan ang piniling item
                        lastSelectedItem = item;
                        selectedFragment = new Hotel();
                        break;
                    case R.id.nav_post:
                        Intent intent = new Intent(MainActivity.this, CreatePost.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_up, 0);

                        break;

                }
                if(selectedFragment != null){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                }
                return true;

            };


    @Override
    protected void onResume() {
        super.onResume();

        // Tandaan ang huling piniling item sa navigation bar
        lastSelectedItem.setChecked(true);
    }

}