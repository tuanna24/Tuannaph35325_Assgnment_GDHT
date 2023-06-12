package com.example.tuannaph35325_assgnment_gdht;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class NhanVienAdapter extends BaseAdapter implements Filterable {
    Activity atv;
    ArrayList<NhanVienModels> arr;
    ArrayList<NhanVienModels> listold;

    public NhanVienAdapter(Activity atv, ArrayList<NhanVienModels> arr) {
        this.atv = atv;
        this.arr = arr;
        this.listold = arr;
    }


    @Override
    public int getCount() {

        return arr.size();
    }

    @Override
    public Object getItem(int position) {
        return arr.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = atv.getLayoutInflater();
        convertView = inflater.inflate(R.layout.editlistview_nhanvien, parent, false);
        NhanVienModels nhanVienModels = arr.get(position);
        TextView maNV = convertView.findViewById(R.id.txt_manhanvien);
        TextView hoVaTen = convertView.findViewById(R.id.txt_hovaten);
        TextView phongBan = convertView.findViewById(R.id.txt_phongban);
        ImageView iconPen = convertView.findViewById(R.id.icon_pencil);
        ImageView iconBin = convertView.findViewById(R.id.icon_bin);
        maNV.setText("Mã NV: " + nhanVienModels.getMaNhanVien());
        hoVaTen.setText("Họ tên: " + nhanVienModels.getHoVaTen());
        phongBan.setText("Phòng ban: " + nhanVienModels.getPhongBan());
        iconPen.setImageResource(R.mipmap.icon_applepencil);
        iconBin.setImageResource(R.mipmap.icon_bin);

        iconBin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(atv);
                builder.setMessage("Ban co muon xoa khong ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        arr.remove(position);
                        notifyDataSetChanged();
                        ((ActivityNhanVien)atv).ghiNhanVien();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });

        iconPen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((ActivityNhanVien) atv).setUpdateNhanVien(position);

            }
        });


        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if (strSearch.isEmpty()) {
                    arr = listold;
                } else {
                    ArrayList<NhanVienModels> list1 = new ArrayList<>();
                    for (NhanVienModels nv : listold) {
                        if (nv.getHoVaTen().toLowerCase().contains(strSearch.toLowerCase())) {
                            list1.add(nv);
                        }
                    }
                    arr = list1;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = arr;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                arr = (ArrayList<NhanVienModels>) results.values;
                notifyDataSetChanged();
            }
        };
    }


}
