package com.example.application1.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;

import com.example.application1.R;
import com.example.application1.adapter.ProductGridAdapter;
import com.example.application1.db.Constants;
import com.example.application1.db.Utils;
import com.example.application1.model.BraceletFragment;
import com.example.application1.model.EarRingFragment;
import com.example.application1.model.NecklaceFragment;
import com.example.application1.model.Product;
import com.example.application1.model.RingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

public class ProductListActivity extends BackActivity implements ProductGridAdapter.ActionListener {


    RecyclerView recyclerView;
    ProductGridAdapter adapter;
    ArrayList<Product> products = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

//        BottomNavigationView bottomnav=findViewById(R.id.bottom_navigation);
//        //bottomnav.setOnNavigationItemSelectedListener(navListener);
//
//        bottomnav.setOnNavigationItemSelectedListener(
//                new BottomNavigationView.OnNavigationItemSelectedListener()
//                {
//                    @Override
//                    public boolean onNavigationItemSelected(@NonNull MenuItem item)
//                    {
//                        Fragment selectedFragment=null;
//                        switch (item.getItemId()) {
//                            case R.id.earring:
//                            selectedFragment =new EarRingFragment();
//                               break;
//                            case R.id.ring:
//                    selectedFragment=new RingFragment();
//                    break;
//                            case R.id.necklace:
//                   selectedFragment=new NecklaceFragment();
//                    break;
//                            case R.id.bracelet:
//                    selectedFragment=new BraceletFragment();
//                    break;
//                        }
//                        return false;
//                    }
//                });

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new ProductGridAdapter(this, products, this);
        recyclerView.setAdapter(adapter);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
    }
//    private BottomNavigationView.OnNavigationItemReselectedListener navListener= new BottomNavigationView.OnNavigationItemReselectedListener() {
//        @Override
//        public void onNavigationItemReselected(@NonNull MenuItem menuItem)
//        {
//            Fragment selectedFragment=null;
//            switch (menuItem.getItemId())
//            {
//                case R.id.earring:
//                    selectedFragment =new EarRingFragment();
//                    break;
//                case R.id.ring:
//                    selectedFragment=new RingFragment();
//                    break;
//                case R.id.necklace:
//                    selectedFragment=new NecklaceFragment();
//                    break;
//                case R.id.bracelet:
//                    selectedFragment=new BraceletFragment();
//                    break;
//            }
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
//        }
//    };

    @Override
    protected void onResume() {
        super.onResume();
        loadProducts();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuRefresh) {
            loadProducts();
        } else if (item.getItemId() == R.id.menuSignout) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            preferences.edit()
                    .putInt("user_id", 0)
                    .putString("email_id", "")
                    .putString("password", "")
                    .putBoolean("login_status", false)
                    .commit();

            Intent intent = new Intent(this, SignInActivity.class);
            startActivity(intent);
            finish();
        } else if (item.getItemId() == R.id.menuCart) {
            Intent intent = new Intent(this, CartActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadProducts() {
        products.clear();

        String url = Utils.createUrl(Constants.ROUTE_PRODUCT);

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
                                String lc_product_code = object.get("lc_product_code").getAsString();
                                String lc_product_name = object.get("lc_product_name").getAsString();
                                String category_id = object.get("category_id").getAsString();
                                int weight_per_garm=object.get("weight_per_garm").getAsInt();
                                int available_quantity=object.get("available_quantity").getAsInt();
                                String description = object.get("description").getAsString();
                                String image = object.get("image").getAsString();
                                float lc_charge=object.get("lc_charge").getAsFloat();
                                float gst=object.get("gst").getAsFloat();
                                String type_of_making = object.get("type_of_making").getAsString();
                                float purity=object.get("purity").getAsFloat();
                                float total = object.get("Total").getAsFloat();
                               // String rating = object.get("rating").getAsString();


                                products.add(new Product(p_id, lc_product_code,category_id,weight_per_garm,available_quantity,description,lc_product_name,lc_charge,gst,type_of_making,purity,total,image));
                            }

                            adapter.notifyDataSetChanged();
                        }

                    }

                });

    }

    @Override
    public void onClick(int position) {
        Product product = products.get(position);

        Intent intent = new Intent(this, ProductDetailsActivity.class);
        intent.putExtra("product", product);
        startActivity(intent);
    }
}
