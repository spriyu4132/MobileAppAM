package com.example.application1.adapter;

import android.app.Fragment;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.application1.R;
import com.example.application1.db.Utils;
import com.example.application1.model.EarRingFragment;
import com.example.application1.model.Product;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentEarGridAdapter extends RecyclerView.Adapter<FragmentEarGridAdapter.ViewHolder> {
  //  private final Context context;

    private final ArrayList<Product> products;
    private final EarRingFragment listener;
    private  EarRingFragment earRingFragment;
    public FragmentEarGridAdapter(EarRingFragment earRingFragment, ArrayList<Product> products, EarRingFragment listener)
    {
        this.earRingFragment = earRingFragment;
        this.products = products;
        this.listener = listener;
    }

    public interface EarRingFragment {
        void onClick(int position);
    }



//    private final HomeFragment homeFragment;
//    public ProductGridAdapter(HomeFragment homeFragment, ArrayList<Product> products, HomeFragment listener)
//    {
//        this.homeFragment = homeFragment;
//        this.products = products;
//        this.listener = listener;
//        context = null;
//    }
//    public FragmentEarGridAdapter(Context context, ArrayList<Product> products, ActionListener listener) {
//        this.context = context;
//        this.products = products;
//        this.listener = listener;
//        //homeFragment = null;
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.recyclerview_item_product_grid, null);
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Product product = products.get(position);

        // http://<ip>:4000/<tv_1.png>
        String url = Utils.createUrl(product.getImage());

        Ion.with((androidx.fragment.app.Fragment) this.earRingFragment)
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
