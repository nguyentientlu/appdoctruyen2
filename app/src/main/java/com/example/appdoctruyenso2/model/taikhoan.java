package com.example.appdoctruyenso2.model;

public class taikhoan {
    private int ID;
    private String tentaikhoan;
    private String matkhau;
    private String email;
    private int phanquyen;
    //hàm khởi tạo contructor
    public taikhoan(String tentaikhoan, String matkhau, String email, int phanquyen) {
        this.tentaikhoan = tentaikhoan;
        this.matkhau = matkhau;
        this.email = email;
        this.phanquyen = phanquyen;
    }

    public taikhoan(String tentaikhoan, String email) {
        this.tentaikhoan = tentaikhoan;
        this.email = email;
    }

    public taikhoan(String tentaikhoan) {
        this.tentaikhoan = tentaikhoan;
    }
    //các hàm get và set
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTentaikhoan() {
        return tentaikhoan;
    }

    public void setTentaikhoan(String tentaikhoan) {
        this.tentaikhoan = tentaikhoan;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhanquyen() {
        return phanquyen;
    }

    public void setPhanquyen(int phanquyen) {
        this.phanquyen = phanquyen;
    }
}
