package com.example.appdoctruyenso2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainThongTin extends AppCompatActivity {
    TextView txtthongtin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_thong_tin);

        txtthongtin = (TextView) findViewById(R.id.txtthongtin);

        String thongtin = "Ứng dụng được phát hành bởi Microsoft \n" + "Uy tín đạt : 100 \n"+ "OK";

        txtthongtin.setText(thongtin);
    }
}