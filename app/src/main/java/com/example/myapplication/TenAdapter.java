package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.List;

public class TenAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Ten> listname;
    private int positionSelect  = -1;

    public TenAdapter(Context context, int layout, List<Ten> listname) {
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

        TextView txtName= view.findViewById(R.id.txtName);
        txtName.setText(listname.get(position).getName());

        notifyDataSetChanged();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ten tenTemp = listname.get(position);
                positionSelect = position;
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("id" , tenTemp.getID());
                intent.putExtra("ten", tenTemp.getName());
                context.startActivity(intent);
            }
        });
        final ConstraintLayout layout01 = (ConstraintLayout) view.findViewById(R.id.motdong);
        if(positionSelect == position){
            layout01.setBackgroundColor(Color.WHITE);
        }


        return view;
    }
}
