package com.example.login.model;

public class Qltk {
    public int id;
    public String tenNv;
    public String ma;
    public String chucVu;
    public String tenTk;
    public int soCmnd;
    public String diaChi;
    public int sdt;
    public String email;

    public Qltk(int id, String tenNv, String ma, String chucVu, String tenTk, int soCmnd, String diaChi, int sdt, String email) {
        this.id = id;
        this.tenNv = tenNv;
        this.ma = ma;
        this.chucVu = chucVu;
        this.tenTk = tenTk;
        this.soCmnd = soCmnd;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.email = email;
    }

    public Qltk(){}
    public Qltk(String tenNv, String ma, String chucVu){
        this.tenNv = tenNv;
        this.ma = ma;
        this.chucVu = chucVu;
    }
    public Qltk(int id, String tenNv, String ma, String chucVu) {
        this.id = id;
        this.tenNv = tenNv;
        this.ma = ma;
        this.chucVu = chucVu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenNv() {
        return tenNv;
    }

    public void setTenNv(String tenNv) {
        this.tenNv = tenNv;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getTenTk() {
        return tenTk;
    }

    public void setTenTk(String tenTk) {
        this.tenTk = tenTk;
    }

    public int getSoCmnd() {
        return soCmnd;
    }

    public void setSoCmnd(int soCmnd) {
        this.soCmnd = soCmnd;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getSdt() {
        return sdt;
    }

    public void setSdt(int sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
