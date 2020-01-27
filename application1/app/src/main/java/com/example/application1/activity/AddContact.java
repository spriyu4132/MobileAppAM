package com.example.application1.activity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.application1.R;
import com.example.application1.db.DbHelper;

public class AddContact extends BackActivity
{
    EditText editName,editAddress,editEmail,editPhone;
    String cities[]={"pune","Nashik","Banglore","Mumbai"};
    ArrayAdapter<String> adapter;
    Spinner spinCity;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        editName=findViewById(R.id.editName);
        editAddress=findViewById(R.id.editAddress);
        editEmail=findViewById(R.id.editEmail);
        editPhone=findViewById(R.id.editPhone);
        spinCity=findViewById(R.id.spinnerCity);

        adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,cities);
        spinCity.setAdapter(adapter);
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
        int id=item.getItemId();
        if(id==R.id.menuSave)
        {
            onSave();
        }
        return super.onOptionsItemSelected(item);
    }

    private void onSave()
    {
        if (editName.getText().toString().length() == 0) {
            Toast.makeText(this, "Enter name", Toast.LENGTH_SHORT).show();
        } else if (editAddress.getText().toString().length() == 0) {
            Toast.makeText(this, "Enter address", Toast.LENGTH_SHORT).show();
        } else {

            DbHelper helper = new DbHelper(this);
            SQLiteDatabase db = helper.getWritableDatabase();

            int position = spinCity.getSelectedItemPosition();

            ContentValues values = new ContentValues();
            values.put("name", editName.getText().toString());
            values.put("address", editAddress.getText().toString());
            values.put("email", editEmail.getText().toString());
            values.put("phone", editPhone.getText().toString());
            values.put("city", cities[position]);

            db.insert("contact", null, values);

            db.close();

            Toast.makeText(this, "Added new contact successfully", Toast.LENGTH_SHORT).show();

            finish();
        }
    }
}
