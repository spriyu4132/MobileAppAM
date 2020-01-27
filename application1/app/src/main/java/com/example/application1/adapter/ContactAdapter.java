package com.example.application1.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.application1.R;
import com.example.application1.model.Contact;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder>
{
    public interface ContactAdapterActionListner
    {
         void onEdit(int position);
         void onDelete(int position);
    }
    Context context;
    ArrayList<Contact> contacts;
    ContactAdapterActionListner listner;

    public ContactAdapter(Context context, ArrayList<Contact> contacts, ContactAdapterActionListner listner)
    {
        this.context = context;
        this.contacts = contacts;
        this.listner = listner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        LayoutInflater inflater=LayoutInflater.from(context);
        LinearLayout layout= (LinearLayout) inflater.inflate(R.layout.recyclerview_item_contact,null);
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i)
    {
        final Contact contact=contacts.get(i);

        viewHolder.textName.setText(contact.getName());
        viewHolder.textAddress.setText(contact.getAddress());
        viewHolder.textEmail.setText(contact.getEmail());
        viewHolder.textPhone.setText(contact.getPhone());
        viewHolder.textCity.setText(contact.getCity());

        viewHolder.btnEdit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.e("ContactAdapter","edit"+contact.getName());
                listner.onEdit(i);
            }
        });

        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("ContactAdapter","delete"+contact.getName());
                listner.onDelete(i);
            }
        });

    }

    @Override
    public int getItemCount()
    {
        return contacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView textName,textAddress,textEmail,textPhone,textCity;
        ImageButton btnEdit,btnDelete;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            textName=itemView.findViewById(R.id.textName);
            textAddress=itemView.findViewById(R.id.textAddress);
            textEmail=itemView.findViewById(R.id.textEmail);
            textPhone=itemView.findViewById(R.id.textPhone);
            textCity=itemView.findViewById(R.id.textCity);

            btnEdit=itemView.findViewById(R.id.btnEdit);
            btnDelete=itemView.findViewById(R.id.btnDelete);
        }
    }
}
