package com.example.application1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.application1.R;
import com.example.application1.adapter.ProductAdapter;
import com.example.application1.db.Constants;
import com.example.application1.db.Utils;
import com.example.application1.model.CartItem;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

public class CartActivity extends BackActivity implements ProductAdapter.ActionListener {


    RecyclerView recyclerView;
    ProductAdapter adapter;
    TextView textViewPrice;
    ArrayList<CartItem> products = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        textViewPrice=findViewById(R.id.textViewPrice);

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new ProductAdapter(this, products, this);
        recyclerView.setAdapter(adapter);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadProducts();
    }

    private void updatePrice() {
        float Total = 0;
        for (CartItem item : products) {
            Total += (item.getTotal() * item.getQuantity());
            Log.e("CartActivity","url: "+Total);
        }

        textViewPrice.setText("₹ " + Total);
    }

    private void loadProducts() {
        products.clear();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int user_id = preferences.getInt("user_id", 0);

        String url = Utils.createUrl(Constants.ROUTE_CART_ITEMS + user_id);
        Log.e("CartActivity", "url: " + url);

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

                                int p_id = object.get("p_id").getAsInt();
                                int sr_no = object.get("sr_no").getAsInt();
                                String lc_product_name = object.get("lc_product_name").getAsString();
                                String description = object.get("description").getAsString();
                                String image = object.get("image").getAsString();
                                float Total = object.get("Total").getAsFloat();
                                String category_id = object.get("category_id").getAsString();
                                int quantity = object.get("quantity").getAsInt();

                                products.add(new CartItem(p_id, sr_no, lc_product_name, description, image, Total, category_id, quantity));
                            }

                            adapter.notifyDataSetChanged();

                            updatePrice();
                        }

                    }

                });

    }

    @Override
    public void onDecrement(int position) {
        CartItem item = products.get(position);
        int quantity = item.getQuantity() - 1;

        if (quantity == 0) {
            removeProductFromCart(item.getSr_no());
        } else {
            updateQuantity(item.getSr_no(), quantity);
        }
    }

    @Override
    public void onIncrement(int position) {
        CartItem item = products.get(position);
        int quantity = item.getQuantity() + 1;

        updateQuantity(item.getSr_no(), quantity);
    }

    private void removeProductFromCart(int cartItemId) {
        String url = Utils.createUrl(Constants.ROUTE_CART + cartItemId);

        Ion.with(this)
                .load("DELETE", url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        String status = result.get("status").getAsString();
                        if (status.equals("success")) {
                            Toast.makeText(CartActivity.this, "Successfully removed product", Toast.LENGTH_SHORT).show();

                            loadProducts();
                        }

                    }
                });
    }

    private void updateQuantity(int cartItemId, int quantity) {
        String url = Utils.createUrl(Constants.ROUTE_CART + cartItemId);
        JsonObject body = new JsonObject();
        body.addProperty("quantity", quantity);

        Ion.with(this)
                .load("PUT", url)
                .setJsonObjectBody(body)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        String status = result.get("status").getAsString();
                        if (status.equals("success")) {
                            Toast.makeText(CartActivity.this, "Successfully updated quantity", Toast.LENGTH_SHORT).show();

                            loadProducts();
                        }

                    }
                });
    }

}
