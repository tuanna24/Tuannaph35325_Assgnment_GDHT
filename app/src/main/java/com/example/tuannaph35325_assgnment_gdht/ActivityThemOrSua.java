package com.example.tuannaph35325_assgnment_gdht;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityThemOrSua extends AppCompatActivity {
    public static String KEY_ADDNV = "add_nhanvien";
    EditText edtMNV;
    EditText edtTen;
    EditText edtPhongBan;
    Button btnCancel;
    Button btnConfirm;

    private void getDataUpdate() {
        NhanVienModels nhanvienUpdate = (NhanVienModels) getIntent().getSerializableExtra(ActivityNhanVien.KEY_UPDATE);
        if (nhanvienUpdate != null) {
            edtMNV.setText(nhanvienUpdate.getMaNhanVien());
            edtTen.setText(nhanvienUpdate.getHoVaTen());
            edtPhongBan.setText(nhanvienUpdate.getPhongBan());
        }
    }

    private void FindId() {
        edtMNV = findViewById(R.id.edt_addMNV);
        edtTen = findViewById(R.id.edt_addTen);
        edtPhongBan = findViewById(R.id.edt_addPhongBan);
        btnCancel = findViewById(R.id.btn_cancel);
        btnConfirm = findViewById(R.id.btn_confirm);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_or_sua);
        FindId();
        getDataUpdate();
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sendMNV = edtMNV.getText().toString().trim();
                String sendTen = edtTen.getText().toString().trim();
                String sendPB = edtPhongBan.getText().toString().trim();
                if (sendMNV.length() == 0){
                    Toast.makeText(ActivityThemOrSua.this, "Bạn chưa nhập mã nhân viên ", Toast.LENGTH_SHORT).show();
                } else if(sendTen.length() == 0){
                    Toast.makeText(ActivityThemOrSua.this, "Bạn chưa nhập tên nhân viên ", Toast.LENGTH_SHORT).show();
                } else if (sendPB.length() == 0){
                    Toast.makeText(ActivityThemOrSua.this, "Bạn chưa nhập phòng ban nhân viên ", Toast.LENGTH_SHORT).show();
                }else {
                    NhanVienModels nhanVien = new NhanVienModels(sendMNV, sendTen, sendPB);
                    Intent intent = new Intent();
                    intent.putExtra(KEY_ADDNV, nhanVien);
                    setResult(RESULT_OK, intent);
                    finish();
                }

            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}