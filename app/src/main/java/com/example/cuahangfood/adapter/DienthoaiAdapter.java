package com.example.cuahangfood.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cuahangfood.R;
import com.example.cuahangfood.model.Product;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DienthoaiAdapter extends BaseAdapter {
    Context context ;
    ArrayList<Product> arraydienthoai;

    public DienthoaiAdapter(Context context, ArrayList<Product> arraydienthoai) {

        this.context = context;
        this.arraydienthoai = arraydienthoai;
    }

    @Override
    public int getCount() {
        Log.d("123",arraydienthoai.size()+"");
        return arraydienthoai.size();
    }
    public void filterList(ArrayList<Product> filterList){
        arraydienthoai = filterList;
        notifyDataSetChanged();
    }

    @Override
    public Object getItem(int i) {
        return arraydienthoai.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class ViewHolder{
     public  TextView textViewtendt,textViewgiadt,textViewGioithieudt;
     public ImageView imageViewdt;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_dien_thoai,null);
            viewHolder.textViewtendt =  (TextView) view.findViewById(R.id.textviewtendt);
            viewHolder.textViewgiadt =  (TextView) view.findViewById(R.id.textviewgiadt);
            viewHolder.textViewGioithieudt = (TextView) view.findViewById(R.id.textviewgioithieudt);
            viewHolder.imageViewdt = (ImageView) view.findViewById(R.id.imageviewdienthoai);
            view.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
            Product product = (Product) getItem(i);
            Log.d("hellll",product.getTensanpham());
            viewHolder.textViewtendt.setMaxLines(2);
            viewHolder.textViewtendt.setEllipsize(TextUtils.TruncateAt.END);
            viewHolder.textViewtendt.setText(product.getTensanpham());
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            viewHolder.textViewgiadt.setText("Giá : "+decimalFormat.format(product.getGiasanpham()) +" Đ");
            viewHolder.textViewGioithieudt.setMaxLines(2);
            viewHolder.textViewGioithieudt.setEllipsize(TextUtils.TruncateAt.END);
            viewHolder.textViewGioithieudt.setText(product.getMotosanpham());
            Picasso.get().load(product.getHinhanhsanpham()).placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_banner_foreground).
                    into(viewHolder.imageViewdt);

        return view;
    }
}
