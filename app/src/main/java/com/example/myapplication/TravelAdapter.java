package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.List;

public class TravelAdapter extends BaseAdapter {
    private TravelActivity context;
    private int layout;
    private List<Ten> listname;
    private int positionSelect  = -1;

    public TravelAdapter(TravelActivity context, int layout, List<Ten> listname) {
        this.context = context;
        this.layout = layout;
        this.listname = listname;
    }

    @Override
    public int getCount() {
        return listname.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layout, null);

        TextView txtId = view.findViewById(R.id.txtIdTravel);
        TextView txtName= view.findViewById(R.id.txtNameTravel);
        ImageButton btnRemove = view.findViewById(R.id.btnimgXoa);
        ImageButton btnUpdate = view.findViewById(R.id.btnimgSua);

        txtId.setText(listname.get(position).getID() + ". ");
        txtName.setText(listname.get(position).getName());


        final Ten ten = listname.get(position);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.showDialogUpdate(ten);
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.deleteTravel(ten);
            }
        });
        notifyDataSetChanged();
        return view;
    }
}
