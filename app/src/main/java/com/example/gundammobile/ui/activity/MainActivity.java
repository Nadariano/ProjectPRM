package com.example.gundammobile.ui.activity;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;
import android.view.Menu;

import com.example.gundammobile.R;
import com.example.gundammobile.model.CartItem;
import com.example.gundammobile.model.User;
import com.example.gundammobile.ui.fragment.user.UserViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.gundammobile.databinding.ActivityMainBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    public DrawerLayout drawerLayout;
    private NavigationView navigationView;

    //Check if there is any items in the cart
    private static final String CART_PREFS = "cart_prefs";
    private String CHANNEL_ID = "gundammobile_cart_noti";
    private Integer NOTIFICATION_ID = 12345;
    SharedPreferences cartPreference;

    private UserViewModel userViewModel;
    private User user;
    private boolean loggedIn;
    BottomNavigationView navView;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        // For Bottom Navigation
        navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.navigation_user)
                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main2);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_main2);
        if (navHostFragment != null) {


//            // Handle search icon click
//            toolbar.setOnMenuItemClickListener(item -> {
//                if (item.getItemId() == R.id.action_search) {
//                    navController.navigate(R.id.action_search);
//                    return true;
//                }
//                return false;
//            });
        } else {
            throw new RuntimeException("NavHostFragment not found");
        }
        navController = navHostFragment.getNavController();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        checkIsLoggedIn();
//        if (loggedIn) {
//            Menu menu = navView.getMenu();
//            MenuItem removedItem = menu.findItem(R.id.navigation_dashboard);
//            removedItem.setEnabled(false);
//            removedItem.setVisible(false);
//        }
//        else {
//            Menu menu = navView.getMenu();
//            MenuItem removedItem = menu.findItem(R.id.navigation_dashboard);
//            removedItem.setEnabled(true);
//            removedItem.setVisible(true);
//        }
//        NavigationUI.setupWithNavController(navView, navController);

//        // Search View
//        SearchView searchView = findViewById(R.id.search_view);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                // Handle search query submission
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                // Handle text changes in search view
//                return false;
//            }
//        });

        // Drawer Layout and Navigation View for Filter
        drawerLayout = findViewById(R.id.drawer_layout);
//        navigationView = findViewById(R.id.navigation_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        // Handle search icon click
        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.search_view_header) {
                // Navigate to SearchActivity
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
                return true;
            }
            if (item.getItemId() == R.id.cart_button_home) {
                // Navigate to CartActivity
                Intent intent = new Intent(MainActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
                return true;
            }
            return false;
        });
//        findViewById(R.id.cart_button_home).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Create an Intent to start the CartActivity
//                Intent intent = new Intent(MainActivity.this, ShoppingCartActivity.class);
//                startActivity(intent);
//            }
//        });


//        navigationView.setNavigationItemSelectedListener(item -> {
//            // Handle navigation view item clicks here.
//            int id = item.getItemId();
//            // Handle filter menu items here if needed
//            drawerLayout.closeDrawer(GravityCompat.END);
//            return true;
//        });


//        findViewById(R.id.filter_button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                drawerLayout.openDrawer(GravityCompat.END);
//            }
//        });
//
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(MenuItem item) {
//                // Handle navigation view item clicks here.
//                int id = item.getItemId();
//                switch (id) {
////                    case R.id.filter_mg:
////                        // Apply MG filter
////                        break;
////                    case R.id.filter_pg:
////                        // Apply PG filter
////                        break;
////                    case R.id.action_show:
////                        // Show filtered results
////                        break;
////                    case R.id.action_reset:
////                        // Reset filters
////                        break;
//                }
//                drawerLayout.closeDrawer(GravityCompat.END);
//                return true;
//            }
//        });

        //For displaying notifications for cart
        cartNotification();
    }

    @Override
    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main2);
        NavController navController;
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_main2);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
        } else {
            throw new RuntimeException("NavHostFragment not found");
        }
        return NavigationUI.navigateUp(navController, drawerLayout) || super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    private void cartNotification() {
        cartPreference = getSharedPreferences(CART_PREFS, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = cartPreference.getString("cart_items", null);
        Type type = new TypeToken<ArrayList<CartItem>>() {
        }.getType();
        List<CartItem> cartItems = gson.fromJson(json, type);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "My Channel", NotificationManager.IMPORTANCE_DEFAULT);
            // Customize channel settings (e.g., sound, vibration, etc.)
            // ...
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
        // Check if the preferences are not null
        if (cartItems != null) {
            // Create a notification
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.icon_cart)
                    .setContentTitle("GundamShop_MobileVersion")
                    .setContentText("Có món hàng trong giỏ của bạn đang chờ thanh toán. Cùng xem nhé!")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true); // Auto-cancel the notification when clicked

// Create an intent to open the cart activity (adjust this based on your app's structure)
            Intent intent = new Intent(this, ShoppingCartActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
            builder.setContentIntent(pendingIntent);

// Show the notification
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            notificationManager.notify(NOTIFICATION_ID, builder.build());
        }
    }

    private void checkIsLoggedIn() {
        userViewModel.getIsLoggedIn().observe(this, isLoggedIn -> {
            if(!isLoggedIn){
                Menu menu = navView.getMenu();
                MenuItem removedItem = menu.findItem(R.id.navigation_dashboard);
                removedItem.setEnabled(false);
                removedItem.setVisible(false);
            }
            else {
                Menu menu = navView.getMenu();
                MenuItem unremovedItem = menu.findItem(R.id.navigation_dashboard);
                unremovedItem.setEnabled(true);
                unremovedItem.setVisible(true);
            }
        });
//        if (loggedIn) {
//            Menu menu = navView.getMenu();
//            MenuItem removedItem = menu.findItem(R.id.navigation_dashboard);
//            removedItem.setEnabled(false);
//            removedItem.setVisible(false);
//        }
//        else {
//            Menu menu = navView.getMenu();
//            MenuItem removedItem = menu.findItem(R.id.navigation_dashboard);
//            removedItem.setEnabled(true);
//            removedItem.setVisible(true);
//        }
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkIsLoggedIn();
    }
}
