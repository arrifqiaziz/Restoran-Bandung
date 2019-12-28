
package com.restaurant.arrifqiaziz.model.restaurant;


import com.google.gson.annotations.Expose;


@SuppressWarnings("unused")
public class Restaurant {

    @Expose
    private String alamat;
    @Expose
    private String foto;
    @Expose
    private String kategori;
    @Expose
    private String namarm;
    @Expose
    private String rmid;

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getNamarm() {
        return namarm;
    }

    public void setNamarm(String namarm) {
        this.namarm = namarm;
    }

    public String getRmid() {
        return rmid;
    }

    public void setRmid(String rmid) {
        this.rmid = rmid;
    }

}
