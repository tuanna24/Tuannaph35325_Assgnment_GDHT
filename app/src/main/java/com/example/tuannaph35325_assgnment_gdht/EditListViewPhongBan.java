package com.example.tuannaph35325_assgnment_gdht;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EditListViewPhongBan extends BaseAdapter implements Filterable {
    Activity atv;
    ArrayList<PhongBan> listPhongBan;
    ArrayList<PhongBan> listPhongBanOld;
    public static String KEY_PHONGBAN = "";

    public EditListViewPhongBan(Activity atv, ArrayList<PhongBan> listPhongBan) {
        this.atv = atv;
        this.listPhongBan = listPhongBan;
        this.listPhongBanOld = listPhongBan;
    }

    public EditListViewPhongBan() {
    }

    @Override
    public int getCount() {

        return listPhongBan.size();
    }

    @Override
    public Object getItem(int position) {
        return listPhongBan.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = atv.getLayoutInflater();
        convertView = inflater.inflate(R.layout.itemlistview_phongban, parent, false);

        PhongBan phongBan = listPhongBan.get(position);
        ImageView img = convertView.findViewById(R.id.icon_phongban);
        TextView textView = convertView.findViewById(R.id.name_phongban);

        img.setImageResource(phongBan.getImg());
        textView.setText(phongBan.getNameOfPhongBan());
        if (position == 0) {
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    KEY_PHONGBAN = "NhanSu";
                    Intent intent = new Intent(atv, ActivityNhanVien.class);
                    atv.startActivity(intent);
                }
            });
        } else if (position == 1) {
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    KEY_PHONGBAN = "HanhChinh";
                    Intent intent = new Intent(atv, ActivityNhanVien.class);
                    atv.startActivity(intent);
                }
            });
        } else {
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    KEY_PHONGBAN = "DaoTao";
                    Intent intent = new Intent(atv, ActivityNhanVien.class);
                    atv.startActivity(intent);

                }
            });
        }

        return convertView;
    }

    ArrayList<NhanVienModels> arrayList;
    NhanVienModels nhanVienModels;


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if (strSearch.isEmpty()) {
                    listPhongBan = listPhongBanOld;
                } else {
                    ArrayList<PhongBan> list1 = new ArrayList<>();
                    for (PhongBan phongBan : listPhongBanOld) {
                        if (phongBan.getNameOfPhongBan().toLowerCase().contains(strSearch.toLowerCase())) {
                            list1.add(phongBan);
                        }
                    }
                    listPhongBan = list1;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = listPhongBan;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                listPhongBan = (ArrayList<PhongBan>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}