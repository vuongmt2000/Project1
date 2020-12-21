package com.example.cuahangfood.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cuahangfood.R;
import com.example.cuahangfood.adapter.DonhangAdminAdapter;
import com.example.cuahangfood.model.Oder;
import com.example.cuahangfood.model.Product;
import com.example.cuahangfood.ultil.CheckConnection;
import com.example.cuahangfood.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DonHang extends AppCompatActivity {
    ListView listViewdonhang;
    ArrayList<Oder> mangdonhang;
    DonhangAdminAdapter donhangAdminAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_hang);
        getData();
        anhxa();
    }

    private void anhxa() {
        listViewdonhang = findViewById(R.id.listviewdonhang);
        mangdonhang = new ArrayList<>();
        donhangAdminAdapter = new DonhangAdminAdapter(getApplicationContext(),mangdonhang);
        listViewdonhang.setAdapter(donhangAdminAdapter);
    }


    private void getData() {
        Log.d("getdatadonhang,", "123");
        RequestQueue requestQueue1 = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest1 = new JsonArrayRequest(Server.Duongdanlaydonhang, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("trong",response.toString());
                if (response != null) {
                    int madonhang = 0;
                    String tennguoimua = "";
                    String diachinguoimua = "";
                    String emailnuoimua = "";
                    int phone = 0;
                    int status = 0;
                    for (int i=0;i<response.length();i++){
                        try {
                            Log.d("vao",response.toString());
                            JSONObject jsonObject = response.getJSONObject(i);
                            madonhang  = jsonObject.getInt("id");
                            tennguoimua = jsonObject.getString("custom");
                            phone = jsonObject.getInt("phone");
                            diachinguoimua =jsonObject.getString("addressCustom");
                            emailnuoimua = jsonObject.getString("email");
                            status = jsonObject.getInt("status");
                            mangdonhang.add(new Oder(madonhang, phone, emailnuoimua, diachinguoimua, tennguoimua, status));
                            donhangAdminAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("error data","donhang");
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("response error: ",error.getMessage());
            }
        });
        requestQueue1.add(jsonArrayRequest1);
    }
}