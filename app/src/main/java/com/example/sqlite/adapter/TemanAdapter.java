package com.example.sqlite.adapter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlite.EditData;
import com.example.sqlite.R;
import com.example.sqlite.database.Teman;

import java.util.ArrayList;

public class TemanAdapter extends RecyclerView.Adapter<TemanAdapter.TemanViewHolder> {
    private Context mContext;
    private ArrayList<Teman> listdata;
    View view;
    Teman temp;
    Bundle bundle = new Bundle();
    Intent intent;

    public TemanAdapter(Context mContext, ArrayList<Teman> listdata)
    {
        this.mContext = mContext;
        this.listdata = listdata;
    }

    @Override
    public TemanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(mContext).inflate(R.layout.row_data_teman,parent,false);
        return new TemanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TemanViewHolder holder, int position) {
        String nm,tlp;

        nm = listdata.get(position).getNama();
        tlp = listdata.get(position).getTelpon();

        holder.namaTxt.setTextColor(Color.BLUE);
        holder.namaTxt.setTextSize(20);
        holder.namaTxt.setText(nm);
        holder.telponTxt.setText(tlp);
        holder.menuPopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(mContext, v);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.mnedit:
                                Toast.makeText(mContext, "Edit!", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.mnhapus:
                                Toast.makeText(mContext, "Terhapus!", Toast.LENGTH_SHORT).show();
                                temp = new Teman(listdata.get(position).getNama(), listdata.get(position).getTelpon());
                                hapusData(position);
                                break;
                        }
                        return false;
                    }
                });
            }
        });
    }
    private void hapusData(int position)
    {
        listdata.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listdata.size());
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public class TemanViewHolder extends RecyclerView.ViewHolder {
        private CardView cardku, menuPopUp;
        private TextView namaTxt,telponTxt;
        public TemanViewHolder(View view) {
            super(view);
            cardku = (CardView) view.findViewById(R.id.kartuku);
            namaTxt = (TextView) view.findViewById(R.id.textNama);
            telponTxt = (TextView) view.findViewById(R.id.textTelpon);
            menuPopUp = itemView.findViewById(R.id.kartuku);
        }

    }
}
