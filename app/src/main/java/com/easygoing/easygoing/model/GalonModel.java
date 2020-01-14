package com.easygoing.easygoing.model;

public class GalonModel {
    String nama;
    String alamat;
    String harga;
    String phone;

    public GalonModel() {
    }

    public GalonModel(String nama, String alamat, String harga, String phone) {
        this.nama = nama;
        this.alamat = alamat;
        this.harga = harga;
        this.phone = phone;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
