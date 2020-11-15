package com.example.cuahangfood.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cuahangfood.R;
import com.example.cuahangfood.model.Product;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class Chitietsanpham extends AppCompatActivity {
    Toolbar toolbar;
    ImageView imageViewCHitiet;
    TextView tenSPchitiet, giaSPchitiet, Motosanphamchitiet;
    Button buttonthemvaogio;
    Spinner spchitiet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietsanpham);
        anhxa();
        Actiontoolbar();
        getDATAFromDienthoai();
        CatchEvenSpinner();
    }

    private void CatchEvenSpinner() {
        Integer [] soluong  = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_dropdown_item ,soluong);
        spchitiet.setAdapter(arrayAdapter);
    }

    private void Actiontoolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void getDATAFromDienthoai() {
        int id = 0;
        String tenSP = "";
        int giaSP = 0;
        String anhSP = "";
        String MotoSP = "";
        int idLoaisp = 0;
        Product product= (Product) getIntent().getSerializableExtra("thongtinsanpham");
        id = product.getID();
        tenSP = product.getTensanpham();
        giaSP = product.getGiasanpham();
        anhSP = product.getHinhanhsanpham();
        MotoSP = product.getMotosanpham();
        idLoaisp = product.getIDSanpham();
        tenSPchitiet.setText(tenSP);
        DecimalFormat dateFormat = new DecimalFormat("###,###,###");
        giaSPchitiet.setText("Giá : "+dateFormat.format(giaSP)+" Đ");
        Motosanphamchitiet.setText(MotoSP);
        Picasso.get().load(anhSP).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_foreground)
                .into(imageViewCHitiet);
    }

    private void anhxa() {
        toolbar = findViewById(R.id.toolbarchitietsanpham);
        imageViewCHitiet = findViewById(R.id.imagechitiet);
        tenSPchitiet = findViewById(R.id.textviewtenSPchitiet);
        giaSPchitiet = findViewById(R.id.textviewgiaSPchitiet);
        Motosanphamchitiet = findViewById(R.id.textviewmotachitietSP);
        spchitiet = findViewById(R.id.spinnerchitiet);
        buttonthemvaogio= findViewById(R.id.buttonthemvaogiohang);
    }
}