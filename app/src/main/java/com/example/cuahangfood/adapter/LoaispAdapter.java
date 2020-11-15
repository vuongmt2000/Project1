package com.example.cuahangfood.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cuahangfood.R;
import com.example.cuahangfood.model.Product_Type;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.cuahangfood.R.id.textviewloaisp;

public class LoaispAdapter extends BaseAdapter {
    ArrayList<Product_Type> arraylistloaisp;
    Context context;

    public LoaispAdapter(ArrayList<Product_Type> arraylistloaisp, Context context) {
        this.arraylistloaisp = arraylistloaisp;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arraylistloaisp.size();
    }

    @Override
    public Object getItem(int i) {
        return arraylistloaisp.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    public class ViewHolder{
        TextView txttenloaisp;
        ImageView imageView;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if( view ==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_listview_producttype,null);
            viewHolder.txttenloaisp = view.findViewById(textviewloaisp);
            viewHolder.imageView = view.findViewById(R.id.imageviewloaisp);
            view.setTag(viewHolder);
        }
        else {
           viewHolder = (ViewHolder) view.getTag();
    }
        Product_Type Product_Type = (Product_Type) getItem(i);
        viewHolder.txttenloaisp.setText(Product_Type.getName_Product_Type());
        Picasso.get().load(Product_Type.getImage_Product_Type())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(viewHolder.imageView);

        return view;
    }
}
