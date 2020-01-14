package com.easygoing.easygoing.model;

public class PakaianModel {
    String nama, alamat, phone, pakaianreguler, pakaiankilat, karpetreguler, karpetkilat, selimutreguler, selimutkilat;

    public PakaianModel() {
    }

    public PakaianModel(String nama, String alamat, String phone, String pakaianreguler, String pakaiankilat, String karpetreguler, String karpetkilat, String selimutreguler, String selimutkilat) {
        this.nama = nama;
        this.alamat = alamat;
        this.phone = phone;
        this.pakaianreguler = pakaianreguler;
        this.pakaiankilat = pakaiankilat;
        this.karpetreguler = karpetreguler;
        this.karpetkilat = karpetkilat;
        this.selimutreguler = selimutreguler;
        this.selimutkilat = selimutkilat;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPakaianreguler() {
        return pakaianreguler;
    }

    public void setPakaianreguler(String pakaianreguler) {
        this.pakaianreguler = pakaianreguler;
    }

    public String getPakaiankilat() {
        return pakaiankilat;
    }

    public void setPakaiankilat(String pakaiankilat) {
        this.pakaiankilat = pakaiankilat;
    }

    public String getKarpetreguler() {
        return karpetreguler;
    }

    public void setKarpetreguler(String karpetreguler) {
        this.karpetreguler = karpetreguler;
    }

    public String getKarpetkilat() {
        return karpetkilat;
    }

    public void setKarpetkilat(String karpetkilat) {
        this.karpetkilat = karpetkilat;
    }

    public String getSelimutreguler() {
        return selimutreguler;
    }

    public void setSelimutreguler(String selimutreguler) {
        this.selimutreguler = selimutreguler;
    }

    public String getSelimutkilat() {
        return selimutkilat;
    }

    public void setSelimutkilat(String selimutkilat) {
        this.selimutkilat = selimutkilat;
    }
}
