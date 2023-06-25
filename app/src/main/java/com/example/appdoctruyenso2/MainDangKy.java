package com.example.appdoctruyenso2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appdoctruyenso2.database.databaseTruyen;
import com.example.appdoctruyenso2.model.taikhoan;

public class MainDangKy extends AppCompatActivity {
    EditText edtDKTaikhoan,edtDKMatkhau,edtDKEmail;
    Button btnDangky,btnDangNhap;

    databaseTruyen databasetruyen;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dang_ky);
        databasetruyen =new databaseTruyen(this);
        //ánh xạ
        edtDKTaikhoan = findViewById(R.id.dktaikhoan);
        edtDKMatkhau = findViewById(R.id.dkmatkhau);
        edtDKEmail = findViewById(R.id.dkemail);
        btnDangky =findViewById(R.id.dkdangky);
        btnDangNhap = findViewById(R.id.dkdangnhap);

        btnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taikhoan = edtDKTaikhoan.getText().toString();
                String matkhau = edtDKMatkhau.getText().toString();
                String email = edtDKEmail.getText().toString();

                taikhoan taikhoan1 = CreateTaiKhoan();
                if(taikhoan.equals("") || matkhau.equals("") || email.equals("") ){
                    Log.e("thông báo : ", "chưa nhập đầy đủ thông tin");
                }
                else {
                    databasetruyen.AddTaiKhoan(taikhoan1);
                    Toast.makeText(MainDangKy.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private taikhoan CreateTaiKhoan(){
        String taikhoan = edtDKTaikhoan.getText().toString();
        String matkhau = edtDKMatkhau.getText().toString();
        String email = edtDKEmail.getText().toString();
        int phanquyen = 1;

        taikhoan tk = new taikhoan(taikhoan,matkhau,email,phanquyen);
        return tk;
    }
}