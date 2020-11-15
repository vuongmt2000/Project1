package com.example.cuahangfood.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cuahangfood.R;
import com.example.cuahangfood.adapter.DienthoaiAdapter;
import com.example.cuahangfood.model.Product;
import com.example.cuahangfood.ultil.CheckConnection;
import com.example.cuahangfood.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DienThoaiActivity extends AppCompatActivity {
    EditText timkiemdt;
    Toolbar tbdt ;
    ListView listViewDt ;
    DienthoaiAdapter dienthoaiAdapter;
    ArrayList<Product> mangdt;
    int iddt = 0;
    int page = 1;
    View footterview;
    boolean isLoading = false;
    boolean limitData = false;
    mHandler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dien_thoai);
        Anhxa();
        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            Log.d("1234","4567");
            GetIdDt();
            ActionToolbar();
            getDAta(page);
            LoadMoreData();
            searchdienthoai();

        }else{
            CheckConnection.ShowToast_Short(getApplicationContext(),"bạn hãy kiểm tra lại kết nối");
            finish();
        }

    }

    private void searchdienthoai() {
        timkiemdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
    }

    private void filter(String text) {
        ArrayList<Product> arrSearch = new ArrayList<>();
        for(Product item : mangdt){
            if(item.getTensanpham().toLowerCase().contains(text.toLowerCase())){
                arrSearch.add(item);
            }
        }
        dienthoaiAdapter.filterList(arrSearch);
    }

    private void LoadMoreData() {
        listViewDt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),Chitietsanpham.class);
                intent.putExtra("thongtinsanpham",mangdt.get(i));
                startActivity(intent);
            }
        });
        listViewDt.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int FirstItem, int VisibleItem, int TotalItem) {
                if(FirstItem + VisibleItem == TotalItem && TotalItem != 0 && isLoading == false && limitData == false){
                        isLoading = true;
                        TheardData theardData = new TheardData();
                        theardData.start();
                }
            }
        });
    }

    private void getDAta(int Page) {
        Log.d("1234","456");
        RequestQueue requestQueue  = Volley.newRequestQueue(getApplicationContext());
         String duongdandt = Server.Duongdandienthoai +String.valueOf(Page);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, duongdandt, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id = 0;
                String tensanphamdt="";
                int giadienthoai=0;
                String hinhanhdienthoai = "";
                String MotaDT= "";
                int iddienthoai= 0;
                if(response != null && response.length() !=2){
                    listViewDt.removeFooterView(footterview);
                    try {

                        JSONArray jsonArray =  new JSONArray(response);
                        for(int i = 0; i < jsonArray.length(); i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            tensanphamdt= jsonObject.getString("name_product");
                            Log.d("1234",tensanphamdt);
                            giadienthoai = jsonObject.getInt("value");
                            hinhanhdienthoai = jsonObject.getString("image_product");
                            MotaDT = jsonObject.getString("intro_product");
                            iddienthoai = jsonObject.getInt("id_product_type");
                            mangdt.add(new Product(id,tensanphamdt,giadienthoai,hinhanhdienthoai,MotaDT,iddienthoai));

                            dienthoaiAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("1234","response");
                    }
                }else {
                    listViewDt.removeFooterView(footterview);
                    limitData = true;
                    CheckConnection.ShowToast_Short(getApplicationContext(),"Đã hết dữ liệu");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<String,String>();
                param.put("id_product_type",String.valueOf(iddt));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }


    private void ActionToolbar() {
        setSupportActionBar(tbdt);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       tbdt.setNavigationOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View view){
               finish();
           }
       });

    }

    private void GetIdDt() {
        iddt = getIntent().getIntExtra("IDloaisanpham",-1);
        Log.d("giatriLoaiSP",iddt+"");
    }

    private void Anhxa() {
        timkiemdt = findViewById(R.id.timkiemdienthoai);
        mHandler = new mHandler();
        tbdt =  findViewById(R.id.toolbardienthoai);
        listViewDt =   findViewById(R.id.listviewdienthoai);
        mangdt = new ArrayList<>();
        dienthoaiAdapter =  new DienthoaiAdapter(getApplicationContext(),mangdt);
        listViewDt.setAdapter(dienthoaiAdapter);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footterview = inflater.inflate(R.layout.progressbar,null);
    }
    public class mHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case 0 :
                    listViewDt.addFooterView(footterview);
                    break;
                 case 1 :
                     getDAta(++page);
                     isLoading = false;
                     break;
            }
            super.handleMessage(msg);
        }
    }
    public class TheardData extends Thread{
        @Override
        public void run() {
            mHandler.sendEmptyMessage(0);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message  = mHandler.obtainMessage(1);
            mHandler.sendMessage(message);
            super.run();
        }
    }

}