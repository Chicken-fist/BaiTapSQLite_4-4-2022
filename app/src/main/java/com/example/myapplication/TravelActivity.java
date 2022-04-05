package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TravelActivity extends AppCompatActivity {

    Button btnAdd,  btnCancel;
    EditText edt;
    ListView lv;
    TravelAdapter tralveAdapter;
    DatabaseTravelHandler db;
    List<Ten> tenList;
    ImageButton btnRemove;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);

        btnAdd = findViewById(R.id.btnSaveTravel);
        btnCancel = findViewById(R.id.btnCancelTravel);
        edt = findViewById(R.id.edtTravel);
        lv = findViewById(R.id.lvTravel);
        db = new DatabaseTravelHandler(this);
        tenList = new ArrayList<>();
        btnRemove = findViewById(R.id.btnimgXoa);

        resetLVTen();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edt.getText().toString().trim();
                if(!name.equals("")){
                    db.addContact(new Ten(name));
                    resetLVTen();
                    edt.setText("");
                }
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
        tralveAdapter = new TravelAdapter(this, R.layout.mot_dong_location, tenList);
        lv.setAdapter(tralveAdapter);
    }

    public void showDialogUpdate(Ten ten){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_sua_travel);

        EditText edtPlace = dialog.findViewById(R.id.edtSuaTravel);
        Button btnCapNhat = dialog.findViewById(R.id.btnSua);
        Button btnHuy = dialog.findViewById(R.id.btnHuy);

        edtPlace.setText(ten.getName());

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenMoi = edtPlace.getText().toString().trim();
                if(TextUtils.isEmpty(tenMoi)){
                    Toast.makeText(TravelActivity.this, "Noi Dung Sua Chua Duoc Nhap", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
                 else{
                     Ten tenUpdate = new Ten(ten.getID(), tenMoi);
                     db.updateContact(tenUpdate);
                     dialog.dismiss();
                     resetLVTen();
                }
            }
        });

        dialog.show();

    }

    public void deleteTravel(Ten ten){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Ban Chac Chan Muon Xoa " + ten.getName() + "Khong?");
        builder.setPositiveButton("Co", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        db.deleteContact(ten);
                        resetLVTen();
                    }
                });
        builder.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();

    }


}