package com.example.appdoctruyenso2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.appdoctruyenso2.database.databaseTruyen;

public class MainDangNhap extends AppCompatActivity {
    //tạo đối tượng
    EditText edtTaikhoan,edtMatkhau;
    Button btnDangnhap,btnDangky;
    databaseTruyen databasetruyen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dang_nhap);
        //ánh xạ
        edtTaikhoan = findViewById(R.id.taikhoan);
        edtMatkhau = findViewById(R.id.matkhau);
        btnDangnhap = findViewById(R.id.dangnhap);
        btnDangky = findViewById(R.id.dangky);



        //đối tượng databasetruyen
        databasetruyen = new databaseTruyen(this);
        //tạo sự kiện nhấn nút để chuyển form đăng ký
        btnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainDangNhap.this,MainDangKy.class);
                startActivity(intent);
            }
        });
        btnDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tentaikhoan = edtTaikhoan.getText().toString();
                String matkhat = edtMatkhau.getText().toString();

                //sử dụng Curror lấy dữ liệu
                Cursor cursor =databasetruyen.getData();
                //thuwcj hiện vòng lặp để lấy dữ liệu từ con trỏ
                while (cursor.moveToNext()){

                    //lấy dữ liệu gán vào biến
                    String datataikhoan =cursor.getString(1);
                    String datamatkhau = cursor.getString(2);

                    if (datataikhoan.equals(tentaikhoan) && datamatkhau.equals(matkhat)) {
                        //lấy dữ liệu
                        int idd = cursor.getInt(0);
                        String email = cursor.getString(3);
                        int phanquyen = cursor.getInt(4);
                        String taikhoan = cursor.getString(1);

                        //chuyển quan màn hình mânctivity
                        Intent intent = new Intent(MainDangNhap.this, MainActivity.class);
                        //gửi dữ liệu đi
                        intent.putExtra("idd", idd);
                        intent.putExtra("taikhoan", taikhoan);
                        intent.putExtra("email", email);
                        intent.putExtra("phanquyen", phanquyen);

                        startActivity(intent);
                    }
                }
                //đóng khi về đầu
                cursor.moveToFirst();
                //đóng khi k dùng
                cursor.close();
            }
        });
    }
}