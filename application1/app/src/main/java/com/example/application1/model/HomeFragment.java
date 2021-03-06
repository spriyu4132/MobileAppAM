package com.example.application1.model;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.application1.R;
import com.example.application1.activity.CartActivity;
import com.example.application1.activity.ProductDetailsActivity;
import com.example.application1.activity.SignInActivity;
import com.example.application1.adapter.ProductGridAdapter;
import com.example.application1.db.Constants;
import com.example.application1.db.Utils;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
//
public class HomeFragment extends Fragment {
    //
//   // RecyclerView recyclerView;
//    ProductGridAdapter adapter;
//    ArrayList<Product> products = new ArrayList<>();
//
//    @Nullable
//    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
//        // 1. get a reference to recyclerView
//        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.navrecyclerView);
//
//        //recyclerView = findViewById(R.id.navrecyclerView);
//
//        ProductGridAdapter adapter = new ProductGridAdapter(this, products, this);
//        // 4. set adapter
//        //recyclerView.setAdapter(mAdapte
//
//        adapter = new ProductGridAdapter(this, products, this);
//        recyclerView.setAdapter(adapter);
//        final FragmentActivity c = getActivity();
//
//        GridLayoutManager layoutManager = new GridLayoutManager(c,2);
//        recyclerView.setLayoutManager(layoutManager);
        //return inflater.inflate(R.layout.fragment_home,container,false);
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}
//    public void onResume() {
//        super.onResume();
//        loadProducts();
//    }
//
//    private void loadProducts()
//    {
//        products.clear();
//
//        String url = Utils.createUrl(Constants.ROUTE_PRODUCT);
//
//        // send GET HTTP request
//        Ion.with(this)
//                .load("GET", url)
//                .asJsonObject()
//                .setCallback(new FutureCallback<JsonObject>() {
//                    @Override
//                    public void onCompleted(Exception e, JsonObject response) {
//
//                        String status = response.get("status").getAsString();
//                        if (status.equals("success")) {
//
//                            // get the products
//                            JsonArray array = response.get("data").getAsJsonArray();
//                            for (int index = 0; index < array.size(); index++) {
//                                JsonObject object = array.get(index).getAsJsonObject();
//
//                                int p_id = object.get("p_id").getAsInt();
//                                String lc_product_code = object.get("lc_product_code").getAsString();
//                                String lc_product_name = object.get("lc_product_name").getAsString();
//                                String category_id = object.get("category_id").getAsString();
//                                int weight_per_garm=object.get("weight_per_garm").getAsInt();
//                                int available_quantity=object.get("available_quantity").getAsInt();
//                                String description = object.get("description").getAsString();
//                                String image = object.get("image").getAsString();
//                                float lc_charge=object.get("lc_charge").getAsFloat();
//                                float gst=object.get("gst").getAsFloat();
//                                String type_of_making = object.get("type_of_making").getAsString();
//                                float purity=object.get("purity").getAsFloat();
//                                float total = object.get("Total").getAsFloat();
//                                // String rating = object.get("rating").getAsString();
//
//
//                                products.add(new Product(p_id, lc_product_code,category_id,weight_per_garm,available_quantity,description,lc_product_name,lc_charge,gst,type_of_making,purity,total,image));
//                            }
//
//                            adapter.notifyDataSetChanged();
//                        }
//
//                    }
//
//                });
//
//    }
//    public void onClick(int position) {
//        Product product = products.get(position);
//
//        Intent intent = new Intent(getActivity(),ProductDetailsActivity.class);
//        intent.putExtra("product", product);
//        startActivity(intent);
//    }
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.menu_main_activity, menu);
//        super.onCreateOptionsMenu(menu,inflater);
//    }
//
////    public boolean onCreateOptionsMenu(Menu menu) {
////        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
////        return super.onCreateOptionsMenu(menu);
////    }
//
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == R.id.menuRefresh) {
//            loadProducts();
//        } else if (item.getItemId() == R.id.menuSignout) {
//            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
//            preferences.edit()
//                    .putInt("user_id", 0)
//                    .putString("email_id", "")
//                    .putString("password", "")
//                    .putBoolean("login_status", false)
//                    .commit();
//
//            Intent intent = new Intent(getActivity(), SignInActivity.class);
//            startActivity(intent);
//            //finish();
//        } else if (item.getItemId() == R.id.menuCart) {
//            Intent intent = new Intent(getActivity(), CartActivity.class);
//            startActivity(intent);
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//        return false;
//    }
//}
