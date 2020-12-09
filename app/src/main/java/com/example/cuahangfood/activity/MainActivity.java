package com.example.cuahangfood.activity;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.appcompat.widget.Toolbar;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.cuahangfood.R;
import com.example.cuahangfood.adapter.LoaispAdapter;
import com.example.cuahangfood.adapter.SanphamAdapter;
import com.example.cuahangfood.model.GioHangSP;
import com.example.cuahangfood.model.Product;
import com.example.cuahangfood.model.Product_Type;
import com.example.cuahangfood.ultil.CheckConnection;
import com.example.cuahangfood.ultil.Server;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {
    public static final String STATUS= "status";
    public static int status;
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewmanhinhchinh;
    NavigationView navigationView;
    ListView listViewmanhinhchinh;
    DrawerLayout drawerLayout;
    ArrayList<Product_Type> mangloaisp;
    LoaispAdapter loaispAdapter;
    ArrayList<Product> mangsanphammoi;
    SanphamAdapter sanphamAdapter;
    int id = 0;
    String product_type ="";
    String image_Product_Type = "";
    public static  ArrayList<GioHangSP> manggiohang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent =getIntent();
        status = intent.getIntExtra("status",0);
        Log.d("1212",status+"");
        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            Anhxa();
            ActionBar();
            ActionViewFlipper();
            GetDuLieuLoaiSP();
            GetDuLieuSPMoi();
            CatchOnItemListView();

        }else {
            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
            finish();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu, menu);
            return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menugiohanag:
                if(MainActivity.status ==1){
                    Intent intent = new Intent(getApplicationContext(),GioHang.class);
                    startActivity(intent);
                }
                if(MainActivity.status == 2){
                    Intent intent = new Intent(getApplicationContext(),DonHang.class);
                    startActivity(intent);
                }
        }
        return super.onOptionsItemSelected(item);
    }

    private void CatchOnItemListView() {
        listViewmanhinhchinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch ( i){
                    case 0:
//                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
//                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
//                            int status = intent.getIntExtra(Login.STATUS,0);
//                            intent.putExtra(STATUS,status);
//                            startActivity(intent);
//                        }else {
//                            CheckConnection.ShowToast_Short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
//                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, DienThoaiActivity.class);
                            intent.putExtra("IDloaisanpham",mangloaisp.get(i).getId());
                            startActivity(intent);
                        }else {
                            CheckConnection.ShowToast_Short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, LapTopActivity.class);
                            intent.putExtra("IDloaisanpham",mangloaisp.get(i).getId());
                            startActivity(intent);
                        }else {
                            CheckConnection.ShowToast_Short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 5:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, AddProduct.class);
                            startActivity(intent);
                        }else {
                            CheckConnection.ShowToast_Short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
            }
        });
    }

    private void GetDuLieuSPMoi() {
        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.DuongdanSanPhammoi, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    int ID = 0;
                    String tensanpham = "";
                    Integer giasanpham = 0;
                    String Hinhanhsanpham = "";
                    String Motosapham = "";
                    int IDsanpham = 0;
                    for (int i=0;i<response.length();i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            ID  = jsonObject.getInt("id");
                            tensanpham = jsonObject.getString("name_product");
                            giasanpham = jsonObject.getInt("value");
                            Hinhanhsanpham =jsonObject.getString("image_product");
                            Motosapham = jsonObject.getString("intro_product");
                            IDsanpham = jsonObject.getInt("id_product_type");
                            mangsanphammoi.add(new Product(ID ,tensanpham,giasanpham,Hinhanhsanpham,Motosapham,IDsanpham ));
                            sanphamAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("Get product error: ", e.getMessage());
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
        requestQueue.add(jsonArrayRequest);
    }
    private void GetDuLieuLoaiSP() {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest( Server.DuongdanLoaiSp, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
                    for(int i = 0; i < response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            product_type = jsonObject.getString("name_product_type");
                            image_Product_Type = jsonObject.getString("image_product_type");
                            mangloaisp.add(new Product_Type(id, product_type, image_Product_Type));
                            loaispAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    mangloaisp.add(3, new Product_Type(0,"Liên hệ","https://cdn.pixabay.com/photo/2016/11/01/03/05/contact-1787332_960_720.png"));
                    mangloaisp.add(4,new Product_Type(0,"Thông tin" , "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSUwxF7tSeL3mVdFZYJAXWXBF4yrd5EOTfuHA&usqp=CAU"));

                    if(MainActivity.status ==2) {
                        mangloaisp.add(5, new Product_Type(0, "Thêm sản phẩm mới", "https://kenh14cdn.com/A3YmnWqkHeph7OwGyu6TwbX57tgTw/Image/2012/03/120320kpbieutuong06_8f682.jpg"));
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.ShowToast_Short(getApplicationContext() ,error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private  void ActionViewFlipper(){
        ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://tinphatapple.vn/wp-content/uploads/2018/10/macbook-air-13inch-2018-tinphatapple.png");
        mangquangcao.add("https://tinphatapple.vn/wp-content/uploads/2018/11/thu-mua-macbook-cu-gia-cao.jpg");
        mangquangcao.add("https://bizweb.dktcdn.net/100/318/659/files/banner-old-get-new-macbook1.jpg?v=1535711169004");
        mangquangcao.add("https://binhminhdigital.com/StoreData/images/PageData/review-top-nhung-chiec-macbook-tot-nhat-hien-nay.jpg");
        for(int i = 0; i<mangquangcao.size() ; i++){
            ImageView imageView =new ImageView(getApplicationContext());
            Picasso.get().load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
  //      viewFlipper.getFlipInterval(1234);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);
    }

    private  void  ActionBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }



    private void Anhxa () {
        toolbar = findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper = findViewById(R.id.viewflipper);
        recyclerViewmanhinhchinh = findViewById(R.id.recyclerview);
        navigationView = findViewById(R.id.navigationview);
        listViewmanhinhchinh = findViewById(R.id.listviewmanhinhchinh);
        drawerLayout = findViewById(R.id.drawerlayout);
        mangloaisp = new ArrayList<>();
        mangloaisp.add(0, new Product_Type(0,"Trang Chính","https://png.pngtree.com/png-vector/20190129/ourlarge/pngtree-home-icon-graphic-design-template-vector-png-image_358126.jpg"));
        loaispAdapter = new  LoaispAdapter( mangloaisp,getApplicationContext());
        listViewmanhinhchinh.setAdapter(loaispAdapter);
        mangsanphammoi = new ArrayList<>();
        sanphamAdapter = new SanphamAdapter( getApplicationContext(),mangsanphammoi);
        recyclerViewmanhinhchinh.setHasFixedSize(true);
        recyclerViewmanhinhchinh.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerViewmanhinhchinh.setAdapter(sanphamAdapter);
        if(manggiohang != null){

        }else{
            manggiohang = new ArrayList<>();
        }
    }

}