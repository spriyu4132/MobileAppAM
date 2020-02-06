package com.example.application1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.application1.R;
import com.example.application1.model.Contact;
import com.example.application1.model.Product;
import com.example.application1.model.User;

import java.util.ArrayList;

public class EditProfileActivity extends AppCompatActivity
{
    EditText editFirstName,editLastName,editEmail,editMobileNumber;
    String gender[]={"Female","Male","Other"};

    ArrayAdapter<String> adapter;
    //ArrayList<User> users = new ArrayList<>();
    Spinner spinnerGender;
    User users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);


        editFirstName=findViewById(R.id.editFirstName);
        editLastName=findViewById(R.id.editLastName);
        editEmail=findViewById(R.id.editEmail);
        editMobileNumber=findViewById(R.id.editMobileNumber);

        spinnerGender=findViewById(R.id.spinnerGender);



        editFirstName.setText(users.getFirst_name());
        editLastName.setText(users.getLast_name());
        editEmail.setText(users.getEmail_id());
        editMobileNumber.setText(users.getMobile_no());

        adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,gender);
        spinnerGender.setAdapter(adapter);
    }
}
