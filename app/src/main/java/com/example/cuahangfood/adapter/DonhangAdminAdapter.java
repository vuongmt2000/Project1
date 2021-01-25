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
        ViewHolder viewHolder1 = null;
        if (view == null){
            Log.d("vuong123","donhangAdapter");
            viewHolder1 = new DonhangAdminAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dongdonhang,null);
            viewHolder1.madonhang =  (TextView) view.findViewById(R.id.textviewmadonhang);
            viewHolder1.tennguoimua =  (TextView) view.findViewById(R.id.tennguoimuahang);
            viewHolder1.sodienthoai = (TextView) view.findViewById(R.id.sodienthoainguoimua);
            viewHolder1.diachinguoimua = (TextView) view.findViewById(R.id.diachinguoimua);
            viewHolder1.email = (TextView) view.findViewById(R.id.emailnguoimua);
            view.setTag(viewHolder1);

        }else {
            viewHolder1 = (DonhangAdminAdapter.ViewHolder) view.getTag();
        }
        Oder oder = (Oder) getItem(i);
        Log.d("hellll",oder.getSodienthoai()+"");
        viewHolder1.madonhang.setText(oder.getMadonhang()+"");
        viewHolder1.tennguoimua.setText(oder.getTennguoimua());
        viewHolder1.sodienthoai.setText("0"+oder.getSodienthoai());
        viewHolder1.diachinguoimua.setMaxLines(2);
        viewHolder1.diachinguoimua.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder1.diachinguoimua.setText(oder.getDiachinguoimua());
        viewHolder1.email.setText(oder.getEmail());

        return view;
    }
}
