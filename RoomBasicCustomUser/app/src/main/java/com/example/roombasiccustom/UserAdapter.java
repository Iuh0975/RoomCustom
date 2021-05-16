package com.example.roombasiccustom;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class UserAdapter extends BaseAdapter {
    private Context context;
    private List<User> userList;
    private int layout;
    private OnClick deleteClick;
    private OnClick updateClick;

    public UserAdapter(Context context, List<User> userList, int layout, OnClick deleteClick, OnClick updateClick) {
        this.context = context;
        this.userList = userList;
        this.layout = layout;
        this.deleteClick = deleteClick;
        this.updateClick = updateClick;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return userList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(this.context).inflate(this.layout, parent, false);

        TextView txtname = convertView.findViewById(R.id.txtname);
        TextView txtSTT = convertView.findViewById(R.id.txtSTT);
        Button btnedit = convertView.findViewById(R.id.btnedit);
        Button btnremove = convertView.findViewById(R.id.btnremove);


        int id = userList.get(position).getId();

        txtSTT.setText(id + ". ");
        txtname.setText(userList.get(position).getName());

        btnremove.setOnClickListener(v ->{
            this.deleteClick.Click(id);
        });

        btnedit.setOnClickListener(v -> {
            this.updateClick.Click(id);
        });

        return convertView;
    }
}
