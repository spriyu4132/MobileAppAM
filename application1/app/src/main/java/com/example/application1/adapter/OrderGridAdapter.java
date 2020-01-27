package com.example.application1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.application1.R;
import com.example.application1.db.Utils;
import com.example.application1.model.Order;
import com.example.application1.model.Product;
import com.koushikdutta.ion.Ion;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class OrderGridAdapter extends RecyclerView.Adapter<OrderGridAdapter.ViewHolder> {

    public interface ActionListener
    {
        void onClick(int position);
    }

    private final Context context;
    private final ArrayList<Order> orders;
    private final ActionListener listener;

    public OrderGridAdapter(Context context, ArrayList<Order> orders,ActionListener listener) {
        this.context = context;
        this.orders = orders;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.recyclerview_orderdetails, null);
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Order order = orders.get(position);

        String url = Utils.createUrl(order.getImage());

        Ion.with(this.context)
                .load(url)
                .withBitmap()
                .intoImageView(holder.imageView);

        holder.textOrderCode.setText(""+order.getOrder_code());
        holder.textProductName.setText(order.getLc_product_name());
        holder.textTotal.setText("₹ "+order.getTotal());
        holder.textOrderDate.setText(order.getOrder_date());
        holder.textDeliverDate.setText(order.getDeliverd_date());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View itemView;
        ImageView imageView;
        TextView textOrderCode, textOrderDate,textDeliverDate,textProductName,textTotal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;

            imageView=itemView.findViewById(R.id.orderimageView);
            textOrderCode = itemView.findViewById(R.id.textOrderCode);
            textProductName=itemView.findViewById(R.id.textProductName);
            textTotal=itemView.findViewById(R.id.textTotal);
            textOrderDate = itemView.findViewById(R.id.textOrderDate);
            textDeliverDate = itemView.findViewById(R.id.textDeliverDate);
        }
    }
}

