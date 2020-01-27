package com.example.application1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.application1.R;
import com.example.application1.db.Constants;
import com.example.application1.db.Utils;
import com.example.application1.model.Product;
import com.example.application1.model.User;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SignUpActivity extends AppCompatActivity {


    EditText editFirstName,editLastName,editEmail,editPassword,editMobileNo;
    //String cities[]={"Parola","Jalgaon","Dhule","Pune","Nashik","Banglore","Mumbai"};
    String gender[]={"Female","Male","Other"};

//    InputStream is=null;
//    String result=null;
//    String line=null;
//    String pincode[];

    ArrayAdapter<String> adapter;
    ArrayList<User> users = new ArrayList<>();

    Spinner spinCity,spinGender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editFirstName=findViewById(R.id.editFirstName);
        editLastName=findViewById(R.id.editLastName);
        spinGender=findViewById(R.id.spinnerGender);
        editEmail=findViewById(R.id.editEmail);
        editPassword=findViewById(R.id.editPassword);
        editMobileNo=findViewById(R.id.editMobileNo);

        //spinCity=findViewById(R.id.spinnerCity);
        //final List<String> list1=new ArrayList<String>();

        //adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,cities);
        //spinCity.setAdapter(adapter);

        adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,gender);
        spinGender.setAdapter(adapter);
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_save,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();
        if(id==R.id.menuSave)
        {
            onSave();
        }
        return super.onOptionsItemSelected(item);
    }

    private void onSave()
    {
        String first_name=editFirstName.getText().toString();
        String last_name=editLastName.getText().toString();
        int gender1 = spinGender.getSelectedItemPosition();
        String email_id = editEmail.getText().toString();
        String password = editPassword.getText().toString();
        int mobileno= Integer.parseInt(editMobileNo.getText().toString());
        //int city=spinCity.getSelectedItemPosition();

        if (first_name.length() == 0) {
            Toast.makeText(this, "Enter First Name", Toast.LENGTH_SHORT).show();
        }
        else if (last_name.length() == 0) {
            Toast.makeText(this, "Enter Last Name", Toast.LENGTH_SHORT).show();
        }
        else if (email_id.length() == 0) {
            Toast.makeText(this, "Enter email_id", Toast.LENGTH_SHORT).show();
        } else if (password.length() == 0) {
            Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show();
        }
        else {

            // create body
            JsonObject body = new JsonObject();
            body.addProperty("first_name", first_name);
            body.addProperty("last_name", last_name);
            body.addProperty("gender", gender[gender1]);
            body.addProperty("email_id", email_id);
            body.addProperty("password", password);
            body.addProperty("mobile_no", mobileno);
            //spinPincode();
            //body.addProperty("pincode", cities[city]);


            // send POST /user/signin HTTP request
            String url = Utils.createUrl(Constants.ROUTE_USER_SIGNUP);
            Log.e("SignUpActivity", "url: " + url);



            Ion.with(this)
                    .load("POST", url)
                    .setJsonObjectBody(body)
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            String status = result.get("status").getAsString();
                            if (status.equals("success")) {
                                finish();
                            } else {
                                String error = result.get("error").getAsString();
                                Toast.makeText(SignUpActivity.this, error, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

}
