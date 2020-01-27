package com.example.application1.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.application1.R;
import com.example.application1.db.Constants;
import com.example.application1.db.Utils;
import com.example.application1.model.Product;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class ProductDetailsActivity extends BackActivity {

    TextView textPrice, textDescription;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        textPrice = findViewById(R.id.textPrice);
        textDescription = findViewById(R.id.textDescription);
        imageView = findViewById(R.id.imageView);

        Intent intent = getIntent();
        Product product = (Product) intent.getSerializableExtra("product");

        // http://<ip>:4000/<tv_1.png>
        String url = Utils.createUrl(product.getImage());

        Ion.with(this)
                .load(url)
                .withBitmap()
                .intoImageView(imageView);

        textDescription.setText(product.getDescription());
        textPrice.setText("₹ " + product.getTotal());

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(product.getLc_product_name());
    }

    public void onAddToCart(View v) {
        Intent intent = getIntent();
        Product product = (Product) intent.getSerializableExtra("product");

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int user_id = preferences.getInt("user_id", 0);
        String url = Utils.createUrl(Constants.ROUTE_CART +user_id );
        Log.e("ProductDetailsActivity", "url: " + url);

        JsonObject body = new JsonObject();
        body.addProperty("p_id", product.getP_id());
        body.addProperty("quantity", 1);

        Ion.with(this)
                .load("POST", url)
                .setJsonObjectBody(body)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        String status = result.get("status").getAsString();
                        if (status.equals("success")) {
                            Toast.makeText(ProductDetailsActivity.this, "Successfully added this product to your cart", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ProductDetailsActivity.this, result.get("error").getAsJsonObject().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        }
        public void onBuy(View v)
        {
            Intent intent = getIntent();
            Product product = (Product) intent.getSerializableExtra("product");


            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            int user_id = preferences.getInt("user_id", 0);

            String url = Utils.createUrl(Constants.ROUTE_ORDER );
            Log.e("ProductDetailsActivity", "url: " + url);

            JsonObject body = new JsonObject();
            body.addProperty("p_id", product.getP_id());
            body.addProperty("user_id",user_id);

            Ion.with(this)
                    .load("POST", url)
                    .setJsonObjectBody(body)
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            String status = result.get("status").getAsString();
                            if (status.equals("success")) {
                                Toast.makeText(ProductDetailsActivity.this, "Order Placed Successfully ", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(ProductDetailsActivity.this, OrderDetailsActivity.class);
                                startActivity(intent);

                            } else {
                                Toast.makeText(ProductDetailsActivity.this, result.get("error").getAsJsonObject().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }
}
