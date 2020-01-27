package com.example.application1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.application1.R;
import com.example.application1.db.Constants;
import com.example.application1.db.Utils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class SignInActivity extends AppCompatActivity {

    TextInputEditText editEmail,editPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        editEmail=findViewById(R.id.editEmail);
        editPassword=findViewById(R.id.editPassword);
    }
    public void onSignup(View v) {
        Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    public void onSignin(View v) {
        final String email_id = editEmail.getText().toString();
        String password = editPassword.getText().toString();

        if (email_id.length() == 0) {
            Toast.makeText(this, "Enter email_id", Toast.LENGTH_SHORT).show();
        } else if (password.length() == 0) {
            Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show();
        } else {

            // create body
            JsonObject body = new JsonObject();
            body.addProperty("email_id", email_id);
            body.addProperty("password", password);

            // send POST /user/signin HTTP request
            String url = Utils.createUrl(Constants.ROUTE_USER_SIGNIN);
            Log.e("SigninActivity", "url: " + url);

            Ion.with(this)
                    .load("POST", url)
                    .setJsonObjectBody(body)
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            String status = result.get("status").getAsString();
                            if (status.equals("success")) {

                                // get the user details
                                JsonObject object = result.get("data").getAsJsonObject();

                                // save the last login
                                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(SignInActivity.this);
                                preferences.edit()
                                        .putString("email_id", object.get("email_id").getAsString())
                                        .putString("password", object.get("password").getAsString())
                                        .putInt("user_id", object.get("user_id").getAsInt())
                                        .putString("first_name",object.get("first_name").getAsString())
                                        .putBoolean("login_status", true)
                                        .commit();
                                Log.e("SigninActivity", "email_id: " + email_id);

                                //Intent intent = new Intent(SignInActivity.this, ProductListActivity.class);
                                Intent intent = new Intent(SignInActivity.this, NavigationDrawer.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(SignInActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }
    }
}
