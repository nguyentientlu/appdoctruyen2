package com.example.appdoctruyenso2.model;

public class chuyenmuc {
    private String tenchuyenmuc;
    private int hinhanh;

    public chuyenmuc(String tenchuyenmuc, int hinhanh) {
        this.tenchuyenmuc = tenchuyenmuc;
        this.hinhanh = hinhanh;
    }

    public String getTenchuyenmuc() {
        return tenchuyenmuc;
    }

    public void setTenchuyenmuc(String tenchuyenmuc) {
        this.tenchuyenmuc = tenchuyenmuc;
    }

    public int getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(int hinhanh) {
        this.hinhanh = hinhanh;
    }

}
