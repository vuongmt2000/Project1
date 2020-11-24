package com.example.cuahangfood.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.cuahangfood.R;
import com.example.cuahangfood.Retrofit.APIultil;
import com.example.cuahangfood.Retrofit.DataClient;
import com.example.cuahangfood.ultil.CheckConnection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class AddProduct extends AppCompatActivity {
    EditText editnameProduct,editvalueProduct,editintroProduct;
    ImageView imageView;
    ToggleButton chonloaiSP;

    Button buttonadd,buttonUnadd,btn_chooseImage;
    int Request_Code_Image = 123;
    private Bitmap bitmap ;
    String realPath ="";
    int idsanpham1 = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        anhxa();

//        chon loai san pham
       chonloaiSP.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
               Log.d("hello",idsanpham1+"");
               if(b == false){
                   idsanpham1 = 2;
               }else idsanpham1 =1;
           }
       });
        // ấn thêm hoặc hủy
        // hủy thêm ảnh
        buttonUnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // phần chon anh
        btn_chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(intent, Request_Code_Image);

            }
        });
        // chon them san pham
        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 final String tensanpham = editnameProduct.getText().toString();
                 final String giasanpham = editvalueProduct.getText().toString();
                 final String gioithieusanpham = editintroProduct.getText().toString();
                 final String idsanpham = String.valueOf(idsanpham1);
                if(tensanpham.length()>0 && giasanpham.length() > 0 && gioithieusanpham.length() > 0 && realPath.length()> 0){
                    File file = new File(realPath);
                    String file_path = file.getAbsolutePath();
                    String[] mangtenfile = file_path.split("\\.");
                    file_path = mangtenfile[0] +System.currentTimeMillis() + "."+mangtenfile[1];
                    Log.d("manganh",file_path);
                    final RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file);
                    MultipartBody.Part body = MultipartBody.Part.createFormData("uploaded_file",file_path,requestBody);
                    DataClient dataClient = APIultil.getData();
                    Call<String> callback = dataClient.UploadPhoto(body);
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Log.d("message+1:","message");
                            if(response != null){
                                String message =  response.body();
                                if(message.length() >0){
                                    DataClient insertdata = APIultil.getData();
                                    Call<String> callback = insertdata.InsertData(tensanpham,giasanpham,APIultil.Base_Url + "image/"+message,
                                            gioithieusanpham,idsanpham);
                                    callback.enqueue(new Callback<String>() {
                                        @Override
                                        public void onResponse(Call<String> call, Response<String> response) {
                                            Log.d("123456",response.toString());
                                            String result = response.body();
                                            if(result.equals("Success")){
                                                Log.d("dataclient","123");
                                                CheckConnection.ShowToast_Short(getApplicationContext(),"thêm thành công");
                                                finish();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<String> call, Throwable t) {

                                        }
                                    });
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Log.d("loianh",t.getMessage());
                        }
                    });
                }else {
                    CheckConnection.ShowToast_Short(getApplicationContext(),"Cần nhập đủ thông tin ");
                    }

            }

        }
        );


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Request_Code_Image && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            realPath = getRealPathFromURI(uri);
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    private void anhxa() {
        editnameProduct =(EditText) findViewById(R.id.nhaptensp);
        editvalueProduct =(EditText) findViewById(R.id.nhapgiasp);

        editintroProduct = (EditText) findViewById(R.id.nhapgioithieusp);
        chonloaiSP = (ToggleButton) findViewById(R.id.chonloaiSP);
//        checkDienthoai = (CheckBox) findViewById(R.id.dienthoai);
//        checkLaptop = (CheckBox) findViewById(R.id.laptop);
        buttonadd = (Button) findViewById(R.id.themsp);
        buttonUnadd = (Button) findViewById(R.id.khongthemsp);
        btn_chooseImage =(Button) findViewById(R.id.choose_image);
        imageView =(ImageView) findViewById(R.id.image_view);
    }
    public String getRealPathFromURI (Uri contentUri) {
        String path = null;
        String[] proj = { MediaStore.MediaColumns.DATA };
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            path = cursor.getString(column_index);
        }
        cursor.close();
        return path;
    }
}