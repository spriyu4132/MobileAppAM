package com.example.application1.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.application1.R;
import com.example.application1.adapter.ContactAdapter;
import com.example.application1.db.DbHelper;
import com.example.application1.model.Contact;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ContactAdapter.ContactAdapterActionListner
{
    RecyclerView recyclerView;
    ArrayList<Contact> contacts=new ArrayList<>();
    ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerview);
        adapter=new ContactAdapter(this,contacts,this);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public void onEdit(int position)
    {
        Contact contact=contacts.get(position);
        Intent intent=new Intent(this,EditContact.class);
        intent.putExtra("contact", (Serializable) contact);
        startActivity(intent);
    }

    @Override
    public void onDelete(final int position)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Confirmation");
        builder.setMessage("Are you sure to delete contact...");

        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Contact contact=contacts.get(position);
                DbHelper helper=new DbHelper(MainActivity.this);
                SQLiteDatabase database=helper.getWritableDatabase();
                database.delete("Contact","id=?",new String[]{""+contact.getId()});
                database.close();

                loadContact();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void loadContact()
    {
        contacts.clear();

        DbHelper helper=new DbHelper(this);
        SQLiteDatabase database=helper.getReadableDatabase();

        String contact[]={"id","name","address","email","phone","city"};
        Cursor cursor=database.query("contact",contact,null,null,null,null,null);

        if(!cursor.isAfterLast())
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                Contact contact1=new Contact();
                contact1.setId(cursor.getInt(0));
                contact1.setName(cursor.getString(1));
                contact1.setAddress(cursor.getString(2));
                contact1.setEmail(cursor.getString(3));
                contact1.setPhone(cursor.getString(4));
                contact1.setCity(cursor.getString(5));

                contacts.add(contact1);
                cursor.moveToNext();

            }
            }
        cursor.close();
        database.close();
        adapter.notifyDataSetChanged();
    }
    protected void onResume() {
        super.onResume();

        // load the contacts
        loadContact();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_add,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();
        if(id==R.id.menuAdd)
        {
            Intent intent=new Intent(this,AddContact.class);
            startActivity(intent);
        }
        else if(id==R.id.menuClose)
        {
            finish();
        }
        else if (id == R.id.menuSearch) {
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
