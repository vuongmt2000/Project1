package com.example.cuahangfood.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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
import com.example.cuahangfood.ultil.CheckConnection;
import com.example.cuahangfood.ultil.Server;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ThanhToan extends AppCompatActivity {
    EditText tennguoimuahang, email, sodienthoai, diachi;
    TextView madonhang;
    ListView listviewtahnhtaon;
    Button buttonxacnhan, buttontrolai;
    CheckBox checkbox;
    ArrayList<GioHangSP> thanhtoan;
     ThanhToanAdapter thanhToanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
        Anhxa();
        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            EventButton();

        } else {
            CheckConnection.ShowToast_Short(getApplicationContext(),"Kiểm tra lại kết nối ");
        }

    }

    private void EventButton() {
        buttonxacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String tenkhachhang = tennguoimuahang.getText().toString().trim();
                final String emailnd = email.getText().toString().trim();
                final String sodienthoaind = sodienthoai.getText().toString().trim();
                final String diachind = diachi.getText().toString().trim();
                if(tenkhachhang.length() >0 && emailnd.contains("@") && sodienthoaind.length() == 10 && diachind.length()>0 && checkbox.isChecked() == true ){
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.Duongdandonhang, new Response.Listener<String>() {
                        @Override
                        public void onResponse(final String madonhang1) {
                            Log.d("ma don hang", madonhang1);

                            // put don hang chi tiet
                            if( madonhang1 != null){
                                RequestQueue queue1 = Volley.newRequestQueue(getApplicationContext());
                                StringRequest request1 = new StringRequest(Request.Method.POST, Server.Duongdanchitietdonhang, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if(response.equals("1")){
                                            CheckConnection.ShowToast_Short(getApplicationContext(), "Chuyển sang bước thanh toán ");
                                            Log.d("vuong","1212");
                                            MainActivity.manggiohang.clear();
                                            Intent intent = new Intent(ThanhToan.this,MainActivity.class);

                                            intent.putExtra("status",MainActivity.status);
                                            startActivity(intent);
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                }){
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        JSONArray jsonArray = new JSONArray();
                                        for (int i = 0; i < MainActivity.manggiohang.size(); i++){
                                            Log.d("vuong123","456");
                                            JSONObject jsonObject = new JSONObject();
                                            try {
                                                jsonObject.put("madonhang",madonhang1);
                                                jsonObject.put("masanpham",MainActivity.manggiohang.get(i).getIdsp());
                                                jsonObject.put("tensanpham",MainActivity.manggiohang.get(i).getTensp());
                                                jsonObject.put("giasanpham",MainActivity.manggiohang.get(i).getGiasP());
                                                jsonObject.put("soluongsanpham",MainActivity.manggiohang.get(i).getSoluongSP());
                                                jsonObject.put("hinhanhsanpham",MainActivity.manggiohang.get(i).getHinhanhSP());

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            jsonArray.put(jsonObject);
                                        }
                                        HashMap<String, String> hashMap = new HashMap<String, String>();
                                        Log.d("vuong123",jsonArray.toString());
                                        hashMap.put("json",jsonArray.toString());
                                       return hashMap;
                                    }
                                };
                                queue1.add(request1);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("ma don hang","response");
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Log.d("ma don hang","1response");
                            HashMap<String, String> hashMap = new HashMap<String, String>();
                            hashMap.put("tenkhachang",tenkhachhang);
                            hashMap.put("diachi",diachind);
                            hashMap.put("sodienthoai",sodienthoaind);
                            hashMap.put("email",emailnd);
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);
                } else {
                    CheckConnection.ShowToast_Short(getApplicationContext(),"Hãy kiểm tra lại dữ liệu và chấp nhận điều khoản");
                }
            }
        });
        // button trở về
        buttontrolai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void Anhxa() {
        tennguoimuahang = findViewById(R.id.edittexttennguoimua);
        email = findViewById(R.id.edittextemail);
        sodienthoai = findViewById(R.id.edittextdienthoainguoidung);
        listviewtahnhtaon = findViewById(R.id.listviewgiohangthanhtoan);
        madonhang = findViewById(R.id.madonhang);
        diachi = findViewById(R.id.edittextdiachi);
        buttonxacnhan = findViewById(R.id.buttonxacnhan);
        buttontrolai = findViewById(R.id.buttontrolai);
        checkbox = findViewById(R.id.checkboxdongy);
        thanhtoan = MainActivity.manggiohang;
        thanhToanAdapter = new ThanhToanAdapter(getApplicationContext(),thanhtoan);
        listviewtahnhtaon.setAdapter(thanhToanAdapter);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                InputMethodManager im
                        = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
            }
        }
        return super.dispatchTouchEvent(ev);
    }
}