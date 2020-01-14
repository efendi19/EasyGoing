package com.easygoing.easygoing.model;

public class JenisBarangModel {

    String reguler, kilat, jenis, nama, alamat;

    public JenisBarangModel() {
    }


    public JenisBarangModel(String reguler, String kilat, String jenis, String nama, String alamat) {
        this.reguler = reguler;
        this.kilat = kilat;
        this.jenis = jenis;
        this.nama = nama;
        this.alamat = alamat;
    }

    public String getReguler() {
        return reguler;
    }

    public void setReguler(String reguler) {
        this.reguler = reguler;
    }

    public String getKilat() {
        return kilat;
    }

    public void setKilat(String kilat) {
        this.kilat = kilat;
    }

    public String getJenis() {
        return jenis;
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

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }
}
