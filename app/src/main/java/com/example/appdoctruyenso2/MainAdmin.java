package com.example.appdoctruyenso2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.appdoctruyenso2.adapter.adapterTruyen;
import com.example.appdoctruyenso2.database.databaseTruyen;
import com.example.appdoctruyenso2.model.truyen;

import java.util.ArrayList;

public class MainAdmin extends AppCompatActivity {

    ListView listView;
    Button buttonThem;

    ArrayList<truyen> TruyenArrayList;
    adapterTruyen adapterTruyen;

    databaseTruyen databaseTruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);

        listView = findViewById(R.id.listAdmin);
        buttonThem = findViewById(R.id.button);

        initList();
        buttonThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=getIntent();
                int id  = intent1.getIntExtra("Id",0);

                Intent intent = new Intent(MainAdmin.this,MainDangBai.class);
                intent.putExtra("Id",id);
                startActivity(intent);

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return false;
            }
        });
    }
    //dialog hien thi cua so xoa
    private void Dialogdelete(int position) {
        // Tạo đối tượng dialog
        Dialog dialog = new Dialog(this);

        // Nạp layout vào dialog
        dialog.setContentView(R.layout.dialogdelete);

        // Tắt click ra ngoài là đóng, chỉ click no là đóng
        dialog.setCanceledOnTouchOutside(false);

        // Ánh xạ
        Button btnYes = dialog.findViewById(R.id.buttonYes);
        Button btnNo = dialog.findViewById(R.id.buttonNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idtruyen = TruyenArrayList.get(position).getID();

                // Xóa dữ liệu
                databaseTruyen.Delete(idtruyen);

                // Cập nhật
                Intent intent = new Intent(MainAdmin.this, MainAdmin.class);
                finish();
                startActivity(intent);
                Toast.makeText(MainAdmin.this, "Xóa truyện thành công!", Toast.LENGTH_SHORT).show();
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Thực hiện đóng dialog
                dialog.cancel();
            }
        });

        dialog.show();
    }

    // gán dữ liệu cho listview
    private void initList() {
        TruyenArrayList = new ArrayList<>();
        databaseTruyen = new databaseTruyen(this);

        Cursor cursor1 = databaseTruyen.getData2();
        while(cursor1.moveToNext()){
            int id = cursor1.getInt(0);
            String tentruyen = cursor1.getString(1);
            String noidung = cursor1.getString(2);
            String anh = cursor1.getString(3);
            int id_tk = cursor1.getInt(4);

            TruyenArrayList.add(new truyen(id,tentruyen,noidung,anh,id_tk));

            adapterTruyen = new adapterTruyen(getApplicationContext(),TruyenArrayList);

            listView.setAdapter(adapterTruyen);
        }
        cursor1.moveToFirst();
        cursor1.close();

    }

}