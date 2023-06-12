package com.example.tuannaph35325_assgnment_gdht;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class ActivityPhongBan extends AppCompatActivity {
    SearchView searchView;

    EditListViewPhongBan editListViewPhongBan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phong_ban);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Phòng ban");
        ListView listView = findViewById(R.id.listview_phongban);
        ArrayList<PhongBan> arrayListPhongBan = new ArrayList<>();
        arrayListPhongBan.add(new PhongBan("Nhân sự", R.mipmap.house));
        arrayListPhongBan.add(new PhongBan("Hành chính", R.mipmap.house));
        arrayListPhongBan.add(new PhongBan("Đào tạo", R.mipmap.house));
        editListViewPhongBan = new EditListViewPhongBan(this, arrayListPhongBan);
        listView.setAdapter(editListViewPhongBan);
        if (this.isFinishing()){
            EditListViewPhongBan.KEY_PHONGBAN = "";
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search_nhanvien).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                editListViewPhongBan.getFilter().filter(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {

                editListViewPhongBan.getFilter().filter(query);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void senData() {
        Intent intent = new Intent(getApplicationContext(), ActivityNhanVien.class);
        startActivity(intent);
    }
}