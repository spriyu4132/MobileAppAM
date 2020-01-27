package com.example.application1.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application1.R;
import com.example.application1.db.Utils;
import com.example.application1.model.HomeFragment;
import com.example.application1.model.Product;
import com.koushikdutta.ion.Ion;


import java.util.ArrayList;

public class ProductGridAdapter extends RecyclerView.Adapter<ProductGridAdapter.ViewHolder> {



    public interface ActionListener {
        void onClick(int position);
    }

    private final Context context;
    private final ArrayList<Product> products;
    private final ActionListener listener;

//    private final HomeFragment homeFragment;
//    public ProductGridAdapter(HomeFragment homeFragment, ArrayList<Product> products, HomeFragment listener)
//    {
//        this.homeFragment = homeFragment;
//        this.products = products;
//        this.listener = listener;
//        context = null;
//    }
    public ProductGridAdapter(Context context, ArrayList<Product> products, ActionListener listener) {
        this.context = context;
        this.products = products;
        this.listener = listener;
        //homeFragment = null;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.recyclerview_item_product_grid, null);
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Product product = products.get(position);

        // http://<ip>:4000/<tv_1.png>
        String url = Utils.createUrl(product.getImage());

        Ion.with(this.context)
                .load(url)
                .withBitmap()
                .intoImageView(holder.imageView);

        holder.textTitle.setText(product.getLc_product_name());
        holder.textPrice.setText("₹ " + product.getTotal());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(position);
                Log.e("Id",""+position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View itemView;

        ImageView imageView;

        TextView textTitle, textPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.itemView = itemView;

            imageView = itemView.findViewById(R.id.imageView);

            textTitle = itemView.findViewById(R.id.textTitle);
            textPrice = itemView.findViewById(R.id.textPrice);
        }
    }
}
