package com.example.roombasiccustom;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText txtputname;
    private Button btnsave, btncancel;
    private ListView listview;

    private UserAdapter userAdapter;
    private User userselected;
    private UserDatabase userDatabase;
    private UserDao userDao;

    private OnClick deleteClick;
    private OnClick updateClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtputname = findViewById(R.id.txtputname);
        btnsave = findViewById(R.id.btnsave);
        btncancel = findViewById(R.id.btncancel);
        listview = findViewById(R.id.listview);


        userDatabase = UserDatabase.getInstance(getApplicationContext());
        userDao = userDatabase.userDao();

        deleteClick = id -> {
            userDao.delete(userDao.getById(id));
            update();
        };

        updateClick = id -> {
            userselected = userDao.getById(id);
            String name = userselected.getName();

            txtputname.setText(name);
        };
        update();


        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txtputname.getText().toString();
                if (userselected != null){
                    userselected.setName(name);
                    userDao.update(userselected);
                    userselected = null;
                    txtputname.setText("");
                }
                else {
                    User user = new User(name);
                    userDao.add(user);
                }
                update();
            }
        });

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtputname.setText("");
                userselected = null;
            }
        });

    }

    private void update() {
        userAdapter = new UserAdapter(MainActivity.this, userDao.getListUser(), R.layout.item, deleteClick, updateClick);
        listview.setAdapter(userAdapter);
    }


}