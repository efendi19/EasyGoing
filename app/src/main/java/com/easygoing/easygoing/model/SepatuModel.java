package com.easygoing.easygoing.model;

public class SepatuModel {

    String jenis, harga, hp;

    public SepatuModel() {
    }

    public SepatuModel(String jenis, String harga, String hp) {
        this.jenis = jenis;
        this.harga = harga;
        this.hp = hp;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getNohp() {
        return hp;
    }

    public void setNohp(String nohp) {
        this.hp = nohp;
    }
}

