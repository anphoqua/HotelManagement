package com.example.login.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.login.AccountActivity;
import com.example.login.R;
import com.example.login.model.Qltk;

import java.util.ArrayList;

public class QltkAdapter extends BaseAdapter {
    Context context;
    ArrayList<Qltk> list = new ArrayList<>();
    public QltkAdapter(Context context, ArrayList<Qltk> item) {
        this.context = context;
        this.list = item;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.item_tk, null);

        TextView tvTen = (TextView) convertView.findViewById(R.id.tvTen);
        TextView tvMa = (TextView) convertView.findViewById(R.id.tvMa);

        tvTen.setText(String.valueOf(list.get(position).getTenNv()));
        tvMa.setText(list.get(position).getMa() + " - " + list.get(position).getChucVu());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, AccountActivity.class);
                context.startActivity(i);
            }
        });

        return convertView;
    }
}
