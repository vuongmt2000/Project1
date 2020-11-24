package com.example.cuahangfood.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.cuahangfood.R;
import com.example.cuahangfood.model.ArrayLogin;
import com.example.cuahangfood.ultil.CheckConnection;
import com.example.cuahangfood.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    public static final String STATUS="status";
    private Button btnLogin;
    ArrayList<ArrayLogin> arrayLogin;
    EditText editusers,editpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = (Button) findViewById(R.id.btn_login);
        Anhxa();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.duongdanLogin, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response != null) {
                            int id = 0;
                            String taikhoan = "";
                            String matkhau = "";
                            int status = 1;
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject jsonObject = response.getJSONObject(i);

                                    if(editusers.getText().toString().equals(jsonObject.getString("taikhoan"))&&
                                            editpassword.getText().toString().equals(jsonObject.getString("matkhau"))) {
                                        Intent intent = new Intent(Login.this , MainActivity.class);
                                        intent.putExtra(STATUS,jsonObject.getInt("status"));
                                        Log.d("api",jsonObject.getInt("status")+"");
                                        startActivity(intent);
                                    }else {
//                                        CheckConnection.ShowToast_Short(getApplicationContext(),"Kiểm tra lại tài khoản mật khẩu");
                                    }
                                } catch (JSONException e) {

                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                requestQueue.add(jsonArrayRequest);
    }
});

    }
    private void Anhxa(){
        editusers = (EditText) findViewById(R.id.editusername);
        editpassword =(EditText) findViewById(R.id.editPassword);
    }
}