package com.example.tuannaph35325_assgnment_gdht;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ActivityNhanVien extends AppCompatActivity {
    TextView themNhanVien;
    ArrayList<NhanVienModels> arrayListNhanvien;
    ListView listViewNhanVien;
    NhanVienAdapter nhanVienAdapter;
    public static String KEY_FILE_STAFF = "nhanvien.txt";
    NhanVienModels nhanVienUpdate;
    public static String KEY_UPDATE = "update_nhanvien";
    SearchView searchView;

    ArrayList<NhanVienModels> arrSetNhanSuPrivate = new ArrayList<>();
    ArrayList<NhanVienModels> arrSetHanhChinhPrivate = new ArrayList<>();
    ArrayList<NhanVienModels> arrSetDaoTaoPrivate = new ArrayList<>();

    public void setPrivateNhanSu() {
        for (int i = 0; i <= arrayListNhanvien.size() - 1; i++) {
            if (arrayListNhanvien.get(i).getPhongBan().equals("Nhân sự")) {
                arrSetNhanSuPrivate.add(arrayListNhanvien.get(i));
                setChangeData(arrSetNhanSuPrivate);
            }
        }
    }

    public void setPrivateHanhChinh() {
        for (int i = 0; i <= arrayListNhanvien.size() - 1; i++) {
            if (arrayListNhanvien.get(i).getPhongBan().equals("Hành chính")) {
                arrSetHanhChinhPrivate.add(arrayListNhanvien.get(i));
                setChangeData(arrSetHanhChinhPrivate);
            }
        }
    }

    public void setPrivateDaoTao() {
        for (int i = 0; i <= arrayListNhanvien.size() - 1; i++) {
            if (arrayListNhanvien.get(i).getPhongBan().equals("Đào tạo")) {
                arrSetDaoTaoPrivate.add(arrayListNhanvien.get(i));

                setChangeData(arrSetDaoTaoPrivate);
            }
        }
    }

    // nhan du lieu de them nhan vien moi
    ActivityResultLauncher<Intent> getData = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {

                        NhanVienModels nhanVienModels = (NhanVienModels) result.getData().getSerializableExtra(ActivityThemOrSua.KEY_ADDNV);
                        arrayListNhanvien.add(nhanVienModels);
                        setChangeData(arrayListNhanvien);
                        ghiNhanVien();
                    }
                }
            });

    // nhan du lieu de sua nhan vien
    ActivityResultLauncher<Intent> dataUpdate = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {

                        NhanVienModels nhanVienModels = (NhanVienModels) result.getData().getSerializableExtra(ActivityThemOrSua.KEY_ADDNV);
                        nhanVienUpdate.setMaNhanVien(nhanVienModels.getMaNhanVien());
                        nhanVienUpdate.setHoVaTen(nhanVienModels.getHoVaTen());
                        nhanVienUpdate.setPhongBan(nhanVienModels.getPhongBan());
                        setChangeData(arrayListNhanvien);
                        ghiNhanVien();
                    }
                }
            });

    public void setUpdateNhanVien(int position) {
        Intent intent = new Intent(getApplicationContext(), ActivityThemOrSua.class);
        nhanVienUpdate = arrayListNhanvien.get(position);
        intent.putExtra(KEY_UPDATE, nhanVienUpdate);
        dataUpdate.launch(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_vien);

        listViewNhanVien = findViewById(R.id.listview_nhanVien);
        arrayListNhanvien = new ArrayList<>();
        docDuLieu();
        if (arrayListNhanvien.size() == 0) {
            arrayListNhanvien.add(new NhanVienModels("NV001", "Nguyễn Văn A", "Hành chính"));
            arrayListNhanvien.add(new NhanVienModels("NV002", "Nguyễn Văn B", "Nhân sự"));
            arrayListNhanvien.add(new NhanVienModels("NV003", "Nguyễn Văn C", "Đào tạo"));
            arrayListNhanvien.add(new NhanVienModels("NV004", "Nguyễn Văn D", "Hành chính"));
            arrayListNhanvien.add(new NhanVienModels("NV005", "Nguyễn Văn E", "Nhân sự"));
            arrayListNhanvien.add(new NhanVienModels("NV006", "Nguyễn Văn F", "Đào tạo"));
            arrayListNhanvien.add(new NhanVienModels("NV007", "Nguyễn Văn G", "Hành chính"));
            arrayListNhanvien.add(new NhanVienModels("NV008", "Nguyễn Văn H", "Nhân sự"));
            arrayListNhanvien.add(new NhanVienModels("NV009", "Nguyễn Văn Y", "Đào tạo"));
        }
        themNhanVien = findViewById(R.id.txt_themNhanVien);
        themNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityThemOrSua.class);
                getData.launch(intent);
            }
        });
        if (EditListViewPhongBan.KEY_PHONGBAN.equals("NhanSu")) {
            setPrivateNhanSu();
        }else if (EditListViewPhongBan.KEY_PHONGBAN.equals("HanhChinh")){
            setPrivateHanhChinh();
        }else if (EditListViewPhongBan.KEY_PHONGBAN.equals("DaoTao")){
            setPrivateDaoTao();
        }else {
            setChangeData(arrayListNhanvien);
        }

        ghiNhanVien();


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Nhân Viên");

        // them nhan vien moi


    }

    private void setChangeData(ArrayList<NhanVienModels> nv) {
        nhanVienAdapter = new NhanVienAdapter(this, nv);
        listViewNhanVien.setAdapter(nhanVienAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);

        searchView = (SearchView) menu.findItem(R.id.search_nhanvien).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                nhanVienAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                nhanVienAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onSupportNavigateUp() {
        EditListViewPhongBan.KEY_PHONGBAN = "";
        onBackPressed();
        return true;
    }

    // doc ghi file nhan vien
    public void ghiNhanVien() {
        try {
            FileOutputStream fos = openFileOutput(KEY_FILE_STAFF, MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(arrayListNhanvien);
            fos.close();
            oos.close();
        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    public void docDuLieu() {
        try {
            FileInputStream fis = openFileInput(KEY_FILE_STAFF);
            ObjectInputStream ois = new ObjectInputStream(fis);
            arrayListNhanvien = (ArrayList<NhanVienModels>) ois.readObject();
            fis.close();
            ois.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}