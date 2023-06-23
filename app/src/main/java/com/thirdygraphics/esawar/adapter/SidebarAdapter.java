package com.thirdygraphics.esawar.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.thirdygraphics.esawar.Loading;
import com.thirdygraphics.esawar.Pages.Budget.Budget;
import com.thirdygraphics.esawar.Pages.Favorite.MyFavorites;
import com.thirdygraphics.esawar.Pages.Inbox.Inbox;
import com.thirdygraphics.esawar.Pages.Post.MyPost;
import com.thirdygraphics.esawar.R;

import java.util.List;

public class SidebarAdapter extends ArrayAdapter<String> {

    private List<String> menuItems;
    private Context context;

    public SidebarAdapter(Context context, List<String> menuItems) {
        super(context, 0, menuItems);
        this.context = context;
        this.menuItems = menuItems;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.sidebar_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.menuIcon = convertView.findViewById(R.id.menu_icon);
            viewHolder.menuTitle = convertView.findViewById(R.id.menu_title);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String menuItem = menuItems.get(position);

        // Set the menu item title
        viewHolder.menuTitle.setText(menuItem);

        // Set the menu item icon based on position or any other logic
        switch (position) {
            case 0:
                viewHolder.menuIcon.setImageResource(R.drawable.mypost);
                break;
            case 1:
                viewHolder.menuIcon.setImageResource(R.drawable.inbox);
                break;
            case 2:
                viewHolder.menuIcon.setImageResource(R.drawable.myfavorite);
                break;
            case 3:
                viewHolder.menuIcon.setImageResource(R.drawable.budget);
                break;
            case 4:
                viewHolder.menuIcon.setImageResource(R.drawable.logout);
                break;
            default:
                viewHolder.menuIcon.setImageResource(R.drawable.baseline_home);
                break;
        }

        // Set click listener for the menu item
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle item click here based on position
                handleMenuItemClick(position);
            }
        });

        return convertView;
    }

    private void handleMenuItemClick(int position) {
        // Handle the click event for each menu item
        switch (position) {
            case 0:
                // Handle "My Post" click
                openMyPostActivity();
                break;
            case 1:
                // Handle "Inbox" click
                openInboxActivity();
                break;
            case 2:
                // Handle "My Favorites" click
                openMyFavoritesActivity();
                break;
            case 3:
                // Handle "Budget" click
                openBudgetActivity();
                break;
            case 4:
                // Handle "Logout" click
                performLogout();
                break;
        }
    }

    private static class ViewHolder {
        ImageView menuIcon;
        TextView menuTitle;
    }

    // Implement your activity or fragment navigation methods here
    private void openMyPostActivity() {
        // Intent to open My Post activity
        Intent intent = new Intent(context, MyPost.class);
        context.startActivity(intent);
    }

    private void openInboxActivity() {
        // Intent to open Inbox activity
        Intent intent = new Intent(context, Inbox.class);
        context.startActivity(intent);
    }

    private void openMyFavoritesActivity() {
        // Intent to open My Favorites activity
        Intent intent = new Intent(context, MyFavorites.class);
        context.startActivity(intent);
    }

    private void openBudgetActivity() {
        // Intent to open Budget activity
        Intent intent = new Intent(context, Budget.class);
        context.startActivity(intent);
    }

    private void performLogout() {
        // Perform logout action
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signOut();
        Intent intent = new Intent(context, Loading.class);
        context.startActivity(intent);

    }
}


