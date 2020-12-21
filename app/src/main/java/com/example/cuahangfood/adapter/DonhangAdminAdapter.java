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
import com.example.cuahangfood.model.Oder;
import com.example.cuahangfood.model.Product;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DonhangAdminAdapter extends BaseAdapter {
    Context context;
    ArrayList<Oder> mangdonhang;

    public DonhangAdminAdapter(Context context, ArrayList<Oder> mangdonhang) {
        this.context = context;
        this.mangdonhang = mangdonhang;
    }

    @Override
    public int getCount() {
        Log.d("123456",mangdonhang.size()+"");
        return mangdonhang.size();
    }

    @Override
    public Object getItem(int i) {
        return mangdonhang.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class ViewHolder {
        public TextView  tennguoimua, madonhang, sodienthoai, diachinguoimua, email;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new DonhangAdminAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dongdonhang,null);
            viewHolder.madonhang =  (TextView) view.findViewById(R.id.textviewmadonhang);
            viewHolder.tennguoimua =  (TextView) view.findViewById(R.id.tennguoimuahang);
            viewHolder.sodienthoai = (TextView) view.findViewById(R.id.sodienthoainguoimua);
            viewHolder.diachinguoimua = (TextView) view.findViewById(R.id.diachinguoimua);
            viewHolder.email = (TextView) view.findViewById(R.id.emailnguoimua);
            view.setTag(viewHolder);

        }else {
            viewHolder = (DonhangAdminAdapter.ViewHolder) view.getTag();
        }
        Oder oder = (Oder) getItem(i);
        Log.d("hellll","12211");
        viewHolder.madonhang.setText(oder.getMadonhang());
        viewHolder.tennguoimua.setMaxLines(2);
        viewHolder.tennguoimua.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.tennguoimua.setText(oder.getTennguoimua());
        viewHolder.sodienthoai.setText(oder.getSodienthoai());
        viewHolder.diachinguoimua.setMaxLines(2);
        viewHolder.diachinguoimua.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.diachinguoimua.setText(oder.getDiachinguoimua());
        viewHolder.email.setText(oder.getEmail());

        return view;
    }
}
