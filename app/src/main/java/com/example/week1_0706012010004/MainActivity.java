package com.example.week1_0706012010004;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import model.useruser;

public class MainActivity extends AppCompatActivity implements OnCardListener {

    private RecyclerView recyclerView;
    private FloatingActionButton button_add;
    public static ArrayList<useruser> listUser = new ArrayList<>();
    private UserRVAdapter adapter;
    private TextView emptyView;
    private long backPressedTime;
    private Toast backToast;

    @Override
    public void onBackPressed(){
        if(backPressedTime + 2000 > System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
        }else{
            backToast = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }

        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        recyclerView = findViewById(R.id.recyclerView);
        button_add = findViewById(R.id.button_add);
        emptyView = findViewById(R.id.emptyView);

        adapter = new UserRVAdapter(listUser, this);

        if(listUser.size() > 0){
            emptyView.setVisibility(View.GONE);
        }

        setupRecyclerView();
        setListener();

    }

    private void setupRecyclerView() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    public void onCardClick(int position) {
        Intent intent = new Intent(getBaseContext(), DetailUser.class);
        intent.putExtra("id", position);
        intent.putParcelableArrayListExtra("listUser", listUser);
        startActivity(intent);
    }

    private void setListener() {
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddUser.class);
//                intent.putParcelableArrayListExtra("listUser", listUser);
                intent.putExtra("add_edit", "add");
                startActivity(intent);
            }
        });
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}