package com.example.appdoctruyenso2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.appdoctruyenso2.adapter.adapterTruyen;
import com.example.appdoctruyenso2.database.databaseTruyen;
import com.example.appdoctruyenso2.model.truyen;

import java.util.ArrayList;

public class mainTimkiem extends AppCompatActivity {
    ListView listView;
    EditText edt;

    ArrayList<truyen> truyenArrayList;
    ArrayList<truyen> arrayList;

    adapterTruyen adapterTruyen;

    databaseTruyen databaseTruyen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_timkiem);

        //ánh xạ
        listView = (ListView) findViewById(R.id.listviewTimkiem);
        edt = (EditText) findViewById(R.id.timkiem);

        initList();

        //sự kiện nhấn vào listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mainTimkiem.this, MainNoiDung.class);
                String tent = arrayList.get(position).getTenTruyen();
                String noidung = arrayList.get(position).getNoidung();
                intent.putExtra("tentruyen",tent);
                intent.putExtra("noidung",noidung);
                startActivity(intent);
            }
        });
        //edt tìm kiếm
        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }

    private void filter(String text ){
        //xóa dữ liệu mảng
        arrayList.clear();

        ArrayList<truyen> filterList = new ArrayList<>();

        for( truyen item : truyenArrayList){
            if(item.getTenTruyen().toLowerCase().contains(text.toLowerCase())){
                //thêm item vào filterList
                filterList.add(item);
                //thêm vào mảng
                arrayList.add(item);

            }
        }
        adapterTruyen.filerList(filterList);
    }
    private void initList(){
        truyenArrayList = new ArrayList<>();
        arrayList = new ArrayList<>();

        databaseTruyen = new databaseTruyen(this);

        Cursor cursor = databaseTruyen.getData2();

        while (cursor.moveToNext()){
            int id =  cursor.getInt(0);
            String tentruyen = cursor.getString(1);
            String noidung = cursor.getString(2);
            String anh = cursor.getString(3);
            int id_tk= cursor.getInt(4);

            truyenArrayList.add(new truyen(id,tentruyen,noidung,anh,id_tk));
            arrayList.add(new truyen(id,tentruyen,noidung,anh,id_tk));

            adapterTruyen = new adapterTruyen(getApplicationContext(),truyenArrayList);

            listView.setAdapter(adapterTruyen);

        }
        cursor.moveToFirst();
        cursor.close();
    }

}