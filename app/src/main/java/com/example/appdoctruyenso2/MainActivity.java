package com.example.appdoctruyenso2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.appdoctruyenso2.adapter.adapterchuyenmuc;
import com.example.appdoctruyenso2.adapter.adapterthongtin;
import com.example.appdoctruyenso2.database.databaseTruyen;
import com.example.appdoctruyenso2.model.chuyenmuc;
import com.example.appdoctruyenso2.model.taikhoan;
import com.example.appdoctruyenso2.model.truyen;
import com.example.appdoctruyenso2.adapter.adapterTruyen;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    NavigationView navigationView;
    ListView listViewTruyen,listViewMainchinh,listViewthongtin;
    DrawerLayout drawerLayout;

    ArrayList<truyen> truyenArrayList;
    ArrayList<chuyenmuc> chuyenmucArrayList;
    ArrayList<taikhoan> taikhoanArrayList;

    adapterTruyen adapterTruyen;
    adapterchuyenmuc adapterchuyenmuc;
    adapterthongtin adapterthongtin;

    databaseTruyen databaseTruyen;

    String email,tentaikhoan;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //nhận dữ liệu ở màn đăng nhập gửi đến
        Intent intentpq =getIntent();
        int i = intentpq.getIntExtra("phanquyen",0);
        int idd = intentpq.getIntExtra("idd",0);
        email = intentpq.getStringExtra("email");
        tentaikhoan = intentpq.getStringExtra("taikhoan");

        //anhs xa
        toolbar = findViewById(R.id.toolbarMain);
        viewFlipper = findViewById(R.id.ViewFlipper);
        navigationView = findViewById(R.id.NavigationView);
        listViewTruyen = findViewById(R.id.listTruyen);
        listViewthongtin = findViewById(R.id.listThongtin);
        listViewMainchinh = findViewById(R.id.listMainchinh);
        drawerLayout = findViewById(R.id.drawerlayout);


        truyenArrayList = new ArrayList<>();

        databaseTruyen = new databaseTruyen(this);

        int count = databaseTruyen.getCountTruyen();
        Toast.makeText(this, "có: "+ count +" truyện", Toast.LENGTH_SHORT).show();

        Cursor cursor = databaseTruyen.getDatal();
        while (cursor.moveToNext()){
            int id =  cursor.getInt(0);
            String tentruyen = cursor.getString(1);
            String noidung = cursor.getString(2);
            String anh = cursor.getString(3);
            int id_tk= cursor.getInt(4);

            truyenArrayList.add(new truyen(id,tentruyen,noidung,anh,id_tk));
            adapterTruyen = new adapterTruyen(getApplicationContext(),truyenArrayList);

            listViewTruyen.setAdapter(adapterTruyen);
        }
        cursor.moveToFirst();
        cursor.close();

        //thông tin
        taikhoanArrayList = new ArrayList<>();
        taikhoanArrayList.add(new taikhoan(tentaikhoan,email));

        adapterthongtin = new adapterthongtin(this,R.layout.navigation_thongtin,taikhoanArrayList);
        listViewthongtin.setAdapter(adapterthongtin);

        //chuyên mục
        chuyenmucArrayList =new ArrayList<>();
        chuyenmucArrayList.add(new chuyenmuc("Đăng bài",R.drawable.post_add_24));
        chuyenmucArrayList.add(new chuyenmuc("Thông tin",R.drawable.face));
        chuyenmucArrayList.add(new chuyenmuc("Đăng xuất",R.drawable.login_24));

        adapterchuyenmuc = new adapterchuyenmuc(this,R.layout.activity_chuyenmuc,chuyenmucArrayList);
        listViewMainchinh.setAdapter(adapterchuyenmuc);

        //bắt sự kiện click vào listView

        listViewMainchinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    if(i == 2){
                        Intent intent = new Intent(MainActivity.this,MainAdmin.class);
                        //gửi id tài khoản qua màn admin
                        intent.putExtra("Id",idd);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Bạn không có quyền đăng bài", Toast.LENGTH_SHORT).show();
                        Log.e("Đăng bài : ","Bạn không có quyền");
                    }
                }
                //Chuyển qua thông tin app
                else if(position == 1){
                    Intent intent = new Intent(MainActivity.this, MainThongTin.class);
                    startActivity(intent);

                }
                //Đăng xuất
                else if (position == 2 ){
                    finish();
                }
            }
        });

        //goij ham
        ActionBar();
        AcctionViewFlipper();

        listViewTruyen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Create an intent to navigate to the MainNoiDung activity
                Intent intent = new Intent(MainActivity.this, MainNoiDung.class);

                // Retrieve data from the clicked item based on position
                String ten = truyenArrayList.get(position).getTenTruyen();
                String noidung = truyenArrayList.get(position).getNoidung();

                // Add data as intent extras
                intent.putExtra("tentruyen", ten);
                intent.putExtra("noidung", noidung);

                // Start the MainNoiDung activity
                startActivity(intent);
            }
        });
    }



    //thanh actionBar với toolBar
    private void ActionBar() {
        //hàm hỗ trợ toolbar
        setSupportActionBar(toolbar);
        //khởi tạo nút cho actionBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //tạo icon cho toolBar
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        //tạo sự kiện
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }

    //phuong thuc chay quangr cao
    private void AcctionViewFlipper() {
        //mang chua tam anh cho quang cao
        ArrayList<String> mangquangcao = new ArrayList<>();
        //add anh vao mang
        mangquangcao.add("https://xetreem.com.vn/public/uploads/images/truyen-co-tich-con-cu.jpg");
        mangquangcao.add("https://xetreem.com.vn/public/uploads/images/con-cuu-long-den.jpg");
        mangquangcao.add("https://xetreem.com.vn/public/uploads/images/ca-sau-va-khi.jpg");
        mangquangcao.add("https://xetreem.com.vn/public/uploads/images/ngong-va-rua.jpg");
        mangquangcao.add("https://xetreem.com.vn/public/uploads/images/chuyen-ke-nha-buon-va-tho-cat-toc.jpg");
        mangquangcao.add("https://xetreem.com.vn/public/uploads/images/con-lua-hat.jpg");
        //thuc hien vong lap for gan anh vao ImgView roi tu do day leen app
        for(int i=0 ;i<mangquangcao.size();i++){
            ImageView imageView = new ImageView(getApplicationContext());
            //su dung thu vien picasso
            Picasso.get().load(mangquangcao.get(i)).into(imageView);
            //phuowng thuc chinh tam hinh vao khunggg quang cao
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            //them tam anh tu imgView vao ViewFlipper
            viewFlipper.addView(imageView);
        }
        //thiet lap tu dong chay
        viewFlipper.setFlipInterval(3000);
        //run flipper
        viewFlipper.setAutoStart(true);

        //goi amination cho vao va ra
        Animation animation_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.viewfipper);
        Animation animation_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        //goi anination vao viewFlipper
        viewFlipper.setInAnimation(animation_in);
        viewFlipper.setInAnimation(animation_out);
    }
    //nạp 1 menu tìm kiếm vào acctionBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //neu click vào tim kiem sẽ chuyển trnag
        if (item.getItemId() == R.id.menul) {
            // Handle the menu item with the specified ID
            Intent intent = new Intent(MainActivity.this, mainTimkiem.class);
            startActivity(intent);
            return true;
        } else {
            // Handle other menu items
            return super.onOptionsItemSelected(item);
        }
    }
}