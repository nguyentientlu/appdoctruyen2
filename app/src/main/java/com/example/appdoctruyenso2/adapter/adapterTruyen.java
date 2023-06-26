package com.example.appdoctruyenso2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appdoctruyenso2.R;
import com.example.appdoctruyenso2.model.truyen;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Base64;

public class adapterTruyen extends BaseAdapter {
    private Context context;
    private ArrayList<truyen> listTruyen;

    public adapterTruyen(Context context, ArrayList<truyen> listTruyen) {
        this.context = context;
        this.listTruyen = listTruyen;
    }

    @Override
    public int getCount() {
        return listTruyen.size();
    }

    @Override
    public Object getItem(int position) {
        return listTruyen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void filerList(ArrayList<truyen> filterList) {
        listTruyen = filterList;
        notifyDataSetChanged();
    }

    public class ViewHolder{
        TextView txtTentruyen;
        ImageView imageTruyen;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        viewHolder = new ViewHolder();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.newtruyen,null);
        viewHolder.txtTentruyen = convertView.findViewById(R.id.txttentruyen);
        viewHolder.imageTruyen = convertView.findViewById(R.id.imgtruyen);
        convertView.setTag(viewHolder);

        truyen truyen = (com.example.appdoctruyenso2.model.truyen) getItem(position);
        viewHolder.txtTentruyen.setText(truyen.getTenTruyen());

        Picasso.get().load(truyen.getAnh()).placeholder(R.drawable.load).error(R.drawable.image_24).into(viewHolder.imageTruyen);

        return convertView;
    }
}
