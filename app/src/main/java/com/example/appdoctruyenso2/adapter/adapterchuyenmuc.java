package com.example.appdoctruyenso2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appdoctruyenso2.R;
import com.example.appdoctruyenso2.model.chuyenmuc;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class adapterchuyenmuc extends BaseAdapter {

    private Context context;
    private int layout;

    private ArrayList<chuyenmuc> chuyenmucArrayList;

    public adapterchuyenmuc(Context context, int layout, ArrayList<chuyenmuc> chuyenmucArrayList) {
        this.context = context;
        this.layout = layout;
        this.chuyenmucArrayList = chuyenmucArrayList;
    }

    @Override
    public int getCount() {
        return chuyenmucArrayList.size();
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
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = layoutInflater.inflate(layout,null);

        ImageView img = (ImageView) convertView.findViewById(R.id.imgchuyenmuc);
        TextView txtV = (TextView) convertView.findViewById(R.id.txtTenchuyenmuc);

        chuyenmuc cm = chuyenmucArrayList.get(position);
        txtV.setText(cm.getTenchuyenmuc());

        Picasso.get().load(cm.getHinhanh()).placeholder(R.drawable.load).error(R.drawable.image_24).into(img);

        return convertView;
    }
}
