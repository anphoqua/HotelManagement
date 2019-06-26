package com.example.login;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.example.login.adapter.QltkAdapter;
import com.example.login.dao.QltkDao;
import com.example.login.model.Qltk;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ListView lvDs;
    EditText etTen, etMa, etCv;
    Button btnT, btnEdit;
    Qltk qltk;
    QltkAdapter qltkAdapter;
    QltkDao qltkDao = null;
    ArrayList<Qltk> list = new ArrayList<>();

    int MENU_ITEM_EDIT = 111;
    int MENU_ITEM_DELETE = 222;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, QltkActivity.class);
                startActivity(i);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        GetTk();

//        btnT.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(MainActivity.this, QltkActivity.class);
//                startActivity(i);
//            }
//        });
    }
    public void GetTk(){
        qltkDao = new QltkDao(MainActivity.this);
        list.clear();
        list = qltkDao.getAllQltk();
        qltkAdapter = new QltkAdapter(MainActivity.this, list);
        lvDs = (ListView) findViewById(R.id.lvDs);
//        btnT = (Button) findViewById(R.id.btnThem);
        lvDs.setAdapter(qltkAdapter);
        registerForContextMenu(lvDs);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, MENU_ITEM_EDIT, 0, "Sửa nhân viên");
        menu.add(0, MENU_ITEM_DELETE,1, "Xóa nhân viên");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Qltk selectedQltk = (Qltk) this.lvDs.getItemAtPosition(info.position);
        if (item.getItemId() == MENU_ITEM_EDIT){
            dialogEdit(selectedQltk);
        }else if (item.getItemId() == MENU_ITEM_DELETE){
            showAlertDialog(selectedQltk);
        }else {
            return false;
        }
        return true;
    }

    public void showAlertDialog(final Qltk qltk){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn có chắc chắn muốn xóa không?");
        builder.setCancelable(false);
        builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                qltkDao = new QltkDao(MainActivity.this);
                qltkDao.deleteQltk(qltk);
                list.remove(qltk);
                Toast.makeText(getApplicationContext(), "Đã xóa", Toast.LENGTH_LONG).show();
                qltkAdapter.notifyDataSetChanged();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
    public void dialogEdit(final Qltk qltk){
        final Dialog dialog = new Dialog(this);
        LayoutInflater inf = getLayoutInflater();
        final View view = inf.inflate(R.layout.dialog_edit, null);
        dialog.setContentView(view);

        qltkDao = new QltkDao(MainActivity.this);
        etTen = (EditText) dialog.findViewById(R.id.etNamenv);
        etMa = (EditText) dialog.findViewById(R.id.etManv);
        etCv = (EditText) dialog.findViewById(R.id.etChucvu);
        btnEdit = (Button) dialog.findViewById(R.id.btnEdit);

        etTen.setText(qltk.getTenNv());
        etMa.setText(qltk.getMa());
        etCv.setText(qltk.getChucVu());

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etTen.getText().toString().trim();
                String ma = etMa.getText().toString().trim();
                String cv = etCv.getText().toString().trim();
                if (name.equals("") || ma.equals("") || cv.equals("")){
                    Toast.makeText(getApplicationContext(), "Thông tin không được để trống", Toast.LENGTH_LONG).show();
                }else {
                    qltk.setTenNv(name);
                    qltk.setMa(ma);
                    qltk.setChucVu(cv);
                    qltkDao.updateQltk(qltk);
                    Toast.makeText(getApplicationContext(), "Đã sửa", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                    GetTk();
                }
            }
        });
        dialog.show();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_qltk) {
            // Handle the camera action
        } else if (id == R.id.nav_dmk) {

        } else if (id == R.id.nav_tk) {

        } else if (id == R.id.nav_dbc) {

        } else if (id == R.id.nav_dx) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
