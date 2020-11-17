package com.example.cuahangfood.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahangfood.R;
import com.example.cuahangfood.activity.Chitietsanpham;
import com.example.cuahangfood.activity.MainActivity;
import com.example.cuahangfood.model.Product;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SanphamAdapter extends RecyclerView.Adapter<SanphamAdapter.ItemHolder> {
   Context context;
   ArrayList<Product> arraySanpham;

    public SanphamAdapter(Context context, ArrayList<Product> arraySanpham) {
        this.context = context;
        this.arraySanpham = arraySanpham;
    }

    @Override
    public ItemHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sanpham_moinhat,null);
        ItemHolder itemHolder = new ItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder( ItemHolder holder, int position) {
    Product sanpham =arraySanpham.get(position);
    holder.txttensanphan.setText(sanpham.getTensanpham());
        DecimalFormat  decimalFormat = new DecimalFormat("###,###,###");
        holder.txtgiasanpham.setText("Giá : "+decimalFormat.format(sanpham.getGiasanpham()) +" Đ");
        Log.d("hinh anh:", sanpham.getHinhanhsanpham());
        Picasso.get().load(sanpham.getHinhanhsanpham()).placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_banner_foreground).
                into(holder.imghinhsanpham);

    }

    @Override
    public int getItemCount() {
        return arraySanpham.size();
    }


    public class ItemHolder extends  RecyclerView.ViewHolder{
        public ImageView imghinhsanpham;
        public TextView txttensanphan,txtgiasanpham;

        public ItemHolder(View itemView){
            super(itemView);
                imghinhsanpham = (ImageView) itemView.findViewById(R.id.imageviewsanpham);
                txtgiasanpham = (TextView) itemView.findViewById(R.id.textviewgiasp);
                txttensanphan = (TextView) itemView.findViewById(R.id.texrviewtensanpham);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(MainActivity.status==1){
                            Intent intent = new Intent(context, Chitietsanpham.class);
                            intent.putExtra("thongtinsanpham",arraySanpham.get(getPosition()));
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        }
                    }
                });
        }
    }
}
