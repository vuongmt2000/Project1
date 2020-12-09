package com.example.cuahangfood.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.cuahangfood.model.Oder;

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
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
