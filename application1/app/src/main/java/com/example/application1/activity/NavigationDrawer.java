package com.example.application1.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.application1.R;
import com.example.application1.adapter.ProductGridAdapter;
import com.example.application1.db.Constants;
import com.example.application1.db.Utils;
import com.example.application1.model.BraceletFragment;
import com.example.application1.model.EarRingFragment;
//import com.example.application1.model.HomeFragment;
import com.example.application1.model.HomeFragment;
import com.example.application1.model.NecklaceFragment;
import com.example.application1.model.Product;
import com.example.application1.model.RingFragment;
import com.example.application1.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

public class NavigationDrawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,ProductGridAdapter.ActionListener {

    TextView nav_email,nav_fullname;
    RecyclerView recyclerView;
    ProductGridAdapter adapter;
    ArrayList<Product> products = new ArrayList<>();
    View mHeaderView;
    NavigationView mNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(NavigationDrawer.this);
        boolean Islogin = prefs.getBoolean("login_status", false);
       // int user_id=prefs.getInt("user_id",2);
        String first_name=prefs.getString("first_name","priyu");
        String email_id=prefs.getString("email_id","priyu");
        Log.e("nav","user-"+email_id);


        // NavigationView
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);

        // NavigationView Header
        mHeaderView =  mNavigationView.getHeaderView(0);
        nav_fullname=(TextView)mHeaderView.findViewById(R.id.nav_fullname);
        nav_email = (TextView) mHeaderView.findViewById(R.id.nav_email);
        nav_fullname.setText(first_name);
        nav_email.setText(email_id);
       // nav_email.setText((CharSequence) nav_email);
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
//        int user_id = preferences.getInt("user_id",0);
        // Set username & email
        //nav_email.setText(PreferenceManager);
        //nav_email.setText();
//        Bundle b = getIntent().getExtras();
       // nav_email.setText(b.getCharSequence("email_id"));

        mNavigationView.setNavigationItemSelectedListener(this);

//        nav_email=findViewById(R.id.nav_email);
//
//          Intent intent = getIntent();
//         User user = (User) intent.getSerializableExtra("user_info");
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
//        int user_id = preferences.getInt("user_id",0);

//        Log.e("nav","user-"+user_id);
//         nav_email.setText(""+user_id);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        BottomNavigationView bottomnav=findViewById(R.id.bottom_navigation);
//        bottomnav.setOnNavigationItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) navListener);

                BottomNavigationView bottomnav=findViewById(R.id.bottom_navigation);
        //bottomnav.setOnNavigationItemSelectedListener(navListener);

        //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
        bottomnav.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener()
                {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item)
                    {
                        Fragment selectedFragment=null;
                        switch (item.getItemId()) {
                            case R.id.earring:
                            selectedFragment =new EarRingFragment();
                               break;
                            case R.id.ring:
                    selectedFragment=new RingFragment();
                    break;
                            case R.id.necklace:
                   selectedFragment=new NecklaceFragment();
                    break;
                            case R.id.bracelet:
                    selectedFragment=new BraceletFragment();
                    break;
                            case R.id.home:
                                selectedFragment=new HomeFragment();
                                break;
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                        return false;
                    }
                });

        recyclerView = findViewById(R.id.navrecyclerView);
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
    protected void onResume() {
        super.onResume();
        loadProducts();
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

    public void onClick(int position) {
        Product product = products.get(position);

        Intent intent = new Intent(this, ProductDetailsActivity.class);
        intent.putExtra("product", product);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        //getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (item.getItemId() == R.id.menuRefresh)
        {
            loadProducts();
        }
        else if (item.getItemId() == R.id.menuSignout)
        {
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
        }
        else if (item.getItemId() == R.id.menuCart)
        {
            Intent intent = new Intent(this, CartActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_myorder)
        {
            //Intent intent = new Intent(NavigationDrawer.this, ProductListActivity.class);
            Intent intent = new Intent(NavigationDrawer.this, OrderDetailsActivity.class);
            startActivity(intent);
            //finish();
            // Handle the camera action
        }
        else if (id == R.id.nav_setting)
        {

        }
        else if (id == R.id.nav_smartphone)
        {

        }
        else if (id == R.id.nav_logout)
        {
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
        }
        else if (id == R.id.nav_aboutus) {

        }
        else if (id == R.id.nav_terms) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
