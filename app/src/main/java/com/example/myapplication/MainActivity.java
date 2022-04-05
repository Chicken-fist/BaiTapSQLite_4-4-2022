package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnAdd, btnRemove, btnCancel;
    EditText edt;
    ListView lv;
    TenAdapter tenAdapter;
    DatabaseTenHandler db;
    List<Ten> tenList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        btnRemove = findViewById(R.id.btnRemove);
        btnCancel = findViewById(R.id.btnCancel);
        edt = findViewById(R.id.edtName);
        lv = findViewById(R.id.lv);
        db = new DatabaseTenHandler(this);
        tenList = new ArrayList<>();

        resetLVTen();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edt.getText().toString().trim();
                if(!name.equals("")){
                    db.addContact(new Ten(name));
                    resetLVTen();

                }
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                int id = intent.getExtras().getInt("id");
                String name = intent.getExtras().getString("name");
                Ten  ten = new Ten(id, name);
                db.deleteContact(ten);
                resetLVTen();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onStop();
            }
        });

    }

    private void resetLVTen(){
        tenList = db.getAllContacts();
        tenAdapter = new TenAdapter(this, R.layout.mot_dong_ten, tenList);
        lv.setAdapter(tenAdapter);
    }
}