package com.example.week1_0706012010004;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import model.useruser;

public class AddUser extends AppCompatActivity {

    private TextInputLayout input_name, input_age, input_address;
    private Button add_button_save;
    private ImageView add_button_back;
    private ArrayList<useruser> listUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        getSupportActionBar().hide();

        input_name = findViewById(R.id.input_name);
        input_age = findViewById(R.id.input_age);
        input_address = findViewById(R.id.input_address);
        add_button_save = findViewById(R.id.add_button_save);
        add_button_back = findViewById(R.id.add_button_back);

        Bundle b = getIntent().getExtras();
        String s = b.getString("add_edit");
        int position = b.getInt("position");

        Intent intent = getIntent();
        listUser = intent.getParcelableArrayListExtra("listUser") ;

        if(s.equalsIgnoreCase("add")){

            add_button_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LoadingDialog loadingDialog = new LoadingDialog(AddUser.this);

                    String name = input_name.getEditText().getText().toString().trim();
                    int age = Integer.parseInt(input_age.getEditText().getText().toString().trim());
                    String address = input_address.getEditText().getText().toString().trim();

                    if(name != null && age != 0 && address != null){

                        useruser temp = new useruser(name, age, address);
                        MainActivity.listUser.add(temp);

                        loadingDialog.startLoadingDialog();

                        Intent intent = new Intent(AddUser.this, MainActivity.class);
                        intent.putParcelableArrayListExtra("listUser", listUser);
                        startActivity(intent);
                        finish();

                    }

                }
            });

        }else if(s.equalsIgnoreCase("edit")){

            input_name.getEditText().setText(listUser.get(position).getName());
            input_age.getEditText().setText(String.valueOf(listUser.get(position).getAge()));
            input_address.getEditText().setText(listUser.get(position).getAddress());

            add_button_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //LoadingDialog loadingDialog = new LoadingDialog(AddUser.this);

                    String name = input_name.getEditText().getText().toString().trim();
                    int age = Integer.parseInt(input_age.getEditText().getText().toString().trim());
                    String address = input_address.getEditText().getText().toString().trim();

                    if(name != null && age != 0 && address != null){

                        useruser temp = new useruser(name, age, address);
                        MainActivity.listUser.set(position, temp);

                        //loadingDialog.startLoadingDialog();

                        Intent intent = new Intent(AddUser.this, MainActivity.class);
                        intent.putParcelableArrayListExtra("listUser", listUser);
                        startActivity(intent);
                        finish();

                    }

                }
            });

        }



        add_button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

    }

}