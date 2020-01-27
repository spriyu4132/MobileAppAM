package com.example.application1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.application1.R;
import com.example.application1.adapter.OrderGridAdapter;
//import com.example.application1.adapter.ProductGridAdapter;
import com.example.application1.db.Constants;
import com.example.application1.db.Utils;
import com.example.application1.model.Order;
import com.example.application1.model.Product;
import com.example.application1.model.User;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class OrderDetailsActivity extends BackActivity implements OrderGridAdapter.ActionListener {

    TextView textPrice, textProductName,textOrderCode,textOrderDate,textDeliverDate;
    ImageView imageView;

    RecyclerView recyclerView;
    OrderGridAdapter adapter;
    ArrayList<Order> orders = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        //imageView = findViewById(R.id.imageView);
        //textPrice = findViewById(R.id.textPrice);
        //textProductName = findViewById(R.id.textProductName);
//        Intent intent = getIntent();
//        User user = (User) intent.getSerializableExtra("user_info");

        //textDescription.setText(user.getDescription());
        textOrderCode=findViewById(R.id.textOrderCode);
        textOrderDate=findViewById(R.id.textOrderDate);
        textDeliverDate=findViewById(R.id.textDeliverDate);

        recyclerView = findViewById(R.id.orderrecyclerView);
        adapter=new OrderGridAdapter(this,orders,this);
        recyclerView.setAdapter(adapter);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);



    }
    protected void onResume() {
        super.onResume();
        loadProducts();
    }
    private void loadProducts() {
        orders.clear();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int user_id = preferences.getInt("user_id", 0);


        String url = Utils.createUrl(Constants.ROUTE_ORDER_DETAILS+user_id);
        Log.e("OrderDetailsActivity", "url: " + url);

        // send GET HTTP request
        Ion.with(this)
                .load("GET", url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject response) {

                        String status = response.get("status").getAsString();
                        if (status.equals("success")) {

                            // get the products
                            JsonArray array = response.get("data").getAsJsonArray();
                            for (int index = 0; index < array.size(); index++) {
                                JsonObject object = array.get(index).getAsJsonObject();

                                //String order_date="Mar 10, 2016 6:30:00 PM";
                                int order_code = object.get("order_code").getAsInt();
                                String order_date=object.get("order_date").getAsString();
                                Log.e("OrderDetailsActivity",order_date);

                                String deliverd_date=object.get("deliverd_date").getAsString();
                                Log.e("OrderDetailsActivity","DeliveryDate"+ deliverd_date);

                                String lc_product_name = object.get("lc_product_name").getAsString();
                                String image = object.get("image").getAsString();
                                float total = object.get("Total").getAsFloat();
                                orders.add(new Order(order_code,order_date,deliverd_date,lc_product_name,total,image));
                            }

                            adapter.notifyDataSetChanged();
                        }

                    }

                });

    }

    @Override
    public void onClick(int position) {

    }
}
