package com.example.doanfashionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doanfashionapp.Adapter.OrderAdapter;
import com.example.doanfashionapp.DAO.DAO_Orders;
import com.example.doanfashionapp.DTO.Order;

import java.util.List;
public class ActivityDS_Name extends AppCompatActivity {

    private ListView listViewOrders;
    private OrderAdapter orderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2_dsdonhang);

        listViewOrders = findViewById(R.id.listViewOrders);
        Button btnQuayLai = findViewById(R.id.btnquaylai);

        // Get the username from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        // Load orders from the database
        DAO_Orders daoOrders = new DAO_Orders(this);
        List<Order> orders = daoOrders.loadOrdersByUsername(username);

        // Set up the adapter
        orderAdapter = new OrderAdapter(this, R.layout.order_item, orders);
        listViewOrders.setAdapter(orderAdapter);

        // Set item click listener
        listViewOrders.setOnItemClickListener((parent, view, position, id) -> {
            // Get the selected order
            Order selectedOrder = orders.get(position);

            // Extract the order ID
            String orderId = selectedOrder.getOrderId();

            // Create an intent to start the OrderDetailsActivity
            Intent intent = new Intent(ActivityDS_Name.this, OrderDetailsActivity.class);

            // Pass the order ID to the next activity
            intent.putExtra("ORDER_ID", orderId);

            // Start the activity
            startActivity(intent);
        });

        btnQuayLai.setOnClickListener(v -> finish());
    }
}