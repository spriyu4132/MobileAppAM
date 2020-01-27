package com.example.application1.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.application1.R;
import com.example.application1.db.DbHelper;
import com.example.application1.model.Contact;

public class EditContact extends BackActivity
{
    EditText editName,editAddress,editEmail,editPhone;
    Contact contact;
    String cities[]={"Pune","Nashik","Mumbai","Banglore"};
    ArrayAdapter<String> cityAdapter;
    Spinner spinnerCity;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("EditContact");

        editName=findViewById(R.id.editName);
        editAddress=findViewById(R.id.editAddress);
        editEmail=findViewById(R.id.editEmail);
        editPhone=findViewById(R.id.editPhone);

        spinnerCity=findViewById(R.id.spinnerCity);

        Intent intent=getIntent();
        contact= (Contact) intent.getSerializableExtra("contact");

        editName.setText(contact.getName());
        editAddress.setText(contact.getAddress());
        editEmail.setText(contact.getEmail());
        editPhone.setText(contact.getPhone());

        cityAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,cities);
        spinnerCity.setAdapter(cityAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_save,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == R.id.menuSave) {

            // update

            DbHelper helper = new DbHelper(this);
            SQLiteDatabase db = helper.getWritableDatabase();
            int position = spinnerCity.getSelectedItemPosition();

            ContentValues values = new ContentValues();
            values.put("name", editName.getText().toString());
            values.put("address", editAddress.getText().toString());
            values.put("email", editEmail.getText().toString());
            values.put("phone", editPhone.getText().toString());
            values.put("city",cities[position]);

            db.update("contact", values, "id=?", new String[] { "" + contact.getId() });

            db.close();

            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}