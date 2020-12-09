package com.example.cuahangfood.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.cuahangfood.R;

public class DonHang extends AppCompatActivity {
    ListView listViewdonhang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_hang);
        anhxa();
    }

    private void anhxa() {
        listViewdonhang = findViewById(R.id.listviewdonhang);
    }
}