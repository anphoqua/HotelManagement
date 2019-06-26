package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.login.dao.QltkDao;
import com.example.login.model.Qltk;

import java.util.ArrayList;


public class QltkActivity extends AppCompatActivity {

    EditText etN, etM, etTk, etCm, etSdt, etDc, etEm;
    Spinner spCv;
    Qltk qltk;
    QltkDao qltkDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qltk);

        AnhXa();

        qltkDao = new QltkDao(QltkActivity.this);

        ArrayList<String> arrCv = new ArrayList<String>();
        arrCv.add("Nhân viên");
        arrCv.add("Quản lý");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrCv);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCv.setAdapter(arrayAdapter);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Thêm tài khoản");
        String title = actionBar.getTitle().toString();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save:
                String name = etN.getText().toString();
                String ma = etM.getText().toString();
                String cv = spCv.getSelectedItem().toString();
                if (name.equals("")||ma.equals("")||cv.equals("")){
                    Toast.makeText(getApplicationContext(), "Thông tin không được để trống", Toast.LENGTH_LONG).show();
                }else {
                    qltk = new Qltk(name, ma, cv);
                    qltkDao.insertQltk(qltk);
                    Toast.makeText(getApplicationContext(), "Đã thêm", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(QltkActivity.this, MainActivity.class);
                    startActivity(i);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void AnhXa(){
        etN = (EditText) findViewById(R.id.etName);
        etM = (EditText) findViewById(R.id.etMa);
        etTk = (EditText) findViewById(R.id.etTk);
        etCm = (EditText) findViewById(R.id.etCm);
        etSdt = (EditText) findViewById(R.id.etSdt);
        etDc = (EditText) findViewById(R.id.etDc);
        etEm = (EditText) findViewById(R.id.etEm);
        spCv = (Spinner) findViewById(R.id.spCv);
    }
}