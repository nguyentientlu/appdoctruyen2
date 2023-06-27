package com.example.appdoctruyenso2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appdoctruyenso2.database.databaseTruyen;
import com.example.appdoctruyenso2.model.truyen;

public class MainDangBai extends AppCompatActivity {
    EditText edtTenTruyen,edtNoiDung,edtAnh;
    Button btnDangBai;
    databaseTruyen databaseTruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dang_bai);

        edtAnh = findViewById(R.id.dbimg);
        edtTenTruyen = findViewById(R.id.dbTentruyen);
        edtNoiDung = findViewById(R.id.dbnoidung);
        btnDangBai = findViewById(R.id.dbdangbai);

        databaseTruyen = new databaseTruyen(this);
        btnDangBai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tenchuyen = edtTenTruyen.getText().toString();
                String noidung = edtNoiDung.getText().toString();
                String img = edtAnh.getText().toString();

                truyen truyen = CreatTruyen();

                if (tenchuyen.equals("") || noidung.equals("")|| img.equals("")){
                    Toast.makeText(MainDangBai.this,"Yêu cầu nhập đủ thông tin",Toast.LENGTH_SHORT).show();
                    Log.e("ERR","Nhập đầy đủ thông tin");
                }
                //nhập đủ thì thêm dlieu
                else {
                    databaseTruyen.AddTruyen(truyen);
                    //chuyển qua màn admin thêm dữ liệu
                    Intent intent = new Intent(MainDangBai.this,MainAdmin.class);
                    finish();
                    startActivity(intent);
                }
            }
        });


    }
    //phương tức tạo truyện
    private truyen CreatTruyen(){
        String tenchuyen = edtTenTruyen.getText().toString();
        String noidung = edtNoiDung.getText().toString();
        String img = edtAnh.getText().toString();

        Intent intent = getIntent();

        int id = intent.getIntExtra("Id",0);

        truyen truyen = new truyen(tenchuyen,noidung,img,id);
        return truyen;

    }
}