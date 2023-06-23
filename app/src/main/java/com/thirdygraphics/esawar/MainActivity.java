package com.thirdygraphics.esawar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.thirdygraphics.esawar.Fragment.Feed;
import com.thirdygraphics.esawar.Fragment.Home;
import com.thirdygraphics.esawar.Fragment.Hotel;
import com.thirdygraphics.esawar.Fragment.Restaurant;
import com.thirdygraphics.esawar.Pages.Post.CreatePost;
import com.thirdygraphics.esawar.adapter.SidebarAdapter;

import java.util.Arrays;
import java.util.List;

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

        //sidebar section
        sidebarSection();


    }

    private void sidebarSection() {

        //listview
        // Find the ListView in the sidebar
        ListView sidebarMenu = findViewById(R.id.sidebar_menu);

        // Create a list of menu items
        List<String> menuItems = Arrays.asList("My Post", "Inbox", "My Favorites", "Budget", "Logout");

        // Create the adapter and set it to the ListView
        SidebarAdapter sidebarAdapter = new SidebarAdapter(this, menuItems);
        sidebarMenu.setAdapter(sidebarAdapter);




        // Find the sidebar container and the button
        RelativeLayout sidebarContainer = findViewById(R.id.sidebar_container);
        LinearLayout btnViewSidebar = findViewById(R.id.btnViewSidebar);

        // Set click listener for the button
        btnViewSidebar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sidebarContainer.getVisibility() == View.VISIBLE) {
                    // If sidebar is already visible, hide it
                    sidebarContainer.setVisibility(View.GONE);
                } else {
                    // If sidebar is hidden, show it
                    sidebarContainer.setVisibility(View.VISIBLE);
                }
            }
        });

        // Set click listener for the main content layout
        RelativeLayout mainContent = findViewById(R.id.btnHide);
        mainContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hide the sidebar when the main content is clicked
                sidebarContainer.setVisibility(View.GONE);
            }
        });

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