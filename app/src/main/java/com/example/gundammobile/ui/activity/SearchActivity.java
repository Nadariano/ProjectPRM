package com.example.gundammobile.ui.activity;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.gundammobile.R;

public class SearchActivity extends AppCompatActivity {
    private SearchView searchView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_search);

        searchView = findViewById(R.id.search_view);

//        productList = findViewById(R.id.product_list);

//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                // Handle the search query submission
//                // For demonstration, we'll just show a toast message
//                Toast.makeText(SearchActivity.this, "Searching for: " + query, Toast.LENGTH_SHORT).show();
//                // TODO: Implement search functionality and update product list
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                // Handle text changes in search view
//                return false;
//            }
//        });
    }
}
