package com.example.cuahangfood.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.cuahangfood.R;
import com.example.cuahangfood.activity.GioHang;
import com.example.cuahangfood.activity.MainActivity;
import com.example.cuahangfood.model.GioHangSP;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static java.lang.String.valueOf;

public class GioHangAdapter extends BaseAdapter {
    Context context;
    ArrayList<GioHangSP> giohang;

    public GioHangAdapter(Context context, ArrayList<GioHangSP> giohang) {
        this.context = context;
        this.giohang = giohang;
    }

    @Override
    public int getCount() {
        Log.d("111111+",giohang.size()+"");
        return giohang.size();
    }

    @Override
    public Object getItem(int i) {
        return giohang.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class ViewCart{
        public TextView txtTensp,txtGiasp,tongtien;
        public Button buttonsoluong,buttontru,buttoncong;
        public ImageView anhsp;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewCart viewCart = null;
        if( view == null){
            viewCart = new ViewCart();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dongspgiohang,null);
            viewCart.txtTensp = view.findViewById(R.id.textviewtenspgiohang);
            viewCart.txtGiasp = view.findViewById(R.id.giaspgiohang);
            viewCart.buttonsoluong = view.findViewById(R.id.buttonsoluong);
            viewCart.buttoncong = view.findViewById(R.id.buttoncong);
            viewCart.buttontru = view.findViewById(R.id.buttontru);
            viewCart.anhsp = view.findViewById(R.id.anhsptrengio);
            viewCart.tongtien = view.findViewById(R.id.textviewgiatientong);
            view.setTag(viewCart);
        }else {
            viewCart =(ViewCart)  view.getTag();
        }
       GioHangSP gioHang = (GioHangSP)getItem(i);
        Log.d("111112",gioHang.getSoluongSP()+"");
        viewCart.txtTensp.setMaxLines(2);
        viewCart.txtTensp.setEllipsize(TextUtils.TruncateAt.END);
        viewCart.txtTensp.setText(gioHang.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewCart.txtGiasp.setText(decimalFormat.format(gioHang.getGiasP())+" Đ");
        viewCart.buttonsoluong.setText(valueOf(gioHang.getSoluongSP()));
        Picasso.get().load(gioHang.getHinhanhSP()).placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_banner_foreground).into(viewCart.anhsp);
        int soluong = Integer.parseInt(viewCart.buttonsoluong.getText().toString());
        if(soluong >= 99){
            viewCart.buttoncong.setVisibility(View.INVISIBLE);
            viewCart.buttontru.setVisibility(View.VISIBLE);
        }else if (soluong <=1){
            viewCart.buttoncong.setVisibility(View.VISIBLE);
            viewCart.buttontru.setVisibility(View.INVISIBLE);
        }else {
            viewCart.buttoncong.setVisibility(View.VISIBLE);
            viewCart.buttontru.setVisibility(View.VISIBLE);
        }
        final ViewCart finalViewCart = viewCart;

        final ViewCart finalViewCart1 = viewCart;
        viewCart.buttoncong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("helloword","123");
                int slmoinhat = Integer.parseInt(finalViewCart.buttonsoluong.getText().toString())+1;
                int slhientai = MainActivity.manggiohang.get(i).getSoluongSP();
                long giahientai = MainActivity.manggiohang.get(i).getGiasP();
                MainActivity.manggiohang.get(i).setSoluongSP(slmoinhat);
                long giamoinhat = (giahientai*slmoinhat)/slhientai;
                MainActivity.manggiohang.get(i).setGiasP(giamoinhat);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewCart.txtGiasp.setText(decimalFormat.format(giamoinhat)+" Đ");
                GioHang.tongtien();
                if(slmoinhat > 98 ){
                    finalViewCart.buttoncong.setVisibility(View.INVISIBLE);
                    finalViewCart.buttontru.setVisibility(View.VISIBLE);
                    finalViewCart.buttonsoluong.setText(String.valueOf(slmoinhat));
                }else {
                    finalViewCart.buttoncong.setVisibility(View.VISIBLE);
                    finalViewCart.buttontru.setVisibility(View.VISIBLE);
                    finalViewCart.buttonsoluong.setText(String.valueOf(slmoinhat));
                }

            }
        });
        viewCart.buttontru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoinhat = Integer.parseInt(finalViewCart.buttonsoluong.getText().toString())-1;
                int slhientai = MainActivity.manggiohang.get(i).getSoluongSP();
                long giahientai = MainActivity.manggiohang.get(i).getGiasP();
                MainActivity.manggiohang.get(i).setSoluongSP(slmoinhat);
                long giamoinhat = (giahientai*slmoinhat)/slhientai;
                MainActivity.manggiohang.get(i).setGiasP(giamoinhat);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewCart.txtGiasp.setText(decimalFormat.format(giamoinhat)+" Đ");
                GioHang.tongtien();
                if(slmoinhat <1 ){
                    finalViewCart.buttoncong.setVisibility(View.VISIBLE);
                    finalViewCart.buttontru.setVisibility(View.INVISIBLE);
                    finalViewCart.buttonsoluong.setText(String.valueOf(slmoinhat));
                }else {
                    finalViewCart.buttoncong.setVisibility(View.VISIBLE);
                    finalViewCart.buttontru.setVisibility(View.VISIBLE);
                    finalViewCart.buttonsoluong.setText(String.valueOf(slmoinhat));
                }

            }

        });
        return view;
    }
}
