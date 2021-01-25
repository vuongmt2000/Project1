package com.example.cuahangfood.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cuahangfood.R;
import com.example.cuahangfood.adapter.GioHangAdapter;
import com.example.cuahangfood.adapter.ThanhToanAdapter;
import com.example.cuahangfood.model.GioHangSP;
import com.example.cuahangfood.ultil.Server;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DonHangChiTiet extends AppCompatActivity {
    private static TextView tongtiendonhang;
    ListView listviewdonhangchitiet;
    Toolbar toolbardonhangchitiet;
    int id = 0;
//    int masanpham = 0;
    String tensanpham = "";
    int giasanpham = 0;
    int soluongsanpham = 0;
    String hinhanhsanpham = "";
    ArrayList<GioHangSP> mangdonhangchitiet;
    ThanhToanAdapter donhangAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_hang_chi_tiet);
        anhXa();
        getData();
        actionToolbar();
        catchOnItemListview();
    }

    private void actionToolbar() {
        setSupportActionBar(toolbardonhangchitiet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbardonhangchitiet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void getData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.Duongdanlaydonhangchitiet, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("donhangchitiet10",response);
                try {

                    JSONArray jsonArray = new JSONArray(response);
                    long tongtien = 0;
                    for(int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        id = jsonObject.getInt("id");
                        tensanpham = jsonObject.getString("tensanpham");
                        giasanpham = jsonObject.getInt("giasanpham");
                        hinhanhsanpham = jsonObject.getString("hinhanhsanpham");
                        soluongsanpham = jsonObject.getInt("soluongsanpham");
//                 GioHangSP(int idsp, String tensp, long giasP, String hinhanhSP, int soluongSP)
                        mangdonhangchitiet.add( new GioHangSP(id, tensanpham, giasanpham, hinhanhsanpham, soluongsanpham));
                        donhangAdapter.notifyDataSetChanged();

                        tongtien += giasanpham;
                    }
                    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                    tongtiendonhang.setText(decimalFormat.format(tongtien)+" Đ");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("donhangchitiet","response.toString()1");
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<String, String>();
                Intent intent = getIntent();
                int madonhang = intent.getIntExtra("madonhang",0);
                param.put("madonhang", String.valueOf(madonhang));
                return param;
            };
        };
        requestQueue.add(stringRequest);
    }

    private void anhXa() {
        listviewdonhangchitiet = findViewById(R.id.listviewdonhangchitiet);
        toolbardonhangchitiet = findViewById(R.id.toolbardonhangchitiet);
        tongtiendonhang = findViewById(R.id.textviewdonhangtongtien);
        mangdonhangchitiet = new ArrayList<>();
//      GioHangSP(int idsp, String tensp, long giasP, String hinhanhSP, int soluongSP)
//        mangdonhangchitiet.add( new GioHangSP(10, "vuao", 545458, "https:\\/\\/macstores.vn\\/wp-content\\/uploads\\/2019\\/11\\/mvvm2-macbook-pro-16-inch-2019-1.jpg", 2));
        donhangAdapter = new ThanhToanAdapter(getApplicationContext(), mangdonhangchitiet);
        listviewdonhangchitiet.setAdapter(donhangAdapter);
    }


    private void catchOnItemListview() {
        listviewdonhangchitiet.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                AlertDialog.Builder builder= new AlertDialog.Builder(DonHangChiTiet.this);
                builder.setMessage("Bạn có muốn xóa sản phẩm ?");
                builder.setPositiveButton("có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("1111222","123");
                        if(MainActivity.manggiohang.size() <= 0 ){

                        }else {
                            MainActivity.manggiohang.remove(position);
                            donhangAdapter.notifyDataSetChanged();

                            if(MainActivity.manggiohang.size() <= 0){

                            }else {
                                donhangAdapter.notifyDataSetChanged();

                            }
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        donhangAdapter.notifyDataSetChanged();
                    }
                });
                builder.show();
                return true;
            }
        });
    }

}