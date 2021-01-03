package com.example.oasisproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class FullItemAdapter extends BaseAdapter {

    private Context mContext = null;
    private LayoutInflater mLayoutInflater = null;
    private  ArrayList<FullItemData> itemdata;

    public FullItemAdapter(Context context, ArrayList<FullItemData> itemdata){
        mContext = context;
        this.itemdata =itemdata;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return itemdata.size();
    }

    @Override
    public Object getItem(int position) {
        return itemdata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.showitemset, null);

        TextView textName = (TextView)view.findViewById(R.id.textItemName);
        TextView textNumber = (TextView)view.findViewById(R.id.textItemNumber);
        TextView textUntil  = (TextView)view.findViewById(R.id.textItemUntil);

        textName.setText(itemdata.get(position).getName());
        textNumber.setText(""+itemdata.get(position).getNumber());
        textUntil.setText(itemdata.get(position).getUntil());

        return view;
    }
}
