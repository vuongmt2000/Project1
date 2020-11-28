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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;



import com.example.cuahangfood.R;
import com.example.cuahangfood.adapter.GioHangAdapter;
import com.example.cuahangfood.model.GioHangSP;
import com.example.cuahangfood.ultil.CheckConnection;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GioHang extends AppCompatActivity {
    GioHangAdapter gioHangAdapter;
    Toolbar toolbargiohang;
    TextView thongbaogiohang;
    ListView listViewgiohang;
    static TextView textViewtongtien;
    Button buttonthanhtoan,buttonmuatiep;
    ArrayList<GioHangSP> giohang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        anhxa();
        tongtien();
        catchOnItemListview();
        ActionToolbar();
        buttonmuatiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(GioHang.this,MainActivity.class);
               intent.putExtra("status",MainActivity.status);
               startActivity(intent);
            }
        });
        buttonthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.manggiohang.size() > 0){
                    Intent intent = new Intent(getApplicationContext(),ThanhToan.class);
                    startActivity(intent);
                }else {
                    CheckConnection.ShowToast_Short(getApplicationContext(),"Cần mua hàng :3");
                }
            }
        });
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbargiohang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbargiohang.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });
    }

    // khi chọn giữ vào một hàng hóa để xóa sản phẩm
    private void catchOnItemListview() {
        listViewgiohang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                Log.d("hhh111",MainActivity.manggiohang.size()+"");
                AlertDialog.Builder builder= new AlertDialog.Builder(GioHang.this);
                builder.setMessage("Bạn có muốn xóa sản phẩm ?");
                builder.setPositiveButton("có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("1111222","123");
                        if(MainActivity.manggiohang.size() <= 0 ){
                            thongbaogiohang.setVisibility(View.VISIBLE);
                        }else {
                            MainActivity.manggiohang.remove(position);
                            gioHangAdapter.notifyDataSetChanged();
                            tongtien();
                            if(MainActivity.manggiohang.size() <= 0){
                                thongbaogiohang.setVisibility(View.VISIBLE);
                            }else {
                                thongbaogiohang.setVisibility(View.INVISIBLE);
                                gioHangAdapter.notifyDataSetChanged();
                                tongtien();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        gioHangAdapter.notifyDataSetChanged();
                        tongtien();
                    }
                });
                builder.show();
                return true;
            }
        });
    }


    // tính tổng tiền cho hóa đơn
    public static void tongtien() {
        long tongtien = 0;
        for(int i= 0; i < MainActivity.manggiohang.size() ; i++){
            tongtien += MainActivity.manggiohang.get(i).getGiasP();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        textViewtongtien.setText(decimalFormat.format(tongtien)+" Đ");
    }


    private void anhxa() {
        toolbargiohang = findViewById(R.id.toolbargiohang);
        thongbaogiohang = findViewById(R.id.thongbaotronggiohang);
        listViewgiohang = findViewById(R.id.listviewgiohang);
        textViewtongtien = findViewById(R.id.textviewgiatientong);
        buttonmuatiep = findViewById(R.id.buttontieptucmua);
        buttonthanhtoan = findViewById(R.id.buttonthanhtoan);
        giohang = MainActivity.manggiohang;
        gioHangAdapter = new GioHangAdapter(getApplicationContext(),giohang);
        if(MainActivity.manggiohang.size() >0){
            thongbaogiohang.setVisibility(View.INVISIBLE);
            listViewgiohang.setAdapter(gioHangAdapter);

        }else{
            thongbaogiohang.setVisibility(View.VISIBLE);
        }


    }
}