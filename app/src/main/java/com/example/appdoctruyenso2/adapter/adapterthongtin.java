package com.example.appdoctruyenso2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.appdoctruyenso2.R;
import com.example.appdoctruyenso2.model.taikhoan;

import java.util.List;

public class adapterthongtin extends BaseAdapter {
    private Context context;
    private int layout;

    private List<taikhoan> taikhoanList;

    public adapterthongtin(Context context, int layout, List<taikhoan> taikhoanList) {
        this.context = context;
        this.layout = layout;
        this.taikhoanList = taikhoanList;
    }

    @Override
    public int getCount() {
        return taikhoanList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout,null);

        TextView txtTenTK = (TextView) convertView.findViewById(R.id.txtname);
        TextView txtgmail = (TextView) convertView.findViewById(R.id.txtgmail);

        taikhoan taikhoan = taikhoanList.get(position);

        txtTenTK.setText(taikhoan.getTentaikhoan());
        txtgmail.setText(taikhoan.getEmail());
        return convertView;
    }
}
