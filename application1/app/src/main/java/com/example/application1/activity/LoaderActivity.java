package com.example.application1.activity;

import android.content.Intent;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.example.application1.R;

public class LoaderActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(LoaderActivity.this);
                boolean Islogin = prefs.getBoolean("login_status", false);

                if(Islogin)
                {   // condition true means user is already login
                    Intent i = new Intent(LoaderActivity.this,NavigationDrawer.class);
                    startActivityForResult(i, 1);
                    //startActivity(i);
                    finish();
                }

                else
                {
                    // condition false take it user on login form
                    Intent intent = new Intent(LoaderActivity.this, SignInActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        }).start();
    }
}
