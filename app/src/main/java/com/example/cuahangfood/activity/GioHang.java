package com.example.cuahangfood.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.cuahangfood.R;
import com.example.cuahangfood.adapter.GioHangAdapter;
import com.example.cuahangfood.model.GioHangSP;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GioHang extends AppCompatActivity {
    GioHangAdapter gioHangAdapter;
    Toolbar toolbar;
    ListView listViewgiohang;
    TextView textViewtongtien;
    Button buttonthanhtoan,buttonmuatiep;
    ArrayList<GioHangSP> giohang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        anhxa();
        tongtien();
        buttonmuatiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(getApplicationContext(),MainActivity.class);
               startActivity(intent);
            }
        });
    }

    private void tongtien() {
        long tongtien = 0;
        for(int i= 0; i < MainActivity.manggiohang.size() ; i++){
            tongtien += MainActivity.manggiohang.get(i).getGiasP();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        textViewtongtien.setText("Giá : "+decimalFormat.format(tongtien)+" Đ");
    }


    private void anhxa() {
        listViewgiohang = findViewById(R.id.listviewgiohang);
        textViewtongtien = findViewById(R.id.textviewgiatientong);
        buttonmuatiep = findViewById(R.id.buttontieptucmua);
        buttonthanhtoan = findViewById(R.id.buttonthanhtoan);
        giohang = MainActivity.manggiohang;
        gioHangAdapter = new GioHangAdapter(getApplicationContext(),giohang);
        listViewgiohang.setAdapter(gioHangAdapter);

    }
}