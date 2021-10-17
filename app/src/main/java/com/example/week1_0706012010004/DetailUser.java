package com.example.week1_0706012010004;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import model.useruser;

public class DetailUser extends AppCompatActivity {

    private ImageView detail_button_back, detail_button_edit, detail_button_delete;
    private TextView detail_name, detail_age, detail_address;
    private int position;
    private ArrayList<useruser> listUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);

        getSupportActionBar().hide();

        detail_button_back = findViewById(R.id.detail_button_back);
        detail_button_edit = findViewById(R.id.detail_button_edit);
        detail_button_delete = findViewById(R.id.detail_button_delete);
        detail_name = findViewById(R.id.detail_name);
        detail_age = findViewById(R.id.detail_age);
        detail_address = findViewById(R.id.detail_address);

        Intent intent = getIntent();
        listUser = intent.getParcelableArrayListExtra("listUser");
        position = intent.getIntExtra("id", 0);

        detail_name.setText(listUser.get(position).getName());
        detail_address.setText(listUser.get(position).getAddress());
        detail_age.setText(String.valueOf(listUser.get(position).getAge()));

        detail_button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        detail_button_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadingDialog loadingDialog = new LoadingDialog(DetailUser.this);
                loadingDialog.startLoadingDialog();
                Intent intent = new Intent(getBaseContext(), AddUser.class);
                intent.putParcelableArrayListExtra("listUser", listUser);
                intent.putExtra("add_edit", "edit");
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

        detail_button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadingDialog loadingDialog = new LoadingDialog(DetailUser.this);
                loadingDialog.startLoadingDialog();
                MainActivity.listUser.remove(position);
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.putParcelableArrayListExtra("listUser", listUser);
                startActivity(intent);
            }
        });

    }
}