package com.example.gundammobile.ui.activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gundammobile.R;
import com.example.gundammobile.model.CartItem;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shopping_cart);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerViewCart);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Sample data
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(new CartItem("https://firebasestorage.googleapis.com/v0/b/prmgundamshop.appspot.com/o/HG001.jpg?alt=media&token=a1bba414-f632-45a1-8cb9-96835e6b8f42",
                "HG RX-78-2 Fighter Beyond Global ",
                5,
                420));
        cartItems.add(new CartItem("https://firebasestorage.googleapis.com/v0/b/prmgundamshop.appspot.com/o/HG001.jpg?alt=media&token=a1bba414-f632-45a1-8cb9-96835e6b8f42 ",
                "HG RX-78-2 Fighter Beyond Global ",
                5,
                420));
        cartItems.add(new CartItem("https://firebasestorage.googleapis.com/v0/b/prmgundamshop.appspot.com/o/HG001.jpg?alt=media&token=a1bba414-f632-45a1-8cb9-96835e6b8f42 ",
                "HG RX-78-2 Fighter Beyond Global ",
                5,
                420));
        // Add more items as needed

        ShoppingCartAdapter adapter = new ShoppingCartAdapter(cartItems);
        recyclerView.setAdapter(adapter);
    }
}
