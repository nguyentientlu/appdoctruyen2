package com.example.appdoctruyenso2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class MainNoiDung extends AppCompatActivity {

    TextView txtTruyen,txtNoidung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_noi_dung);

        //ánh xạ
        txtTruyen = (TextView) findViewById(R.id.txttentruyen);
        txtNoidung = (TextView) findViewById(R.id.txtnoidung);

        //Nhận dữ liệu mainactivity
        Intent intent = getIntent();
        String tentruyen = intent.getStringExtra("tentruyen");
        String noidung = intent.getStringExtra("noidung");

        txtTruyen.setText(tentruyen);
        txtNoidung.setText(noidung);

        //cho phép chọn 1 nội dung truyện
        txtNoidung.setMovementMethod(new ScrollingMovementMethod());
    }
}