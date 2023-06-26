package com.example.appdoctruyenso2.model;

public class truyen {
    private int ID;
    private String TenTruyen;
    private  String Noidung;
    private  String Anh;
    private  int ID_TK;

    public truyen(String tenTruyen, String noidung, String anh, int ID_TK) {
        TenTruyen = tenTruyen;
        Noidung = noidung;
        Anh = anh;
        this.ID_TK = ID_TK;
    }

    public truyen(int ID, String tenTruyen, String noidung, String anh, int ID_TK) {
        this.ID = ID;
        TenTruyen = tenTruyen;
        Noidung = noidung;
        Anh = anh;
        this.ID_TK = ID_TK;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenTruyen() {
        return TenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        TenTruyen = tenTruyen;
    }

    public String getNoidung() {
        return Noidung;
    }

    public void setNoidung(String noidung) {
        Noidung = noidung;
    }

    public String getAnh() {
        return Anh;
    }

    public void setAnh(String anh) {
        Anh = anh;
    }

    public int getID_TK() {
        return ID_TK;
    }

    public void setID_TK(int ID_TK) {
        this.ID_TK = ID_TK;
    }
}
